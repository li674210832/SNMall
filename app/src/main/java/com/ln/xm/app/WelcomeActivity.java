package com.ln.xm.app;

import android.content.Intent;
import android.media.tv.TvView;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ln.xm.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    public int n=4;
    public TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initview();
    }
    private void initview() {
         tv=  (TextView) findViewById(R.id.tv3);
        final Timer timer = new Timer();
        TimerTask timerTask= new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(n+"秒");




                        if(n<=0){
                            tv.setVisibility(View.GONE);
                            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                            timer.cancel();
                            finish();
                        }
                    }
                });
               n--;
            }
        };
         timer.schedule(timerTask,1000,1000);
    }
}
