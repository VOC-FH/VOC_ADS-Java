package uebung_2.tests;

import uebung_2.ue.MySList;
import kapitel_3.tests.Student;
import kapitel_3.tests.StudentKeys;
import kapitel_3.vl.IFIterator;
import kapitel_3.vl.SList;

public class TestSList {
	public static void main(String[] args) {
		SList studentList = new MySList();
		
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
