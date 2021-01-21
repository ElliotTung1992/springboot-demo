package com.github.dge1992.commonforwardbiz.common.impl;

import com.github.dge1992.commonforwardbiz.common.BeanNameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:35
 */
@Service
public class MapBeanNameServiceImpl implements BeanNameService {

    private Map<String, String> map = new HashMap<>();

    {
        map.put("testPre", "testPreStrategy");
        map.put("testPost", "testPostStrategy");
    }

    @Override
    public Optional<String> getBeanName(String preStrategyCode) {
        String beanName = StringUtils.isNotBlank(preStrategyCode) ? map.get(preStrategyCode) : null;
        return Optional.ofNullable(beanName);
    }
}
