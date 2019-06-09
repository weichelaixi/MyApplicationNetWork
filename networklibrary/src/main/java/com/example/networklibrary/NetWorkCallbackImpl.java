package com.example.networklibrary;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.networklibrary.utils.Constants;

/**
 * @ProjectName: MyApplication
 * @Package: com.example.networklibrary
 * @ClassName: NetWorkCallbackImpl
 * @Description: java类作用描述
 * @Author: 车伟
 * @CreateDate: 2019/4/14 16:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/14 16:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetWorkCallbackImpl extends ConnectivityManager.NetworkCallback {
    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.e(Constants.LOG_TAG, "网络已连接");
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
            if(networkCapabilities.hasCapability(NetworkCapabilities.TRANSPORT_WIFI)){

            } else {

            }
        }
    }
}