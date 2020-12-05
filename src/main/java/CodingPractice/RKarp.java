package CodingPractice;

public class RKarp {

    public static void main(String[] args) {
       // System.out.println(Integer.toBinaryString(11));

        StringBuilder sb = new StringBuilder("abc");
        sb.setLength(sb.length());
        System.out.println(sb.toString());
        String str = "abbbbbb";
        int val = 0;
        for (char ch : str.toCharArray()) {
            val = val | 1 << (ch-'a');
           System.out.println(Integer.bitCount(val));
        }
        System.out.println(val);
//        int prime = 3;
//        int hash = 0;
//        for (int i =0;i < str.length();i++) {
//      //      System.out.println((int)str.charAt(i) + " --- " + Math.pow(prime, i));
//            hash  += str.charAt(i) * Math.pow(prime, i);
//        }
//      //  System.out.println(hash);
//        int newHash = (hash - str.charAt(0))/prime;
//       // System.out.println(newHash);
//    //    System.out.println(5 & 6 & 7);
//        System.out.println(rangeBitwiseAnd(3,5));
       // System.out.println(1 << 2);
       // System.out.println(3 & 4 & 5);


    }


    public static int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
            System.out.println(m + "-- " + n + " -- " + moveFactor);
        }
        return m * moveFactor;
    }
}
