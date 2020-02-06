package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author jzedlitz
 */
public class IslamicDateTest {

    @Test
    public void testGetJulianDate() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);

        assertEquals("correct julian date", 2453978.5, islamicDate
                .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        IslamicDate islamicDate = new IslamicDate();
        islamicDate.setJulianDay(2453978.5);

        assertEquals("year", 1427, islamicDate.getYear());
        assertEquals("month", 8, islamicDate.getMonth());
        assertEquals("day", 6, islamicDate.getDay());
    }

    @Test
    public void testIsLeapYear() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);

        assertFalse("no leap year", islamicDate.isLeapYear());
    }

    @Test
    public void testGetDayOfWeek() {
        final IslamicDate date = new IslamicDate(2453978.5);

        assertEquals("correct day of week", 4, date.getDayOfWeek());
    }
}
