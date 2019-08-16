package com.stylefeng.guns.generator.action.config;

public class CollectionKit {
	
	private CollectionKit() {
		// 静态类不可实例化
	}

	/**
	 * 数组是否为空
	 * @param array 数组
	 * @return 是否为空
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

}
