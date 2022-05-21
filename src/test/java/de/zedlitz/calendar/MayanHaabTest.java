package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class MayanHaabTest {

    @Test
    public void testSetJulianDay() {
        final MayanHaab mayanHaab = new MayanHaab();
        mayanHaab.setJulianDay(2453978.5);

        assertEquals(8, mayanHaab.getMonth());
        assertEquals(9, mayanHaab.getDay());
    }

}
