package lecture.chapter03.tests;

import lecture.chapter03.IKey;

public class TestStudent {
    public static void main(String[] args) {
        Student s1 = new Student("Volker", "Christian", "MTD0100001");
        Student s2 = new Student("Albert", "Einstein", "MTD0100002");
        
        IKey surNameKey = new StudentKeys.SurNameKey("Einstein");
        
        if (surNameKey.matches(s1)) {
            System.out.println("Student s1 is Albert Einstein");
            System.out.println(s1);
        } else {
            System.out.println("Student s1 is not Albert Einstein");
        }
        
        if (surNameKey.matches(s2)) {
            System.out.println("Student s2 is Albert Einstein");
            System.out.println(s2);
        } else {
            System.out.println("Student s2 is not Albert Einstein");
        }
        
        IKey matrNrKey = new StudentKeys.MatrNrKey("MTD0100001");
        
        if (matrNrKey.matches(s1)) {
            System.out.println("Student s1 has MatrNr MTD0100001:\nStudent: " + s1);
        } else {
            System.out.println("Student s1 didn't have MatrNr MTD0100001");
        }
        
        if (matrNrKey.matches(s2)) {
            System.out.println("Student s2 has MatrNr MTD0100001:\nStudent: " + s2);
        } else {
            System.out.println("Student s2 didn't have MatrNr MTD0100001");
        }
    }
}
