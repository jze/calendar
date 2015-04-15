package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class GregorianDate extends AbstractDate {

    private static final double GREGORIAN_EPOCH = 1721425.5;

    private int year;

    private int month;

    private int day;

    public GregorianDate() {

    }

    public GregorianDate(final int year, final int month, final int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public GregorianDate(final double julianDate) {
        this.setJulianDay(julianDate);
    }

    /**
     * @return the day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * @param day
     *            the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /** Is a given year in the Gregorian calendar a leap year ? */

    public boolean isLeapYear() {
        return GregorianDate.isLeapYear(this.year);
    }

    public static boolean isLeapYear(final int year) {
        return ((year % 4) == 0)
                && (!(((year % 100) == 0) && ((year % 400) != 0)));
    }

    /** Determine Julian day number from Gregorian calendar date */
    public double getJulianDay() {
        double result = (GREGORIAN_EPOCH - 1)
                + (365 * (year - 1))
                + Math.floor((year - 1) / 4)
                + (-Math.floor((year - 1) / 100))
                + Math.floor((year - 1) / 400)
                + Math.floor((((367 * month) - 362) / 12)
                        + ((month <= 2) ? 0 : (this.isLeapYear() ? -1 : -2))
                        + day);

        return result;
    }

    /** Calculate Gregorian calendar date from Julian day */

    public void setJulianDay(double jd) {
        int quadricent, dqc, cent, dcent, quad, dquad, yindex, yearday, leapadj;

        double wjd = Math.floor(jd - 0.5) + 0.5;
        double depoch = wjd - GREGORIAN_EPOCH;
        quadricent = (int) Math.floor(depoch / 146097);
        dqc = Astro.mod(depoch, 146097);
        cent = (int) Math.floor(dqc / 36524);
        dcent = Astro.mod(dqc, 36524);
        quad = (int) Math.floor(dcent / 1461);
        dquad = Astro.mod(dcent, 1461);
        yindex = (int) Math.floor(dquad / 365);
        year = (quadricent * 400) + (cent * 100) + (quad * 4) + yindex;
        if (!((cent == 4) || (yindex == 4))) {
            year++;
        }
        yearday = (int) (wjd - new GregorianDate(year, 1, 1).getJulianDay());
        leapadj = ((wjd < new GregorianDate(year, 3, 1).getJulianDay()) ? 0
                : (this.isLeapYear() ? 1 : 2));
        this.month = (int) Math.floor((((yearday + leapadj) * 12) + 373) / 367);
        this.day = (int) (wjd - new GregorianDate(year, month, 1)
                .getJulianDay()) + 1;
    }

    public int getDayOfWeek() {
        return Astro.jwday(this.getJulianDay());
    }

}
