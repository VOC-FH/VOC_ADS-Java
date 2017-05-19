package lecture.chapter03;

public class HashTable {
    protected Bucket[] buckets = null;
    protected int size = 0;
    protected int maxLoad = 0;
    protected int currentLoad = 0;
    
    protected static class Bucket {
        private SList list;
        
        public Bucket() {
            list = new SList();
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
    
    protected static class TupleKey implements IKey {
        String key;
        
        public TupleKey(String key) {
            this.key = key;
        }

        public boolean matches(Object data) {
            return key.equals(((Tuple) data).key);
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
    
    private static Bucket[] initBuckets(int size) {
        Bucket[] b = new Bucket[size];
        
        for (int i = 0; i < b.length; i++) {
            b[i] = new Bucket();
        }
        return b;
    }
    
    public void insert(String key, Object data) {
        long hash = sdbm(key);
        
        buckets[(int) (hash & (size - 1))].list.prepend(new Tuple(hash, key, data));
        
        currentLoad++;
        if (currentLoad >= maxLoad) {
            resize();
        }
    }
    
    public void resize() {
        Bucket[] newBuckets = initBuckets(size << 1);
        
        for (int i = 0; i < size; i++) {
            SList list = buckets[i].list;
            IFIterator it = list.iterator();
            while(it.hasNext()) {
                Tuple entry = (Tuple) it.next();
                newBuckets[(int) (entry.hash & ((size << 1) - 1))].list.prepend(entry);
            }
        }
        size <<= 1;
        maxLoad = (int) (size * 0.75);
        buckets = newBuckets;
    }
    
    public Object get(String key) {
        long hash = sdbm(key);
        Bucket bucket = buckets[(int) (hash & (size -1 ))];
        
        TupleKey tupleKey = new TupleKey(key);
        
        Tuple entry = (Tuple) bucket.list.search(tupleKey);
        
        Object ret = null;
        if (entry != null) {
            ret = entry.data;
        }
        return ret;
    }
    
    public void remove(String key) {
        long hash = sdbm(key);
        Bucket bucket = buckets[(int) (hash & (size -1 ))];

        TupleKey tupleKey = new TupleKey(key);
        Tuple entry = (Tuple) bucket.list.search(tupleKey);
        
        bucket.list.remove(entry);
    }
}
