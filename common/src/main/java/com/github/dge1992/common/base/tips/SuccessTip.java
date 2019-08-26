package com.github.dge1992.common.base.tips;

/**
 * 返回给前台的成功提示
 *
 * @author dongganen
 */
public class SuccessTip extends Tip {
	
	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}
}
