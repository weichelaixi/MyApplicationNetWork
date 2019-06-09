package com.example.networklibrary.listener;

import com.example.networklibrary.type.NetType;

/**
 * @ClassName: NetChangeObserver
 * @Description: java类作用描述
 * @Author: 车伟
 * @CreateDate: 2019/4/7 20:29
 */
public interface NetChangeObserver {

    //网络连接
    void onConnect(NetType netType);

    //断网了
    void onDisConnect();
}
