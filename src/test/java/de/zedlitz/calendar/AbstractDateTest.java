package de.zedlitz.calendar;

import junit.framework.TestCase;

/**
 * @author jzedlitz
 * 
 */
public class AbstractDateTest extends TestCase {

    public void testGetModifiedJulianDay() {
        final AbstractDate date = new GregorianDate(2006, 8, 31);
        assertEquals("correct MJD", 53978, date.getModifiedJulianDay(), 0);
    }
}
