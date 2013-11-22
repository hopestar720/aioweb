package com.xhsoft.framework.common.utils;  
  
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xhsoft.framework.base.entity.AbstractEntity;
  
@Service  
public class HibernateUtil extends HibernateDaoSupport {  
  
    @Autowired  
    public HibernateUtil(@Qualifier("local-hibernateTemplate")  
    HibernateTemplate hibernateTemplate) {  
        super.setHibernateTemplate(hibernateTemplate);  
    }  
  
    /** 
     * 取得数据库中的所有表名 
     *  
     * @return 
     */  
    public List<String> getAllDbTableName() {  
        List<String> resultList = new ArrayList<String>();  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        Map metaMap = factory.getAllClassMetadata();  
        for (String key : (Set<String>) metaMap.keySet()) {  
            AbstractEntityPersister classMetadata = (SingleTableEntityPersister) metaMap  
                    .get(key);  
            resultList.add(classMetadata.getTableName());  
        }  
        return resultList;  
    }  
  
    /** 
     * 按对象取得相应的表名 
     *  
     * @param objClass 
     * @return 
     */  
    public String getDbTableName(Class objClass) {  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        Class cls = objClass;  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(cls);  
        return classMetadata.getTableName();  
    }  
  
    /** 
     * 取得对象中所有映射的列名 
     *  
     * @param objClass 
     * @return 
     */  
    public List<String> getDbCellName(Class objClass) {  
        List<String> resultList = new ArrayList<String>();  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        Class cls = objClass;  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(cls);  
        // 添加主键  
        resultList.addAll(Arrays.asList(classMetadata  
                .getIdentifierColumnNames()));  
        String[] propertyNames = classMetadata.getPropertyNames();  
        for (String propertyName : propertyNames) {  
            // 判断是否一对多的对像,移除  
            boolean isCollection = classMetadata.getClassMetadata()  
                    .getPropertyType(propertyName).isCollectionType();  
            if (!isCollection) {  
                String[] propertyColumnNames = classMetadata  
                        .getPropertyColumnNames(propertyName);  
                for (String columnName : propertyColumnNames) {  
                    resultList.add(columnName);  
                }  
            }  
        }  
        return resultList;  
    }  
  
    /** 
     * 按列名与对像取出映射对象的字段 
     *  
     * @param objClass 
     * @param columnName 
     * @return 
     */  
    public String getPropertyByColunm(Class objClass, String columnName) {  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(objClass);  
        String[] propertyNames = classMetadata.getPropertyNames();  
        for (String propertyName : propertyNames) {  
            // 判断是否一对多的对像,移除  
            boolean isCollection = classMetadata.getClassMetadata()  
                    .getPropertyType(propertyName).isCollectionType();  
            if (!isCollection) {  
                String[] propertyColumnNames = classMetadata  
                        .getPropertyColumnNames(propertyName);  
                for (String tempColumnName : propertyColumnNames) {  
                    if (columnName.equals(tempColumnName)) {  
                        return propertyName;  
                    }  
                }  
            }  
        }  
        return null;  
    }  
  
    /** 
     * 按列名与对像取出映射对像的类型(不包括主键) 
     *  
     * @param objClass 
     * @param columnName 
     * @return 
     */  
    public Class getPropertyTypeByColumn(Class objClass, String columnName) {  
        String propertyName = getPropertyByColunm(objClass, columnName);  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(objClass);  
        return classMetadata.getPropertyType(propertyName).getReturnedClass();  
    }  
      
    /** 
     * 取得主键propertyColumn 
     * @param objClass 
     * @param columnName 
     * @return 
     */  
    public String getPrimaryKeyColumn(Class objClass) {  
        List<String> resultList = new ArrayList<String>();  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        Class cls = objClass;  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(cls);  
        // 添加主键  
        resultList.addAll(Arrays.asList(classMetadata  
                .getIdentifierColumnNames()));  
        if(!resultList.isEmpty()){  
            String propertyName=resultList.get(0);  
            return propertyName;  
        }  
        return null;  
    }  
      
    /** 
     * 取得主键property 
     * @param objClass 
     * @param columnName 
     * @return 
     */  
    public String getPrimaryKeyProperty(Class objClass) {  
        List<String> resultList = new ArrayList<String>();  
        SessionFactory factory = getHibernateTemplate().getSessionFactory();  
        Class cls = objClass;  
        AbstractEntityPersister classMetadata = (SingleTableEntityPersister) factory  
                .getClassMetadata(cls);  
        return classMetadata    .getIdentifierPropertyName();  
    }  
      
