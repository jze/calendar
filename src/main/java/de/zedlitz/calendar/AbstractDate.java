package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public abstract class AbstractDate {
    /** Epoch of Modified Julian Date system */
    private static final double JMJD = 2400000.5;

    public abstract double getJulianDay();

    public abstract void setJulianDay(double julianDay);

    public double getModifiedJulianDay() {
        return this.getJulianDay() - JMJD;
    }
}
