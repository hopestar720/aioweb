package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheMethod {
	
	private static final Log logger = LogFactory.getLog(CacheMethod.class);
	@Cacheable("getName")
	public String getName(String name){
		logger.info("ehcache--getName():"+name);
		return name;
	}

}
