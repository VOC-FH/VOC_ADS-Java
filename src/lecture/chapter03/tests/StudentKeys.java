package lecture.chapter03.tests;

import lecture.chapter03.IKey;

public class StudentKeys {
    public static class SurNameKey implements IKey {
        String surName;
        public SurNameKey(String surName) {
            this.surName = surName;
        }
        public boolean matches(Object data) {
            return surName.equals(((Student) data).surName);
        }
    }
    
    public static class MatrNrKey implements IKey {
        String matrNr;
        public MatrNrKey(String matrNr) {
            this.matrNr = matrNr;
        }
        public boolean matches(Object data) {
            return matrNr.equals(((Student) data).matrNr);
        }
    }
}
