package com.example.networklibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import com.example.networklibrary.listener.NetChangeObserver;
import com.example.networklibrary.utils.Constants;

/**
 * @ClassName: NetworkManager
 * @Description: java类作用描述
 * @Author: 车伟
 */
public class NetworkManager {

    private static volatile NetworkManager instance;
    private Application application;

    private NetStateRecevicer recevicer;

    private NetworkManager(){
        recevicer = new NetStateRecevicer();
    }

    public static NetworkManager getDefault(){
        if(instance == null){
            synchronized (NetworkManager.class){
                if(instance == null){
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public Application getApplication(){
        if(application == null){
            throw new RuntimeException("NetworkManager.getDefault().init()未初始化");
        }
        return application;
    }

    public void init(Application application){
        this.application = application;
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
//        application.registerReceiver(recevicer,filter);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ConnectivityManager.NetworkCallback networkCallback = new NetWorkCallbackImpl();
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            NetworkRequest request = builder.build();
            ConnectivityManager cmgr = (ConnectivityManager) NetworkManager.getDefault().getApplication()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if(cmgr != null){
                cmgr.registerNetworkCallback(request,networkCallback);
            }
        }
    }

    public void registerObserver(Object register) {
        recevicer.registerObserver(register);
    }

    public void unRegisterObserver(Object register) {
        recevicer.unRegisterObserver(register);
    }

    public void unRegisterAllObserver() {
        recevicer.unRegisterAllObserver();
    }
}
