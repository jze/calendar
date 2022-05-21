package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author jzedlitz
 */
public class IslamicDateTest {

    @Test
    public void testGetJulianDate() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);

        assertEquals(2453978.5, islamicDate
                .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        IslamicDate islamicDate = new IslamicDate();
        islamicDate.setJulianDay(2453978.5);

        assertEquals(1427, islamicDate.getYear());
        assertEquals(8, islamicDate.getMonth());
        assertEquals(6, islamicDate.getDay());
    }

    @Test
    public void testIsLeapYear() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);

        assertFalse(islamicDate.isLeapYear());
    }

    @Test
    public void testGetDayOfWeek() {
        final IslamicDate date = new IslamicDate(2453978.5);

        assertEquals(4, date.getDayOfWeek());
    }
}
