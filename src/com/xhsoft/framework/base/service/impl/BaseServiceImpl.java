package com.xhsoft.framework.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.metadata.ClassMetadata;

import com.xhsoft.framework.base.dao.IBaseDao;
import com.xhsoft.framework.base.entity.AbstractEntity;
import com.xhsoft.framework.base.service.AppServiceHelper;
import com.xhsoft.framework.base.service.IBaseService;
import com.xhsoft.framework.common.page.Page;

public class BaseServiceImpl<T extends AbstractEntity> implements IBaseService<T> 
{
	/** serialVersionUID */
	private static final long serialVersionUID = -8057151908816926831L;

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	protected Class<T> entityClass;
	
	protected String entityClassName;
	
	private IBaseDao<T> baseDao;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl()
	{
		try {
			
			Object genericClz = getClass().getGenericSuperclass();
			
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];
				entityClassName = entityClass.getSimpleName();
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
		
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings("unchecked")
	public IBaseDao<T> getBaseDao() 
	{
		IBaseDao<T> baseDao = null;
		
		if (this.entityClass != null) {
			baseDao = (IBaseDao<T>) AppServiceHelper.findBean(StringUtils
					.uncapitalize(entityClassName)
					+ "Dao");
		} else {
			baseDao = this.baseDao;
		}
		
		return baseDao;
	}

	public int delete(T entity) 
	{
		return getBaseDao().delete(entity);
	}

	public void deleteAll(Collection<T> collection) 
	{
		getBaseDao().deleteAll(collection);
	}

	public void deleteAllByPrimaryKey(Collection<Long> sids) 
	{
		getBaseDao().deleteAllByPrimaryKey(sids);
	}

	public int deleteByPrimaryKey(Long sid) 
	{
		return getBaseDao().deleteByPrimaryKey(sid);
	}

	public List<T> findAll() 
	{
		return getBaseDao().findAll();
	}

	public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit) 
	{
		Date start, end;
		start = new Date();
		Page<T> page = getBaseDao().findByPage(params, pageNo, limit);
		end = new Date();
		
		logger.debug("findByPage from service cost time=" + (end.getTime() - start.getTime()));
		return page;
	}

	public List<T> findByParams(Map<String, Object> params) 
	{
		return getBaseDao().findByParams(params);
	}

	public List<T> findByParams(Map<String, Object> params, int firstResult,int maxResult) 
	{
		return getBaseDao().findLimitByParams(params, firstResult, maxResult);
	}

	public T findByPrimaryKey(Long sid) 
	{
		return getBaseDao().findByPrimaryKey(sid);
	}

	public List<T> findByPrimaryKeys(Long[] sids) 
	{
		return getBaseDao().findByPrimaryKeys(sids);
	}

	public T findUniqueByParams(Map<String, Object> params) 
	{
		return getBaseDao().findUniqueByParams(params);
	}
	
	public Long insert(T entity) 
	{
		return getBaseDao().insert(entity);
	}

	public void insertAll(Collection<T> collection) 
	{
		getBaseDao().insertAll(collection);
	}

	public void update(T entity) 
	{
		getBaseDao().update(entity);
	}

	 public void updateAll(Collection<T> collection) 
	{
		getBaseDao().updateAll(collection);
	}
	
	 public Map<String,ClassMetadata> getClassMetadata()
	{
		return getBaseDao().getClassMetadata();
	}
	
	public  String getAllColumnName(@SuppressWarnings("rawtypes") Class entityClass){
    	return getBaseDao().getAllColumnName(entityClass);
    }
}
