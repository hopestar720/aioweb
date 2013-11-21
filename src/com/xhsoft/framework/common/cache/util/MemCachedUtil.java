package com.xhsoft.framework.common.cache.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;

public class MemCachedUtil {

	private static final Logger logger = Logger.getLogger(MemCachedUtil.class);

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getKeySet(MemCachedClient memCachedClient) {

		Map<String, Object> keys = new HashMap<String, Object>();

		// 遍历statsItems 获取items:2:number=14
		Map<String, Map<String, String>> statsItems = memCachedClient
				.statsItems();
		Map<String, String> statsItems_sub = null;
		String statsItems_sub_key = null;
		int items_number = 0;
		String server = null;
		// 根据items:2:number=14，调用statsCacheDump，获取每个item中的key
		Map<String, Map<String, String>> statsCacheDump = null;
		Map<String, String> statsCacheDump_sub = null;
		String statsCacheDumpsub_key = null;
		String statsCacheDumpsub_key_value = null;

		for (Iterator iterator = statsItems.keySet().iterator(); iterator
				.hasNext();) {
			server = (String) iterator.next();
			statsItems_sub = statsItems.get(server);
			// System.out.println(server+"==="+statsItems_sub);
			for (Iterator iterator_item = statsItems_sub.keySet().iterator(); iterator_item
					.hasNext();) {
				statsItems_sub_key = (String) iterator_item.next();
				// System.out.println(statsItems_sub_key+":=:"+bb);
				// items:2:number=14
				if (statsItems_sub_key.toUpperCase().startsWith(
						"items:".toUpperCase())
						&& statsItems_sub_key.toUpperCase().endsWith(
								":number".toUpperCase())) {
					items_number = Integer.parseInt(statsItems_sub.get(
							statsItems_sub_key).trim());
					// System.out.println(statsItems_sub_key+":=:"+items_number);
					statsCacheDump = memCachedClient.statsCacheDump(
							new String[] { server }, Integer
									.parseInt(statsItems_sub_key.split(":")[1]
											.trim()), items_number);

					for (Iterator statsCacheDump_iterator = statsCacheDump
							.keySet().iterator(); statsCacheDump_iterator
							.hasNext();) {
						statsCacheDump_sub = statsCacheDump
								.get(statsCacheDump_iterator.next());
						// System.out.println(statsCacheDump_sub);
						for (Iterator iterator_keys = statsCacheDump_sub
								.keySet().iterator(); iterator_keys.hasNext();) {
							statsCacheDumpsub_key = (String) iterator_keys
									.next();
							statsCacheDumpsub_key_value = statsCacheDump_sub
									.get(statsCacheDumpsub_key);
							try {
								keys.put(URLDecoder.decode(
										statsCacheDumpsub_key, "UTF-8"), null);
								if (logger.isDebugEnabled())
									logger.debug("key->"
											+ statsCacheDumpsub_key + ": "
											+ "value->"
											+ statsCacheDumpsub_key_value);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}// key是中文被编码了,是客户端在set之前编码的，服务端中文key存的是密文

						}
					}
				}

			}
		}
		return keys;
	}
}