    /** 
     * 取得主键Value 
     * @param Object 
     * @param columnName 
     * @return 
     */  
    public Object getPrimaryKeyValue(Object object) {  
        String propertyPrimaryKey=getPrimaryKeyProperty(object.getClass());  
        String getterMethodName=propertyToGetterMethod(propertyPrimaryKey);  
  
        try {  
            Method getterMethod=object.getClass().getMethod(getterMethodName);  
            return getterMethod.invoke(object, null);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }   
        return null;  
    }  
  
  
    /** 
     * 按列名与对像取出映射对像的setter方法 
     *  
     * @param objClass 
     * @param columnName 
     * @return 
     */  
    public String getSetterMethodByColumn(Class objClass, String columnName) {  
        String property = getPropertyByColunm(objClass, columnName);  
        return propertyToSetterMethod(property);  
    }  
  
    public String getGetterMethodByColumn(Class objClass, String columnName) {  
        String property = getPropertyByColunm(objClass, columnName);  
        return propertyToGetterMethod(property);  
    }  
  
    /** 
     * 把字段转换为setter方法 
     *  
     * @param property 
     * @return 
     */  
    public String propertyToSetterMethod(String property) {  
        if (property != null) {  
            property = StringUtils.capitalize(property);  
            String result = "set" + property;  
            return result;  
        }  
        return null;  
    }  
  
    public String propertyToGetterMethod(String property) {  
        if (property != null) {  
            property = StringUtils.capitalize(property);  
            String result = "get" + property;  
            return result;  
        }  
        return null;  
    }  
  
    /** 
     * 是否有指定的数据表 
     *  
     * @param tableName 
     * @return 
     */  
    public boolean isExistTableName(String tableName) {  
        List<String> allTableNameList = getAllDbTableName();  
        for (String tempTableName : allTableNameList) {  
            if (tableName.equals(tempTableName)) {  
                return true;  
            }  
        }  
        return false;  
    }
    
    /**
	 * 根据对象ID集合, 整理合并集合.
	 * 
	 * 页面发送变更后的子对象id列表时,在Hibernate中删除整个原来的子对象集合再根据页面id列表创建一个全新的集合这种看似最简单的做法是不行的.
	 * 因此采用如此的整合算法：在源集合中删除id不在目标集合中的对象,根据目标集合中的id创建对象并添加到源集合中.
	 * 因为新建对象只有ID被赋值, 因此本函数不适合于cascade-save-or-update自动持久化子对象的设置.
	 * 
	 * @param srcObjects 源集合,元素为对象.
	 * @param checkedIds  目标集合,元素为ID.
	 * @param clazz  集合中对象的类型,必须为IdEntity子类
	 */
	public static <T extends AbstractEntity> void mergeByCheckedIds(final Collection<T> srcObjects,
			                                                                                                               final Collection<Long> checkedIds, 
			                                                                                                               final Class<T> clazz) 
	{
		/*参数校验*/
		Assert.notNull(srcObjects, "scrObjects不能为空");
		Assert.notNull(clazz, "clazz不能为空");
		/*目标集合为空, 删除源集合中所有对象后直接返回.*/
		if (checkedIds == null) {
			srcObjects.clear();
			return;
		}
		/*遍历源对象集合,如果其id不在目标ID集合中的对象删除.*/
		/*同时,在目标集合中删除已在源集合中的id,使得目标集合中剩下的id均为源集合中没有的id.*/
		Iterator<T> srcIterator = srcObjects.iterator();
		try {

			while (srcIterator.hasNext()) 
			{
				T element = srcIterator.next();
				Long id = element.getSid();

				if (!checkedIds.contains(id)) {
					srcIterator.remove();
				} else {
					checkedIds.remove(id);
				}
			}

			/*ID集合目前剩余的id均不在源集合中,创建对象,为id属性赋值并添加到源集合中.*/
			for (Long id : checkedIds) 
			{
				T element = clazz.newInstance();
				element.setSid(id);
				srcObjects.add(element);
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}
	
	/**
	 * <p>Description:将反射时的checked exception转换为unchecked exception.</p>
	 * @param e
	 * @return RuntimeException
	 * 
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) 
	{
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
  
}