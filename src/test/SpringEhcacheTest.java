package test;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml","classpath*:applicationContext-ehcache.xml"})
public class SpringEhcacheTest {
	private static final Log logger = LogFactory.getLog(SpringEhcacheTest.class);
	
	@Resource
	CacheManager manager;

	@Resource
	CacheMethod cache;
	
	@Test
	public void testGetName(){
		String s = cache.getName("admin");
		System.out.println(s);
		String s1 = cache.getName("custom");
		logger.info(s1);
		String[] names = manager.getCacheNames();
		for (int i = 0; i < names.length; i++) {// 输出所有缓存
			logger.info(names[i]);
		}
		Cache c = manager.getCache("admin");// 得到cache
		logger.info("Cache named 'admin':" + c);
		org.junit.Assert.assertEquals("admin", c.getKeys().get(0));
		org.junit.Assert.assertEquals(s, c.get("admin").getValue());

		}

}
