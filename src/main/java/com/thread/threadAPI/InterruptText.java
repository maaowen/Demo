package com.thread.threadAPI;

import org.junit.Test;

/**
 * interrupt 相关方法联系
 *
 * @author maaowen
 * @date 2017/6/10
 */
public class InterruptText {
    /**
     * 创建一个可以持续执行的线程
     */
    class ThreadDemo extends Thread {
        public void run() {
            try {
                for (int i = 0; i < 10000000; i++) {
                    if (Thread.interrupted())//currentThread().isInterrupted()
                        throw new InterruptedException();
                    if (i % 10000 == 0) {
//                        Thread.sleep(10); 线程休眠的时候被interrupt会抛异常终止
                        System.out.println(currentThread().getName() + ":" + i);
                    }
                }
            } catch (Exception e) {
                System.out.println("thread is interrupted !");
            }
        }
    }

    /**
     * .interrupt() 并不能终止线程
     */
    @Test
    public void demo1() throws InterruptedException {
        Thread t1 = new ThreadDemo();
        t1.start();
        Thread.sleep(10);
        System.out.println(t1.isAlive());
        if (t1.isAlive())
            t1.interrupt();
        t1.join();
    }

    /**
     * 测试当前线程是否被中断
     * .interrupted() 是静态方法:只能判断当前线程是否被中断, .interrupted()被调用过一次之后会刷新线程的"中断状态",第二次调用则为false
     * .isInterrupted() 判断调用线程是否被中断
     */
    @Test
    public void demo2() throws InterruptedException {
        Thread t1 = new ThreadDemo();
        t1.start();
        Thread.sleep(10);
        System.out.println(t1.isAlive());
        if (t1.isAlive()) {
            t1.interrupt();
            System.out.println("~~~~~~~" + t1.isInterrupted() + "~~~~~~~ !");
            System.out.println("~~~~~~~" + t1.isInterrupted() + "~~~~~~~ !");
            System.out.println(Thread.currentThread().getName() + " first: " + Thread.interrupted());
//            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " second: " + Thread.interrupted());
            System.out.println(Thread.currentThread().getName() + " third: " + Thread.interrupted());
        }
        t1.join();
    }

    /**
     * 标记位中断循环线程
     */
    @Test
    public void demo3() throws InterruptedException {

        class ThreadDemo1 extends Thread {
            public void run() {
                while (!Thread.interrupted()) {
                    System.out.println(currentThread().getName() + " is running~!");
                }
                System.out.println("the thread is interrupt~!");
            }
        }

        class ThreadDemo2 implements Runnable {
            private Flag f;

            public ThreadDemo2(Flag f) {
                this.f = f;
            }

            public void run() {
                while (f.bool) {
                    System.out.println(Thread.currentThread().getName() + " is running~!");
                }
                System.out.println("the thread is interrupt~!");
            }
        }
        // ------------- 1  -----------------
        ThreadDemo1 demo1 = new ThreadDemo1();
        demo1.start();
        while (!demo1.isAlive()) {
            continue;
        }
        Thread.sleep(10);
        demo1.interrupt();
        // ------------ 2 ------------------
        Flag f = new Flag();
        Thread demo2 = new Thread(new ThreadDemo2(f));
        demo2.start();
        while (!demo2.isAlive()) {
            continue;
        }
        Thread.sleep(10);
        f.bool = false;
        demo2.join();
    }

    static class Flag {
        public boolean bool = true;

        public boolean isBool() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool = bool;
        }
    }

    /**
     * .sleep() 会监听当前线程是否是中断状态，抛出异常(在.sleep()之前或者是sleep之间)
     */
    @Test
    public void demo4() throws InterruptedException {
        Thread thread = new ThreadDemo4();
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        thread.join();
    }

    static class ThreadDemo4 extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; i++) {
                if (i % 10000 == 0)
                    System.out.println(currentThread().getName()+":"+i);
            }
            try {
                System.out.println("the thread is sleep~!");
                Thread.sleep(100);
                System.out.println("the thread is wake~!");
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupt~!");
            }
        }
    }

}
