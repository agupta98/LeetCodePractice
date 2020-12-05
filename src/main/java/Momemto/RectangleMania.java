package Momemto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// [0,0],[0,1],[1,1],[1,0],[2,1],[2,0],[3,1],[3,0]

public class RectangleMania {

    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,1);
        Point p3 = new Point(1,1);
        Point p4 = new Point(1,0);
        Point p5 = new Point(2,1);
        Point p6 = new Point(2,0);
        Point p7 = new Point(3,1);
        Point p8 = new Point(3,0);
        System.out.println(rectangleMania(new Point[]{p1,p2,p3,p4,p5,p6,p7,p8}));
    }

    public List<String> fullJustify(String[] words, int maxLen) {
        List<String> ans = new ArrayList<>();
        List<String> tempList = new ArrayList<>();  //to store the words that can be put in one same line
        int tempLen = 0;     // track the length of current line: wordsLen + spaces between words
        int index = 0;      //index of word in words array
        int wordsLen;   // sum of all words that can be put in same line
        int spaces;     // sapces that need to be filled

        while(index < words.length) {
            if((tempLen + words[index].length()) <= maxLen) {   //check if cur word can be put in cur line
                tempLen += words[index].length() + 1;
                tempList.add(words[index++]);
            }else {
                StringBuilder sb = new StringBuilder();
                wordsLen = tempLen - tempList.size();
                spaces = maxLen - wordsLen;
                if(tempList.size() == 1) {          // there's only one word at cur line, then just fill the spaces
                    sb.append(tempList.get(0));
                    for(int i = 0; i < spaces; i++) {
                        sb.append(" ");
                    }
                    ans.add(sb.toString());
                }else if(tempList.size() > 1) {      // there's many words in cur line, do the evenly spaceing
                    int distance = spaces / (tempList.size() - 1);
                    int reminder = spaces % (tempList.size() - 1);

                    for(int i = 0; i < tempList.size() - 1; i++) {
                        sb.append(tempList.get(i));
                        if(reminder > 0){
                            sb.append(" ");
                            reminder--;
                        }
                        for(int j = 0; j < distance; j++)
                            sb.append(" ");
                    }
                    sb.append(tempList.get(tempList.size() - 1));
                    ans.add(sb.toString());
                }
                tempList.clear();
                tempLen = 0;
            }
        }

        // dealing with last line
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tempList.size() - 1; i++) {
            sb.append(tempList.get(i));
            sb.append(" ");
        }
        sb.append(tempList.get(tempList.size() - 1));
        for(int i = 0; i < maxLen - (tempLen - 1); i++)
            sb.append(" ");
        ans.add(sb.toString());
        return ans;

    }

    public static int rectangleMania(Point[] coords) {
        Set<String> coordsTable = getCoordsTable(coords);
        System.out.println(coordsTable);
        return rectangleCount(coords, coordsTable);
    }

    public static int rectangleCount(Point[] coords, Set<String> coordsTable) {
        int rectangleCount = 0;
        for (Point coord1: coords) {
            for (Point coord2: coords) {
                if (!isUpperRight(coord1, coord2)) continue;
                System.out.println(coord1+"-- "+coord2);
                String upperCoordString = coordsToString(new Point(coord1.x, coord2.y));
                String rightCoordsString = coordsToString(new Point(coord2.x, coord1.y));
                if (coordsTable.contains(upperCoordString) && coordsTable.contains(rightCoordsString)) rectangleCount++;
            }
        }
        return rectangleCount;
    }

    public static Set<String> getCoordsTable(Point[] coords) {
        Set<String> coordsTable = new HashSet<>();
        for (Point coord: coords) {
            String str = coordsToString(coord);
            coordsTable.add(str);
        }
        return coordsTable;
    }

    private static boolean isUpperRight (Point coord1, Point coord2) {  //
        return coord2.x > coord1.x && coord2.y > coord1.y;
    }
   private static String coordsToString(Point coord) {
        return coord.x + "-" + coord.y;
   }

    static class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


}
