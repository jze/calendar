package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class IsoDayDateTest {

    @Test
    public void testGetJulianDate() {
        final IsoDate isoDate = new IsoDate(2006, 35, 4);

        assertEquals(2453978.5, isoDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final IsoDate isoDate = new IsoDate();
        isoDate.setJulianDay(2453978.5);

        assertEquals(2006, isoDate.getYear());
        assertEquals(35, isoDate.getWeek());
        assertEquals(4, isoDate.getDay());
    }

}
