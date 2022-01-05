package com.github.dge.core.listeners;

import com.alibaba.fastjson.JSON;
import com.github.dge.core.cache.ValueBeanDefinitionContainer;
import com.github.dge.core.cache.ValueBeanDefinitionObject;
import com.github.dge.core.domain.AppConfigData;
import com.github.dge.core.instance.ConfigCenterManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static javax.xml.bind.DatatypeConverter.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-31 5:14 PM
 */
public class ConfigCenterZkNodeChangeListener implements NodeCacheListener {

    private NodeCache nodeCache;

    public ConfigCenterZkNodeChangeListener(NodeCache nodeCache){
        this.nodeCache = nodeCache;
    }

    @Override
    public void nodeChanged() {
        ChildData currentData = nodeCache.getCurrentData();
        String data = new String(currentData.getData());
        if(StringUtils.isBlank(data)){
            return;
        }
        AppConfigData appConfigData = JSON.parseObject(data, AppConfigData.class);
        ValueBeanDefinitionContainer container = ValueBeanDefinitionContainer.getInstance();
        // 监听到路径发生变更，重新拉取数据
        // 1. 没有修改的数据不更新
        // 2. 修改的数据更新
        if(Objects.isNull(appConfigData)){
            return;
        }
        Map<String, String> configFileDataMap = appConfigData.getConfigFileDataMap();
        if(MapUtils.isEmpty(configFileDataMap)){
            return;
        }
        configFileDataMap.forEach((key, value) -> {
            List<ValueBeanDefinitionObject> valueBeanDefinitionObjects = container.get(key);
            if(CollectionUtils.isNotEmpty(valueBeanDefinitionObjects)){
                valueBeanDefinitionObjects
                        .forEach(f -> refresh(f, value));
            }
        });
    }

    public void refresh(ValueBeanDefinitionObject beanDefinition, final String value) {
        BeanFactory beanFactory = ConfigCenterManager.getBeanFactory();
        String beanName = beanDefinition.getBeanName();
        Object bean = beanFactory.getBean(beanName);
        if (bean == null) {
            return;
        }
        String fieldName = beanDefinition.getFieldName();
        // 这里需要处理被aop代理的情况
        Object targetBean = getTarget(bean);
        ReflectionUtils.doWithFields(targetBean.getClass(), field -> {
            if (fieldName.equals(field.getName())) {
                try {
                    // 静态属性无法注入
                    if (Modifier.isStatic(field.getModifiers())) {
                        throw new RuntimeException("静态属性无法注入");
                    }
                    Object valueAfterConvert = convertValue(field.getType(), value);
                    field.setAccessible(Boolean.TRUE);
                    field.set(targetBean, valueAfterConvert);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });
    }

    public static Object getTarget(Object proxy) {
        try {
            if (!AopUtils.isAopProxy(proxy)) {
                return proxy;
            }
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                proxy = getJdkDynamicProxyTargetObject(proxy);
            }
            if (AopUtils.isCglibProxy(proxy)) {
                proxy = getCglibProxyTargetObject(proxy);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return proxy;
    }

    /**
     * cglib动态代理的对象
     *
     * @param proxy {@link Object}
     * @return {@link Object}
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        field.setAccessible(true);
        Object dynamicAdvisedInterceptor = field.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    /**
     * jdk动态代理的对象
     *
     * @param proxy {@link Object}
     * @return {@link Object}
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        AopProxy aopProxy = (AopProxy) field.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

    /**
     * 类型转换
     *
     * @param clazz {@link Class}
     * @param value 值
     * @return 实例
     */
    public static Object convertValue(Class<?> clazz, String value) {
        try {
            if (clazz == null || StringUtils.isEmpty(value)) {
                return null;
            }
            value = value.trim();
            if (String.class.equals(clazz)) {
                return value;
            }
            if (Boolean.class.equals(clazz) || Boolean.TYPE.equals(clazz)) {
                if (Boolean.TRUE.toString().equalsIgnoreCase(value)) {
                    return Boolean.TRUE;
                }
                if (Boolean.FALSE.toString().equalsIgnoreCase(value)) {
                    return Boolean.FALSE;
                }
                throw new RuntimeException();
            }
            if (Short.class.equals(clazz) || Short.TYPE.equals(clazz)) {
                return parseShort(value);
            }
            if (Integer.class.equals(clazz) || Integer.TYPE.equals(clazz)) {
                return parseInt(value);
            }
            if (Long.class.equals(clazz) || Long.TYPE.equals(clazz)) {
                return parseLong(value);
            }
            if (Float.class.equals(clazz) || Float.TYPE.equals(clazz)) {
                return parseFloat(value);
            }
            if (Double.class.equals(clazz) || Double.TYPE.equals(clazz)) {
                return parseDouble(value);
            }
            if (BigDecimal.class.equals(clazz)) {
                return new BigDecimal(value);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        throw new RuntimeException();
    }
}
