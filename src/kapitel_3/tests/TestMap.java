package kapitel_3.tests;

import kapitel_3.vl.ListMap;

public class TestMap {
    public static void main(String[] args) {
        ListMap map = new ListMap();
        
//        IntegerKey ik = new IntegerKey(2);
        
        map.insert("Haus", 5);
        
 //       ik = new IntegerKey(2);
        
        map.insert("Baum", 6);
        
        Object data = map.search(new String("Baum"));
        
        System.out.println(data);
        
//        data = map.search(2);
        
//        System.out.println(data);
    }
}
