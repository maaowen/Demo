package com.thread.threadAPI.text;

import org.junit.Test;

/**
 * 线程生产者模式消费者模式
 *
 * @author maaowen
 * @date 2017/6/9
 */
public class Demo2 {

    /**
     * 生产者 / 消费者
     */
    class Product {
        private Object obj;

        public Product(Object obj) {
            this.obj = obj;
        }

        public void setValue(String value) {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    if (value != null)
                        try {
                            obj.wait();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    value = "apple";
                    System.out.println(Thread.currentThread().getName() + ": create value~!" + value);
                    obj.notify();
                }
            }
        }

        public void getValue(String data) {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    if (data == null)
                        try {
                            obj.wait();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    data = null;
                    System.out.println(Thread.currentThread().getName() + ": waste value~!" + data);
                    obj.notify();
                }
            }
        }
    }

    static class Local {
        private static String localValue = "ss";
    }

    /**
     * 创建创建/消费线程
     */
    @Test
    public void productAndConsume() {
        Product product = new Product("lock");
        Thread t1 = new Thread(() -> {
            product.setValue(Local.localValue);
        });
        Thread t2 = new Thread(() -> {
            product.getValue(Local.localValue);
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
