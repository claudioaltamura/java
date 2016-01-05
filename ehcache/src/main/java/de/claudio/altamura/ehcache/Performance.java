package de.claudio.altamura.ehcache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.io.IOUtils;

public class Performance {

	public static void main(String[] args) {
		InputStream is = Performance.class.getResourceAsStream("/cache.xml");
		try {
			CacheManager manager = CacheManager.newInstance(is);
			Cache cache = manager.getCache("sampleCache1");

			long t1 = 0;
			long t2 = 0;
			int cnt = 1000;
			
			//PUT 0.1 ms
			t1 = System.nanoTime();
			for (int i = 0; i < cnt; i++) {

				Element element = new Element(i, new Details(true, false));
				cache.put(element);
				System.out.println(i);
			}
			t2 = System.nanoTime();
			System.out.println("PUT average duration: " + (t2 - t1) / cnt);

			//GET 0.009 ms
			t1 = System.nanoTime();
			for (int i = 0; i < cnt; i++) {
				Element elementFromCache = cache.get(i);
			}
			t2 = System.nanoTime();
			System.out.println("GET average duration: " + (t2 - t1) / cnt);

			//GET WITH VALUE 0.05 ms
			t1 = System.nanoTime();
			for (int i = 0; i < cnt; i++) {
				Element elementFromCache = cache.get(i);
				Details value = (Details) elementFromCache.getObjectValue();
			}
			t2 = System.nanoTime();
			System.out.println("GET WITH VALUE average duration: " + (t2 - t1) / cnt);

			CacheManager.getInstance().shutdown();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

}
