package de.zedlitz.calendar;

/**
 * @author jzedlitz
 *
 */
public class GregorianDateTest extends AbstractDateTestCase {
    public void testGetJulianDate() {
        final GregorianDate gregorianDate = new GregorianDate(2006, 8, 31);

        assertEquals("correct julian date", 2453978.5, gregorianDate.getJulianDay(), 0);
    }

    public void testSetJulianDate() {
        final GregorianDate gregorianDate = new GregorianDate();
        gregorianDate.setJulianDay(2453978.5);

        assertEquals("year", 2006, gregorianDate.getYear());
        assertEquals("month", 8, gregorianDate.getMonth());
        assertEquals("day", 31, gregorianDate.getDay());
    }
    
    public void testGetDayOfWeek() {
        final GregorianDate gregorianDate = new GregorianDate(2006, 8, 31);
        
        assertEquals("correct day of week", 4, gregorianDate.getDayOfWeek());
    }
}
