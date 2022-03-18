package multithreading.medium;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Qidong Ding
 * @description TODO： 1115. 交替打印 FooBar
 * @date 2022-03-18-10:29
 * @since JDK 1.8
 * 给你一个类：
 *
 * class FooBar {
 *   public void foo() {
 *  for (int i = 0; i < n; i++) {
 *   print("foo");
 *    }
 *   }
 *
 *   public void bar() {
 *  for (int i = 0; i < n; i++) {
 *  print("bar");
 *  }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例：
 *
 * 线程 A 将会调用 foo() 方法，而
 * 线程 B 将会调用 bar() 方法
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出："foobar"
 * 解释：这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出："foobarfoobar"
 * 解释："foobar" 将被输出两次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 交替打印FooBar {

    public static void main(String[] args) {
        // 创建 两个 Runnable
        Runnable foo = () -> System.out.print("foo");
        Runnable bar = () -> System.out.print("bar");
        // 创建 对象
        FooBar fooBar = new FooBar(5);


        // 创建两个线程
        new Thread(() -> {
            try {
                fooBar.foo(foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(bar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


    static class FooBar {
        private int n;
        // 创建一个 可重入锁
        private Lock lock = new ReentrantLock();
        // 创建一个标志位
        private volatile boolean flag = true;
        // 得到一个  Condition
        private Condition condition = lock.newCondition();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                    // 上锁
                    lock.lock();
                    try {
                        // 当 标志位 等于 true 的时候 输出 并将 标志位 置为 false 然后 唤醒其他等待线程，释放锁
                        while (!flag) {
                            condition.await();
                        }

                        // printFoo.run() outputs "foo". Do not change or remove this line.
                        printFoo.run();
                        flag = false;
                        condition.signalAll();
                    }finally {
                        // 释放锁
                        lock.unlock();
                    }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                    // 上锁
                    lock.lock();
                    try {

                        // 当 标志位 等于 false 的时候 输出 并将 标志位 置为 true 然后 唤醒其他等待线程，释放锁
                        while (flag) {
                            condition.await();
                        }
                        // printFoo.run() outputs "foo". Do not change or remove this line.
                        printBar.run();
                        flag = true;
                        condition.signalAll();
                    }finally {
                        // 释放锁
                        lock.unlock();
                    }
            }
        }
    }
}
