package lecture.chapter03.utils;

import java.lang.reflect.Field;
import java.util.Random;

import lecture.chapter03.AVLTree;
import lecture.chapter03.tests.IntegerComparator;
import lecture.chapter03.tests.IntegerKey;

class CommonFormat extends ACommonFormat {
    private Class<?> nodeClass = null;
    private Class<?> aVLLayerClass = null;
    private Field balance = null;
    private Field data = null;

    public CommonFormat()
            throws ClassNotFoundException, NoSuchFieldException, SecurityException {
        nodeClass = Class.forName("lecture.chapter03.BTree$Node");
        aVLLayerClass = Class.forName("lecture.chapter03.AVLTree$AVLLayer");
        data = nodeClass.getDeclaredField("data");
        data.setAccessible(true);
        balance = aVLLayerClass.getDeclaredField("balance");
        balance.setAccessible(true);
    }

    @Override
    public String nodeFormat(Object node) {
        String format = "";

        try {
            try {
                Object aVLLayer = data.get(node);
                int b = balance.getInt(aVLLayer);
                format = "label={[draw=none]below:\\tiny " + b + "}";
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return format;
    }
}

class NodeToData implements INodeToData {

    private Class<?> nodeClass = null;
    private Class<?> aVLLayerClass = null;
    private Field realData = null;
    private Field data = null;

    NodeToData() throws ClassNotFoundException, NoSuchFieldException, SecurityException {
        nodeClass = Class.forName("lecture.chapter03.BTree$Node");
        aVLLayerClass = Class.forName("lecture.chapter03.AVLTree$AVLLayer");
        data = nodeClass.getDeclaredField("data");
        data.setAccessible(true);
        realData = aVLLayerClass.getDeclaredField("data");
        realData.setAccessible(true);
    }

    public Object get(Object node)
            throws IllegalArgumentException, IllegalAccessException {
        Object aVLLayer = data.get(node);
        Object rd = realData.get(aVLLayer);
        return rd;
    }
}

public class TestPGFTreePrinter {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchFieldException, SecurityException {

        AVLTree avlTree = new AVLTree(new IntegerComparator());
        PGFTreePrinter ptp = new PGFTreePrinter(avlTree);
        ptp.setCommonFormat(new CommonFormat());
        ptp.setNodeFormat("normal node");
        ptp.setNodeToData(new NodeToData());

        Random rand = new Random();

        int MAX = 90;

        for (int i = 0; i < MAX; i++) {
            int n = 0;
            IntegerKey integerKey = null;

            do {
                n = rand.nextInt(100);
                integerKey = new IntegerKey(n);
            } while (avlTree.search(integerKey) != null);

            Integer data = n;
            avlTree.insert(data);
            /*
             * if (i == 1) { ptp.format(data).setNodeFormat("removed node"); }
             * else if (i == 2) {
             * ptp.format(data).setNodeFormat("inserted node"); }
             */
        }

        System.out.println(ptp);
    }
}
