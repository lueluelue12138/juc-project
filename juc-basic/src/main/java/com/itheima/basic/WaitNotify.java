/**
 * 等待唤醒示例
 */
package com.itheima.basic;

public class WaitNotify {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 线程1
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "...等待中...");
                try {
                    lock.wait(); // 线程1等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...被唤醒了");
            }
        }, "t1");

        // 线程2
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "...等待中...");
                try {
                    lock.wait(); // 线程2等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...被唤醒了");
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(2000); // 等待2秒

        synchronized (lock) {
            lock.notifyAll(); // 唤醒所有等待的线程
        }
    }
}