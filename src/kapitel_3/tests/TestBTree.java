package kapitel_3.tests;

import kapitel_3.vl.IComparator;
import kapitel_3.vl.IFIterator;
import kapitel_3.vl.IKey;
import kapitel_3.vl.SearchTree;

public class TestBTree {
	public static void main(String[] args) {
		IComparator surNameComparator = new StudentComparators.SurNameComparator();
		
		SearchTree studentTree = new SearchTree(surNameComparator);

		Student student = new Student("Volker", "Christian", "MTD0100001");
		studentTree.insert(student);
		
		student = new Student("Albert", "Einstein", "MTD0100002");
		studentTree.insert(student);
		
		student = new Student("Wolfgang", "Ambros", "MTD0100003");
		studentTree.insert(student);
		
		IKey surNameKey = new StudentKeys.SurNameKey("Ambros");
		Object oStudent = studentTree.binarySearch(surNameKey);
		
		if (oStudent != null) {
			System.out.println("Binary-Search-Test");
			System.out.println("Student: " + (Student) oStudent);
		}
		
		oStudent = studentTree.breadthFirstSearch(surNameKey);
	
		if (oStudent != null) {
			System.out.println("\nBreadth-First-Search-Test");
			System.out.println("Student: " + (Student) oStudent);
		}	
		
		oStudent = studentTree.depthFirstPreOrderSearch(surNameKey);
	
		if (oStudent != null) {
			System.out.println("\nDepth-First-Search-PreOrder-Test");
			System.out.println("Student: " + (Student) oStudent);
		}

		System.out.println("\nBreadth-First-Test");
		studentTree.breadthFirst(new PrintWorker());

		System.out.println("\nDepth-First-PreOrder-Test");
		studentTree.depthFirstPreOrder(new PrintWorker());

		System.out.println("\nDepth-First-InOrder-Test");
		studentTree.depthFirstInOrder(new PrintWorker());
		
		System.out.println("\nDepth-First-PostOrder-Test");
		studentTree.depthFirstPostOrder(new PrintWorker());
		
		System.out.println("\nIterator-Test");
		IFIterator it = studentTree.iterator();
		
		while(it.hasNext()) {
			student = (Student) it.next();
			System.out.println(student);
		}
	}
}
