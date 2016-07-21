package com.spencer.core;

/**
 * Created by spencer on 16/7/12.
 *
 * 异步回调函数
 *
 */
public interface AsyncCallBack {

    void success(Object o);

    void fail(Exception e);
}
