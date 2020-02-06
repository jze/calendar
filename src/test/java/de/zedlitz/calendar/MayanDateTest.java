package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanDateTest {

    @Test
    public void testGetJulianDate() {
        final MayanDate mayanDate = new MayanDate(12, 19, 13, 10, 16);
        assertEquals("correct julian date", 2453978.5,
                mayanDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final MayanDate mayanDate = new MayanDate();
        mayanDate.setJulianDay(2453978.5);
        assertEquals("correct baktun", 12, mayanDate.getBaktun());
        assertEquals("correct katun", 19, mayanDate.getKatun());
        assertEquals("correct tun", 13, mayanDate.getTun());
        assertEquals("correct uinal", 10, mayanDate.getUinal());
        assertEquals("correct kin", 16, mayanDate.getKin());
    }

}
