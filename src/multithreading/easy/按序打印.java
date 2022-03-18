package multithreading.easy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Qidong Ding
 * @description TODO：1114. 按序打印
 * @date 2022-03-18-9:36
 * @since JDK 1.8
 *
 * 给你一个类：
 *
 * public class Foo {
 *  public void first() { print("first"); }
 *  public void second() { print("second"); }
 *  public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个Foo实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * 提示：
 *
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出："firstsecondthird"
 * 解释：
 * 有三个线程会被异步启动。输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。正确的输出是 "firstsecondthird"。
 * 示例 2：
 *
 * 输入：nums = [1,3,2]
 * 输出："firstsecondthird"
 * 解释：
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。正确的输出是 "firstsecondthird"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 按序打印 {
    public static void main(String[] args) {
        // 创建三个 Runnable
        Runnable first = () -> System.out.println("first");
        Runnable second = () -> System.out.println("second");
        Runnable third = () -> System.out.println("third");
        // 创建对象
        Foo foo = new Foo();

        // 创建三个线程，分别执行 三个 方法
        new Thread(() -> {
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Foo {
        // 提供原子操作的Integer的类,是线程安全的。适用于高并发的场景
        private AtomicInteger atomicInteger = new AtomicInteger(0);

        public Foo() {}

        /**
         * 无论如何 first 方法是第一个执行的所以 不需要去判断，线程启动即可执行，输出后 将 atomicInteger 自增
         * @param printFirst
         * @throws InterruptedException
         */
        public  void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            atomicInteger.incrementAndGet();
        }

        /**
         * second 总是在 first 后面执行，所以 只需要判断当前 atomicInteger 是否等于 1 ，然后 atomicInteger 自增
         * @param printSecond
         * @throws InterruptedException
         */
        public void second(Runnable printSecond) throws InterruptedException {
            while (atomicInteger.get() != 1) {
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            atomicInteger.incrementAndGet();
        }

        /**
         * third 总是在 second 后面执行，所以 只需要判断当前 atomicInteger 是否等于 2 即可
         * @param printThird
         * @throws InterruptedException
         */
        public void third(Runnable printThird) throws InterruptedException {
            while (atomicInteger.get() != 2) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }


}
