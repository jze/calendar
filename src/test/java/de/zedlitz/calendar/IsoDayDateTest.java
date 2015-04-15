package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class IsoDayDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        final IsoDate isoDate = new IsoDate(2006, 35, 4);

        assertEquals("correct julian date", 2453978.5, isoDate.getJulianDay(),
                0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        final IsoDate isoDate = new IsoDate();
        isoDate.setJulianDay(2453978.5);

        assertEquals("correct year", 2006, isoDate.getYear());
        assertEquals("correct week", 35, isoDate.getWeek());
        assertEquals("correct day", 4, isoDate.getDay());
    }

}
