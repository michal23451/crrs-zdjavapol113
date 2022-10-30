package pl.sdaacademy.conferenceroomreservationsystem.test;

import java.util.Arrays;
import java.util.List;

public class Student implements Cloneable {
    private String name;
    private String lastName;
    private int age;
    private Course course;

    public Student(String name, String lastName, int age, Course course) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", course=" + course +
                '}';
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student studentClone = (Student) super.clone();//shallow copy
        Course courseCopy = (Course) this.getCourse().clone();//shallow copy course = deepCopy
        studentClone.setCourse(courseCopy);//zmieniamy kurs na skopiowany
        return studentClone;
    }

    public static void main(String[] args) {
        Course course = new Course("Math", "Biology", "History");
        Student student = new Student("Jan", "Nowak", 30, course);

        try {
            Student studentCopy = (Student) student.clone();
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change age
            System.out.println("Change age: ");
            studentCopy.setAge(35);
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change name
            System.out.println("Change name: ");
            studentCopy.setName("Wacław");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change course
            System.out.println("Change course: ");
            studentCopy.getCourse().setSubject1("Art");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Course implements Cloneable {
    private String subject1;
    private String subject2;
    private String subject3;

    public Course(String subject1, String subject2, String subject3) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Course{" +
                "subject1='" + subject1 + '\'' +
                ", subject2='" + subject2 + '\'' +
                ", subject3='" + subject3 + '\'' +
                '}';
    }
}



/*
* //Shallow copy:
public class Student implements Cloneable {
    private String name;
    private String lastName;
    private int age;
    private Course course;

    public Student(String name, String lastName, int age, Course course) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", course=" + course +
                '}';
    }

    public static void main(String[] args) {
        Course course = new Course("Math", "Biology", "History");
        Student student = new Student("Jan", "Nowak", 30, course);

        try {
            Student studentCopy = (Student) student.clone();
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change age
            System.out.println("Change age: ");
            studentCopy.setAge(35);
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change name
            System.out.println("Change name: ");
            studentCopy.setName("Wacław");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change course
            System.out.println("Change course: ");
            studentCopy.getCourse().setSubject1("Art");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Course {
    private String subject1;
    private String subject2;
    private String subject3;

    public Course(String subject1, String subject2, String subject3) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    @Override
    public String toString() {
        return "Course{" +
                "subject1='" + subject1 + '\'' +
                ", subject2='" + subject2 + '\'' +
                ", subject3='" + subject3 + '\'' +
                '}';
    }
}
 *
*
* //Deep copy:`
package pl.sdaacademy.conferenceroomreservationsystem.test;

public class Student implements Cloneable {
    private String name;
    private String lastName;
    private int age;
    private Course course;

    public Student(String name, String lastName, int age, Course course) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", course=" + course +
                '}';
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student studentClone = (Student) super.clone();//shallow copy
        Course courseCopy = (Course) this.getCourse().clone();//shallow copy course = deepCopy
        studentClone.setCourse(courseCopy);//zmieniamy kurs na skopiowany
        return studentClone;
    }

    public static void main(String[] args) {
        Course course = new Course("Math", "Biology", "History");
        Student student = new Student("Jan", "Nowak", 30, course);

        try {
            Student studentCopy = (Student) student.clone();
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change age
            System.out.println("Change age: ");
            studentCopy.setAge(35);
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change name
            System.out.println("Change name: ");
            studentCopy.setName("Wacław");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);

            //change course
            System.out.println("Change course: ");
            studentCopy.getCourse().setSubject1("Art");
            System.out.println("Student: " + student);
            System.out.println("Student copy: " + studentCopy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Course implements Cloneable {
    private String subject1;
    private String subject2;
    private String subject3;

    public Course(String subject1, String subject2, String subject3) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Course{" +
                "subject1='" + subject1 + '\'' +
                ", subject2='" + subject2 + '\'' +
                ", subject3='" + subject3 + '\'' +
                '}';
    }
}
*
*
* */