package CodingPractice;

import java.util.HashMap;
import java.util.Map;

class EvenVowels {

    public static void main(String[] args) {
        System.out.println(findTheLongestSubstring("eleetminicoworoep"));
    }

        public static boolean check(char ch) {
            return (ch == 'a' || ch == 'e' || ch == 'i' | ch == 'o' || ch == 'u');
        }
        private static boolean isVowelEven( Map<Character, Integer> map)
        {
            if( map.get('a') % 2 ==0 && map.get('e') %2 ==0 && map.get('i')%2 ==0 && map.get('o')%2 ==0 && map.get('u')%2==0)
                return(true);
            return(false);
        }
        public static int findTheLongestSubstring(String s) {
            int max_len =0;
            int [] map = new int[26]; // Global map
            Map<Character, Integer> hm = new HashMap<>();
            hm.put('a', 0);
            hm.put('e', 0);
            hm.put('i', 0);
            hm.put('o', 0);
            hm.put('u', 0);
// Sliding Window- substring
            int i=0;
            for( int j=0; j < s.length(); j++)
            {
                char c2 = s.charAt(j); // new Char coming in, if it is Vowels, incrase count in map
                if(check(c2))
                    hm.put(c2, hm.get(c2) + 1);


                //int [] temp = map.clone(); // local map  , copy global map and check if remove of left side vowel can satisfy condition
                Map<Character, Integer> temp = new HashMap<>(hm);
                while(isVowelEven(temp) == false && i <= j ) // if Vowels count is Odd ,
                {
                    char c1 = s.charAt(i);  // Left behind( previous) char if it is Vowels,  decrase it's counts  and see if it match
                    if(check(c1))
                        temp.put(c1, temp.get(c1) - 1);
                    i++;
                }
                // if map contains even number of vowel , calculate len of substring  and update max_len if applicable
                max_len = Math.max(j-i+1,max_len);  // Update new len if it is max
            }
            return(max_len);
        }
    }