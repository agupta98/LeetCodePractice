package CodingPractice;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class PQObject {



    public static void main(String[] args) {
        Student s = new Student("1","Anshul", 31);
        Student s1 = new Student("2","Hari", 25);
        Student s2 = new Student("3","Om", 28);
        PriorityQueue<Student> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.age));
        pq.add(s);
        pq.add(s1);
        pq.add(s2);
        System.out.println(pq);
        Student s3 = new Student("3","Om", 24);
        if (pq.contains(s3)) {
            pq.remove(s3);
            pq.add(s3);
        }
        System.out.println(pq);

    }




}

class Student {
    String id;
    String name;
    int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
