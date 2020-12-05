package CodingPractice;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MinimumPartitionSum
{


    // Partition the set S into two subsets S1, S2 such that the
    // difference between the sum of elements in S1 and the sum
    // of elements in S2 is minimized
    public static int minVal = 999999999;
    static List<List<Integer>> al3 = null;
    public static int minPartition(int[] S, int n, int S1, int S2,
                                   Map<String, Integer> lookup,List<Integer> al1, List<Integer> al2)
    {
        // base case: if list becomes empty, return the absolute
        // difference between two sets
        if (n < 0) {
            if (minVal > Math.abs(S1 - S2)) {
                al3 = new ArrayList<>();
                al3.add(new ArrayList<>(al1));
                al3.add(new ArrayList<>(al2));
                minVal = Math.abs(S1 - S2);
                System.out.println(minVal);
                System.out.println(al3);
            }

            return Math.abs(S1 - S2);
        }

        // construct an unique map key from dynamic elements of the input
        // Note that can uniquely identify the sub-problem with n and S1 only,
        // as S2 is nothing but S - S1 where S is sum of all elements
        String key = n + "|" + S1;

        // if sub-problem is seen for the first time, solve it and
        // store its result in a map
        if (!lookup.containsKey(key))
        {
            // Case 1. include current item in the subset S1 and recur
            // for remaining items (n - 1)
            al1.add(S[n]);
            int inc = minPartition(S, n - 1, S1 + S[n], S2, lookup,al1,al2);
            al1.remove(al1.size()-1);
            // Case 2. exclude current item from subset S1 and recur for
            // remaining items (n - 1)
            al2.add(S[n]);
            int exc = minPartition(S, n - 1, S1, S2 + S[n], lookup,al1,al2);
            al2.remove(al2.size()-1);
            lookup.put(key, Integer.min(inc, exc));
        }
        System.out.println(lookup);
        System.out.println(key);
        return lookup.get(key);
    }

    public static void main(String[] args)
    {

    //    System.out.println(Integer.min());
        // Input: set of items
        int[] S = { 10, 20, 15, 5, 25 };

        // create a map to store solutions of subproblems
        Map<String, Integer> lookup = new HashMap<>();
        List<Integer> al1 = new ArrayList<>();
        List<Integer> al2 = new ArrayList<>();
        List<Integer> al4 = new ArrayList<>();
        int minVal = Integer.MAX_VALUE;
        System.out.println("The minimum difference is "
                + minPartition(S, S.length - 1, 0, 0, lookup, al1, al2));
        System.out.println(al3);
        //System.out.println(al2);
    }
}
