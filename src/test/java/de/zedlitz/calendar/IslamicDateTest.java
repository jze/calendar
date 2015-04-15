package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IslamicDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);

        assertEquals("correct julian date", 2453978.5, islamicDate
                .getJulianDay(), 0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        IslamicDate islamicDate = new IslamicDate();
        islamicDate.setJulianDay(2453978.5);

        assertEquals("year", 1427, islamicDate.getYear());
        assertEquals("month", 8, islamicDate.getMonth());
        assertEquals("day", 6, islamicDate.getDay());
    }

    public void testIsLeapYear() {
        IslamicDate islamicDate = new IslamicDate(1427, 8, 6);
        
        assertFalse("no leap year", islamicDate.isLeapYear());
    }
    
    public void testGetDayOfWeek() {
        final IslamicDate date = new IslamicDate(2453978.5);
        
        assertEquals("correct day of week", 4, date.getDayOfWeek());
    } 
}
