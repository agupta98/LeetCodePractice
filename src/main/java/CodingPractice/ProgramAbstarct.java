package CodingPractice;

public class ProgramAbstarct extends PA {
    @Override
    void ride() {
        System.out.println("Riding");
    }
    public void show() {
      //  super.show();
        System.out.println("Showing Child");
    }

    public static void main(String[] args) {
        ProgramAbstarct pa = new ProgramAbstarct();
        pa.ride();
        pa.show();
        System.out.println("*********************************************************");
        PA p = new PA();
        p.ride();
        p.show();
    }

}


 class PA {
    public void show() {
        System.out.println("Showing Parent");
    }

      void ride() {
          System.out.println("Showing Riding");
      }
}
