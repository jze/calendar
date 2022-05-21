package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class HebrewDateTest {

    @Test
    public void testGetJulianDate() {
        final HebrewDate hebrewDate = new HebrewDate(5766, 6, 7);

        assertEquals(2453978.5, hebrewDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final HebrewDate hebrewDate = new HebrewDate();
        hebrewDate.setJulianDay(2453978.5);

        assertEquals(5766, hebrewDate.getYear());
        assertEquals(6, hebrewDate.getMonth());
        assertEquals(7, hebrewDate.getDay());
    }

    @Test
    public void testGetDaysInYear() {
        assertEquals(354, HebrewDate.getDaysInYear(5766));
    }
}
