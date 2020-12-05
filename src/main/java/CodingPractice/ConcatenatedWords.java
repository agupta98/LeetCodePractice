package CodingPractice;

import java.util.*;

public class ConcatenatedWords {

    public static void main(String[] args) {
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(findAll(words));
    }

    public static List<String> findAll(String[] words) {

        List<String> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        for (String word: words) {
            if (canForm(word, dict)) result.add(word);
         }
        return result;

    }

    public static Boolean canForm(String word, Set<String> s) {
        for (int i = 1; i < word.length(); i++) {
            String left = word.substring(0, i);
            String right = word.substring(i);
            if (s.contains(left)) {
                if (s.contains(right) || canForm(right, s)) return true;
            }
        }
        return false;
    }
}
