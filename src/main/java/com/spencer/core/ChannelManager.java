package com.spencer.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by spencer on 16/7/21.
 * channel 管理类,负责管理连接过来的channel.
 * 可以在这里做负载均衡
 * 必须做成单例,保证jvm只一个实例
 */
public class ChannelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);
    private static volatile ChannelManager channelManager;
    //线程池用于连接server 节点
    private static ThreadPoolExecutor connectedExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
    private ReentrantLock lock = new ReentrantLock();
    private Condition connected = lock.newCondition();
    private ConcurrentHashMap<String/**ip:port**/, Channel> connecetedChannels = new ConcurrentHashMap<>();
    private CopyOnWriteArrayList<Channel> activeChannel = new CopyOnWriteArrayList<>(); //处于连接状态下的channel
    private long connectTimeout = 5 * 10000; //连接超时
    private AtomicInteger roundRobin = new AtomicInteger(0);
    private volatile boolean isRunning = true;

    private ChannelManager() {


    }

    public static ChannelManager getSingleInstance() {
        //双重检测
        if (channelManager == null) {
            synchronized (ChannelManager.class) {
                if (channelManager == null) {
                    channelManager = new ChannelManager();
                }
            }
        }
        return channelManager;
    }

    /**
     * 当zk上节点发生变化,更新本地map
     * @param serversAddresses
     */
    public void notifyServer(List<String> serversAddresses) {
        if (serversAddresses != null) {
            if (serversAddresses.size() > 0) {
                //update local serverNodes cache
                HashSet<InetSocketAddress> newAllServerNodeSet = new HashSet<InetSocketAddress>();
                for (int i = 0; i < serversAddresses.size(); ++i) {
                    String[] array = serversAddresses.get(i).split(":");
                    if (array.length == 2) { // Should check IP and port
                        String host = array[0];
                        int port = Integer.parseInt(array[1]);
                        final InetSocketAddress remotePeer = new InetSocketAddress(host, port);
                        newAllServerNodeSet.add(remotePeer);
                    }
                }
            }//end if




        }
    }

    //连接远程主机
    private void connectServerNode(InetSocketAddress remotePeer) {
        connectedExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Bootstrap b = new Bootstrap();

            }
        });
    }


}
