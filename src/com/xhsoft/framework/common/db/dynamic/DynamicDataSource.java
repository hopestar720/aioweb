package com.xhsoft.framework.common.db.dynamic;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 动态获取数据源(依赖SPRING框架)
 * @author hopestar720@126.com
 * @since 2013年11月25日
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private DataSourceEntry dataSourceEntry;

	@Override
	protected Object determineCurrentLookupKey() {
		return this.dataSourceEntry.get();
	}

	@Resource
	public void setDataSourceEntry(DataSourceEntry dataSourceEntry) {
		this.dataSourceEntry = dataSourceEntry;
	}

}
