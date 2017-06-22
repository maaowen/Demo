package com.thread.threadAPI;

import org.junit.Test;

/**
 * .interrupt() 终止线程
 *
 * @author maaowen
 * @date 2017/6/4
 */
public class TestInterrupt {

    static class A {
        public static void staticMethod() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "start static method~!");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "static method end !");
        }
    }

    /**
     * 静态变量，静态方法都是类方法；同步时候需要锁类对象
     */
    @Test
    public void demo1() throws InterruptedException {
        Runnable runnable = () -> {
            synchronized (A.class) {
                try {
                    A.staticMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void demo() {
        System.out.println("main thread is start~!");
        Thread thread = new Thread(() -> {
            System.out.println("thread is start !");
            try {
                for (int i = 1; i < 10; i++) {
                    Thread.sleep(i * 100);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupt~!");
            } finally {
                System.out.println("thread is end !");
            }
        });
        thread.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("main thread is closed~!");
    }
}
