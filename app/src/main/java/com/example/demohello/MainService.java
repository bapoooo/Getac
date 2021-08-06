package com.example.demohello;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

public class MainService extends Service {
    private static final String TAG = "";
    private boolean isRunning  = false;



   // public MainService() {
   // }

    @Override
    public void onCreate() {
        Log.i(TAG, "服務Service onCreate");
        isRunning = true;


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "服務Service onStartCommand");
        new Thread(new Runnable() { //產生新的執行序

            @Override
            public void run() {


                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }
                    if(isRunning){
                        Log.i(TAG, "服務正在執行");
                    }

                }

                //Stop service once it finishes its task
                stopSelf(); //stopSelf()立即銷毀service（onDestroy()函數被立即執行）

            }

        }).start();

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        isRunning = false;

        Log.i(TAG, "服務終止Service onDestroy");
    }

}