package com.spencer.core;


/**
 * Created by luxiaoxun on 2016/3/16.
 */
public interface IAsyncObjectProxy {
    public RpcFutrue call(String funcName, Object... args);
}