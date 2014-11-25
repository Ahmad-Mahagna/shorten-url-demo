package com.easy.url.algorithm;


/**
 * This class represent algorithm for generate easy URL .
 * the idea is generate IDs then convert them to base 62 (letters upper , lower case and numbers )
 *
 * <p/>
 * <p/>
 * README.MD:  currently the algorithm based on generate ID then convert it to base 62. we need to improve generate ID process to be more effcient by using UUID and other libraries to make sure that is unique . this algorith required high CPU during
 * processing the requests ,to reduce the amount of resources and processing time. option one is preparing generates easy urls pre-requests then all we need is to pick randomly  generated easy url and link it to in DB.
 * option two if we need to save more time while generate easy urls another efficient way to generate easy URL by running    dictionary algorithm for all convention of 5-8 letters and numbers.the solution is based on our service and resources
 * @author Ahmad Mahagna
 */
public class EasyUrlAlgorithm {

    // base
    private static String[] BASE_DIGITS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    // base length
    private static int BASE = BASE_DIGITS.length;


    /**
     * get easy URL by given decimal number.
     * convert the number to base 62 to generate shorten string
     * @param decimalNumber given decimal to generate shorten string
     * @return shorten string represent easy url
     */
    public static String getEasyUrl(long decimalNumber) {

        StringBuilder shortenValueSb = new StringBuilder();
        if (decimalNumber == 0) {
            return "0";
        }

        // convert it to base 62
        long mod;
        while (decimalNumber != 0) {
            mod = decimalNumber % BASE;
            shortenValueSb.append(BASE_DIGITS[((int) mod)]);
            decimalNumber = decimalNumber / BASE;
        }
        // reverse
        return shortenValueSb.reverse().toString();
    }

}
