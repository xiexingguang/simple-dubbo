package com.spencer.app;



import com.spencer.client.HelloPersonService;
import com.spencer.client.Person;
import com.spencer.client.RpcClient;
import com.spencer.core.AsyncCallBack;
import com.spencer.core.IAsyncObjectProxy;
import com.spencer.core.RpcFutrue;
import com.spencer.registry.ServiceDiscovery;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class HelloPersonCallbackTest {
    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            IAsyncObjectProxy client = rpcClient.createAsync(HelloPersonService.class);
            int num = 5;
            RpcFutrue helloPersonFuture = client.call("GetTestPerson", "xiaoming", num);
            helloPersonFuture.addCallback(new AsyncCallBack() {
                @Override
                public void success(Object result) {
                    List<Person> persons = (List<Person>) result;
                    for (int i = 0; i < persons.size(); ++i) {
                        System.out.println(persons.get(i));
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void fail(Exception e) {
                    System.out.println(e);
                    countDownLatch.countDown();
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rpcClient.stop();

        System.out.println("End");
    }
}
