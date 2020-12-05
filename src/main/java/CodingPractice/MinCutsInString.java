package CodingPractice;

import java.util.*;

public class MinCutsInString {

    public static void main(String[] args) {
//        String str = "156723498";
//        Set<String> set = new HashSet<>(Arrays.asList("15", "67234", "98", "567", "23498"));
//        System.out.println(numInPi(str, set));

//        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
//        System.out.println(Arrays.deepToString(flipUD(mat)));
//        System.out.println(Arrays.deepToString(rotate(mat)));
        System.out.println(longestSubarray(Arrays.asList(2,3,3,2,2,4,6,7,8), 2));
    }

    public static int longestSubarray (List<Integer> al, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int unique = 0, res = 0, end =0 , start = 0;
        int s = 0, e = 0;
        while (end < al.size()) {

            System.out.println(map.getOrDefault(map.get(al.get(end)), 0) + 1);
            System.out.println("The val for end is " + end);
           map.put(al.get(end), map.getOrDefault(al.get(end), 0) + 1);
            System.out.println(map);
           if (map.get(al.get(end)) == 1) unique += 1;
           // System.out.println(map);
           while (unique > k) {
               map.put(al.get(start), map.get(al.get(start)) - 1);
               if (map.get(al.get(start)) == 0) unique -= 1;
               start += 1;
           }
           if (res < (end - start + 1)) {
               res = end - start + 1;
               s = start;
               e = end;
           }
            end += 1;


        }
        for (int i =s;i <= e;i++) {
            System.out.println(al.get(i));
        }
        return res;
    }

    public static int[][] flipUD(int grid[][]){//flip up down
        int res[][]=new int[grid.length][grid[0].length];
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                res[r][c]=grid[grid.length-1-r][c];
            }
        }
        return res;
    }
    public static int[][] rotate(int grid[][]){//rotate right 90 degree
        int row=grid.length;
        int col=grid[0].length;
        int res[][]=new int[col][row];
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                int correspondR=c;
                int correspondC=row-r-1;
                res[correspondR][correspondC]=grid[r][c];
            }
        }
        return res;
    }

    public static int numInPi(String str, Set<String> set) {
        int minSpaces = getMinSpaces(str, set, new HashMap<>(), 0);
        return minSpaces == Integer.MAX_VALUE ? -1 : minSpaces;

    }


    public static int getMinSpaces (String pi, Set<String> set, Map<Integer, Integer> map, int idx) {

        if (idx == pi.length()) return -1;
        if (map.containsKey(idx)) return map.get(idx);
        int minSpaces = Integer.MAX_VALUE;
        for (int i = idx; i < pi.length(); i++) {
            String prefix = pi.substring(idx, i+1);
            System.out.println(prefix);
            if (set.contains(prefix)) {
                int minSpacesInSuffix = getMinSpaces(pi, set, map,i+1);
                if (minSpacesInSuffix == Integer.MAX_VALUE) minSpaces = Math.min(minSpaces, minSpacesInSuffix);
                else minSpaces = Math.min(minSpaces, minSpacesInSuffix + 1);
            }
        }
        map.put(idx, minSpaces);
        System.out.println(map);
        return map.get(idx);
    }
}
