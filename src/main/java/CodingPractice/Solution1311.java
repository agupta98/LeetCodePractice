package CodingPractice;

import java.util.*;

class Solution1311 {
    public static void main(String[] args) {
        int sign = Integer.compare(9,9);
        System.out.println(sign);

        List<List<Integer>> al = new ArrayList<>();
        al.add(Arrays.asList(1,2,3));
        System.out.println(al);
        List<List<Integer>> al1 = new ArrayList<>();
        al1.addAll(al);

    }
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        boolean[] visited = new boolean[n];
        visited[id] = true;
        while (level-- > 0) {
            int size = q.size();
            while (size-- > 0) {
                for (int f : friends[q.poll()]) {
                    if (!visited[f]) {
                        visited[f] = true;
                        q.offer(f);
                    }
                }
            }
        }
        System.out.println(q);
        HashMap<String, Integer> freq = new HashMap<>();
        while (!q.isEmpty()) {
            for (String s : watchedVideos.get(q.poll()))
                freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        System.out.println(freq);
        List<String> ans = new ArrayList<>(freq.keySet());
        System.out.println(ans);
        ans.sort((s1, s2) -> {
            int f1 = freq.get(s1), f2 = freq.get(s2);
            if (f1 == f2) return s1.compareTo(s2);
            return f1 - f2;
        });
        return ans;
    }
}
