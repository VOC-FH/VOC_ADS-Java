package uebung_4.tests;

import java.lang.reflect.Field;

import kapitel_3.tests.Student;

public class TestMyTree {
    private static Class<?> treeClass = null;
    private static Class<?> nodeClass = null;
    private static Field rootField = null;
    private static Field leftField = null;
    private static Field rightField = null;
    private static Field dataField = null;
    private static Field parentField = null;
    
    static {
        try {
            treeClass = Class.forName("kapitel_3.vl.BTree");
            nodeClass = Class.forName("kapitel_3.vl.BTree$Node");
            
            rootField = treeClass.getDeclaredField("root");
            rootField.setAccessible(true);
            
            leftField = nodeClass.getDeclaredField("left");
            leftField.setAccessible(true);

            rightField = nodeClass.getDeclaredField("right");
            rightField.setAccessible(true);

            dataField = nodeClass.getDeclaredField("data");
            dataField.setAccessible(true);

            parentField = nodeClass.getDeclaredField("parent");
            parentField.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        uebung_4.ue.BTree bTree = new uebung_4.ue.BTree();

        Student s = new Student("Volker", "Christian", "MTD01221101");
        bTree.insert(s);

        s = new Student("Albert", "Einstein", "MTD01221102");
        bTree.insert(s);

        s = new Student("Wolfgang", "Ambros", "MTD01221103");
        bTree.insert(s);

        s = new Student("Josef", "Hinterleitner", "MTD01221104");
        bTree.insert(s);

        s = new Student("Wilhelm", "Burger", "MTD01221105");
        bTree.insert(s);

        s = new Student("Andreas", "Stöckl", "MTD01221106");
        bTree.insert(s);

        try {
            if (treeClass.isInstance(bTree)) {
                Object root = rootField.get(bTree);
                depthFirstPreorder(root);
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    static void depthFirstPreorder(Object node) throws IllegalArgumentException, IllegalAccessException {
        if (node != null) {
            System.out.println((Student) dataField.get(node));
            depthFirstPreorder(leftField.get(node));
            depthFirstPreorder(rightField.get(node));
        }
    }

    static void depthFirstInorder(Object node) throws IllegalArgumentException, IllegalAccessException {
        if (node != null) {
            depthFirstInorder(leftField.get(node));
            System.out.println((Student) dataField.get(node));
            depthFirstInorder(rightField.get(node));
        }
    }
    
    static void depthFirstPostorder(Object node) throws IllegalArgumentException, IllegalAccessException {
        if (node != null) {
            depthFirstPostorder(leftField.get(node));
            depthFirstPostorder(rightField.get(node));
            System.out.println((Student) dataField.get(node));
        }
    }
}
