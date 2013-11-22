package com.xhsoft.framework.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.xhsoft.framework.base.dao.IBaseDao;
import com.xhsoft.framework.base.entity.AbstractEntity;
import com.xhsoft.framework.common.constant.Constant;
import com.xhsoft.framework.common.exception.DaoException;
import com.xhsoft.framework.common.page.Page;

public class HibernateGenericDaoImpl<T extends AbstractEntity> extends
		HibernateDaoSupport implements IBaseDao<T> {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	protected final static String ENTITY = "entity";
	private static final int BATCH_SIZE = 50;
	/** model's Class */
	protected Class<T> entityClass;
	/** model's ClassName */
	protected String entityClassName;

	@SuppressWarnings("unchecked")
	public HibernateGenericDaoImpl() {
		try {
			Object genericClz = getClass().getGenericSuperclass();

			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) genericClz)
						.getActualTypeArguments()[0];
				entityClassName = entityClass.getSimpleName();
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public int delete(T entity) {
		String deleteSqlMapId = getEntityName() + "." + PRE_DELETE;
		logger.debug("deleteSqlMapId=" + deleteSqlMapId);

		try {
			this.getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.delete",
					new String[] { entityClass.getName(),
							entity.getSid().toString() }, e);
		}

		return 0;
	}

	public void deleteAll(final Collection<T> collection) {
		if (collection == null || collection.isEmpty())
			return;

		try {
			this.getHibernateTemplate().deleteAll(collection);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.deleteAll",
					new String[] { entityClass.getName() }, e);
		}
	}

	public void deleteAllByPrimaryKey(Collection<Long> sids) {
		if (sids == null || sids.isEmpty())
			return;

		try {
			this.getHibernateTemplate().deleteAll(
					this.findByPrimaryKeys((Long[]) sids.toArray()));
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.deleteAll",
					new String[] { entityClass.getName() }, e);
		}
	}

	public int deleteByParams(Map<String, Object> params) {
		String deleteSqlMapId = getEntityName() + "." + PRE_DELETE_BY_PARAMS;
		logger.debug("deleteSqlMapId=" + deleteSqlMapId);

		try {
			this.getHibernateTemplate().deleteAll(this.findByParams(params));
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.delete",
					new String[] { entityClass.getName(), params.toString() },
					e);
		}

		return 0;
	}

	public int deleteByPrimaryKey(Long sid) {
		String deleteSqlMapId = getEntityName() + "." + PRE_DELETE;
		logger.debug("deleteSqlMapId=" + deleteSqlMapId);

		try {
			this.getHibernateTemplate().delete(this.findByPrimaryKey(sid));
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.delete",
					new String[] { entityClass.getName(), sid.toString() }, e);
		}

		return 0;
	}

	public int deleteLogicByPrimaryKey(Long sid) {
		return 0;
	}

	public List<T> findAll() {
		return findByParams(null);
	}

	public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit) {
		Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		page.setLimit(limit);

		String findSqlMapId = getEntityName() + "." + PRE_FIND;
		logger.debug("findSqlMapId=" + findSqlMapId);

		try {
			List<T> resultList = this.findByPage(this.getHqlByParams(params),
					page.getOffset(), limit);
			page.setResultList(resultList);

			int intTemp = findCountByParams(params);
			if (0 != intTemp) {
				page.setTotalRows(intTemp);

				if (intTemp % limit == 0) {
					page.setTotalPages(intTemp / limit);
				} else {
					page.setTotalPages((intTemp / limit) + 1);
				}

			}

		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findByPage",
					new String[] { entityClass.getName() }, e);
		}

		return page;
	}

	private int findCountByParams(Map<String, Object> params) {
		int intValue = 0;

		this.getHqlByParams(params);

		String hql = "SELECT COUNT(*) ";
		hql += " FROM " + this.entityClassName + "  " + ENTITY;
		hql += this.hqlCondition(params);
		intValue = ((Long) this.getHibernateTemplate().find(hql).iterator().next()).intValue();

		return intValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<T> findByPage(final String hql, final int firstRow,
			final int maxRow) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws SQLException,
					HibernateException {
				Query q = session.createQuery(hql);
				q.setFirstResult(firstRow);
				q.setMaxResults(maxRow);
				return q.list();
			}

		});
	}

	private String getHqlByParams(Map<String, Object> params) {
		String hql = "SELECT ";
		hql = hql + ENTITY;
		hql = hql + " FROM " + this.entityClassName + "  " + ENTITY;
		hql = hql + this.hqlCondition(params);

		logger.debug(hql);
		return hql;
	}

	private String hqlCondition(Map<String, Object> params) {
		String hql = "";
		String wherehql = " WHERE 1=1 ";
		String orderSql = "";

		String[] strArrayOrderField;
		int orderCount = 0;
		if (null != params && params.size() != 0) {
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String,Object> pairs = (Map.Entry<String,Object>) it.next();

				if (pairs.getKey().toString().equals("limit")
						|| pairs.getKey().toString().equals("pn")) {
					continue;
				}

				if (null == pairs.getValue()
						|| pairs.getValue().toString().equals("")) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + " ( " + ENTITY + "."
							+ pairs.getKey().toString();
					wherehql = wherehql + " = '" + pairs.getValue() + "' or  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString() + " is null )";
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_ISDATE) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(7);
					wherehql = wherehql + " = to_date('" + pairs.getValue()
							+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_LIKE) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(5);
					wherehql = wherehql + " like '%" + pairs.getValue() + "%' ";
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_NONULL) != -1) {
					String condition = pairs.getValue().toString();

					if (!condition.matches("true|false")) {
						continue;
					}

					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(7);

					if (condition.equals("true")) {
						wherehql = wherehql + " is not null ";
					} else if (condition.equals("false")) {
						wherehql = wherehql + " is null ";
					}
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_NOEQ) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(5);
					wherehql = wherehql + " != " + pairs.getValue();
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUREY_DESC) != -1) {
					if (orderCount > 0) {

						if (pairs.getValue().toString()
								.indexOf(Constant.ORDER_JOIN) > -1) {

							strArrayOrderField = pairs.getValue().toString()
									.split(Constant.ORDER_JOIN);

							for (int i = 0; i < strArrayOrderField.length; i++) {
								orderSql += "," + ENTITY + "."
										+ strArrayOrderField[i] + " desc ";
							}

						} else {
							orderSql += "," + ENTITY + "."
									+ pairs.getValue().toString() + " desc ";
						}
					} else {
						if (pairs.getValue().toString()
								.indexOf(Constant.ORDER_JOIN) > -1) {
							strArrayOrderField = pairs.getValue().toString()
									.split(Constant.ORDER_JOIN);

							for (int i = 0; i < strArrayOrderField.length; i++) {
								if (i == 0) {
									orderSql = " order by " + ENTITY + "."
											+ strArrayOrderField[i] + " desc ";
								} else {
									orderSql += "," + ENTITY + "."
											+ strArrayOrderField[i] + " desc ";
								}
							}

						} else {
							orderSql = " order by " + ENTITY + "."
									+ pairs.getValue().toString() + " desc ";
						}
					}

					orderCount++;
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_ASC) != -1) {
					if (orderCount > 0) {

						if (pairs.getValue().toString()
								.indexOf(Constant.ORDER_JOIN) > -1) {
							strArrayOrderField = pairs.getValue().toString()
									.split(Constant.ORDER_JOIN);

							for (int i = 0; i < strArrayOrderField.length; i++) {
								orderSql += "," + ENTITY + "."
										+ strArrayOrderField[i] + " asc ";
							}
						} else {
							orderSql += "," + ENTITY + "."
									+ pairs.getValue().toString() + " asc ";
						}

					} else {
						strArrayOrderField = pairs.getValue().toString()
								.split(Constant.ORDER_JOIN);

						for (int i = 0; i < strArrayOrderField.length; i++) {
							if (i == 0) {
								orderSql = " order by " + ENTITY + "."
										+ strArrayOrderField[i] + " asc ";
							} else {
								orderSql += "," + ENTITY + "."
										+ strArrayOrderField[i] + " asc ";
							}
						}

					}
					orderCount++;
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_GTEQ) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(5);
					wherehql = wherehql + " >= " + pairs.getValue();
				} else if (pairs.getKey().toString().indexOf(Constant.QUERY_IN) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(3);
					wherehql = wherehql + " in (" + pairs.getValue();
					wherehql = wherehql + ") ";
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_MINVAL) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(7);

					if (pairs.getValue().toString().length() > 10) {
						wherehql = wherehql + " >= to_date('"
								+ pairs.getValue()
								+ "','yyyy-mm-dd HH24:MI:SS')";
					} else {
						wherehql = wherehql + " >= to_date('"
								+ pairs.getValue()
								+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
					}
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_MAXVAL) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString().substring(7);

					if (pairs.getValue().toString().length() > 10) {
						wherehql = wherehql + " <= to_date('"
								+ pairs.getValue()
								+ "','yyyy-mm-dd HH24:MI:SS')";
					} else {
						wherehql = wherehql + " <= to_date('"
								+ pairs.getValue()
								+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
					}
				} else if (pairs.getKey().toString()
						.indexOf(Constant.QUERY_ORFIR) != -1) {
					wherehql = wherehql + " and  ";
					wherehql = wherehql
							+ " ( "
							+ ENTITY
							+ "."
							+ pairs.getKey()
									.toString()
									.substring(
											6,
											pairs.getKey()
													.toString()
													.indexOf(
															Constant.QUERY_ORSEC));
					wherehql = wherehql + " = '" + pairs.getValue() + "' ";
					wherehql = wherehql + " or  ";
					wherehql = wherehql
							+ ENTITY
							+ "."
							+ pairs.getKey()
									.toString()
									.substring(
											pairs.getKey()
													.toString()
													.indexOf(
															Constant.QUERY_ORSEC) + 6);
					wherehql = wherehql + " = '" + pairs.getValue() + "' ";
					wherehql = wherehql + " ) ";
				} else {
					wherehql = wherehql + " and  ";
					wherehql = wherehql + ENTITY + "."
							+ pairs.getKey().toString();
					wherehql = wherehql + " = '" + pairs.getValue() + "' ";
				}

			}
		}

		hql += wherehql + orderSql;
		;

		return hql;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByParams(Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}

		String findSqlMapId = getEntityName() + "." + PRE_FIND;
		logger.debug("findSqlMapId=" + findSqlMapId);

		try {
			return this.getHibernateTemplate()
					.find(this.getHqlByParams(params));
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findByParams",
					new String[] { entityClass.getName(), params.toString() },
					e);
		}

	}

	public List<T> findLimitByParams(Map<String, Object> params,
			int firstResult, int maxResult) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}

		String findSqlMapId = getEntityName() + "." + PRE_FIND;
		logger.debug("findSqlMapId=" + findSqlMapId);

		try {
			return this.findByPage(this.getHqlByParams(params), firstResult,
					maxResult);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findLimitByParams",
					new String[] { entityClass.getName(), params.toString() },
					e);
		}
	}

	public T findByPrimaryKey(Long sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		return findUniqueByParams(params);
	}

	public List<T> findByPrimaryKeys(Long[] sids) {
		List<T> tempList = new ArrayList<T>();

		T entity = null;
		for (Long sid : sids) {
			entity = this.findByPrimaryKey(sid);

			if (null != entity && !entity.getSid().toString().equals("")) {
				tempList.add(entity);
			}
		}

		return tempList;
	}

	
	public T findUniqueByParams(Map<String, Object> params) {
		String findAllSqlMapId = getEntityName() + "." + PRE_FIND;
		logger.debug("findAllSqlMapId=" + findAllSqlMapId);

		try {
			List<T> list = this.findByParams(params);

			if (list == null || list.size() == 0) {
				return null;
			} else if (list.size() == 1) {
				return list.get(0);
			} else {
				throw new DaoException(
						"jdbc.error.code.Common.findUniqueByParams.notUique",
						new String[] { entityClass.getName(), params.toString() },
						null);
			}

		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.findUniqueByParams",
					new String[] { entityClass.getName(), params.toString() },
					e);
		}

	}

	public Long insert(T entity) {
		Assert.notNull(entity);
		String insertSqlMapId = getEntityName() + "." + PRE_INSERT;
		logger.debug("insertSqlMapId=" + insertSqlMapId);

		Long sid = null;

		try {
			entity.setCreateDt(new Date());
			this.getHibernateTemplate().save(entity);
			sid = entity.getSid();
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.insert",
					new String[] { entityClass.getName() }, e);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}

		return sid;
	}

	public void insertAll(Collection<T> collection) {
		if (collection == null || collection.isEmpty())
			return;

		try {
			this.getHibernateTemplate().saveOrUpdateAll(collection);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.insertAll",
					new String[] { entityClass.getName() }, e);
		}

	}

	public void update(T entity) {
		String updateSqlMapId = getEntityName() + "." + PRE_UPDATE;
		logger.debug("updateSqlMapId=" + updateSqlMapId);

		entity.setUpdateDt(new Date());

		try {
			this.getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.update",
					new String[] { entityClass.getName(),
							entity.getSid().toString() }, e);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateAll(final Collection<T> collection) {
		if (collection == null || collection.isEmpty())
			return;

		try {
			this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Transaction tx = session.beginTransaction();
					int i = 0;
					String updateSqlMapId = getEntityName() + "." + PRE_UPDATE;
					logger.debug("updateSqlMapId=" + updateSqlMapId);
					for (T tempEntity : collection) {
						tempEntity.setUpdateDt(new Date());
						session.update(tempEntity);
						if (i++ > 0 && i % BATCH_SIZE == 0) { /* 以每50个数据作为一个处理单元 */
							session.flush(); /* 保持与数据库数据的同步 */
							session.clear(); /* 清除内部缓存的全部数据，及时释放出占用的内存 */
						}
					}
					tx.commit();
					return null;
				}

			});

		} catch (DataAccessException e) {
			throw new DaoException("jdbc.error.code.Common.updateAll",
					new String[] { entityClass.getName() }, e);
		}
	}

	public Map<String, ClassMetadata> getClassMetadata() {
		Map<String, ClassMetadata> sfs = this.getHibernateTemplate()
				.getSessionFactory().getAllClassMetadata();
		return sfs;
	}

	public String getAllColumnName(@SuppressWarnings("rawtypes") Class entityClass) {
		StringBuffer sb = new StringBuffer();
		SessionFactory factory = this.getHibernateTemplate()
				.getSessionFactory();
		AbstractEntityPersister entityPersister = (SingleTableEntityPersister) factory
				.getClassMetadata(entityClass);

		boolean isFirst = true;

		for (String columnName : entityPersister.getIdentifierColumnNames()) {
			if (isFirst) {
				isFirst = false;
				sb.append(columnName);
			} else {
				sb.append("," + columnName);
			}
		}
		for (String propertyName : entityPersister.getPropertyNames()) {
			/* 判断是否一对多 */
			boolean isCol = entityPersister.getPropertyType(propertyName).isCollectionType();

			if (!isCol) {

				if (isFirst) {
					sb.append(propertyName);
				} else {
					sb.append("," + propertyName);
				}

			}

		}

		return sb.toString();
	}

	protected String getEntityName() {
		return this.entityClassName;
	}
}
