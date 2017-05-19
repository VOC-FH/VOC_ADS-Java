package lecture.chapter03;

public class ReferenceKey implements IKey {
    private Object data = null;
    
    public ReferenceKey(Object data) {
        this.data = data;
    }
    
    public boolean matches(Object data) {
        return this.data == data;
    }
}
