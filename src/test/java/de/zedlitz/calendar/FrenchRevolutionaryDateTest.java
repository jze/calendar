package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class FrenchRevolutionaryDateTest extends AbstractDateTestCase {

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testGetJulianDate()
     */
    public void testGetJulianDate() {
        final FrenchRevolutionaryDate frenchRevolutionaryDate = new FrenchRevolutionaryDate(
                214, 12, 2, 4);

        assertEquals("correct julian day", 2453978.5, frenchRevolutionaryDate
                .getJulianDay(), 0);
    }

    /**
     * @see de.zedlitz.calendar.AbstractDateTestCase#testSetJulianDate()
     */
    public void testSetJulianDate() {
        final FrenchRevolutionaryDate frenchRevolutionaryDate = new FrenchRevolutionaryDate();
        frenchRevolutionaryDate.setJulianDay(2453978.5);

        assertEquals("correct year", 214, frenchRevolutionaryDate.getAnnee());
        assertEquals("correct mois", 12, frenchRevolutionaryDate.getMois());
        assertEquals("correct jour", 4, frenchRevolutionaryDate.getJour());
        assertEquals("correct decade", 2, frenchRevolutionaryDate.getDecade());
        

    }

}
