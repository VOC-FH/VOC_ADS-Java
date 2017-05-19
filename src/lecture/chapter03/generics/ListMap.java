package lecture.chapter03.generics;

public class ListMap<T, K> {
    protected SList<Map> list = new SList<Map>();
    
    protected class Map {
        K key;
        T data;
        
        private Map (K key, T data) {
            this.key = key;
            this.data = data;
        }
    }
    
    protected class MapKey implements IKey<Map> {
        K key;
        
        public MapKey(K key) {
            this.key = key;
        }
        
        public boolean matches(Map data) {
            K key = data.key;
            return key.equals(this.key);
        }
    }
    
    public void insert(K key, T data) {
        Map map = new Map(key, data);
        list.prepend(map);
    }
    
    public Object search(K key) {
        MapKey mapKey = new MapKey(key);
        Map map = (Map) list.search(mapKey);
        return (map != null) ? map.data : null;
    }
}
