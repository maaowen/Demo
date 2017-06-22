package com.thread.threadAPI;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 关键字
 *
 * @author maaowen
 * @date 2017/6/8
 */
public class VolatileTest {

    static class RunnableDemo2 implements Runnable{
        private int i ;

        public RunnableDemo2(int i) {
            this.i = i;
        }

        public void run() {
            synchronized (this){
                while(i>0){
                    System.out.println(Thread.currentThread().getName()+" i: "+i);
                    i-=1;
                    this.notifyAll();
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.notifyAll();
            }
        }
    }


    static class RunnableDemo1 implements Runnable{
        private volatile int i ;
//        Thread-7 i: 23
//        Thread-5 i: 23
//        Thread-6 i: 23
//        Thread-0 i: 21
//        Thread-9 i: 23
//        Thread-1 i: 22
//        Thread-2 i: 17
//        Thread-3 i: 17
//        Thread-4 i: 16
//        Thread-8 i: 14
        public RunnableDemo1(int i) {
            this.i = i;
        }

        public void run() {
            while(i>0){
                System.out.println(Thread.currentThread().getName()+" i: "+i);
                i-=1;
                Thread.yield();
            }
        }
    }

    @Test
    public void demo() throws InterruptedException {
//        Runnable runnable = new RunnableDemo1(100);
        Runnable runnable = new RunnableDemo2(100);
        List<Thread> list = new ArrayList();
        for(int i = 0 ; i<10 ; i++){
            list.add(new Thread(runnable));
        }
        for(Thread thread:list){
            thread.start();
        }
        for(Thread thread:list){
            thread.join();
        }
    }
}
