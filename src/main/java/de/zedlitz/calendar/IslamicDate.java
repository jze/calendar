package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IslamicDate extends AbstractDate {
    private static final double ISLAMIC_EPOCH = 1948439.5;

    public static final String[] ISLAMIC_WEEKDAYS = new String[] { "al-'ahad",
            "al-'ithnayn", "ath-thalatha'", "al-'arb`a'", "al-khamis",
            "al-jum`a", "as-sabt" };

    public static final String[] MONTHS = { "Muharram", "Safar",
            "Rabi`al-Awwal", "Rabi`ath-Thani", "Jumadal-Ula", "Jumadat-Tania",
            "Rajab", "Sha`ban", "Ramadan", "Shawwal", "Dhul-Qa`da",
            "Dhul-Hijja" };

    private int year;

    private int month;

    private int day;

    public IslamicDate() {

    }

    public IslamicDate(double julianDate) {
        this.setJulianDay(julianDate);
    }

    public IslamicDate(int year, int month, int day) {
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
     * Determine Julian day from Islamic date
     * 
     */
    public double getJulianDay() {
        return (day + Math.ceil(29.5 * (month - 1)) + (year - 1) * 354
                + Math.floor((3 + (11 * year)) / 30) + ISLAMIC_EPOCH) - 1;
    }

    /**
     * Calculate Islamic date from Julian day
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        this.year = (int) Math
                .floor(((30 * (jd - ISLAMIC_EPOCH)) + 10646) / 10631);
        this.month = (int) Math.min(12, Math.ceil((jd - (29 + new IslamicDate(
                year, 1, 1).getJulianDay())) / 29.5) + 1);
        this.day = (int) (jd - new IslamicDate(year, month, 1).getJulianDay()) + 1;
    }

    /** Is a given year a leap year in the Islamic calendar ? */
    public boolean isLeapYear() {
        return (((year * 11) + 14) % 30) < 11;
    }

    public int getDayOfWeek() {
        return Astro.jwday(this.getJulianDay());
    }

}
