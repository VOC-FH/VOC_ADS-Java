package kapitel_1.vl;

public class IndexSearch {
	public static int indexSearch(Array array, Long key) {
	    int ret = -1;
	    
		for (int i = 0; i < array.size() && ret == -1; i++) { // Iterate over every index
			Long currentLong = (Long) array.get(i);

			if (key.compareTo(currentLong) == 0) { // Found?
				ret = i + 1; // Found! adjust the index
			}
		}
		return ret; // return the math index on success and -1 when not found
	}
}
