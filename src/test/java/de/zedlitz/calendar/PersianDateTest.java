package de.zedlitz.calendar;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class PersianDateTest {
    @Test
    public void testSetJulianDate() {
        final PersianDate persianDate = new PersianDate();
        persianDate.setJulianDay(2453978.5);

        assertEquals(1385, persianDate.getYear());
        assertEquals(6, persianDate.getMonth());
        assertEquals(9, persianDate.getDay());
    }

    @Test
    public void testGetJulianDate() {
        final PersianDate persianDate = new PersianDate(1385, 6, 9);

        assertEquals(2453978.5, persianDate.getJulianDay(), 0);
    }
}
