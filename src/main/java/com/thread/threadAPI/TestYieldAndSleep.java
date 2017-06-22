package com.thread.threadAPI;

import org.junit.Test;

/**
 * sleep / yield 都是让出线程执行资源，然后恢复成待执行状态；跟线程内部执行无关(资源占用)
 *
 * @author maaowen
 * @date 2017/6/4
 */
public class TestYieldAndSleep {

    /**
     * .sleep() 在休眠的时候不会释放对象锁，然后在sleep时间结束的时候线程是恢复待执行状态
     */
    @Test
    public void testSleep() throws InterruptedException {
        // 顺便验证一下 字符串在常量池是同一个对象
        Thread thread1 = new Thread(new ClockDemo("s","thread1"));
        Thread thread2 = new Thread(new ClockDemo("s","thread2"));
        thread1.start();
        thread2.start();
    }

    static class ClockDemo implements Runnable{
        // 锁对象
        private Object obj;
        private String threadName;

        public ClockDemo(Object obj,String threadName) {
            this.obj = obj;
            this.threadName = threadName;
        }

        public void run() {
            System.out.println(threadName+" start !");
            synchronized (obj){
                System.out.println("Object is clocked by "+threadName+" !");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Clock is closed!");
            }
            System.out.println(threadName+" end !");
        }
    }


    /**
     * .yield() 和 线程优先级
     */
    @Test
    public void testYield(){
        /* 默认有这三种优先级
          MIN_PRIORITY = 1
  　　    NORM_PRIORITY = 5
          MAX_PRIORITY = 10
        */
        System.out.println("main thread start!");
        Thread demo1 = new ThreadPackage("thread1",5).getThread();
        Thread demo2 = new ThreadPackage("thread2",5).getThread();
        demo1.start();
        demo2.start();
        try {
            demo1.join();
            demo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end!");
    }

    static class ThreadPackage{

        private Thread  thread ;

        public ThreadPackage(String threadName,int priority) {
            thread = new Thread(new RunnableDemo(threadName));
            thread.setPriority(priority);
        }

        public Thread getThread() {
            return thread;
        }
    }

    static class RunnableDemo implements Runnable{

        private String threadName;

        public RunnableDemo(String threadName) {
            this.threadName = threadName;
        }

        public void run() {
            for(int i = 0 ; i<10 ; i++){
                if(i%3==0)
                    Thread.yield();
                System.out.println(threadName+" : "+i);
            }
        }
    }

}
