package de.claudioaltamura.os.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CuratorExample {

	public static void main(String[] args) throws Exception {
		String zookeeperConnectionString = "localhost:2181";

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(zookeeperConnectionString )
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener()
		{

			@Override
			public void unhandledError(String message, Throwable e)
			{
				System.out.println("unhandled Error " + e.getMessage());

			}
		});

		client.getConnectionStateListenable().addListener(new ConnectionStateListener()
		{

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState)
			{
				System.out.println("zk " + newState);
			}
		});

		client.start();
		client.blockUntilConnected(10, TimeUnit.SECONDS);

		client.getChildren().usingWatcher(new Watcher(){

			@Override
			public void process(WatchedEvent event)
			{
				System.out.println("zk watcher: " + event.getType());
		}});

		String prefix = "/project";
		String path = prefix + "/featureX";
		if(client.checkExists().forPath(path) == null)
		{
			client.create().creatingParentsIfNeeded().forPath(path);
			System.out.println("node created");
		} else {
			System.out.println("node exist");
			client.setData().forPath(path, "Hello!".getBytes());
			System.out.println("setting data");
		}

		byte[] result = client.getData().forPath(path);
		System.out.println("result: " + new String(result, "UTF-8"));
	}

}
