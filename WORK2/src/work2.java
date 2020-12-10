import java.util.Scanner;

public class work2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入x:");
        long x = scanner.nextLong();
        System.out.println("请输入n:");
        long n = scanner.nextLong();
        long present = 1;
        Thread[] thread = new MyThread[1000010];
        for (int i = 1; i <= ((int) Math.sqrt(n)) + 1; i++) {
            thread[i] = new MyThread(x, n, present);
            thread[i].start();
            present += (long) Math.sqrt(n) + 2;
        }
        for (int i = 1; i <= ((int) Math.sqrt(n)) + 1; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("1~" + n + "中包含有" + x + "的数的和为:  " + Counter.ans);
    }
}

class Counter {
    public static final Object lock = new Object();
    public static long ans = 0;
}

class MyThread extends Thread {
    long present;
    long x;
    long n;

    public MyThread(long x, long n, long present) {
        this.x = x;
        this.n = n;
        this.present = present;
    }

    @Override
    public void run() {
        long sum = 0;
        for (long i = present; i <= present + ((long) Math.sqrt(n)) + 1; i++)
            if (contain(i, x) && i <= n) {
                sum += i;
            }
        synchronized (Counter.lock) {
            Counter.ans += sum;
        }
    }

    private static boolean contain(long num, long x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }

}

