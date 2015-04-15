package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class MayanTzolkin extends AbstractDate {
    public static final String[] MAYAN_TZOLKIN_MONTHS = new String[] { "Imix",
            "Ik", "Akbal", "Kan", "Chicchan", "Cimi", "Manik", "Lamat",
            "Muluc", "Oc", "Chuen", "Eb", "Ben", "Ix", "Men", "Cib", "Caban",
            "Etznab", "Cauac", "Ahau" };

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
                "Converting from Mayan Tzolkin is not well-defined.");
    }

    /**
     * @see de.zedlitz.calendar.AbstractDate#setJulianDay(double)
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        double lcount = jd - MayanDate.MAYAN_COUNT_EPOCH;

        this.month = Astro.amod(lcount + 20, 20);
        this.day = Astro.amod(lcount + 4, 13);

    }
}
