package Momemto;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()).getClass().getName());
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(1);
        l.add(1);
        Iterator<Integer> ll = l.iterator();
        Originator originator = new Originator();

        Caretaker careTaker = new Caretaker();

        originator.setState("State #1");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(2));
        System.out.println("Third saved State: " + originator.getState());
    }

}
class Memento {
    private String state;
    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

class Originator {
    private String state;
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public Memento saveStateToMemento() {
        return new Memento(state);
    }
    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}
class Caretaker {

    private List<Memento> mementoList = new LinkedList<>();

    public void add(Memento state){
        mementoList.add(0, state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }

}

