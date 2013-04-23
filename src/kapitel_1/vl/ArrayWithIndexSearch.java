package kapitel_1.vl;

public class ArrayWithIndexSearch extends Array { // Inherit Array as base
	public ArrayWithIndexSearch(int size) {
		super(size);
	}
	
	public int indexSearch(Long key) {
		for (int i = 0; i < this.size(); i++) { // Iterate over every index
			Long currentLong = (Long) this.get(i);

			if (key.compareTo(currentLong) == 0) { // Found? 
				return i + 1; // Found: Return the
							  // adjusted index
			}
		}
		return -1; // Not found: Return -1
	}
}
