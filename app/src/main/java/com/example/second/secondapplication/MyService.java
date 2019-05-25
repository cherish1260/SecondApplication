package com.example.second.secondapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by sxiaoxia on 2018/5/3.
 */

public class MyService extends Service {

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void useFromRemoteService() throws RemoteException {
            Log.i("service", "我来自远端的服务");
        }
    };

    //重写与Service生命周期的相关方法
    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("执行了onCreat()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    @Override
    public IBinder onBind(Intent intent) {

        System.out.println("执行了onBind()");
        //在onBind()返回继承自Binder的Stub类型的Binder，非常重要
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("执行了onUnbind()");
        return super.onUnbind(intent);
    }
}
