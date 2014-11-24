package com.easy.url.algorithm;

public class EasyUrlAlgorithm {

    private static String[] BASE_DIGITS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private static int BASE = BASE_DIGITS.length;


    public static String getEasyUrl(long decimalNumber) {

        StringBuilder shortenValueSb = new StringBuilder();
        if (decimalNumber == 0) {
            return "0";
        }

        long mod = 0;
        while (decimalNumber != 0) {
            mod = decimalNumber % BASE;
            shortenValueSb.append(BASE_DIGITS[((int) mod)]);
            decimalNumber = decimalNumber / BASE;
        }
        return shortenValueSb.reverse().toString();
    }

}
