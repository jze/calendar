package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jzedlitz
 */
public class GregorianDateTest {
    @Test
    public void testGetJulianDate() {
        final GregorianDate gregorianDate = new GregorianDate(2006, 8, 31);

        assertEquals("correct julian date", 2453978.5, gregorianDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final GregorianDate gregorianDate = new GregorianDate();
        gregorianDate.setJulianDay(2453978.5);

        assertEquals("year", 2006, gregorianDate.getYear());
        assertEquals("month", 8, gregorianDate.getMonth());
        assertEquals("day", 31, gregorianDate.getDay());
    }

    @Test
    public void testGetDayOfWeek() {
        final GregorianDate gregorianDate = new GregorianDate(2006, 8, 31);

        assertEquals("correct day of week", 4, gregorianDate.getDayOfWeek());
    }

    @Test
    public void testSetAndGet() {
        final GregorianDate gregorianDate = new GregorianDate();
        gregorianDate.setYear(2020);
        gregorianDate.setMonth(2);
        gregorianDate.setDay(6);

        assertEquals(2020, gregorianDate.getYear());
        assertEquals(2, gregorianDate.getMonth());
        assertEquals(6, gregorianDate.getDay());
    }

    @Test
    public void testIsLeapYear_1900() {
        final GregorianDate gregorianDate = new GregorianDate(1900, 2, 6);
        assertFalse("1900 was no leap year", gregorianDate.isLeapYear());
    }

    @Test
    public void testIsLeapYear_2000() {
        final GregorianDate gregorianDate = new GregorianDate(2000, 2, 6);
        assertTrue("2000 was a leap year.", gregorianDate.isLeapYear());
    }

    @Test
    public void testIsLeapYear_2019() {
        final GregorianDate gregorianDate = new GregorianDate(2019, 2, 6);
        assertFalse("2019 was no leap year.", gregorianDate.isLeapYear());
    }

    @Test
    public void testIsLeapYear_2020() {
        final GregorianDate gregorianDate = new GregorianDate(2020, 2, 6);
        assertTrue("2020 was a leap year.", gregorianDate.isLeapYear());
    }

    @Test
    public void testGetJulianDay() {
        final GregorianDate gregorianDate = new GregorianDate(2020, 2, 6);
        assertEquals(2458885.5, gregorianDate.getJulianDay(), 0.01);
    }

    @Test
    public void testSetJulianDate_leapYear() {
        final GregorianDate gregorianDate = new GregorianDate();
        gregorianDate.setJulianDay(2458885.5);

        assertEquals("year", 2020, gregorianDate.getYear());
        assertEquals("month", 2, gregorianDate.getMonth());
        assertEquals("day", 6, gregorianDate.getDay());
    }

    
    public void testGetModifiedJulianDay() {
        final AbstractDate date = new GregorianDate(2006, 8, 31);
        assertEquals("correct MJD", 53978, date.getModifiedJulianDay(), 0);
    }
}
