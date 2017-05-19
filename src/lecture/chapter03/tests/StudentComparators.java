package lecture.chapter03.tests;

import lecture.chapter03.IComparator;
import lecture.chapter03.IKey;

public class StudentComparators {
    public static class SurNameComparator implements IComparator {
        public int compare(Object data1, Object data2) {
            return ((Student) data1).surName.compareTo(((Student) data2).surName);
        }

        public int compare(Object data, IKey key) {
            Student student = (Student) data;
            StudentKeys.SurNameKey sKey = (StudentKeys.SurNameKey) key;
            
            return student.surName.compareTo(sKey.surName);
        }
    }
}
