package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanTzolkinTest {

    @Test
    public void testSetJulianDay() {
        final MayanTzolkin mayanTzolkin = new MayanTzolkin();
        mayanTzolkin.setJulianDay(2453978.5);

        assertEquals(16, mayanTzolkin.getMonth());
        assertEquals(1, mayanTzolkin.getDay());
    }
}
