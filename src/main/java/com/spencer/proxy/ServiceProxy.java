package com.spencer.proxy;

import com.spencer.core.RpcFutrue;
import com.spencer.protocol.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by spencer on 16/7/20.
 */
public class ServiceProxy<T> implements InvocationHandler ,AsyncObjectService{

    private static final Logger LOG = LoggerFactory.getLogger(ServiceProxy.class);

    public Class<T> tClass ;

    public ServiceProxy(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤掉 equals,hashcode,toString 方法
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();

            //TODO
        }

        //构造请求对像
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(UUID.randomUUID().toString()); //标记请求的唯一id
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameterTypes(method.getParameterTypes());
        rpcRequest.setParameters(args);


        // Debug
        LOG.debug(method.getDeclaringClass().getName());
        LOG.debug(method.getName());
        for (int i = 0; i < method.getParameterTypes().length; ++i) {
            LOG.debug(method.getParameterTypes()[i].getName());
        }
        for (int i = 0; i < args.length; ++i) {
            LOG.debug(args[i].toString());
        }


        //负载均衡选择合适的server 进行请求调用




        return null;
    }

    @Override
    public RpcFutrue call(String funcName, Object... args) {
        return null;
    }
}
