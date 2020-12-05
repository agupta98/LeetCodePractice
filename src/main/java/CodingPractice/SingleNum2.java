package CodingPractice;

import java.util.Arrays;
import java.util.List;

public class SingleNum2 {

    public static void main(String[] args) {
        System.out.println(0 ^ 1);
        System.out.println(0 ^ 0);
       // System.out.println(singleNum2(Arrays.asList(2,2,2,5)));
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            int bit = (5 >> i) & 1;   // Ex 5
            arr[i] = bit;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static int singleNum2 (List<Integer> al) {

        int[] arr = new int[32];
        for (int num: al) {
            for (int i = 0; i < 32; i++) {
                int bit = num >> i & 1;   // Ex 5 create an array with bit representaion of 5
                arr[i] = (arr[i] + bit) % 3;
            }
        }
        System.out.println(Arrays.toString(arr));
        int res  = 0;
        for (int i = 0; i < arr.length;i++) {
            if (arr[i]== 1)
             res += Math.pow(2,i);
        }
        return res;
    }
}
