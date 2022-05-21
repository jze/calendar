package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class BahaiDateTest {

    @Test
    public void testGetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate(1, 9, 11, 9, 12);

        assertEquals(2453978.5, bahaiDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate();
        bahaiDate.setJulianDay(2453978.5);

        assertEquals(1, bahaiDate.getMajor());
        assertEquals(9, bahaiDate.getCycle());
        assertEquals(11, bahaiDate.getYear());
        assertEquals(9, bahaiDate.getMonth());
        assertEquals(12, bahaiDate.getDay());

    }

}
