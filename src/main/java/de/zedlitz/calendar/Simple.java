package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class Simple {
    /**
     * Julian date at Unix epoch: 1970-01-01
     */
    private static final double J1970 = 2440587.5;

    /**
     * Epoch (day 1) of Excel 1900 date system (PC)
     */
    private static final double J1900 = 2415020.5;

    public static double unixToJulian(final long t) {
        return J1970 + (t / (60 * 60 * 24));
    }

    public static int julianToUnix(final double jd) {
        return (int) ((jd - J1970) * (60 * 60 * 24));
    }

    public static double excelSerial1900ToJulian(long d) {
        /*
         * Idiot Kode Kiddies didn't twig to the fact (proclaimed in 1582) that
         * 1900 wasn't a leap year, so every Excel day number in every database
         * on Earth which represents a date subsequent to February 28, 1900 is
         * off by one. Note that there is no acknowledgement of this betrayal or
         * warning of its potential consequences in the Excel help file. Thank
         * you so much Mister Talking Paper Clip. Some day we're going to
         * celebrate your extinction like it was February 29 ... 1900.
         */

        if (d > 60) {
            d--;
        }

        return ((d - 1) + J1900);
    }
}
