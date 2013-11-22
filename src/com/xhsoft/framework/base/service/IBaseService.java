package com.xhsoft.framework.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.metadata.ClassMetadata;

import com.xhsoft.framework.common.page.Page;

public interface IBaseService<T> extends Serializable {
	
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

	/**
	 * <p>Description:通过主键ID删除实体</p>
	 * @param sid
	 * @return 
	 * @author lijw
	 * @since 2013-6-20
	 */
	public int deleteByPrimaryKey(Long sid);

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
	public List<T> findByParams(Map<String, Object> params, int firstResult,
			int maxResult);

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
