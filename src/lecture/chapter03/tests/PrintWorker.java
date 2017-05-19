package lecture.chapter03.tests;

import lecture.chapter03.IWorker;

public class PrintWorker implements IWorker {
    public void work(Object data) {
        System.out.println(data);
    }
}
