package com.spencer.proxy;

import com.spencer.core.RpcFutrue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by apple on 16/7/20.
 */
public class ServiceProxy<T> implements InvocationHandler ,AsyncObjectService{

    private static final Logger LOG = LoggerFactory.getLogger(ServiceProxy.class);

    public Class<T> tClass ;

    public ServiceProxy(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    @Override
    public RpcFutrue call(String funcName, Object... args) {
        return null;
    }
}
