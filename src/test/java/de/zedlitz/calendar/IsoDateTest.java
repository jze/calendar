package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class IsoDateTest {

    @Test
    public void testGetJulianDate() {
        final IsoDayDate isoDate = new IsoDayDate(2006, 243);

        assertEquals(2453978.5, isoDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final IsoDayDate isoDate = new IsoDayDate();
        isoDate.setJulianDay(2453978.5);

        assertEquals(2006, isoDate.getYear());
        assertEquals(243, isoDate.getDay());
    }
}
