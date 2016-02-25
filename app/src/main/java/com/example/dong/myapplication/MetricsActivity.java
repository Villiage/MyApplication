package com.example.dong.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Observable;

public class MetricsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        getInfo();
    }
    public void getInfo(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        Log.d("cyd",metric.widthPixels + "/" + metric.heightPixels + "/" + metric.density + "/" + metric.densityDpi );

        //160 1280 2.0, 320

        // 480 * 800  320 * 1.5 = 480

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);


    }


}
