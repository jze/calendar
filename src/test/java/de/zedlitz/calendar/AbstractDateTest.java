package de.zedlitz.calendar;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class AbstractDateTest {

    @Test
    public void testGetModifiedJulianDay() {
        final AbstractDate date = new GregorianDate(2006, 8, 31);
        assertEquals(53978, date.getModifiedJulianDay(), 0);
    }
}
