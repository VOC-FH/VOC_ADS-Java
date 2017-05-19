package lecture.chapter03.tests;

import lecture.chapter03.SList;

public class TestSList {
    public static void main(String[] args) {
        SList studentList = new SList();
        
        Student student = new Student("Volker", "Christian", "MTD0100001");
        studentList.prepend(student);
        
        student = new Student("Albert", "Einstein", "MTD0100002");
        studentList.prepend(student);
        
        student = new Student("Wolfgang", "Ambros", "MTD0100003");
        studentList.prepend(student);
        
        StudentKeys.SurNameKey nameKey = new StudentKeys.SurNameKey("Einstein");
        student = (Student) studentList.search(nameKey);
        if (student != null) {
            System.out.println(student); 
        }
        
        StudentKeys.MatrNrKey matrNrKey = new StudentKeys.MatrNrKey("MTD0100002");
        student = (Student) studentList.search(matrNrKey);
        if (student != null) { 
            System.out.println(student);
            studentList.remove(student);
        }
        
        matrNrKey = new StudentKeys.MatrNrKey("MTD0100003");
        student = (Student) studentList.search(matrNrKey);
        if (student != null) { 
            System.out.println(student);
        }
    }
}
