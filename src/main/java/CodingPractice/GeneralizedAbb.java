package CodingPractice;


import java.util.ArrayList;
import java.util.List;
// 010 & 011
public class GeneralizedAbb {
    public static void main(String[] args) {
        System.out.println(1 & 2 & 3 & 4);
        System.out.println(1 & 7);
   //     System.out.println(Integer.bitCount(7));
    //    System.out.println(generateAbbreviations("word"));

    }
    public static List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        int n = word.length();
        for(int mask = 0;mask < (1 << n);mask++) {
            int count = 0;
            StringBuffer sb = new StringBuffer();
            for(int i = 0;i < n;i++) {
                if(((1 << i) & mask) > 0) { // to check whether bit is set or not
                    count++;
                } else {
                    if(count != 0) {
                        sb.append(count);
                        count = 0;
                    }
                     sb.append(word.charAt(i));
                }
            }
            if (count > 0) sb.append(count);
             System.out.println(sb.toString());
            ret.add(sb.toString());
        }
        return ret;
    }
}

//class Solution {
//    public :
//    vector < string > generateAbbreviations( string word) {
//        vector < string > res;
//        for ( int i = 0 ; i <pow( 2 , word.size()); ++ i) {
//            string  out = "" ;
//            int cnt = 0 ;
//            for ( int j = 0 ; j <word.size(); ++ j) {
//                if ((i >> j) & 1 ) ++ cnt;
//                else {
//                    if (cnt != 0 ) {
//                        out += to_string(cnt);
//                        cnt = 0 ;
//                    }
//                    out += word[j];
//                }
//            }
//            if (cnt> 0 ) out += to_string(cnt);
//            res.push_back( out );
//        }
//        return res;
//    }
//};

