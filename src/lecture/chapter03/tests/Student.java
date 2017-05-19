package lecture.chapter03.tests;

public class Student {	
    protected String name;
    protected String surName;
    protected String matrNr;
    
    public Student(String name, String sureName, String matrNr) {
        this.name = name;
        this.surName = sureName;
        this.matrNr = matrNr;
    }
    
    public String toString() {
        return "Name: " + name + "\nSurname: " + surName + "\nMatrNr: " + matrNr;
    }
    
    public String getName() { return name; }
    
    public String getSurName() { return surName; }
    
    public String getMatrNr() { return matrNr; }
}