package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class IsoDateTest {

    @Test
    public void testGetJulianDate() {
        final IsoDayDate isoDate = new IsoDayDate(2006, 243);

        assertEquals("correct julian date", 2453978.5, isoDate.getJulianDay(),
                0);
    }

    @Test
    public void testSetJulianDate() {
        final IsoDayDate isoDate = new IsoDayDate();
        isoDate.setJulianDay(2453978.5);

        assertEquals("correct year", 2006, isoDate.getYear());
        assertEquals("correct day", 243, isoDate.getDay());
    }
}
