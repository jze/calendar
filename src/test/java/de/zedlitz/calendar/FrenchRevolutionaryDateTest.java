package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class FrenchRevolutionaryDateTest {

    @Test
    public void testGetJulianDate() {
        final FrenchRevolutionaryDate frenchRevolutionaryDate = new FrenchRevolutionaryDate(
                214, 12, 2, 4);

        assertEquals(2453978.5, frenchRevolutionaryDate
                .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final FrenchRevolutionaryDate frenchRevolutionaryDate = new FrenchRevolutionaryDate();
        frenchRevolutionaryDate.setJulianDay(2453978.5);

        assertEquals( 214, frenchRevolutionaryDate.getAnnee());
        assertEquals( 12, frenchRevolutionaryDate.getMois());
        assertEquals( 4, frenchRevolutionaryDate.getJour());
        assertEquals( 2, frenchRevolutionaryDate.getDecade());


    }

}
