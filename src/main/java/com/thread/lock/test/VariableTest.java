package com.thread.lock.test;

import org.junit.Test;

/**
 * 成员变量在多线程的时候什么时候会脏读,共享变量；从内存中同步变量到当前线程
 *
 * @author maaowen
 * @date 2017/6/10
 */
public class VariableTest {

    static class VariableDemo{
        public static Integer i = 10;
    }

    @Test
    public void demo1() throws InterruptedException {

        Thread t1 = new Thread(()->{
            VariableDemo.i--;
            System.out.println(Thread.currentThread().getName()+":"+VariableDemo.i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+VariableDemo.i);
        })
        , t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":"+VariableDemo.i);
            VariableDemo.i--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+VariableDemo.i);
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

        t1.join();
        t2.join();
    }
}
