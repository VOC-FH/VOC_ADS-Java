package uebung_6;

import kapitel_3.vl.AVLTree;
import kapitel_3.vl.IComparator;
import kapitel_3.vl.IKey;
import kapitel_3.vl.IWorker;

public class HashTable {
    protected Bucket[] buckets = null;
    protected int size = 0;
    protected int maxLoad = 0;
    protected int currentLoad = 0;
    
    protected static class Bucket {
        protected static class TupleKey implements IKey {
            private String key;
            
            public TupleKey(String key) {
                this.key = key;
            }

            public boolean matches(Object data) {
                return key.equals(((Tuple) data).key);
            }
        }
        
        protected static class TupleComparator implements IComparator {
            public int compare(Object data1, Object data2) {
                Tuple tuple1 = (Tuple) data1;
                Tuple tuple2 = (Tuple) data2;
                
                return tuple1.key.compareTo(tuple2.key);
            }

            public int compare(Object data, IKey key) {
                Tuple tuple = (Tuple) data;
                TupleKey tupleKey = (TupleKey) key;
                
                return tuple.key.compareTo(tupleKey.key);
            }
        }
        
        private AVLTree tree;
        static private IComparator comparator = new TupleComparator();
        
        public Bucket() {
            tree = new AVLTree(comparator);
        }
        
        public void insert(Object data) {
            tree.insert(data);
        }
        
        public Object search(String key) {
            TupleKey tupleKey = new TupleKey(key);
            
            return tree.search(tupleKey);
        }
        
        public boolean remove(Object entry) {
            return tree.remove(entry);
        }
                
        public boolean empty() {
            return !tree.iterator().hasNext();
        }
    }
    
    protected static class Tuple {
        public long hash;
        public String key;
        public Object data;
        
        public Tuple(long hash, String key, Object data) {
            this.hash = hash;
            this.key = key;
            this.data = data;
        }
    }
    
    private static long sdbm(String s) {
      long hash = 0;
    
      for (int i = 0; i < s.length(); i++) {
          hash = s.charAt(i) + (hash << 6) + (hash << 16) - hash;
      }
    
      return hash;
    }
    
    protected static long knuth(String s) {
        long hash = 0;
        
        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 5) ^ (hash >>> 27)) ^ s.charAt(i);
        }
        return hash;
    }
    
    protected static long kernighamRitchie(String s) {
        long hash = 0;
        
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i);
        }
        return hash;
    }
    
    protected static long stl(String s) {
        long hash = 0;
        
        for (int i = 0; i < s.length(); i++) {
            hash = 5 * hash + s.charAt(i);
        }
        return hash;
    }
    
    protected static long java(String s)  {
        long hash = 0;
    
        for (int i = 0; i < s.length(); i++) {
            hash += (s.charAt(i) * 31) ^ (s.length() - 1 - i);
        }
        return hash;
    }
    
    public HashTable() {
        this(0);
    }
    
    public HashTable(int exponent) {
        size = 1 << exponent;
        maxLoad = (int) (size * 0.75);
        buckets = initBuckets(size);
    }
    
    protected static Bucket[] initBuckets(int size) {
        return new Bucket[size];
    }
    
    public void insert(String key, Object data) {
        long hash = sdbm(key);
        
        int index = (int) (hash & (size - 1));
        
        if (buckets[index] == null) {
            buckets[index] = new Bucket();
            currentLoad++;
        }
        
        buckets[index].insert(new Tuple(hash, key, data));

        if (currentLoad >= maxLoad) {
            resize();
        }
    }
    
    protected void resize() {
        final Bucket[] newBuckets = initBuckets(size << 1);
        
        IWorker resizeWorker = new IWorker() {
            public void work(Object data) {
                Tuple entry = (Tuple) data;
                int index = (int) (entry.hash & ((size << 1) - 1));
                
                if (newBuckets[index] == null) {
                    newBuckets[index] = new Bucket();
                }
                newBuckets[(int) (entry.hash & ((size << 1) - 1))].insert(entry);
            }
        };
        
        for (int i = 0; i < size; i++) {
            if (buckets[i] != null) {
                AVLTree tree = buckets[i].tree;
                tree.depthFirstPreOrder(resizeWorker);
            }
        }
        
        size <<= 1;
        maxLoad = (int) (size * 0.75);
        buckets = newBuckets;
    }
    
    public Object get(String key) {
        Object ret = null;
        
        long hash = sdbm(key);
        Bucket bucket = buckets[(int) (hash & (size -1 ))];
        
        if (bucket != null) {
            Tuple entry = (Tuple) bucket.search(key);
            
            if (entry != null) {
                ret = entry.data;
            }
        }
        
        return ret;
    }
    
    public void remove(String key) {
        long hash = sdbm(key);
        int index = (int) (hash & (size -1 ));
        
        Bucket bucket = buckets[(int) (hash & (size -1 ))];
        
        if (bucket != null) {
            Tuple entry = (Tuple) bucket.search(key);
        
            bucket.remove(entry);
            
            if (bucket.empty()) {
                buckets[index] = null;
                currentLoad--;
            }
        }
    }
}
