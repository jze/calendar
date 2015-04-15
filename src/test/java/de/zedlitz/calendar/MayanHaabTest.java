package de.zedlitz.calendar;

import junit.framework.TestCase;

/**
 * @author jzedlitz
 * 
 */
public class MayanHaabTest extends TestCase {
    public void testSetJulianDay() {
        final MayanHaab mayanHaab = new MayanHaab();
        mayanHaab.setJulianDay(2453978.5);

        assertEquals("correct month", 8, mayanHaab.getMonth());
        assertEquals("correct day", 9, mayanHaab.getDay());
    }

}
