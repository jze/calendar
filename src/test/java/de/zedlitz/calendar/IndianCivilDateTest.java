package de.zedlitz.calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jzedlitz
 */
public class IndianCivilDateTest {

    @Test
    public void testGetJulianDate() {
        final IndianCivilDate indianCivilDate = new IndianCivilDate(1928, 6, 9);

        assertEquals("correct julian date", 2453978.5, indianCivilDate
                .getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final IndianCivilDate indianCivilDate = new IndianCivilDate();
        indianCivilDate.setJulianDay(2453978.5);

        assertEquals("year", 1928, indianCivilDate.getYear());
        assertEquals("month", 6, indianCivilDate.getMonth());
        assertEquals("day", 9, indianCivilDate.getDay());

    }

}
