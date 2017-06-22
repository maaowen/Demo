package com.thread.threadAPI.text;

import org.junit.Test;

/**
 * 两个线程交替打印AB
 * @author maaowen
 * @date 2017/6/4
 */
public class Demo1 {

    /**
     * 对象锁-释放锁
     */
    @Test
    public void text2(){
        Thread thread1 = new Thread(new InsideDemo("A","s"));
        Thread thread2 = new Thread(new InsideDemo("B","s"));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class InsideDemo implements Runnable{

        private String key;
        private Object obj;

        public InsideDemo(String key,Object obj) {
            this.key = key;
            this.obj = obj;
        }

        public void run() {
            synchronized (obj){
                for(int i = 0 ; i<10 ; i++) {
                    System.out.println(i+"~"+key+"~"+Thread.currentThread().getName());
                    // 通知等待线程
                    obj.notify();
                    try {
                        // 释放资源
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 通知最后一个线程.wait() 执行结束
                obj.notify();
            }
        }
    }

    /**
     * yield 以及静态变量
     */
    @Test
    public void text1(){
        Thread t1 = new Thread(new ThreadDemo("A",0));
        Thread t2 = new Thread(new ThreadDemo("B",1));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadDemo implements Runnable{
        private String name;
        private int mol;
        private static int i = 20;

        public ThreadDemo(String name, int mol) {
            this.name = name;
            this.mol = mol;
        }

        public void run() {
            while(i>0){
                if(i%2==mol){
                    System.out.print(name);
                    i--;
                }else{
                    Thread.yield();
                }
            }
        }
    }
}
