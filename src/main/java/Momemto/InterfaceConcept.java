package Momemto;

import java.util.Arrays;

public class InterfaceConcept {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(arr.clone()));
        A a = new C();
        a.method1();
        B b = new C();

        b.method2();
    }
}
//interface A{
//     void method1();
//}
//interface B extends A{
//     void method2();
//}
//class C implements B{
//    @Override public void method1(){
//        System.out.println("method1");
//    }
//    @Override public void method2(){
//        System.out.println("method2");
//    }
//}
interface A{
     void method1();
}
interface B{
     void method2();
}
class C implements A, B{
    @Override public void method1(){}
    @Override public void method2(){}
}
