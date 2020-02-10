package com.github.dge1992.common.aop;

import com.github.dge1992.common.base.tips.ErrorTip;
import com.github.dge1992.common.exception.CustomException;
import com.github.dge1992.common.exception.CustomExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author dongganen
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(CustomException e) {
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ErrorTip(CustomExceptionEnum.SERVER_ERROR.getCode(), CustomExceptionEnum.SERVER_ERROR.getMessage());
    }

    /**
     * 拦截数据校验异常
     **/
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip handle(ValidationException exception) {
        //违反约束异常
        String message = "";
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                log.error(item.getMessage());
                message = message + "/" + item.getMessage() ;
            }
        }
        return new ErrorTip(CustomExceptionEnum.BAD_REQUEST_PARAM.getCode(), CustomExceptionEnum.BAD_REQUEST_PARAM.getMessage() + message);
    }

}
