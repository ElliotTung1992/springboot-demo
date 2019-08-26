package com.github.dge1992.common.base.tips;

/**
 * 返回给前台的错误提示
 *
 * @author dongganen
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
