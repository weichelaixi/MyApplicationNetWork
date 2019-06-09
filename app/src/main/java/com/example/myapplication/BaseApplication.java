package com.example.myapplication;

import android.app.Application;

import com.example.networklibrary.NetworkManager;

/**
 * @ProjectName: MyApplication
 * @Package: com.example.myapplication
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: 车伟
 * @CreateDate: 2019/4/13 22:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/13 22:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getDefault().init(this);
    }
}
