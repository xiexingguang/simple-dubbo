package com.spencer.app;

/**
 * Created by apple on 16/7/23.
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
              //  System.out.println("wait begin ....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("current thread is  :" + Thread.currentThread().getName());
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread is  :" + Thread.currentThread().getName());
            }
        });

        thread.start();
        thread1.start();

        System.out.println("main begin..." + Thread.currentThread().getName());
    }

}
