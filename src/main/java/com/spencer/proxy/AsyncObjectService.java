package com.spencer.proxy;

import com.spencer.core.RpcFutrue;

/**
 * Created by spencer on 16/7/21.
 * 异步服务接口对象
 */
public interface AsyncObjectService {
    public RpcFutrue call(String funcName, Object... args);

}
