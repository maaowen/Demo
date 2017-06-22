package com.thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 的使用
 *
 * @author maaowen
 * @date 2017/6/9
 */
public class LockTest {

    @Test
    public void demo() throws InterruptedException {
        ServiceTest service = new ServiceTest();
        Thread t = new Thread(new ThreadDemo(service));
        t.start();
        Thread.sleep(200);
        service.signal();
    }

    class ThreadDemo implements Runnable {
        private ServiceTest test;
        public ThreadDemo(ServiceTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            test.await();
        }
    }


    class ServiceTest{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void await() {
            lock.lock();
            try {
                System.out.println("await A");
                condition.await();//使当前执行的线程处于等待状态 waiting
                System.out.println("await B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("释放锁");
            }
        }
        public void signal() {
            lock.lock();
            System.out.println("signal ~!");
            condition.signal();
            lock.unlock();
        }
    }
}
