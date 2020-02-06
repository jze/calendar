package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class PersianDate extends AbstractDate {
    private static final double PERSIAN_EPOCH = 1948320.5;

    public static final String[] PERSIAN_WEEKDAYS = new String[] {
            "Yekshanbeh", "Doshanbeh", "Seshhanbeh", "Chaharshanbeh",
            "Panjshanbeh", "Jomeh", "Shanbeh" };

    public static final String[] MONTHS = new String[] { "Farvardin",
            "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar", "Mehr",
            "Aban", "Azar", "Dey", "Bahman", "Esfand" };

    private int year;

    private int month;

    private int day;

    public PersianDate() {

    }

    public PersianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
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

    /** Determine Julian day from Persian date */
    public double getJulianDay() {
        int epbase = year - ((year >= 0) ? 474 : 473);
        int epyear = 474 + Astro.mod(epbase, 2820);

        return day
                + ((month <= 7) ? ((month - 1) * 31) : (((month - 1) * 30) + 6))
                + Math.floor(((epyear * 682) - 110) / 2816.) + (epyear - 1)
                * 365 + Math.floor(epbase / 2820.) * 1029983
                + (PERSIAN_EPOCH - 1);
    }

    /**
     * Calculate Persian date from Julian day
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;

        double depoch = jd - new PersianDate(475, 1, 1).getJulianDay();
        int cycle = (int) Math.floor(depoch / 1029983);
        int cyear = Astro.mod(depoch, 1029983);

        int ycycle;
        if (cyear == 1029982) {
            ycycle = 2820;
        } else {
            double aux1 = Math.floor(cyear / 366.0);
            double aux2 = Astro.mod(cyear, 366.0);
            ycycle = (int) (Math
                    .floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522)
                    + aux1 + 1);
        }
        year = ycycle + (2820 * cycle) + 474;
        if (year <= 0) {
            year--;
        }
        double yday = (jd - new PersianDate(year, 1, 1).getJulianDay()) + 1;
        month = (yday <= 186) ? (int) Math.ceil(yday / 31) : (int) Math
                .ceil((yday - 6) / 30);
        day = (int) ((jd - new PersianDate(year, month, 1).getJulianDay()) + 1);
    }

    /**
     * Is a given year a leap year in the Persian calendar ?
     */
    public boolean isLeapYear() {
        return ((((((year - ((year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682;
    }

}
