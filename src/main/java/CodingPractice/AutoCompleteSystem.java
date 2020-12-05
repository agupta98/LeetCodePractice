package CodingPractice;

import java.util.*;

class Node {
    String sentence;
    int times;

    Node(String st, int t) {
        sentence = st;
        times = t;
    }
}
//"i love you" : 5 times
//"island" : 3 times
//"ironman" : 2 times
//"i love leetcode" : 2 times
class AutocompleteSystem {
    public static void main(String[] args) {

        start: for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("#");
                if (j >= i)
                    continue start;
            }
            System.out.println("This will never"
                    + " be printed");
        }

        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("#");
                if (j >= i)
                    continue;
            }
            System.out.println("This will never"
                    + " be printed");
        }

//        System.out.println("abbc".hashCode());
//        Map<String, Integer> map = new HashMap<>();
//        map.put("abbc",2);
//        for (String i: map.keySet()) System.out.println(i.hashCode());
//        String str = "anshgbfans";
//        System.out.println(str.indexOf("ans", 1));
//        String s  = "nacajnvc";
//        System.out.println(s.indexOf("na"));
//        String[] sent = new String[]{"i love you", "island", "ironman", "i love leetcode"};
//        int[] times = new int[]{5, 3, 2, 2};
//        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sent,times);
//        autocompleteSystem.input('i');
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("ans", 1);
//        map.put("hul", 1);
//        Map<String, Integer> map1 = new HashMap<>();
//        map1.put("ans", 1);
//        map1.put("hul", 2);
//        System.out.println(map.equals(map1));
    }
    private HashMap<String, Integer> map = new HashMap<>();
    private String cur_sent = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) map.put(sentences[i], times[i]);

        System.out.println(map);


    }
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] pair = new int[n];
        int res = 0;
        for (int[] p : pairs) {
            pair[p[0]] = p[1];
            pair[p[1]] = p[0];
        }

        here:
        for (int i = 0; i < n; i++) {
            for (int x : preferences[i]) {
                if (x == pair[i]) break;
                for (int x0 : preferences[x]) {
                    if (x0 == pair[x]) break;
                    if (x0 == i) {
                        res++;
                        continue here;
                    }
                }
            }
        }

        return res;
    }
    public int[] frequencySort(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<Integer, Integer>();
        for (int n : nums) {
            freq.put(n, 1 + freq.getOrDefault(n, 0));
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.<Integer, Integer>comparing(i ->
                freq.get(i)).thenComparing(i -> -i));
        for (int n : nums) {
            pq.offer(n);
        }
        int[] ans = new int[nums.length];
        for (int i = 0; !pq.isEmpty(); ++i) {
            ans[i]= pq.poll();
        }
        return ans;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            map.put(cur_sent, map.getOrDefault(cur_sent, 0) + 1);
            cur_sent = "";
        } else {
            List<Node> list = new ArrayList<>();
            cur_sent += c;
            for (String key : map.keySet())
                if (key.indexOf(cur_sent) == 0) {
                    list.add(new Node(key, map.get(key)));
                }
            Collections.sort(
                    list,
                    (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */