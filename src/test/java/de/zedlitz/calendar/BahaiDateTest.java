package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class BahaiDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate(1, 9, 11, 9, 12);

        assertEquals("correct julian date", 2453978.5,
                bahaiDate.getJulianDay(), 0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        final BahaiDate bahaiDate = new BahaiDate();
        bahaiDate.setJulianDay(2453978.5);

        assertEquals("correct major", 1, bahaiDate.getMajor());
        assertEquals("correct cycle", 9, bahaiDate.getCycle());
        assertEquals("correct year", 11, bahaiDate.getYear());
        assertEquals("correct month", 9, bahaiDate.getMonth());
        assertEquals("correct day", 12, bahaiDate.getDay());

    }

}
