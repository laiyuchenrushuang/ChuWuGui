package com.seatrend.lockerapp;

import com.seatrend.utilsdk.thread.ThreadPoolManager;
import com.seatrend.utilsdk.thread.manyThread.LyThread;
import com.seatrend.utilsdk.time.TimeCounter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Created by ly on 2020/8/11 9:11
 */
public class JavaTest {

    static Thread1 thread1 = new Thread1();
    static Thread2 thread2 = new Thread2();
    static Thread3 thread3 = new Thread3();

    public static void main(String[] args) {



        //  创建被观察者
        Observable novelObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("新章节1001");
                observableEmitter.onNext("新章节1002");
                observableEmitter.onNext("新章节1003");
                observableEmitter.onComplete();
            }
        });

        // 创建观察者
        Observer<String> reader = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                print("subscribe success");
            }

            @Override
            public void onNext(String s) {
                print("收到最新章节 ===》" + s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                print("接受结束！");
            }
        };
        novelObservable.subscribe(reader);

    }

    public static class Thread1 extends Thread {
        @Override
        public synchronized void run() {
            for (int i = 0; i < 5; i++) {
                print(currentThread().getName() + "  " + i);
                if (i == 3) {
                    try {
                        thread2.start();
                        thread1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public synchronized void run() {
            for (int i = 0; i < 5; i++) {
                print(currentThread().getName() + "  " + i);
                if (i == 3) {
                    try {
                        thread3.start();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            synchronized (thread1) {
                thread1.notify();
            }
        }
    }

    public static class Thread3 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                print(currentThread().getName() + "  " + i);
            }

            synchronized (thread2) {
                thread2.notify();
            }

        }
    }

    public static class Thread4 extends LyThread {
        @Override
        public synchronized void run() {

            for (int i = 0; i < 10; i++) {
                print(currentThread().getName() + "  " + i);
                if (i == 5) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void print(Object x) {
        System.out.println(x);
    }
}