package CodingPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion {

    public static void main(String[] args) {
      //  System.out.println(Arrays.toString(expand("{a,b}c{d,e}f")));
        System.out.println(~7);

    }

    public static String[] expand(String s) {
        List<String> list = new ArrayList<>();
        int n = s.length();
        for (int i = 0;i < n;i++) {
            if (s.charAt(i) == '{') {
                int j = i + 1;
                StringBuilder sb = new StringBuilder();
                while (j < n && s.charAt(j) != '}') {
                    if (s.charAt(j) == ',') {
                        j++;
                        continue;
                    }
                    sb.append(s.charAt(j));
                    j++;
                }
                list.add(sb.toString());
                i = j;
            }
            else {
                list.add(s.charAt(i) + "");
            }
        }
        System.out.println(list);
        List<String> res = new ArrayList<>();
        dfs(list, res, new StringBuilder(), 0);
        int index  = 0;
        String[] result = new String[res.size()];
        for (String s1: res){
            result[index++] = s1;
        }
        Arrays.sort(result);
        return result;

    }

    private static void dfs(List<String> list, List<String> res, StringBuilder sb, int pos) {
        if (list.size()== sb.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c: list.get(pos).toCharArray()) {
            sb.append(c);
            dfs(list, res, sb, pos + 1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
