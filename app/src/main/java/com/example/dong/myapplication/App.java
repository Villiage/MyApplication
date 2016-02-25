package com.example.dong.myapplication;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by dong on 2016/2/25.
 */
public class App extends Application {
    Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Bus getBus() {
        if (bus == null)
            bus = new Bus();
        return bus;

    }
}
