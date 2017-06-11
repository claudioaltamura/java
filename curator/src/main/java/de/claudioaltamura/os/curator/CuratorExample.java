package de.claudioaltamura.os.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CuratorExample {

	public static void main(String[] args) throws Exception {
		String zookeeperConnectionString = "localhost:2181";

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(zookeeperConnectionString )
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		//Unhandled Error Listener
		client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener()
		{

			@Override
			public void unhandledError(String message, Throwable e)
			{
				System.out.println("unhandled Error " + e.getMessage());

			}
		});

		//Connection State Listener
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

		//Curator Listener
		client.getCuratorListenable().addListener(new CuratorListener() {

			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println("event " + event);
			}
		});

		//create
		String prefix = "/project";
		String path = prefix + "/featureX";

		if(client.checkExists().forPath(path) == null)
		{
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
			System.out.println("node created");
		} else {
			System.out.println("node exist");
			//async
			//with CuratorListener
			//client.setData().inBackground().forPath(path);
			//client.setData().inBackground(new BackgroundCallback() {
			//	@Override
			//	public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
			//		// TODO Auto-generated method stub
			//
			//	}
			//}).forPath(path);
			client.setData().forPath(path, ("Hello! " + System.currentTimeMillis()).getBytes());
			System.out.println("setting data");
		}

		//Watcher
		client.getChildren().usingWatcher(new Watcher(){

			@Override
			public void process(WatchedEvent event)
			{
				System.out.println("zk watcher: " + event.getType());
			}}
		).forPath(path);

		byte[] result = client.getData().forPath(path);
		System.out.println("result: " + new String(result, "UTF-8"));

		//delete
		//client.delete().deletingChildrenIfNeeded().forPath(path);
		//client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);

		CloseableUtils.closeQuietly(client);
	}

}
