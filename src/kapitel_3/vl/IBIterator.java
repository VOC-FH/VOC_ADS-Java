package kapitel_3.vl;

public abstract class IBIterator implements IFIterator, IRIterator {
	// Try to cast a forward iterator to a bidirectional iterator.
	public static IBIterator bidirectionalIterator(IFIterator iterator) {
		return (iterator instanceof IBIterator) ? (IBIterator) iterator : null;
	}
	
	// Try to cast a reverse iterator to a bidirectional iterator.
	public static IBIterator bidirectionalIterator(IRIterator iterator) {
		return (iterator instanceof IBIterator) ? (IBIterator) iterator : null;
	}
}
