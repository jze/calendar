package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 * 
 */
public class BahaiDateTest   {

   @Test
    public void testGetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate(1, 9, 11, 9, 12);

        assertEquals("correct julian date", 2453978.5,
                bahaiDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate();
        bahaiDate.setJulianDay(2453978.5);

        assertEquals("correct major", 1, bahaiDate.getMajor());
        assertEquals("correct cycle", 9, bahaiDate.getCycle());
        assertEquals("correct year", 11, bahaiDate.getYear());
        assertEquals("correct month", 9, bahaiDate.getMonth());
        assertEquals("correct day", 12, bahaiDate.getDay());

    }

}
