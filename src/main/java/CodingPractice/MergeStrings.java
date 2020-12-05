package CodingPractice;

public class MergeStrings {
    public static void main(String[] args) {
        System.out.println(merge("catg", "atgcatc"));
        System.out.println(merge("cacd", "facd"));

    }

    private static String merge(String head, String tail) {
        int i = 0, j = 0;
        while (i < head.length()) {
            if (j < tail.length() && head.charAt(i) == tail.charAt(j)) {
                i++;
                j++;
            } else if (j >= tail.length() && j != 0) {
                j = 0;
            } else if (j != 0) {
                j = 0;
            } else {
                i++;
            }
        }

        return head + tail.substring(j);
    }
}
