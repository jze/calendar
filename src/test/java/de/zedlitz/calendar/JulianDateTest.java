package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class JulianDateTest {
    @Test
    public void testGetJulianDate() {
        final JulianDate julianDate = new JulianDate(2006, 8, 18);

        assertEquals(2453978.5, julianDate                 .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final JulianDate julianDate = new JulianDate();
        julianDate.setJulianDay(2453978.5);

        assertEquals(2006, julianDate.getYear());
        assertEquals(8, julianDate.getMonth());
        assertEquals(18, julianDate.getDay());
    }

    @Test
    public void testGetDayOfWeek() {
        final JulianDate gregorianDate = new JulianDate(2006, 8, 18);

        assertEquals(4, gregorianDate.getDayOfWeek());
    }
}
