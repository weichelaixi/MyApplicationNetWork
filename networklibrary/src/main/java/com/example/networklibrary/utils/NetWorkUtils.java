package com.example.networklibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.networklibrary.NetworkManager;
import com.example.networklibrary.type.NetType;

/**
 * @ClassName: NetWorkUtils
 * @Description: java类作用描述
 * @Author: 车伟
 */
public class NetWorkUtils {

    /**
     * 检查是否连接网络
     * @return
     */
    public static boolean isConnected() {
        ConnectivityManager connectMgr = (ConnectivityManager) NetworkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr == null) return false;
        NetworkInfo[] info = connectMgr.getAllNetworkInfo();
        if (info != null) {
            for (NetworkInfo anInfo : info) {
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 获取当前连接网络类型
     * @return
     */
    public static NetType getNetworkType() {
        ConnectivityManager connectMgr = (ConnectivityManager) NetworkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr == null) return NetType.NONE;
        NetworkInfo networkInfo = connectMgr.getActiveNetworkInfo();
        if(networkInfo == null){
            return NetType.NONE;
        }
        int netType = networkInfo.getType();
        if(netType == ConnectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                return NetType.CMNET;
            }else{
                return NetType.CMWAP;
            }
        }else if(netType == ConnectivityManager.TYPE_WIFI){
            return NetType.WiFi;
        }
        return NetType.NONE;
    }
}