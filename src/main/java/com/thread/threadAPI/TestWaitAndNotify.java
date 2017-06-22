package com.thread.threadAPI;

import org.junit.Test;

/**
 * 线程等待和唤醒线程
 *
 * @author maaowen
 * @date 2017/6/4
 */
public class TestWaitAndNotify {

    /**
     * 一个线程在锁obj对象后,调用obj.wait()对其他需要obj锁的线程有什么影响
     * 对锁对象以外的.wait() 会报java.lang.IllegalMonitorStateException 当前线程不含有当前对象的锁资源
     */
    @Test
    public void demo2() throws InterruptedException {
        final Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("thread1 is running ~!");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("obj is waiting~!");
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                System.out.println("thread2 is running~!");
                obj.notify();
            }
        });
        t1.start();
        Thread.sleep(10);
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void demo1() throws InterruptedException {
        Object a = new Object(),
                b = new Object(),
                c = new Object();
        ThreadDemo first = new ThreadDemo("A", c, a);
        ThreadDemo second = new ThreadDemo("B", a, b);
        ThreadDemo third = new ThreadDemo("C", b, c);

        //确保按顺序A、B、C执行
        new Thread(first).start();
        Thread.sleep(100);
        new Thread(second).start();
        Thread.sleep(100);
        new Thread(third).start();
    }

    static class ThreadDemo implements Runnable {
        private String name;
        private Object prev;
        private Object self;

        private ThreadDemo(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        public void run() {
            int count = 5;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        self.notify();
                    }
                    try {
                        // 释放资源，当前线程休眠;等待notify()唤醒
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
