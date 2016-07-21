package com.spencer.app;
import com.spencer.client.HelloPersonService;
import com.spencer.client.HelloService;
import com.spencer.client.Person;
import com.spencer.client.RpcClient;
import com.spencer.core.IAsyncObjectProxy;
import com.spencer.core.RpcFutrue;
import com.spencer.proxy.AsyncObjectService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client-spring.xml")
public class HelloServiceTest {

    @Autowired
    private RpcClient rpcClient;

    @Test
    public void helloTest1() {
        HelloService helloService = rpcClient.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);
        Assert.assertEquals("Hello! World", result);
    }

    @Test
    public void helloTest2() {
        HelloService helloService = rpcClient.create(HelloService.class);
        Person person = new Person("Yong", "Huang");
        String result = helloService.hello(person);
        Assert.assertEquals("Hello! Yong Huang", result);
    }

    @Test
    public void helloPersonTest(){
        HelloPersonService helloPersonService = rpcClient.create(HelloPersonService.class);
        int num = 5;
        List<Person>  persons = helloPersonService.GetTestPerson("xiaoming",num);
        List<Person> expectedPersons = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            expectedPersons.add(new Person(Integer.toString(i), "xiaoming"));
        }
        assertThat(persons, equalTo(expectedPersons));

        for (int i = 0; i<persons.size(); ++i){
            System.out.println(persons.get(i));
        }
    }

    @Test
    public void helloFutureTest1() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloService = rpcClient.createAsync(HelloService.class);
        RpcFutrue result = helloService.call("hello", "World");
        Assert.assertEquals("Hello! World", result.get());
    }

    @Test
    public void helloFutureTest2() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloService = rpcClient.createAsync(HelloService.class);
        Person person = new Person("Yong", "Huang");
        RpcFutrue result = helloService.call("hello", person);
        Assert.assertEquals("Hello! Yong Huang", result.get());
    }

    @Test
    public void helloPersonFutureTest1() throws ExecutionException, InterruptedException {
        IAsyncObjectProxy helloPersonService = rpcClient.createAsync(HelloPersonService.class);
        int num = 5;
        RpcFutrue result = helloPersonService.call("GetTestPerson", "xiaoming", num);
        List<Person> persons = (List<Person>) result.get();
        List<Person> expectedPersons = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            expectedPersons.add(new Person(Integer.toString(i), "xiaoming"));
        }
        assertThat(persons, equalTo(expectedPersons));

        for (int i = 0; i < num; ++i) {
            System.out.println(persons.get(i));
        }
    }
}
