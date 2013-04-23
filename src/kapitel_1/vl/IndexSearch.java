package kapitel_1.vl;

public class IndexSearch {
	public static int indexSearch(Array array, Long key) {
		for (int i = 0; i < array.size(); i++) { // Iterate over every index
			Long currentLong = (Long) array.get(i);

			if (key.compareTo(currentLong) == 0) { // Found?
				return i + 1; // Found: Return the
				              // adjusted index
			}
		}
		return -1; // Not found: Return -1
	}
}
