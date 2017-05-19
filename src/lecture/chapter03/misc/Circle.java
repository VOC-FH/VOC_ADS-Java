package lecture.chapter03.misc;

public class Circle {
    protected String name;
    private float radius;
    
    public Circle() { radius = 1; }
    public float getRadius() { return radius; }
    protected int protectedMethod(String arg) {
        return privateMethod(arg);
    }
    private int privateMethod(Object data) {
        return 1;
    }
}
