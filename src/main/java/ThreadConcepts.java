import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadConcepts {


    int count = 0;
    synchronized void incrementSync() {
        count = count + 1;
    }

    public void callIncrement() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::incrementSync));

      executor.shutdown();

        System.out.println(count);  // 10000
    }
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            System.out.println(i);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });
        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }

    public static void main(String[] args) {
       // int[] nums  = {4, 6, 7, 9};
        int[] nums  = {9, 6, 7, 4};
        int len = nums.length;
        int[] arr = new int[len];
        int l = 0;
        for (int num:  nums){
            int i = Arrays.binarySearch(arr, 0, l, num);
            System.out.println(-(i+1));
            l++;
        }
     ExecutorService executorService = Executors.newSingleThreadExecutor();
     CountDownLatch countDownLatch = new CountDownLatch(5);
     for (int i = 0;i < 5; i++)
         executorService.execute(new Worker(i+1, countDownLatch));
        try {
          //  countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           executorService.shutdown();
        }
        System.out.println("All Done");

    }

}

class Worker implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;

    public Worker (int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }
    private void doWork() {
        System.out.println("Thread with id " + this.id + " starts working...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

