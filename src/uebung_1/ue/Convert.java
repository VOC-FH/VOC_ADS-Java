package uebung_1.ue;
public class Convert {
	public static char[] convertFromS10toS2(short s10) {
		char[] s2 = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
		int i = 15;
		while(s10 > 0) {
			if (s10 % 2 == 1) {
				s2[i] = '1';
			} else {
				s2[i] = '0';
			}
			s10 = (short) (s10 / 2);
			i--;
		}
		return s2;
	}
	
	public static char[] convertWithSignFromS10toS2(short s10) {
		if (s10 < 0) {
			short abs = (short) -s10;
			char[] bin = convertFromS10toS2(abs);
			for (int i = 0; i < bin.length; i++) {
				bin[i] = (bin[i] == '1') ? '0' : '1';
			}
			
			int i = 0;
			for (i = bin.length - 1; i >= 0 && bin[i] == '1'; i--) {
				bin[i] = '0';
			}
			bin[i] = '1';

			return bin;
		} else {
			return convertFromS10toS2(s10);
		}
	}
	
	public static int convertFromS16toS10(char[] hex) {
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		int value = 0;
		int weight = 1;
		
		for (int digitNumber = 0; digitNumber < hex.length; digitNumber++) {
			int number = 0;
			for (number = 0; number < hexDigits.length; number++) {
				if (hex[hex.length - digitNumber - 1] == hexDigits[number]) {
					break;
				}
			}
			value += number * weight;
			weight *= 16;
		}
		return value;
	}
	
	public static char[] convertWithDecimalFromS10toS2(double dec) {
		char[] dual = new char[26];
		short v = (short) dec;
		double n = dec - v;
		
		System.out.println("Short: " + v);
		System.out.println("Dec: " + n);
		
		char[] vDual = convertFromS10toS2(v);
		for (int i = 0; i < 16; i++) {
			dual[i] = vDual[i];
		}
		
		for (int i = 0; i < 10; i++) {
			n *= 2;
			if (n >= 1) {
				dual[16 + i] = '1';
				n--;
			} else {
				dual[16 + i] = '0';
			}
		}
		
		return dual;
	}
}
