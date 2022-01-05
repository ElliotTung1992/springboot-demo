package com.github.dge.core.cache;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-14 5:43 PM
 */
public class ValueBeanDefinitionContainer {

    public Map<String, List<ValueBeanDefinitionObject>> cache = new ConcurrentHashMap(256);

    private static ValueBeanDefinitionContainer INSTANCE;

    private ValueBeanDefinitionContainer(){

    }

    public static synchronized ValueBeanDefinitionContainer getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ValueBeanDefinitionContainer();
        }
        return INSTANCE;
    }

    public List<ValueBeanDefinitionObject> get(String fieldName){
        if(StringUtils.isBlank(fieldName)){
            return null;
        }
        return cache.get(fieldName);
    }

    public void set(String fieldName, List<ValueBeanDefinitionObject> valueBeanDefinitionObjects){
        if(StringUtils.isBlank(fieldName) || CollectionUtils.isEmpty(valueBeanDefinitionObjects)){
            return;
        }
        List<ValueBeanDefinitionObject> getValueBeanDefinitionObjects = get(fieldName);
        if(CollectionUtils.isNotEmpty(getValueBeanDefinitionObjects)){
            valueBeanDefinitionObjects.addAll(getValueBeanDefinitionObjects);
        }
        cache.put(fieldName, valueBeanDefinitionObjects);
    }
}
