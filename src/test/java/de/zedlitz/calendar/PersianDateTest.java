package de.zedlitz.calendar;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 * 
 */
public class PersianDateTest   {
    @Test
    public void testSetJulianDate() {
        final PersianDate persianDate = new PersianDate();
        persianDate.setJulianDay(2453978.5);

        assertEquals("correct year", 1385, persianDate.getYear());
        assertEquals("correct month", 6, persianDate.getMonth());
        assertEquals("correct day", 9, persianDate.getDay());
    }

    @Test
    public void testGetJulianDate() {
        final PersianDate persianDate = new PersianDate(1385, 6, 9);

        assertEquals("correct julian date", 2453978.5, persianDate
                .getJulianDay(), 0);
    }
}
