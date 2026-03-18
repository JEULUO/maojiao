package com.thread;

import com.thread.MyRannab;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// ... existing code ...
public class Thread_pool {
    public static void main(String[] args) {
        ThreadPoolExecutor t = new ThreadPoolExecutor(3, 8, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 20; i++) {
            MyRannab m = new MyRannab(i);
            t.execute(m);
        }
        t.shutdown();
    }
}
