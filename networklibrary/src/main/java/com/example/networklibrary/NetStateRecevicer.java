package com.example.networklibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.networklibrary.annotation.NetWork;
import com.example.networklibrary.bean.MethodManager;
import com.example.networklibrary.listener.NetChangeObserver;
import com.example.networklibrary.type.NetType;
import com.example.networklibrary.utils.Constants;
import com.example.networklibrary.utils.NetWorkUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: NetStateRecevicer
 * @Description: 网络监听
 * @Author: 车伟
 */
public class NetStateRecevicer extends BroadcastReceiver {

    private NetType netType;

    private Map<Object, List<MethodManager>> netWorkList;

    public NetStateRecevicer(){
        netType = NetType.NONE;
        netWorkList = new HashMap<>();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getAction() == null){
            Log.e(Constants.LOG_TAG, "异常了");
        }
        //处理网络
        if(intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)){
            Log.e(Constants.LOG_TAG, "网络发生改变");
            netType = NetWorkUtils.getNetworkType();
            if(NetWorkUtils.isConnected()){ //网络连接了
                Log.e(Constants.LOG_TAG, "网络连接成功");
            } else{
                Log.e(Constants.LOG_TAG, "没有网络连接");
            }
            //通知所有注册过得方法
            post(netType);
        }
    }

    //同事分发
    private void post(NetType netType) {
        Set<Object> set = netWorkList.keySet();
        for (final Object getter : set) {
            //所有注解的方法
            List<MethodManager> methodManagerList = netWorkList.get(getter);
            if(methodManagerList != null){
                //循环每个方法
                for (final MethodManager method : methodManagerList) {
                    //是否是我们想要的类型
                    if(method.getType().isAssignableFrom(netType.getClass())){
                        switch (method.getNetType()){
                            case AUTO:
                                invoke(method,getter,netType);
                                break;
                            case WiFi:
                                if(netType == NetType.WiFi || netType == NetType.NONE){
                                    invoke(method,getter,netType);
                                }
                                break;
                            case CMNET:
                                if(netType == NetType.CMNET || netType == NetType.NONE){
                                    invoke(method,getter,netType);
                                }
                                break;
                            case CMWAP:
                                if(netType == NetType.CMWAP || netType == NetType.NONE){
                                    invoke(method,getter,netType);
                                }
                                break;
                            case NONE:
                                if(netType == NetType.NONE || netType == NetType.NONE){
                                    invoke(method,getter,netType);
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    private void invoke(MethodManager method, Object getter, NetType netType) {
        Method excute = method.getMethod();
        try {
            excute.invoke(getter,netType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterAllObserver() {
        if(!netWorkList.isEmpty()){
            netWorkList.clear();
        }
        NetworkManager.getDefault().getApplication().unregisterReceiver(this);
        netWorkList = null;

    }

    public void unRegisterObserver(Object register) {
        if(!netWorkList.isEmpty()){
            netWorkList.remove(register);
        }
    }

    public void registerObserver(Object register) {
        //获取activity中所有的方法
        List<MethodManager> methodManagerList = netWorkList.get(register);
        if(methodManagerList == null){
            methodManagerList = findAnnotationMethod(register);
            netWorkList.put(register,methodManagerList);
        }
    }

    private List<MethodManager> findAnnotationMethod(Object register) {
        List<MethodManager> methodManagerList = new ArrayList<>();
        Class<?> clazz = register.getClass();
        Method[] methods = clazz.getMethods();
        for ( Method method: methods) {
            //获取方法的注解
            NetWork network = method.getAnnotation(NetWork.class);
            if(network == null){
                continue;
            }
            Type returnType = method.getGenericReturnType();
            if(!"void".equals(returnType.toString())){
                throw new RuntimeException(method.getName() + "方法返回必须是void");
            }

            //参数检验
            Class<?>[] parameterTypes = method.getParameterTypes();
            if(parameterTypes.length != 1){
                throw new RuntimeException(method.getName() + "方法有且只有一个");
            }
            MethodManager manager = new MethodManager(parameterTypes[0],network.netType(),method);
            methodManagerList.add(manager);
        }
        return methodManagerList;
    }
}
