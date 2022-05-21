package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanDateTest {

    @Test
    public void testGetJulianDate() {
        final MayanDate mayanDate = new MayanDate(12, 19, 13, 10, 16);
        assertEquals(2453978.5, mayanDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final MayanDate mayanDate = new MayanDate();
        mayanDate.setJulianDay(2453978.5);
        assertEquals(12, mayanDate.getBaktun());
        assertEquals(19, mayanDate.getKatun());
        assertEquals(13, mayanDate.getTun());
        assertEquals(10, mayanDate.getUinal());
        assertEquals(16, mayanDate.getKin());
    }

}
