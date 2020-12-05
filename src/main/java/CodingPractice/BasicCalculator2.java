package CodingPractice;

import java.util.*;

public class BasicCalculator2 {
    public static void main(String[] args) {
        String str = "3+2+ 3*2";
       // System.out.println(calculate(str));
       // System.out.println(andProduct(1,7));
        System.out.println(maxCoins1(new int[]{3, 1, 5, 8}));
    }


        public static int maxCoins1(int[] iNums) {
            int[] nums = new int[iNums.length + 2];
            int n = 1;
            for (int x : iNums)
                nums[n++] = x;
            nums[0] = nums[n++] = 1;

            // System.out.println(Arrays.toString(nums));
            int[][] memo = new int[n][n];
            int ans =  burst(memo, nums, 0, n - 1);
            System.out.println(Arrays.deepToString(memo));
            return ans;
        }

        public static int burst(int[][] memo, int[] nums, int left, int right) {
            if (left + 1 == right) return 0;
            if (memo[left][right] != 0) return memo[left][right];
            int ans = 0;
            for (int i = left + 1; i < right; ++i) {
                System.out.println(left + " -- " + i + " -- " + right);
                ans = Math.max(ans, nums[left] * nums[i] * nums[right] //+ burst(memo, nums, i, right));
                        + burst(memo, nums, left, i) + burst(memo, nums, i, right));
            }
         //   System.out.println(left + " -- " + right + " -- " + ans);
            memo[left][right] = ans;
            return ans;
        }


    public static int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return maxCoints(list);
    }

    public static int maxCoints(List<Integer> list) {
        int max = 0;
        if (list.size() == 1) {
            return list.get(0);
        }
        for (int i = 0; i < list.size(); i++) {
            int left = i == 0 ? 1 : list.get(i-1);
            int right = i == list.size()-1 ? 1 : list.get(i+1);
            int n = left * list.get(i) * right;
            List<Integer> tmp = new ArrayList<>(list);
            tmp.remove(i);
            System.out.println(tmp);
            max = Math.max(max, n + maxCoints(tmp));
        }
        return max;
    }
    public static int shortestWay(String source, String target) {
        int count = 0;
        int i = 0;
        int j = 0;
        int m = target.length();
        int n = source.length();
        while(i < m)
        {
            int start = i;
            while(i < m && j < n)
            {
                if(target.charAt(i) == source.charAt(j))
                {
                    i++;
                    j++;
                }
                else
                {
                    j++;
                }
            }
            if(start == i)
            {
                return -1;
            }
            else
            {
                count++;
                j = 0;
            }
        }
        return count;
    }
    public static int shortestWay1(String source, String target) {
        Map<Character, ArrayList<Integer>> map = new HashMap();
        for(int i = 0; i < source.length(); i++)
        {
            char c = source.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        int count = 0;
        int i = 0;
        while(i < target.length())
        {
            int j = -1;
            while(i < target.length() && findIndex(map.get(target.charAt(i)), j) != -1)
            {
                j = findIndex(map.get(target.charAt(i)), j);
                i++;
            }
            if(j == -1)
            {
                return -1;
            }
            else
            {
                count++;
            }
        }
        return count;

    }

    private static int findIndex(ArrayList<Integer> list, int begin)
    {
        if(list == null)
        {
            return -1;
        }
        if(list.size() == 0)
        {
            return -1;
        }
        int left = 0;
        int right = list.size() - 1;
        while(left < right)
        {
            int mid = (left + right) / 2;
            if(list.get(mid) > begin)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        if(list.get(left) > begin)
        {
            return list.get(left);
        }
        else
        {
            return -1;
        }
    }

    public static int shortestWay2(String source, String target) {
        int count = 0;
        int[] array = new int[26];
        Arrays.fill(array, -1);
        // 记录在string source 的i位置以后，第一次出现字符 c 的位置
        Map<Integer, int[]> map = new HashMap<>();
        for(int i = source.length() - 1; i >= 0; i--)
        {
            map.put(i, Arrays.copyOf(array, 26));
            array[source.charAt(i) - 'a'] = i;
        }
        map.put(-1, Arrays.copyOf(array, 26));
        int i = 0;
        while(i < target.length())
        {
            int index = -1;
            while(i < target.length() && map.get(index)[target.charAt(i) - 'a'] != -1)
            {
                index = map.get(index)[target.charAt(i)- 'a'];
                i++;
            }
            if(index == -1)
            {
                return -1;
            }
            else
            {
                count++;
            }
        }
        return count;
    }


    public static int calculate (String s) {
        int tail = 0;
        char operator = '+';
        int res = 0;
        char[] chrs = s.toCharArray();
        int num;
        for (int i = 0;i < chrs.length;i++) {
            char c = chrs[i];
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = (int) (c - '0');
                System.out.println(num + " -- " + operator);
                while (i + 1 < chrs.length && Character.isDigit(chrs[i+1])) {
                    num = num * 10 + (int) (chrs[i+1] - '0');
                    i++;
                }
                switch (operator) {
                    case '+':
                        res += tail;
                        tail = num;
                        break;
                    case '-':
                        res += tail;
                        tail = -num;
                        break;
                    case '*':
                        tail *= num;
                        break;
                    case '/':
                        tail /= num;
                        break;
                }
            }
            else {
                operator = c;
            }
        }
        return res + tail;
    }

    public static int andProduct (int m, int n) {
        int res = m;
        for (int i = m+1; i < n+1;i++) {
            res &= i;
        }
        return res;
    }
}

//
//LMIA,ICT