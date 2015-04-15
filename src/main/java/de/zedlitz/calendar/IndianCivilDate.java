package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IndianCivilDate extends AbstractDate {
    public static final String[] INDIAN_CIVIL_WEEKDAYS = new String[] {
            "ravivara", "somavara", "mangalavara", "budhavara",
            "brahaspativara", "sukravara", "sanivara" };

    public static final String[] MONTHS = new String[] { "Caitra", "Vaisakha",
            "Jyaistha", "Asadha", "Sravana", "Bhadra", "Asvina", "Kartika",
            "Agrahayana", "Pausa", "Magha", "Phalguna"
    };

    private int year;

    private int month;

    private int day;

    public IndianCivilDate() {

    }

    public IndianCivilDate(double julianDate) {
        this.setJulianDay(julianDate);
    }

    public IndianCivilDate(int year, int month, int day) {
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
     * Obtain Julian day for Indian Civil date
     * 
     */
    public double getJulianDay() {

        double jd;
        int gyear = year + 78;
        boolean leap = GregorianDate.isLeapYear(gyear); // Is this a
        // leap year ?
        double start = new GregorianDate(gyear, 3, leap ? 21 : 22)
                .getJulianDay();
        int Caitra = leap ? 31 : 30;

        if (month == 1) {
            jd = start + (day - 1);
        } else {
            jd = start + Caitra;
            int m = month - 2;
            m = Math.min(m, 5);
            jd += m * 31;
            if (month >= 8) {
                m = month - 7;
                jd += m * 30;
            }
            jd += day - 1;
        }

        return jd;
    }

    /**
     * Calculate Indian Civil date from Julian day
     */
    public void setJulianDay(double jd) {

        int Saka = 79 - 1; // Offset in years from Saka era to Gregorian epoch
        int start = 80; // Day offset between Saka and Gregorian

        jd = Math.floor(jd) + 0.5;
        // Gregorian date for Julian day
        final GregorianDate greg = new GregorianDate(jd);
        // Is this a leap year?
        boolean leap = GregorianDate.isLeapYear(greg.getYear());
        this.year = greg.getYear() - Saka; // Tentative year in Saka era

        // JD at start of Gregorian year
        double greg0 = new GregorianDate(greg.getYear(), 1, 1).getJulianDay();
        double yday = jd - greg0; // Day number (0 based) in Gregorian year
        int Caitra = leap ? 31 : 30; // Days in Caitra this year

        if (yday < start) {
            // Day is at the end of the preceding Saka year
            year--;
            yday += Caitra + (31 * 5) + (30 * 3) + 10 + start;
        }
        yday -= start;
        if (yday < Caitra) {
            month = 1;
            day = (int) yday + 1;
        } else {
            double mday = yday - Caitra;
            if (mday < (31 * 5)) {
                month = (int) Math.floor(mday / 31) + 2;
                day = (int) (mday % 31) + 1;
            } else {
                mday -= 31 * 5;
                month = (int) Math.floor(mday / 30) + 7;
                day = (int) (mday % 30) + 1;
            }
        }
    }

}
