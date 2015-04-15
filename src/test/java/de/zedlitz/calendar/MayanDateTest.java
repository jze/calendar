package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class MayanDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        final MayanDate mayanDate = new MayanDate(12, 19, 13, 10, 16);
        assertEquals("correct julian date", 2453978.5,
                mayanDate.getJulianDay(), 0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        final MayanDate mayanDate = new MayanDate();
        mayanDate.setJulianDay(2453978.5);
        assertEquals("correct baktun", 12, mayanDate.getBaktun());
        assertEquals("correct katun", 19, mayanDate.getKatun());
        assertEquals("correct tun", 13, mayanDate.getTun());
        assertEquals("correct uinal", 10, mayanDate.getUinal());
        assertEquals("correct kin", 16, mayanDate.getKin());
    }

}
