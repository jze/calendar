package de.zedlitz.calendar;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 * 
 */
public class AbstractDateTest  {

    @Test
    public void testGetModifiedJulianDay() {
        final AbstractDate date = new GregorianDate(2006, 8, 31);
        assertEquals("correct MJD", 53978, date.getModifiedJulianDay(), 0);
    }
}
