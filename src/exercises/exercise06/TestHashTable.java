package exercises.exercise06;

import lecture.chapter03.tests.Student;

public class TestHashTable {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        
        Student student = new Student("Volker", "Christian", "MTD0100001");
        hashTable.insert("MTD1", student);
        
        student = new Student("Volker", "Christian", "MTD0100001");
        hashTable.insert("MTD1", student);
        
        student = new Student("Albert", "Einstein", "MTD0100002");
        hashTable.insert("MTD2", student);
        
        student = new Student("Wolfgang", "Ambros", "MTD0100003");
        hashTable.insert("MTD3", student);
        
        System.out.println(hashTable.get("MTD1"));
        
        System.out.println(hashTable.get("MTD2"));
        
        System.out.println(hashTable.get("MTD3"));
        
        hashTable.remove("MTD1");
        
        System.out.println(hashTable.get("MTD1"));
        
        hashTable.remove("MTD1");
        
        System.out.println(hashTable.get("MTD1"));
    }
}
