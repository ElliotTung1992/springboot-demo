package com.github.dge1992.commonforwardbiz.common.impl;

import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;
import com.github.dge1992.commonforwardbiz.common.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-14 09:47
 */
@Component
public class CommonHandler {

    @Autowired
    private UrlService urlService;

    public Optional<String> getUrl(CommonReceiveObject receiveObject) {
        AtomicReference<String> url = new AtomicReference<>();
        String urlCode = receiveObject.getURLCode();
        if (StringUtils.isNotBlank(receiveObject.getURL())) {
            url.set(receiveObject.getURL());
        }
        if (StringUtils.isNotBlank(urlCode)) {
            urlService.getUrl(urlCode).ifPresent(url::set);
        }
        return Optional.ofNullable(url.get());
    }
}
