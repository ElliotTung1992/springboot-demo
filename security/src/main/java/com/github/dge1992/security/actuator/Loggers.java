package com.github.dge1992.security.actuator;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董感恩
 * @date 2020-04-05 20:40
 * @desc Loggers端点
 */
@Log4j2
@RestController
@RequestMapping("/loggers")
public class Loggers {

    /**
     * @author 董感恩
     * @date 2020-04-05 20:46:31
     * @desc
     * url: http://localhost:8080/dgeActuator/loggers/com.github.dge1992
     * method: Post
     * value: json
     *  {
     *      "configuredLevel": "DEBUG"
     *  }
     **/
    @RequestMapping("/testLogLevel")
    public void testLogLevel(){
        log.info("info级别的日志");
        log.debug("debug级别的日志");
    }
}
