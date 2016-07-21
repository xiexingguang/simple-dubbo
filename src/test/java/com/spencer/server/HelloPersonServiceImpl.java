package com.spencer.server;



import com.spencer.client.HelloPersonService;
import com.spencer.client.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxiaoxun on 2016-03-10.
 */
@RpcService(HelloPersonService.class)
public class HelloPersonServiceImpl implements HelloPersonService {

    @Override
    public List<Person> GetTestPerson(String name, int num) {
        List<Person> persons = new ArrayList<Person>(num);
        for (int i = 0; i < num; ++i) {
            persons.add(new Person(Integer.toString(i), name));
        }
        return persons;
    }
}
