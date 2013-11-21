package com.xhsoft.framework.common.cache;

import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.danga.MemCached.MemCachedClient;
import com.xhsoft.framework.common.cache.util.MemCachedUtil;

/**
 * 缓存数据变动后让数据保持同步状态
 * @author hopestar720@126.com
 * @since 2013年11月21日
 */
public class CacheMethodAfterAdvice {

	private static final Logger logger = Logger
			.getLogger(CacheMethodAfterAdvice.class);

	private MemCachedClient memCachedClient;

	/**
	 * 原始数据发生变更时保持强一致性(刪除緩存管理器里指定key的value)
	 * 
	 * @param joinPoint
	 */
	public void afterReturning(JoinPoint joinPoint) {
		// String methodName = joinPoint.getSignature().getName();// 得到执行的方法
		if (memCachedClient.stats().isEmpty()) {
			if (logger.isDebugEnabled())
				logger.debug("MemCached服务器尚未初始化或连接失败...");
			return;
		}

		// 获取当前访问类
		Class<?> clazz = joinPoint.getTarget().getClass();

		// 获取memcached的key集合
		Map<String, Object> keys = MemCachedUtil.getKeySet(memCachedClient);

		// 遍历删除匹配的key
		for (Map.Entry<String, Object> entry : keys.entrySet()) {
			String key = entry.getKey();
			if (key.startsWith(clazz.getName())) {
				memCachedClient.delete(key);
			}
		}
	}

	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

}