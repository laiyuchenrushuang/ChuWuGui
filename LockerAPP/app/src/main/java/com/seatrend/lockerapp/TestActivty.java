package com.seatrend.lockerapp;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import com.seatrend.lockerapp.base.BaseActivity;
import com.seatrend.utilsdk.thread.manyThread.LyThread;
import com.seatrend.utilsdk.utils.StringUtils;

public class TestActivty extends BaseActivity {

    Button test;
    Button state;
    Button stop;
    Button wait;
    final Thread4 thread4 = new Thread4();

    @Override
    public void initView() {
        test = findViewById(R.id.test);
        state = findViewById(R.id.state);
        stop = findViewById(R.id.stop);
        wait = findViewById(R.id.wait);


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                thread4.start();
            }
        });

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLog(thread4.getState());
            }
        });
        wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLog("wait onClick");
                try {

                    synchronized (thread4) {
                        showLog("wait");
                        thread4.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    showLog("wait InterruptedException");
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLog("interrupt");
                flag = false;
            }
        });
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_text;
    }

    private boolean flag;

    public class Thread4 extends LyThread {


        @Override
        public synchronized void run() {

            while (flag) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showLog(currentThread().getName() + "  " + StringUtils.longToStringData(System.currentTimeMillis()));
            }


        }
    }

}