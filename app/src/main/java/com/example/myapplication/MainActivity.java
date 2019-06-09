 package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.networklibrary.NetStateRecevicer;
import com.example.networklibrary.NetworkManager;
import com.example.networklibrary.annotation.NetWork;
import com.example.networklibrary.listener.NetChangeObserver;
import com.example.networklibrary.type.NetType;
import com.example.networklibrary.utils.Constants;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getDefault().registerObserver(this);
    }

    @NetWork(netType = NetType.AUTO)
     public void netWork(NetType netType){
        switch (netType){
            case NONE:
                Log.e(Constants.LOG_TAG,"没网络");
                break;
            case WiFi:
                Log.e(Constants.LOG_TAG,"WIFI网络");
                break;
            case CMNET:
            case CMWAP:
                Log.e(Constants.LOG_TAG,"有网络");
                break;
        }
    }

     @Override
     protected void onDestroy() {
         super.onDestroy();
         NetworkManager.getDefault().unRegisterObserver(this);
         NetworkManager.getDefault().unRegisterAllObserver();
     }
 }
