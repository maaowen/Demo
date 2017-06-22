package com.thread.threadAPI.text;

import org.junit.Test;

/**多线程交替打印
 *
 * 串行修改标记位变量
 * @author maaowen
 * @date 2017/6/9
 */
public class Demo3 {

    @Test
    public void demo(){
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            ThreadB tb = new ThreadB(dbTools);
            ThreadA ta = new ThreadA(dbTools);
            tb.start();
            ta.start();
            try {
                ta.join();
                tb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ThreadA extends Thread{
        private DBTools dbTools;
        public ThreadA(DBTools dbTools) {
            this.dbTools = dbTools;
        }
        @Override
        public void run() {
            dbTools.backA();
        }
    }

    class ThreadB extends Thread{
        private DBTools dbTools;
        public ThreadB(DBTools dbTools) {
            this.dbTools = dbTools;
        }
        @Override
        public void run() {
            dbTools.backB();
        }
    }

    class DBTools {
        volatile private boolean prevIsA = false;

        public synchronized void backA() {
            while (prevIsA == true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": A ~!");
            prevIsA = true;
            notify();
        }

        public synchronized void backB() {
            while (prevIsA == false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": B ~!");
            prevIsA = false;
            notify();
        }
    }
}
