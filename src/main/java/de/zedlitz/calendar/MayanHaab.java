package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class MayanHaab extends AbstractDate {

    public static final String[] MAYAN_HAAB_MONTHS = new String[] { "Pop",
        "Uo", "Zip", "Zotz", "Tzec", "Xul", "Yaxkin", "Mol", "Chen", "Yax",
        "Zac", "Ceh", "Mac", "Kankin", "Muan", "Pax", "Kayab", "Cumku",
        "Uayeb" };
    private int month;

    private int day;

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
     * @see de.zedlitz.calendar.AbstractDate#getJulianDay()
     */
    public double getJulianDay() {
        throw new UnsupportedOperationException(
                "Converting from Mayan Haad is not well-defined.");
    }

    /**
     * @see de.zedlitz.calendar.AbstractDate#setJulianDay(double)
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        double lcount = jd - MayanDate.MAYAN_COUNT_EPOCH;
        int day = Astro.mod(lcount + 8 + ((18 - 1) * 20), 365);

        this.month = (int) Math.floor(day / 20) + 1;
        this.day = Astro.mod(day, 20);

    }

}
