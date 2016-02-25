package com.example.dong.myapplication;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {
   Bus bus;
    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransation();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bus = ((App)getApplication()).getBus();

        getInfo();
        final View view = findViewById(R.id.view);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }

        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent it = new Intent();
//                it.setClass(MainActivity.this, ScrollingActivity.class);
//                startActivity(it, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
//                circleReavel();
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, "ShareName");
//                startActivity(it, options.toBundle());
                  bus.post(new AnswerAbleEvent());

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void circleReavel() {
        View view = findViewById(R.id.view);
        Animator animator = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0
                , view.getHeight() / 2);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setTransation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade().setDuration(1000));
            getWindow().setExitTransition(new Fade().setDuration(1000));
        }
//        getWindow().setSharedElementExitTransition(new Explode().setDuration(1000));
//        getWindow().setSharedElementEnterTransition(new Explode().setDuration(1000));
    }
    public void getInfo(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        Log.d("cyd",metric.widthPixels + "/" + metric.heightPixels + "/" + metric.density + "/" + metric.densityDpi );

        //160 1280 2.0, 320


    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe
    public void answer(String s){
        Log.d("cyd","answer :" + s);

    }
    @Subscribe
    public void answer2(AnswerAbleEvent e){
        Log.d("cyd","answerableEvent");
    }
    @Produce
    public void puduceAnswer(){

    }

   public  class  MBuss extends Bus{
       Bus instance;
       private MBuss(){

       }
       public Bus getBus(){
           if(instance == null)
               instance = new MBuss();
           return instance;
       }

   }
    class AnswerAbleEvent {


    }
}
