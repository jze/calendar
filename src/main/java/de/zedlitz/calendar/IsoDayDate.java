package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IsoDayDate extends AbstractDate {

    private int year;

    private int day;

    public IsoDayDate() {

    }

    public IsoDayDate(double julianDate) {
        this.setJulianDay(julianDate);
    }

    public IsoDayDate(final int year, final int day) {
        this.year = year;
        this.day = day;
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

    /** Return Julian day of given ISO year, and day of year */
    public double getJulianDay() {
        return (day - 1) + new GregorianDate(year, 1, 1).getJulianDay();
    }

    /** Return array of ISO (year, day_of_year) for Julian day */

    public void setJulianDay(double jd) {
        this.year = new GregorianDate(jd).getYear();
        this.day = (int) Math.floor(jd
                - new GregorianDate(year, 1, 1).getJulianDay()) + 1;
    }
}
