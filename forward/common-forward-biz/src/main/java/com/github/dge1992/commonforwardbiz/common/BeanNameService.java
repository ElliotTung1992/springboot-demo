package com.github.dge1992.commonforwardbiz.common;

import java.util.Optional;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 16:35
 */
public interface BeanNameService {

    /**
     * 根据前置策略编号获取对应的BeanName
     * @param preStrategyCode 前置策略编号
     * @return java.util.Optional<java.lang.String>
     * @author dge
     * @date 2021-01-13 16:42
     */
    Optional<String> getBeanName(String preStrategyCode);
}
