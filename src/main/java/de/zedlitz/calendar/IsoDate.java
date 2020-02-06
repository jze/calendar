package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IsoDate extends AbstractDate {

    private int year;

    private int week;

    private int day;

    public IsoDate() {

    }

    public IsoDate(double julianDate) {
        this.setJulianDay(julianDate);
    }

    public IsoDate(int year, int week, int day) {
        this.year = year;
        this.week = week;
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
     * @return the week
     */
    public int getWeek() {
        return this.week;
    }

    /**
     * @param week
     *            the week to set
     */
    public void setWeek(int week) {
        this.week = week;
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
     * Return Julian date of given weekday (0 = Sunday) in the seven days ending
     * on jd.
     */
    public double weekday_before(int weekday, final double jd) {
        return jd - Astro.jwday(jd - weekday);
    }

    /**
     * Determine the Julian date for:
     * 
     * weekday Day of week desired, 0 = Sunday jd Julian date to begin search
     * direction 1 = next weekday, -1 = last weekday offset Offset from jd to
     * begin search
     */
    public double search_weekday(int weekday, final double jd, int direction,
            int offset) {
        return weekday_before(weekday, jd + (direction * offset));
    }

    // Utility weekday public static s, just wrappers for search_weekday

    public double nearest_weekday(final int weekday, final double jd) {
        return search_weekday(weekday, jd, 1, 3);
    }

    public double  next_weekday(final int weekday, final double jd) {
        return search_weekday(weekday, jd, 1, 7);
    }

    public double next_or_current_weekday(final int weekday, final double jd) {
        return search_weekday(weekday, jd, 1, 6);
    }

    public double previous_weekday(final int weekday, final double jd) {
        return search_weekday(weekday, jd, -1, 1);
    }

    public double previous_or_current_weekday(final int weekday, final double jd) {
        return search_weekday(weekday, jd, 1, 0);
    }

    private double n_weeks(int weekday, double jd, int nthweek) {
        double j = 7 * nthweek;

        if (nthweek > 0) {
            j += previous_weekday(weekday, jd);
        } else {
            j += next_weekday(weekday, jd);
        }
        return j;
    }

    /**
     * Return Julian day of given ISO year, week, and day
     */
    public double getJulianDay() {
        return day
                + n_weeks(0, new GregorianDate(year - 1, 12, 28)
                        .getJulianDay(), week);
    }

    /** Return array of ISO (year, week, day) for Julian day */
    public void setJulianDay(double jd) {
        year = new GregorianDate(jd - 3).getYear();
        if (jd >= new IsoDate(year + 1, 1, 1).getJulianDay()) {
            year++;
        }
        week = (int) Math
                .floor((jd - new IsoDate(year, 1, 1).getJulianDay()) / 7) + 1;
        day = Astro.jwday(jd);
        if (day == 0) {
            day = 7;
        }
    }

}
