package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class IsoDayDateTest {

    @Test
    public void testGetJulianDate() {
        final IsoDate isoDate = new IsoDate(2006, 35, 4);

        assertEquals("correct julian date", 2453978.5, isoDate.getJulianDay(),
                0);
    }

    @Test
    public void testSetJulianDate() {
        final IsoDate isoDate = new IsoDate();
        isoDate.setJulianDay(2453978.5);

        assertEquals("correct year", 2006, isoDate.getYear());
        assertEquals("correct week", 35, isoDate.getWeek());
        assertEquals("correct day", 4, isoDate.getDay());
    }

}
