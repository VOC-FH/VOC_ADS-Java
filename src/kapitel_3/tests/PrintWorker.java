package kapitel_3.tests;

import kapitel_3.vl.IWorker;

public class PrintWorker implements IWorker {
	public void work(Object data) {
		System.out.println(data);
	}
}
