package exercises.exercise02.tests;

import exercises.exercise02.SList;
import exercises.exercise02.TelephonebookEntry;
import exercises.exercise02.TelephonebookEntryKey;
import lecture.chapter03.IFIterator;
import lecture.chapter03.tests.Student;
import lecture.chapter03.tests.StudentKeys;

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
        
        StudentKeys.MatrNrKey matrNrKey = new StudentKeys.MatrNrKey("MTD0100003");
        student = (Student) studentList.search(matrNrKey);
        if (student != null) { 
            System.out.println(student);
            studentList.remove(student);
        }
        
        IFIterator it = studentList.iterator();
        while(it.hasNext()) {
            Student s = (Student) it.next();
            System.out.println("Student:");
            System.out.println(s);
        }
        
        SList telephoneBook = new SList();
        TelephonebookEntry te = new TelephonebookEntry("Volker", "Christian", "Linz");
        te.addTelnumber("+436764118959");
        te.addTelnumber("+43664737733");
        telephoneBook.prepend(te);
        
        TelephonebookEntryKey tek = new TelephonebookEntryKey("Volker", "Christian");
        TelephonebookEntry se = (TelephonebookEntry) telephoneBook.search(tek);
        
        System.out.println(se);
    }
}
