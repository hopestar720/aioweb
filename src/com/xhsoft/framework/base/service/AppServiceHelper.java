package com.xhsoft.framework.base.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;

import com.xhsoft.framework.common.exception.GlobalRuntimeException;
import com.xhsoft.framework.common.exception.ServiceException;
import com.xhsoft.framework.common.utils.CacheManager;

public class AppServiceHelper implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2948134059806209627L;

	private static final Log logger = LogFactory.getLog(AppServiceHelper.class);

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext appCtxIn) {
		applicationContext = appCtxIn;
	}

	public static Object findBean(String beanId) throws ServiceException {
		Object service = null;

		try {
			service = applicationContext.getBean(beanId);
		} catch (NoSuchBeanDefinitionException ex) {
			throw new GlobalRuntimeException(
					"no such bean for[" + beanId + "]", ex);
		} catch (BeansException ex) {
			throw new GlobalRuntimeException("bean exception for[" + beanId
					+ "]", ex);
		}

		return service;
	}

	public static Object findBeanOfType(@SuppressWarnings("rawtypes") Class clz) throws ServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("findBeanOfType="
					+ (clz == null ? "Empty Class Name" : clz.getName()));
		}

		if (clz == null) {
			return null;
		}

		Object service = CacheManager.getFromCache(clz.getName());

		if (service == null) {

			try {
				@SuppressWarnings("unchecked")
				Map<String, Object> serviceMap = applicationContext
						.getBeansOfType(clz);
				Iterator<String> beanNames = serviceMap.keySet().iterator();

				while (beanNames.hasNext()) {
					Object instance = serviceMap.get(beanNames.next());

					if (instance.getClass().equals(clz)) {
						service = instance;
					} else if (AopUtils.isAopProxy(instance)) {
						service = instance;
						break;
					}

				}

				CacheManager.putInCache(clz.getName(), service);

			} catch (NoSuchBeanDefinitionException ex) {
				throw new GlobalRuntimeException("no such bean for[" + clz
						+ "]", ex);
			} catch (BeansException ex) {
				throw new GlobalRuntimeException("bean exception for[" + clz
						+ "]", ex);
			}

		}

		return service;
	}

	public static String getMessage(String key, Object[] params, Locale locale) {
		if (locale == null) {
			locale = new Locale("zh_CN");
		}

		String i18n = "";

		try {
			i18n = applicationContext.getMessage(key, params, locale);
		} catch (NoSuchMessageException e) {
			logger.error("i18n definition for [" + key
					+ "] not found in properties file.", e);
		}

		return i18n;
	}

	public static String getMessageDirect(String key, Object[] params,
			Locale locale) {
		if (locale == null) {
			locale = new Locale("zh_CN");
		}

		return applicationContext.getMessage(key, params, locale);
	}

}
