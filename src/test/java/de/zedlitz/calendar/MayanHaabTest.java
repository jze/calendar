package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanHaabTest {

    @Test
    public void testSetJulianDay() {
        final MayanHaab mayanHaab = new MayanHaab();
        mayanHaab.setJulianDay(2453978.5);

        assertEquals("correct month", 8, mayanHaab.getMonth());
        assertEquals("correct day", 9, mayanHaab.getDay());
    }

}
