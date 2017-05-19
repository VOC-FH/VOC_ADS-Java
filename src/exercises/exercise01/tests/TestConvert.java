package exercises.exercise01.tests;

import exercises.exercise01.Convert;

public class TestConvert {

    public static void main(String[] args) {
        char[] s2 = Convert.convertFromS10toS2((short) 334);
        for (int i = 0; i < s2.length; i++) {
            System.out.print(s2[i]);
        }
        System.out.println();
        
        char[] hex = {'F', 'F', 'F', 'D'};
        System.out.println(Convert.convertFromS16toS10(hex));
        
        char[] s2sign = Convert.convertWithSignFromS10toS2((short) -1);
        for (int i = 0; i < s2sign.length; i++) {
            System.out.print(s2sign[i]);
        }
        System.out.println();
        
        char[] s2dec = Convert.convertWithDecimalFromS10toS2(3.75342334);
        for (int i = 0; i < s2dec.length; i++) {
            if (i == 16) {
                System.out.print(",");
            }
            System.out.print(s2dec[i]);
        }
        System.out.println();
    }
}
