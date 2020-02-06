package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class JulianDateTest {
    @Test
    public void testGetJulianDate() {
        final JulianDate julianDate = new JulianDate(2006, 8, 18);

        assertEquals("correct julian date", 2453978.5, julianDate
                .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final JulianDate julianDate = new JulianDate();
        julianDate.setJulianDay(2453978.5);

        assertEquals("year", 2006, julianDate.getYear());
        assertEquals("month", 8, julianDate.getMonth());
        assertEquals("day", 18, julianDate.getDay());
    }

    @Test
    public void testGetDayOfWeek() {
        final JulianDate gregorianDate = new JulianDate(2006, 8, 18);

        assertEquals("correct day of week", 4, gregorianDate.getDayOfWeek());
    }
}
