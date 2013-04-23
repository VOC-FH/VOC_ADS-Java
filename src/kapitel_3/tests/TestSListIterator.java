package kapitel_3.tests;

import kapitel_3.vl.IFIterator;
import kapitel_3.vl.SList;

public class TestSListIterator {
	public static void main(String[] args) {
		SList studentList = new SList();
		
		Student student = new Student("Volker", "Christian", "MTD0100001");
		studentList.prepend(student);
		
		student = new Student("Albert", "Einstein", "MTD0100002");
		studentList.prepend(student);
		
		student = new Student("Wolfgang", "Ambros", "MTD0100003");
		studentList.prepend(student);
		
		// Create an Iterator associated with the studentList
		IFIterator it = studentList.iterator();
		
		// Loop as long as there are additional datasets
		while(it.hasNext()) {
			Student s = (Student) it.next();  // Fetch the next dataset
			System.out.println(s);  		  // Do something with the dataset
		}
	}
}
