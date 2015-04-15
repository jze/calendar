package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class BahaiDate extends AbstractDate {
    private static final double BAHAI_EPOCH = 2394646.5;

    public static final String[] YEARS = new String[] { "Alif", "Bá'", "Ab",
            "Dál", "Báb", "Váv", "Abad", "Jád", "Bahá", "Hubb", "Bahháj",
            "Javáb", "Ahad", "Vahháb", "Vidád", "Badí'", "Bahí", "Abhá",
            "Vahíd" };

    public static final String[] MONTHS = new String[] { "Bahá", "Jalál",
            "Jamál", "`Azamat", "Núr", "Rahmat", "Kalimát", "Kamál", "Asmá'",
            "`Izzat", "Mashíyyat", "`Ilm", "Qudrat", "Qawl", "Masáil",
            "Sharaf", "Sultán", "Mulk", "Ayyám-i-Há", "`Alá'" };

    public static final String[] DAYS = new String[] { "Bahá", "Jalál",
            "Jamál", "`Azamat", "Núr", "Rahmat", "Kalimát", "Kamál", "Asmá'",
            "`Izzat", "Mashíyyat", "`Ilm", "Qudrat", "Qawl", "Masáil",
            "Sharaf", "Sultán", "Mulk", "`Alá'" };

    public static final String[] BAHAI_WEEKDAYS = new String[] { "Jamál",
            "Kamál", "Fidál", "Idál", "Istijlál", "Istiqlál", "Jalál" };

    private int major;

    private int cycle;

    private int year;

    private int month;

    private int day;

    public BahaiDate() {

    }

    public BahaiDate(double julianDay) {
        this.setJulianDay(julianDay);
    }

    public BahaiDate(int major, int cycle, int year, int month, int day) {
        this.major = major;
        this.cycle = cycle;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * @return the cycle
     */
    public int getCycle() {
        return this.cycle;
    }

    /**
     * @param cycle the cycle to set
     */
    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the major
     */
    public int getMajor() {
        return this.major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(int major) {
        this.major = major;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * @param month the month to set
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
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Determine Julian day from Bahai date
     * 
     */
    public double getJulianDay() {
        int gy = (361 * (major - 1)) + (19 * (cycle - 1)) + (year - 1)
                + new GregorianDate(BAHAI_EPOCH).getYear();
        return new GregorianDate(gy, 3, 20).getJulianDay()
                + (19 * (month - 1))
                + ((month != 20) ? 0 : (GregorianDate.isLeapYear(gy + 1) ? -14
                        : -15)) + day;
    }

    /** Calculate Bahai date from Julian day. */

    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        int gy = new GregorianDate(jd).getYear();
        int bstarty = new GregorianDate(BAHAI_EPOCH).getYear();
        int bys = gy
                - (bstarty + (((new GregorianDate(gy, 1, 1).getJulianDay() <= jd) && (jd <= new GregorianDate(
                        gy, 3, 20).getJulianDay())) ? 1 : 0));
        this.major = (int) Math.floor(bys / 361) + 1;
        this.cycle = (int) Math.floor(Astro.mod(bys, 361) / 19) + 1;
        this.year = Astro.mod(bys, 19) + 1;
        double days = jd
                - new BahaiDate(major, cycle, year, 1, 1).getJulianDay();
        double bld = new BahaiDate(major, cycle, year, 20, 1).getJulianDay();
        this.month = (jd >= bld) ? 20 : (int) (Math.floor(days / 19) + 1);
        this.day = (int) ((jd + 1) - new BahaiDate(major, cycle, year, month, 1)
                .getJulianDay());
    }

}
