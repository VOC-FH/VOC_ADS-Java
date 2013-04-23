package kapitel_3.vl;

public class ListMap {
    protected SList list = new SList();
    
    protected class Map {
        Object key;
        Object data;
        
        private Map (Object key, Object data) {
            this.key = key;
            this.data = data;
        }
    }
    
    protected class MapKey implements IKey {
        Object data;
        
        public MapKey(Object data) {
            this.data = data;
        }
        
        public boolean equals(Object data) {
            Object key = ((Map) data).key;
            return key.equals(this.data);
        }
    }
    
    public void insert(Object key, Object data) {
        Map map = new Map(key, data);
        list.prepend(map);
    }
    
    public Object search(Object data) {
        MapKey mapKey = new MapKey(data);
        Map map = (Map) list.search(mapKey);
        return (map != null) ? map.data : null;
    }
}
