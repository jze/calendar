package de.zedlitz.calendar;

import junit.framework.TestCase;

/**
 * @author jzedlitz
 * 
 */
public class MayanTzolkinTest extends TestCase {

    public void testSetJulianDay() {
        final MayanTzolkin mayanTzolkin = new MayanTzolkin();
        mayanTzolkin.setJulianDay(2453978.5);

        assertEquals("correct month", 16, mayanTzolkin.getMonth());
        assertEquals("correct day", 1, mayanTzolkin.getDay());
    }
}
