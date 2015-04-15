package de.zedlitz.calendar;

/**
 * 
 * @author jzedlitz
 * 
 */
public class HebrewDate extends AbstractDate {

    private static final double HEBREW_EPOCH = 347995.5;

    public static final String[] MONTHS = new String[] { "Nisan", "Iyyar",
            "Sivan", "Tammuz", "Av", "Elul", "Tishri", "Heshvan", "Kislev",
            "Teveth", "Shevat", "Adar", "Veadar" };

    private int year;

    private int month;

    private int day;

    public HebrewDate() {

    }

    public HebrewDate(double julianDate) {
        this.setJulianDay(julianDate);
    }

    public HebrewDate(int year, int month, int day) {
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

    /** Is a given Hebrew year a leap year ? */

    public boolean isLeapYear() {
        return this.isLeapYear(this.year);
    }

    private boolean isLeapYear(int year) {
        return Astro.mod(((year * 7) + 1), 19) < 7;
    }

    /** How many months are there in a Hebrew year (12 = normal, 13 = leap) */

    public int getMonthInYear(int year) {
        return isLeapYear(year) ? 13 : 12;
    }

    /**
     * Test for delay of start of new year and to avoid Sunday, Wednesday, and
     * Friday as start of the new year.
     */
    private int getDelay1(int year) {
        int months, day, parts;

        months = (int) Math.floor(((235 * year) - 234) / 19);
        parts = 12084 + (13753 * months);
        day = (months * 29) + (int) Math.floor(parts / 25920);

        if (Astro.mod((3 * (day + 1)), 7) < 3) {
            day++;
        }
        return day;
    }

    /** Check for delay in start of new year due to length of adjacent years */
    private int getDelay2(int year) {
        int last, present, next;

        last = getDelay1(year - 1);
        present = getDelay1(year);
        next = getDelay1(year + 1);

        return ((next - present) == 356) ? 2 : (((present - last) == 382) ? 1
                : 0);
    }

    /**
     * How many days are in a Hebrew year ?
     */
    public static int getDaysInYear(final int year) {
        return (int) (new HebrewDate(year + 1, 7, 1).getJulianDay() - new HebrewDate(
                year, 7, 1).getJulianDay());
    }

    /**
     * How many days are in a given month of a given year
     */
    public int getDaysInMonth(int year, int month) {
        // First of all, dispose of fixed-length 29 day months
        if (month == 2 || month == 4 || month == 6 || month == 10
                || month == 13) {
            return 29;
        }

        // If it's not a leap year, Adar has 29 days
        if (month == 12 && !isLeapYear(year)) {
            return 29;
        }

        // If it's Heshvan, days depend on length of year
        if (month == 8 && !(Astro.mod(getDaysInYear(year), 10) == 5)) {
            return 29;
        }

        // Similarly, Kislev varies with the length of year
        if (month == 9 && (Astro.mod(getDaysInYear(year), 10) == 3)) {
            return 29;
        }

        // Nope, it's a 30 day month
        return 30;
    }

    /** Determine Julian day from Hebrew date */
    public double getJulianDay() {

        int months = getMonthInYear(year);
        double jd = HEBREW_EPOCH + getDelay1(year) + getDelay2(year) + day + 1;

        if (month < 7) {
            for (int mon = 7; mon <= months; mon++) {
                jd += getDaysInMonth(year, mon);
            }
            for (int mon = 1; mon < month; mon++) {
                jd += getDaysInMonth(year, mon);
            }
        } else {
            for (int mon = 7; mon < month; mon++) {
                jd += getDaysInMonth(year, mon);
            }
        }

        return jd;
    }

    /**
     * Convert Julian date to Hebrew date This works by making multiple calls to
     * the inverse public static , and is this very slow.
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        int count = (int) Math
                .floor(((jd - HEBREW_EPOCH) * 98496.0) / 35975351.0);
        this.year = count - 1;
        for (int i = count; jd >= new HebrewDate(i, 7, 1).getJulianDay(); i++) {
            year++;
        }
        int first = (jd < new HebrewDate(year, 1, 1).getJulianDay()) ? 7 : 1;
        this.month = first;
        for (int i = first; jd > new HebrewDate(year, i,
                getDaysInMonth(year, i)).getJulianDay(); i++) {
            month++;
        }
        this.day = (int) (jd - new HebrewDate(year, month, 1).getJulianDay()) + 1;

    }

}
