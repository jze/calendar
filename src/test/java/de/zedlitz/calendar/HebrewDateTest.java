package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class HebrewDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        final HebrewDate hebrewDate = new HebrewDate(5766, 6, 7);

        assertEquals("correct julian date", 2453978.5, hebrewDate
                .getJulianDay(), 0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        final HebrewDate hebrewDate = new HebrewDate();
        hebrewDate.setJulianDay(2453978.5);

        assertEquals("year", 5766, hebrewDate.getYear());
        assertEquals("month", 6, hebrewDate.getMonth());
        assertEquals("day", 7, hebrewDate.getDay());
    }

    public void testGetDaysInYear() {
        assertEquals("correct number of days", 354, HebrewDate
                .getDaysInYear(5766));
    }
}
