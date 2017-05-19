package lecture.chapter03.generics;

public class HashTable<T> {
    protected Bucket<T>[] buckets = null;
    protected int size = 0;
    protected int maxLoad = 0;
    protected int currentLoad = 0;
    
    protected static class Bucket<T> {
        private SList<Tuple<T>> list;
        
        public Bucket() {
            list = new SList<Tuple<T>>();
        }
    }
    
    protected static class Tuple<T> {
        public long hash;
        public String key;
        public T data;
        
        public Tuple(long hash, String key, T data) {
            this.hash = hash;
            this.key = key;
            this.data = data;
        }
    }
    
    protected static class TupleKey<T> implements IKey<Tuple<T>> {
        String key;
        
        public TupleKey(String key) {
            this.key = key;
        }

        public boolean matches(Tuple<T> data) {
            return key.equals(data.key);
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
    
    private static <T> Bucket<T>[] initBuckets(int size) {
        @SuppressWarnings("unchecked")
        Bucket<T>[] b = (Bucket<T>[]) new Object[size];
        
        for (int i = 0; i < b.length; i++) {
            b[i] = new Bucket<T>();
        }
        return b;
    }
    
    public void insert(String key, T data) {
        long hash = sdbm(key);
        
        buckets[(int) (hash & (size - 1))].list.prepend(new Tuple<T>(hash, key, data));
        
        currentLoad++;
        if (currentLoad >= maxLoad) {
            resize();
        }
    }
    
    public void resize() {
        Bucket<T>[] newBuckets = initBuckets(size << 1);
        
        for (int i = 0; i < size; i++) {
            SList<Tuple<T>> list = buckets[i].list;
            IFIterator<Tuple<T>> it = list.iterator();
            while(it.hasNext()) {
                Tuple<T> entry = it.next();
                newBuckets[(int) (entry.hash & ((size << 1) - 1))].list.prepend(entry);
            }
        }
        size <<= 1;
        maxLoad = (int) (size * 0.75);
        buckets = newBuckets;
    }
    
    public Object get(String key) {
        long hash = sdbm(key);
        Bucket<T> bucket = buckets[(int) (hash & (size -1 ))];
        
        TupleKey<T> k = new TupleKey<T>(key);
        
        Tuple<T> entry = bucket.list.search(k);
        
        Object ret = null;
        if (entry != null) {
            ret = entry.data;
        }
        return ret;
    }
    
    public void remove(String key) {
        long hash = sdbm(key);
        Bucket<T> bucket = buckets[(int) (hash & (size -1 ))];

        TupleKey<T> k = new TupleKey<T>(key);
        Tuple<T> entry = bucket.list.search(k);
        
        bucket.list.remove(entry);
    }
}
