package kapitel_1.vl;

public class ArrayWithIndexSearch extends Array { // Inherit Array as base
	public ArrayWithIndexSearch(int size) {
		super(size);
	}
	
	public int indexSearch(Long key) {
	    int ret = -1;
	    
		for (int i = 0; i < this.size() && ret == -1; i++) { // Iterate over every index
			Long currentLong = (Long) this.get(i);

			if (key.compareTo(currentLong) == 0) { // Found? 
				ret = i + 1; // Found! adjust the index
			}
		}
		return ret; // return the math index on success and -1 when not foun
	}
}
