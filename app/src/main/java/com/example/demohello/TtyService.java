package com.example.demohello;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class TtyService extends Service {
    //第一件事是創建一個名為 BROADCAST_ACTION 的字符串，這只是一個標識正在發生的動作類型的字符串。大多數情況下，使用包名並在末尾添加操作類型是很常見的，因此我們將在此處執行此操作。
    private static final String TAG = "BroadcastService";
    public static final String BROADCAST_ACTION = "com.websmithing.broadcasttest.displayevent";
    private final Handler handler = new Handler();
    Intent intent;
    int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        //創建了一個 Intent 並將 BROADCAST_ACTION 傳遞給 Intent 的構造函數。
        //最上方Intent定義為全局變量。Intent會在下面的Handler中被重複調用，沒有理由每5秒創建一個新的 Intent，在這裡創建一次。
        intent = new Intent(BROADCAST_ACTION);
    }


    //Runnable sendUpdatesToUI;
    public void onStartCommand(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);// removeCallbacks 來刪除對處理程序的任何現有回調，並確保我們不會獲得比我們想要的更多的回調。
        handler.postDelayed(sendUpdatesToUI, 1000); // 延遲一秒調用我們的處理程序。
    }
    //我們的可運行對象創建一個新線程，執行 run 方法中的任何代碼，然後關閉。
    // 在 run 方法中，我們做了兩件事，調用 DisplayLoggingInfo 方法，然後再次調用 handler.postDelayed，
    // 但這次有 5 秒的延遲。這就是重複計時器的創建方式。這樣做的好處之一是，
    // 可以放置​​一個變量來代替 5000，這樣您就可以從外部控制對可運行對象的調用之間的間隔。
    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 5000); // 5 seconds
        }
    };
    //在下面的方法中，我們向上面創建的意圖添加一些數據。
    // 我只是添加了日期和一個自增的簡單計數器。
    // 然後我們使用發送消息的意圖調用 sendBroadcast，然後註冊接收該消息的任何人都會收到它。
    private void DisplayLoggingInfo() {
        Log.d(TAG, "entered DisplayLoggingInfo");
        intent.putExtra("counter", String.valueOf(++counter));
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}