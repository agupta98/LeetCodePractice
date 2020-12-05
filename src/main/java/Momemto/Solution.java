package Momemto;

import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval() {}

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};


class Solution {
    static class InfoMatrixItem {
        public int numOfZeroesBelow;

        public InfoMatrixItem(int numOfZeroesBelow, int numOfZeroesRight) {
            this.numOfZeroesBelow = numOfZeroesBelow;
            this.numOfZeroesRight = numOfZeroesRight;
        }

        @Override
        public String toString() {
            return "InfoMatrixItem{" +
                    "numOfZeroesBelow=" + numOfZeroesBelow +
                    ", numOfZeroesRight=" + numOfZeroesRight +
                    '}';
        }

        public int numOfZeroesRight;
    }

    public static List<List<InfoMatrixItem>> preCompute(List<List<Integer>> mat) {
        List<List<InfoMatrixItem>> dp = new ArrayList<>();
        for (int i = 0;i< mat.size();i++) {
            List<InfoMatrixItem> inner = new ArrayList<>();
            for (int j = 0; j< mat.get(i).size();j++) {
                int numOfZeroes = mat.get(i).get(j) == 0 ? 1:0;
                inner.add(new InfoMatrixItem(numOfZeroes, numOfZeroes));
            }
            dp.add(inner);
        }
        int lastIdx = mat.size() - 1;
        for (int row = lastIdx; row >=0; row--) {
            for (int col = lastIdx;col >=0; col--) {
                if (mat.get(row).get(col) == 1) continue;
                if (row < lastIdx) dp.get(row).get(col).numOfZeroesBelow += dp.get(row + 1).get(col).numOfZeroesBelow;
                if (col < lastIdx) dp.get(row).get(col).numOfZeroesRight += dp.get(row).get(col + 1).numOfZeroesRight;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        List<Integer> row1 = Arrays.asList(0,0,0,0);
        List<Integer> row2 = Arrays.asList(1,1,1,0);
        List<Integer> row3 = Arrays.asList(1,1,1,0);
        List<Integer> row4 = Arrays.asList(1,1,1,0);
        List<List<Integer>> al = new ArrayList<>();
        al.add(row1);
        al.add(row2);
        al.add(row3);
        al.add(row4);
        System.out.println(preCompute(al));
        try {
            System.out.println(positiveNum(-3));
        }
        catch (Exception e) {

        }
        System.out.println("hello");
    }

    public static boolean squaresOfMatrix(List<List<Integer>> matrix) {
        int n = matrix.size();
        for (int row = 0; row < n;row++) {
            for (int col = 0;col < n;col++) {
                int sqlength = 2;
                while (sqlength <= n - col && sqlength <= n - row) {
                    int nr = row + sqlength - 1;
                    int nc = col + sqlength - 1;
                    if (isSquareOfZeroes(matrix, row,col, nr, nc)) return true;
                    sqlength++;
                }
            }
        }
        return false;
    }

    public static boolean isSquareOfZeroes(List<List<Integer>> matrix, int r1,int c1, int r2, int c2) {
        for (int row = r1; row < r2+1;row++) {
            if (matrix.get(row).get(c1) !=0 || matrix.get(row).get(c2) !=0) return false;
        }
        for (int col = c1; col < c2 + 1; col++) {
            if (matrix.get(r1).get(col) != 0 && matrix.get(r2).get(col) !=0) return false;
        }
        return true;
    }

    public static int positiveNum(int num) {
        if (num < 0) throw new IllegalArgumentException("The num is negative");
        return num;
    }
    public List<Interval> employeeFreeTime (List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        avails.forEach(e -> timeLine.addAll(e));
        System.out.println(avails);
        Collections.sort(timeLine, (Comparator.comparingInt(a -> a.start)));
        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            if(temp.end < each.start) {
                result.add(new Interval(temp.end, each.start));
                temp = each;
            }else{
                temp = temp.end < each.end ? each : temp;
            }
        }
        return result;
    }
}