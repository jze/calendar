package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanTzolkinTest {

    @Test
    public void testSetJulianDay() {
        final MayanTzolkin mayanTzolkin = new MayanTzolkin();
        mayanTzolkin.setJulianDay(2453978.5);

        assertEquals("correct month", 16, mayanTzolkin.getMonth());
        assertEquals("correct day", 1, mayanTzolkin.getDay());
    }
}
