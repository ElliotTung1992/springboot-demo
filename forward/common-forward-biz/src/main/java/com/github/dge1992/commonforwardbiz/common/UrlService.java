package com.github.dge1992.commonforwardbiz.common;

import java.util.Optional;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-13 21:56
 */
public interface UrlService {

    /**
     * 根据urlCode查询url
     *
     * @param urlCode url编号
     * @return java.util.Optional<java.lang.String>
     * @author dge
     * @date 2021-01-13 21:56
     */
    Optional<String> getUrl(String urlCode);
}
