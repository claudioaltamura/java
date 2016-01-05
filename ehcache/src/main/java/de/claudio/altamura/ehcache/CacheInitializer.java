package de.claudio.altamura.ehcache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.io.IOUtils;

public class CacheInitializer {

	public static void main(String[] args) {
		InputStream is = CacheInitializer.class.getResourceAsStream("/cache.xml");
		try {
			CacheManager manager = CacheManager.newInstance(is);
			Cache cache = manager.getCache("sampleCache1");
			
			Element element = new Element("key1", new Details(true, false));
			cache.put(element);
			
			System.out.println("size in Bytes: " + element.getSerializedSize());
			
			Element elementFromCache = cache.get("key1");
			Details value = (Details) elementFromCache.getObjectValue();
			System.out.println(value);
			
			long elementsInMemory = cache.getStatistics().getSize();
			System.out.println("elementsInMemory " + elementsInMemory);
			
			long cacheHitCount = cache.getStatistics().cacheHitCount();
			System.out.println("cacheHitCount " + cacheHitCount);
			
			CacheManager.getInstance().shutdown();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

}
