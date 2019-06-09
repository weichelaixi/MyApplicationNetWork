package com.example.networklibrary.bean;

import com.example.networklibrary.type.NetType;

import java.lang.reflect.Method;

/**
 * @ProjectName: MyApplication
 * @Package: com.example.networklibrary.bean
 * @Description: java类作用描述
 * @Author: 车伟
 */
public class MethodManager {
    private Class<?> type;//参数类型

    private NetType netType; // 网络类型

    private Method method; //需要执行的方法   netWork()

    public MethodManager(Class<?> type, NetType netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
