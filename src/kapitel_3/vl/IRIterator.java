package kapitel_3.vl;

public interface IRIterator { // The Backward-iterator interface
	boolean hasPrevious(); // Does a prior object exist?
	Object previous();     // Return the prior object.
}
