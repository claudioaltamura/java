package de.caltamura.os.curator;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorTest {

	TestingServer zkServer;
	private CuratorFramework client;

	@Before
	public void setUp() throws Exception {
		zkServer = new TestingServer(true);
		String zookeeperConnectionString = "localhost:" + zkServer.getPort();
		client = CuratorFrameworkFactory.builder()
				.connectString(zookeeperConnectionString )
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		client.blockUntilConnected(5, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		CloseableUtils.closeQuietly(client);
		zkServer.stop();
	}

	@Test
	public void set() throws Exception {
		String path = "/test";
		byte[] data = "Hello Test".getBytes();
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, data);

		byte[] result = client.getData().forPath(path);
		assertEquals("Hello Test", new String(result, "UTF-8"));
	}
}
