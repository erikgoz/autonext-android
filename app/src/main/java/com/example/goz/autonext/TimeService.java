package com.example.goz.autonext;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.support.annotation.Nullable;

import java.util.Timer;

/**
 * Created by goz on 2/26/18.
 */

public class TimeService extends Service {
    // constant
    public static final long NOTIFY_INTERVAL = 33 * 1000; // 3 seconds
    public long generatedInterval;
    String SKIP="input keyevent 87";
    private static int MIN = 5;
    private static int MAX = 8;
    private int rand;

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    public void setInterval(int rand){
        this.rand = rand;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // cancel if already existed
        this.rand = randomWithRange(MIN,MAX);
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);

        //shellExecuter = new ShellExecuter();
    }


    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
                    Toast.makeText(getApplicationContext(), getDateTime(),
                            Toast.LENGTH_SHORT).show();
                    try {
                        Process p = Runtime.getRuntime().exec(SKIP);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //startNewService();
                    //ShellExecuter shellExecuter = new ShellExecuter();
                    //String output = shellExecuter.Executer(skip);
                    //String output = shellExecuter.Executer("input keyevent 87");
                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }

    }

    private String getDateTime() {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        return sdf.format(new Date());
    }
    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
