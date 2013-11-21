package com.xhsoft.framework.common.cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.danga.MemCached.MemCachedClient;
import com.xhsoft.framework.common.utils.Utils;

/**
 * 缓存方法拦截器核心代码
 * @author hopestar720@126.com
 * @since 2013年11月21日
 */
public class CacheMethodInterceptor implements MethodInterceptor,
		InitializingBean {

	private static final Logger logger = Logger
			.getLogger(CacheMethodInterceptor.class);

	private final static Integer TIMEOUT = 3600000;

	private MemCachedClient memCachedClient;

	private Integer expire;

	public void afterPropertiesSet() throws Exception {
		// /log.info(cache A cache is required. Use setCache(Cache) to provide
		// one.");
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		String cacheKey = getCacheKey(targetName, methodName, arguments);

		if (memCachedClient.stats().isEmpty()) {
			if (logger.isDebugEnabled())
				logger.debug("MemCached服务器尚未初始化或连接失败...");
			return invocation.proceed();
		}

		// 缓存节点不存在的情况
		if (memCachedClient.get(cacheKey) == null) {
			synchronized (memCachedClient) {
				if (logger.isDebugEnabled())
					logger.debug("缓存" + cacheKey + "不存在或已经失效!");
				// 这里判断是为了降低强制同步的负面影响,只需第一个操作该添加过程,后来者则跳过
				if (memCachedClient.get(cacheKey) == null) {
					Object obj = invocation.proceed();
					Integer exp = 0;
					if (expire != null && expire != 0)
						exp = expire;
					else
						exp = TIMEOUT;
					memCachedClient.set(cacheKey, obj, exp);
				}
			}
		}
		// 返回缓存值
		return memCachedClient.get(cacheKey);

	}

	/**
	 * 
	 * 返回具体的方法(全路径+方法名+参数值JSON串)
	 * 
	 * @param targetName
	 *            全路径
	 * @param methodName
	 *            方法名称
	 * @param arguments
	 *            参数(转换成JSON格式方便比较)
	 * @return String
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(targetName).append(".").append(methodName);
		for (Object argument : arguments) {
			buffer.append(".").append(Utils.ContextUtil.toJSON(argument));
		}
		return buffer.toString();
	}

	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

}