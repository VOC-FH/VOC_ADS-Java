package exercises.exercise02.junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import exercises.exercise02.SList;
import exercises.exercise02.TelephonebookEntry;
import exercises.exercise02.TelephonebookEntryKey;
import lecture.chapter03.IFIterator;
import lecture.chapter03.tests.Student;
import lecture.chapter03.tests.StudentKeys;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UE2SlistTest {
    static SList studentList;
    static Student[] students;
    static int einstein;
    static int counter;

    @BeforeClass
    public static void initObjects() {
        counter = 0;

        studentList = new SList();

        students = new Student[3];
    }

    @Test
    public void test1_Append() {
        students[counter] = new Student("Volker", "Christian", "MTD0100001");
        studentList.append(students[counter]);
        counter++;

        students[counter] = new Student("Albert", "Einstein", "MTD0100002");
        studentList.append(students[counter]);
        einstein = counter;
        counter++;

        students[counter] = new Student("Wolfgang", "Ambros", "MTD0100003");
        studentList.append(students[counter]);
        
        IFIterator it = studentList.iterator();

        while (it.hasNext()) {
            assertSame("should be same", it.next(), students[2 - counter--]);
        }

        assertEquals("should be -1", -1, counter);
    }

    @Test
    public void test2_Insert() {
        Student newStudent = new Student("Max", "Einstein", "MTD100004");
        StudentKeys.SurNameKey key = new StudentKeys.SurNameKey("Einstein");
        studentList.insert(key, newStudent);
        
        IFIterator it = studentList.iterator();
        
        it.next();
        it.next();
        assertSame("should be same", it.next(), newStudent);
    }

    @Test
    public void test3_SearchAll() {
        StudentKeys.SurNameKey key = new StudentKeys.SurNameKey("Einstein");
        SList foundStudents = studentList.searchAll(key);
        
        IFIterator it = foundStudents.iterator();
        
        Student s1 = (Student) it.next();
        Student s2 = (Student) it.next();
        
        
        assertFalse("should be false", it.hasNext());
        assertNotSame("should not be same", s1, s2);
        assertTrue("should be true", s1.getName() == "Max" || s1.getName() == "Albert");
        assertTrue("should be true", s2.getName() == "Max" || s2.getName() == "Albert");
    }
    
    @Test
    public void test4_TelephoebookEntry() {
        SList telephoneBook = new SList();
        
        TelephonebookEntry te = new TelephonebookEntry("Volker", "Christian", "Linz");
        te.addTelnumber("+436764118959");
        te.addTelnumber("+43664737733");
        telephoneBook.prepend(te);
        
        TelephonebookEntryKey tek = new TelephonebookEntryKey("Volker", "Christian");
        TelephonebookEntry se = (TelephonebookEntry) telephoneBook.search(tek);
        
        assertSame("should be same", te, se);
    }
}
