package com.xhsoft.framework.base.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.metadata.ClassMetadata;

import com.xhsoft.framework.common.page.Page;

public interface IBaseDao<T> {
	/** 新增字符常量 */
	public static final String PRE_INSERT = "insert";
	/** 更新字符常量 */
	public static final String PRE_UPDATE = "update";
	/** 删除字符常量 */
	public static final String PRE_DELETE = "delete";
	/** 通过参数删除字符常量 */
	public static final String PRE_DELETE_BY_PARAMS = "deleteByParams";
	/** 查询字符常量 */
	public static final String PRE_FIND = "find";
	
	/** Flex应用、报表应用字符常量 */
	public static final String PRE_STAT = "stat";
	/** 新增历史字符常量 */
	public static final String PRE_INSERT_HISTORY = "insertHistory";
	/** 排序字符常量 */
	public static final String PARAMS_KEY_ORDER = "order";

	public static final String GLOBAL_FACILITY_KEY = "globalFacilityId";

	/**
	 * <p>Description:分页查询接口方法</p>
	 * @param 参数集合Map<String,Object>
	 * @param 页码
	 * @param 分页大小
	 * @return Page<T>
	 * @author lijw
	 * @since 2013-6-20
	 */
	public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit);

	/**
	 * <p>Description:新增单个实体</p>
	 * @param entity
	 * @return 数据ID
	 * @author lijw
	 * @since 2013-6-20
	 */
	public Long insert(T entity);

	/**
	 * <p>Description:批量插入多个实体</p>
	 * @param collection 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public void insertAll(final Collection<T> collection);

	/**
	 * <p>Description:更新单个实体</p>
	 * @param entity 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public void update(T entity);

	/**
	 * <p>Description:批量更新多个实体</p>
	 * @param collection 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public void updateAll(final Collection<T> collection);

	/**
	 * <p>Description:查询所有数据</p>
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public List<T> findAll();

	/**
	 * <p>Description:删除单个实体</p>
	 * @param entity
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public int delete(T entity);

	public int deleteByParams(Map<String, Object> params);

	/**
	 * <p>Description:通过主键ID删除实体</p>
	 * @param sid
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public int deleteByPrimaryKey(Long sid);

	/**
	 * <p>Description:根据主键逻辑删除</p>
	 * @param sid
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public int deleteLogicByPrimaryKey(Long sid);

	/**
	 * <p>Description:批量删除所有实体</p>
	 * @param collection 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public void deleteAll(final Collection<T> collection);

	/**
	 * <p>Description:通过主键集合删除实体</p>
	 * @param sids 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public void deleteAllByPrimaryKey(final Collection<Long> sids);

	/**
	 * <p>Description:根据主键查找单个实体</p>
	 * @param sid
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public T findByPrimaryKey(Long sid);

	/**
	 * <p>Description:通过主键集合查找实体列表</p>
	 * @param sids
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public List<T> findByPrimaryKeys(Long[] sids);

	/**
	 * <p>Description:通过参数查询单个实体</p>
	 * @param params
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public T findUniqueByParams(Map<String, Object> params);

	/**
	 * <p>Description:通过参数查询实体列表</p>
	 * @param params
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public List<T> findByParams(Map<String, Object> params);

	/**
	 * <p>Description:通过参数查询指定索引的数据集合</p>
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public List<T> findLimitByParams(Map<String, Object> params,int firstResult, int maxResult);

	/**
	 * <p>Description:获取实体属性</p>
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public Map<String, ClassMetadata> getClassMetadata();

	/**
	 * <p>Description:获取列名</p>
	 * @param entityClass
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public String getAllColumnName(@SuppressWarnings("rawtypes") Class entityClass);
}
