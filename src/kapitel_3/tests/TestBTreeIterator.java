package kapitel_3.tests;

public class TestBTreeIterator {
    
    public static void main(String[] args) {
        uebung_4.ue.BTree bTree = new uebung_4.ue.BTree();
        
        bTree.insert(3);
        bTree.insert(4);
        bTree.insert(5);
        
        kapitel_3.vl.IFIterator it = bTree.iterator();
        
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
    
}
