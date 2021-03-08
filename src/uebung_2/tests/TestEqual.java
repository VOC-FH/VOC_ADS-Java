package uebung_2.tests;

public class TestEqual {
    static class A {
        public String a;
        public int b;
    }

    static class B {
        public String a;
        public int b;
        
        boolean equals(B c) {
            return a.equals(c.a) && b == c.b;
        }
    }
 
    public static void main(String[] args) {
        A a = new A();
        a.a = "Hallo";
        a.b = 10;
        
        A b = a; // a und b referenzieren das SELBE Objekt
        
        System.out.println("Compare 1: With == " + (b == a));
        System.out.println("Compare 2: With .equals() " + a.equals(b));
         
        b = new A();   // a und b referenzieren unterschiedliche A-Objekte
        b.a = "Hallo"; // die den selben Inhalt haben
        b.b = 10;
        
        System.out.println("Compare 3: With == " + (b == a));
        System.out.println("Compare 4: With .equals() " + a.equals(b));
        
        
        String c = "Hallo"; // Auch hier: c und d referenzieren den selben String "Hallo"
        String d = "Hallo"; //            klarer Weise dann auch mit selben Inhalten
                            // Der Java-Compiler optimiert hier und erzeugt nur eine
                            // String-Konstante "Hallo"

        System.out.println("Compare 5: With == " + (c == d));
        System.out.println("Compare 6: With .equals() " + c.equals(d));
        
        String e1 = "Hal";
        String e2 = "lo";
        String e = e1 + e2; // c und e referenzieren unterschiedliche Strings-Objekte
                            // aber mit gleichen Inhalten

        System.out.println("Compare 7: With == " + (c == e));
        System.out.println("Compare 8: With .equals() " + c.equals(e));
        
        String f = "Servus"; // c und f referenzieren unterschiedliche String-Objekte
                             // mit unterschiedlichen Inhalten
        
        System.out.println("Compare 9: With == " + (c == f));
        System.out.println("Compare 10: With .equals() " + c.equals(f));
        
        
        B g = new B();
        g.a = "Hallo";
        
        B h = new B();
        h.a = "Hal" + "lo"; // g und h referenzieren unterschiedliche B-Objekte
                            // die den selben Inhalt haben
        
        System.out.println("Compare 11: With == " + (g == h));
        System.out.println("Compare 12: With .equals() " + g.equals(h));
        
        B i = g; // i und g referenzieren das selbe B-Objekt
                 // klarer Weise dann auch mit selben Inhalt
        
        System.out.println("Compare 13: With == " + (g == i));
        System.out.println("Compare 14: With .equals() " + g.equals(i));
    }
}
