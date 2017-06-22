package com.thread.threadAPI;

import org.junit.Test;

/**
 * Suspend / Resume 被废弃的原因：
 * 容易造成公共的同步对象的独占，使其他线程无法访问同步对象
 *
 * @author maaowen
 * @date 2017/6/10
 */
public class SuspendAndResume {


    static class SyncObj{
        synchronized public void syncMaethod(){
            System.out.println(Thread.currentThread().getName()+": is running~!");
            if(Thread.currentThread().getName().equals("thread1"))
                // 线程挂起
                Thread.currentThread().suspend();
            System.out.println(Thread.currentThread().getName()+": is stopping~!");
        }
    }

    /**
     * .suspend()线程挂起的时候不释放锁资源
     */
    @Test
    public void demo1() throws InterruptedException {
        SyncObj syncObj = new SyncObj();
        Thread t1 = new Thread(()->{
            syncObj.syncMaethod();
        },"thread1"),
        t2 = new Thread(()->{
            syncObj.syncMaethod();
        });
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.join();
        t2.join();
    }


    static boolean flag = true;
    static int count = 0;
    /**
     * .yield()是将线程变为待执行状态 ,即使优先级高的想要获取对象锁，还是会执行低优先级的线程；
     * 所以不会出现线程死锁的情况，.wait()线程休眠的同时释放对象锁；
     * .sleep()也是规定时间内将线程变为可执行状态，死锁情况也不会出现；
     */
    @Test
    public void demo2() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(()->{
            synchronized (obj){
                System.out.println(Thread.currentThread().getName()+": is locking~!");
                while(flag){
                    System.out.println("yield count:"+count);
                    Thread.yield();
                    count++;
                }
                System.out.println(Thread.currentThread().getName()+": is unlocked~!");
            }
        }),
        t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+": is running ~!");
            flag = false;
            synchronized (obj){
                System.out.println(Thread.currentThread().getName()+": has lock~!");
            }
        });
        t1.setPriority(5);
        t2.setPriority(10);
        t1.start();
        Thread.sleep(10);
        t2.start();
        t2.join();
    }
}
