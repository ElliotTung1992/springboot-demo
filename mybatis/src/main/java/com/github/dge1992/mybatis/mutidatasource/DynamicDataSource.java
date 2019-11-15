package com.github.dge1992.mybatis.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author 小眼睛带鱼
 * @date 2019-11-15 10:58:24
 * @desc 动态数据源
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	public Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
