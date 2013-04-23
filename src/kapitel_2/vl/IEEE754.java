package kapitel_2.vl;

public class IEEE754 {
	public static String calculateBitsProceedingTheDot(double number) {
		double n = number - Math.floor(number);
		String nS = new String();
		
		for (int i = 0; i < 23; i++) {
			n = n * 2.;
			if (n >= 1) {
				nS += "1";
				n -= 1;
			} else {
				nS += "0";
			}
		}
		return nS;
	}
	
	public static String calculateBitsPrecedingTheDot(double number) {
		long i = (long) number;
		String iS = new String();
		
		while(i != 0) {
			long r = i % 2;
			iS = Long.toString(r) + iS;
			i /= 2;
		}
		return iS;
	}
	
	public static String convertToIEEE754(double number) {
		String sIEEE754;
		sIEEE754 = calculateBitsPrecedingTheDot(number) + "." + calculateBitsProceedingTheDot(number);
		return sIEEE754;
	}
	
	public static void main(String[] args) {
		double nPi = 3.14159265359;
		System.out.println(convertToIEEE754(nPi));
	}
}
