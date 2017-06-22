package com.thread.threadLocal;

/**
 * Threadlocal 测试类
 * @author maaowen
 * @date 2017/6/4
 */
public class ContextHolderTest {
    //ThreadLocal 在当前线程内开辟空间
    public static void main(String[] args){
        ThreadDemo t1 = new ThreadDemo("thread1","thread2");
        ThreadDemo t2 = new ThreadDemo("thread2","thread1");
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();  // 等待子线程结束
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" finished !");

    }
    static class ThreadDemo implements Runnable{

        private String threadName,otherThread;

        public ThreadDemo(String threadName,String otherThread) {
            this.threadName = threadName;
            this.otherThread = otherThread;
        }

        public void run() {
            ContextHoldler.put(threadName,"first","nothing");
            System.out.println("Give up the power of used "+threadName+" !");
            Thread.yield();
            System.out.println(threadName+" finish!");

            System.out.println(threadName+"-"+threadName+":"+ContextHoldler.get(threadName,"first"));
            System.out.println(threadName+"-"+otherThread+":"+ContextHoldler.get(otherThread,"first"));
        }
    }
}
