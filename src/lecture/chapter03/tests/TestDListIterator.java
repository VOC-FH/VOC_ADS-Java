package lecture.chapter03.tests;

import lecture.chapter03.DList;
import lecture.chapter03.IBIterator;

public class TestDListIterator {
    public static void main(String[] args) {
        DList studentList = new DList();
        
        studentList.prepend(new Student("Volker", "Christian", "MTD0100001"));
        
        studentList.append(new Student("Albert", "Einstein", "MTD0100002"));
        
        studentList.prepend(new Student("Wolfgang", "Ambros", "MTD0100003"));
        
        IBIterator it = IBIterator.bidirectionalIterator(studentList.rIterator());
        
        while(it.hasPrevious()) {
            Student s = (Student) it.previous();  // Fetch the next dataset
            System.out.println(s);  		      // Do something with the dataset
        }

        while(it.hasNext()) {
            Student s = (Student) it.next();  // Fetch the next dataset
            System.out.println(s);  		  // Do something with the dataset
        }
    }
}
