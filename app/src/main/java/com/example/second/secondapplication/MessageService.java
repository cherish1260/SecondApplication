package com.example.second.secondapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MessageService extends Service {


    private static final int MSG_SUM = 0x110;

    private Messenger messager = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromClient) {
            Message msgToClient = Message.obtain(msgFromClient);//返回给客户端的消息
            switch (msgFromClient.what) {
                case MSG_SUM:
                    msgToClient.what = msgFromClient.what;
                    try
                    {
                        //模拟耗时
                        Thread.sleep(2000);
                        msgToClient.arg2 = msgFromClient.arg1 + msgFromClient.arg2;
                        msgFromClient.replyTo.send(msgToClient);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    } catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msgFromClient);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return messager.getBinder();
    }
}
