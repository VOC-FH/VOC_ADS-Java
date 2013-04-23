package kapitel_3.vl;

public interface IComparator {
	// -1: data1 < data2, 
	//  0: data1 = data2,
	// +1: data1 > data2
	int compare(Object data1, Object data2);

	// -1: data < key, 
	//  0: data = key, 
	// +1: data > key
	int compare(Object data, IKey key);
}
