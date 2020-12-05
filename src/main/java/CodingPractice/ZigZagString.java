package CodingPractice;

public class ZigZagString {

    public static void main(String[] args) {
        System.out.println(convertZigZag("PAYPALISHIRING", 4));
    }

    public static String convertZigZag(String str, int numRows) {
        int n = str.length();
        StringBuilder[] sb =new StringBuilder[numRows];
        for (int i = 0;i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        int row = 0, delta = -1;
        for (char c: str.toCharArray()) {
            sb[row].append(c);
            if (row == 0 || row == numRows - 1) delta *= -1;
            row += delta;
        }
        String result = "";
        for (StringBuilder sbs: sb) {
            result += sbs.toString();
        }
        return result;
    }
}
