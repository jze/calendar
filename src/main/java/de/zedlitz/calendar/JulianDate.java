package de.zedlitz.calendar;

/**
 * 
 * 
 * @author jzedlitz
 * 
 */
public class JulianDate extends AbstractDate {

    private int year;

    private int month;

    private int day;

    public JulianDate() {

    }

    public JulianDate(double julianDay) {
        this.setJulianDay(julianDay);
    }

    public JulianDate(int year, int month, int day) {
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

    /**
     * Determine Julian day number from Julian calendar date
     */
    public double getJulianDay() {
        /* Adjust negative common era years to the zero-based notation we use. */
        if (year < 1) {
            year++;
        }

        /*
         * Algorithm as given in Meeus, Astronomical Algorithms, Chapter 7, page
         * 61
         */
        if (month <= 2) {
            year--;
            month += 12;
        }

        return ((Math.floor((365.25 * (year + 4716)))
                + Math.floor((30.6001 * (month + 1))) + day) - 1524.5);
    }

    /** Calculate Julian calendar date from Julian day */
    public void setJulianDay(double td) {
        td += 0.5;
        int z = (int) Math.floor(td);

        int a = z;
        int b = a + 1524;
        int c = (int) Math.floor((b - 122.1) / 365.25);
        int d = (int) Math.floor(365.25 * c);
        int e = (int) Math.floor((b - d) / 30.6001);

        this.month = (int) Math.floor((e < 14) ? (e - 1) : (e - 13));
        this.year = (int) Math.floor((month > 2) ? (c - 4716) : (c - 4715));
        this.day = b - d - (int) Math.floor(30.6001 * e);

        /*
         * If year is less than 1, subtract one to convert from a zero based
         * date system to the common era system in which the year -1 (1 B.C.E)
         * is followed by year 1 (1 C.E.).
         */
        if (year < 1) {
            year--;
        }
    }

    public boolean isLeapYear() {
        return Astro.mod(year, 4) == ((year > 0) ? 0 : 3);
    }

    public int getDayOfWeek() {
        return Astro.jwday(this.getJulianDay());
    }
}
