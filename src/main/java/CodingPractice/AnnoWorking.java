package CodingPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnoWorking {


    public static void main(String[] args) {
//        AnnoWorking annoWorking = new AnnoWorking();
//        annoWorking.show();
//        annoWorking = new AnnoWorking1();
//        annoWorking.show();
        List<String> al = new ArrayList<>();
        al.add("oiu");
        al.add("abhbh");
        al.add("wer");
        Collections.sort(al, (a,b) -> a.length() == b.length() ? a.compareTo(b):b.length()-a.length());
        System.out.println(al);

    }

    public void show() {
        System.out.println("3");
    }

}
 class AnnoWorking1 extends AnnoWorking{



     @Override
     public void show() {
         super.show();
         System.out.println("2");
     }

}
