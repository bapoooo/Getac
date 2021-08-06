package com.example.demohello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private TextView Topic1;
    private TextView Topic11;
    private Button Button1;
    private TextView Topic2;
    private Button Button2;
    private TextView Topic3;
    private Button Button3;
    private TextView Topic4;
    private Button Button4;
    private Button Button44;
    private boolean isRunning;
    private TextView Topic5;
    private Button Button5;
    private Button Button55;
    private TextView Topic6;
    private TextView Topic66;
    private Button Button6;
    private Button Button66;
    int r; //隨機整數
    int quotient; //商數
    int remainder; //餘數
    int revers;
    int storehouse=10;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//第1題
    Topic1=(TextView)findViewById(R.id.Topic1); //抓取佈局中的元件
    Topic11=(TextView)findViewById(R.id.Topic11);
    Button1=(Button)findViewById(R.id.Button1);
    Button1.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
        @Override
        public void onClick(View view) {
            r=0; //隨機整數
            quotient=0; //商數
            remainder=0; //餘數
            revers=0;
            //反向
            r=(int)(Math.random()*1000)+1; // 隨機產生1~1000的整數     Math.random()產生0~0.999
            quotient=r;
            remainder=r;
            while (quotient/10>=0){
                remainder=quotient%10;
                quotient= quotient/10;
                revers=revers*10+remainder;
                if (quotient==0)
                    break;
            }

            Topic1.setText(String.valueOf(r));
            Topic11.setText(String.valueOf(revers));
        }
    });
//第2題
    Topic2=(TextView)findViewById(R.id.Topic2); //抓取佈局中的元件
    Button2=(Button)findViewById(R.id.Button2);


    Button2.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
        @Override
        public void onClick(View view) {
            String text="";
            try {  //修復
                InputStream is = getAssets().open("sample");
                int size=is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                text=new String(buffer);
            }catch (IOException ex){
                ex.printStackTrace();
            }
            Topic2.setText(text);
        }
    });
//第3題
    Topic3=(TextView)findViewById(R.id.Topic3); //抓取佈局中的元件
    Button3=(Button)findViewById(R.id.Button3);

    //至此，Service 已完成，現在是時候查看我們的 Activity 並了解我們如何使用和顯示我們的數據。
    //我們要做的第一件事是使用 Service 類的名稱創建一個意圖。這將傳遞給 startService 和 stopService。


    Button3.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,MainService.class); //目前在MainAtivty欲透過Intent開啟MainService
            startService(intent); //啟動intent (執行service)

        }




    });
//第4題-1
        Topic4=(TextView)findViewById(R.id.Topic4); //抓取佈局中的元件
        Button4=(Button)findViewById(R.id.Button4);
        Button44=(Button)findViewById(R.id.Button44);

        Button4.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
            @Override
            public void onClick(View view) {
                if (storehouse<10){
                    storehouse+=1;
                    Topic4.setText(String.valueOf(storehouse));
                }
            }
        });
        Button44.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
            @Override
            public void onClick(View view) {
                if (storehouse>0){
                    storehouse-=1;
                    Topic4.setText(String.valueOf(storehouse));
                }
                if (storehouse==0){
                    Topic4.setText("缺貨無法消費");
                }

            }
        });








//第5題
        Topic5=(TextView)findViewById(R.id.Topic5); //抓取佈局中的元件
        Button5=(Button)findViewById(R.id.Button5);
        Button55=(Button)findViewById(R.id.Button55);

        Button5.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                r=(int)(Math.random()*1000)+1;
                Topic5.setText(String.valueOf(r));
            }

        });
        Button55.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "您已關閉APP",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
//第6題
        Topic6=(TextView)findViewById(R.id.Topic6); //抓取佈局中的元件
        Topic66=(TextView)findViewById(R.id.Topic66); //抓取佈局中的元件
        Button6=(Button)findViewById(R.id.Button6);


        Button6.setOnClickListener(new View.OnClickListener() { //監聽按鈕點擊 並宣告點擊事件
            @Override
            public void onClick(View view) {

                //產生亂數陣列
                int[] randomArray = new int[10];
               // int[] randomArray1 = new int[10];
                Random rnd = new Random();  //產生亂數初始值
                for (int i = 0; i < 10; i++) {
                    randomArray[i] = (int) (Math.random() * 1000) + 1;   //亂數產生
                    for (int j = 0; j < i; j++) {
                        while (randomArray[j] == randomArray[i])    //檢查是否與前面產生的數值發生重複，如果有就重新產生
                        {
                            j = 0;  //如有重複，將變數j設為0，再次檢查 (因為還是有重複的可能)
                            randomArray[i] = (int) (Math.random() * 1000) + 1;   //重新產生，存回陣列
                        }
                    }
                }
                //randomArray1=randomArray;

                //升序排序
                for (int i = 0; i < randomArray.length; i++) {
                    for (int j = 0; j < randomArray.length; j++) {
                        if (randomArray[i] < randomArray[j]) {
                            int temp = randomArray[i];
                            randomArray[i] = randomArray[j];
                            randomArray[j] = temp;
                        }
                    }
                }
                //Topic6.setText(Arrays.toString(randomArray1));
                Topic6.setText(Arrays.toString(randomArray));
            }

        });

}
}