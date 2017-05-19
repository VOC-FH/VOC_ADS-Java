package lecture.chapter03.tests;

import lecture.chapter03.DList;

public class TestDList {
    public static void main(String[] args) {
        DList studentList = new DList();
        
        Student student = new Student("Volker", "Christian", "MTD0100001");
        studentList.prepend(student);
        
        student = new Student("Albert", "Einstein", "MTD0100002");
        studentList.append(student);
        
        student = new Student("Wolfgang", "Ambros", "MTD0100003");
        studentList.prepend(student);
        
        StudentKeys.SurNameKey nameKey = new StudentKeys.SurNameKey("Einstein");
        student = (Student) studentList.forwardSearch(nameKey);
        if (student != null) {
            System.out.println(student); 
        }
        
        StudentKeys.MatrNrKey matrNrKey = new StudentKeys.MatrNrKey("MTD0100002");
        student = (Student) studentList.reverseSearch(matrNrKey);
        if (student != null) { 
            System.out.println(student);
            studentList.forwardRemove(student);
        }
        
        matrNrKey = new StudentKeys.MatrNrKey("MTD0100003");
        student = (Student) studentList.forwardSearch(matrNrKey);
        if (student != null) { 
            System.out.println(student);
        }
    }
}
