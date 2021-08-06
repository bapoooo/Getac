package com.example.demohello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class TtyActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastTest";
    private Intent intent;
    private TextView Topic3;
    private Button Button3;


    //現在，我們將創建一個 BroadcastReceiver 來接收將從上面的 Service 廣播的消息。
    // BroadcastReceiver 有一個方法 onReceive，它被調用，然後 BroadcastReceiver 對像被銷毀。
    // 在 onReceive 方法中，我們調用 updateUI 傳入我們的意圖，它保存要顯示的數據。
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };
    //在 onResume 和 onPause 中，我們啟動和停止我們的服務。當屏幕（活動）顯示並消失時會發生這種情況。
// 我們還通過傳入一個 IntentFilter 來註冊我們的 BroadcastReceiver。
// 您是否看到 IntentFilter 正在使用我們在上面的服務中創建的相同靜態字符串 BROADCAST_ACTION。
// 這就是我們識別廣播消息的方式。在 onPause 中，我們還確保我們調用 unregisterReceiver 來停止監聽廣播。
    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(TtyService.BROADCAST_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
    }
    //從意圖中獲取數據並使用數據設置我們的兩個 TextView。它們將每 5 秒更新一次。
    private void updateUI(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        Log.d(TAG, counter);
        Log.d(TAG, time);
        Topic3 = (TextView) findViewById(R.id.Topic3);
        Topic3.setText(counter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(TtyActivity.this, TtyService.class);
    }
}