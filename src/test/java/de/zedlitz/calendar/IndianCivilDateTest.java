package de.zedlitz.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jzedlitz
 */
public class IndianCivilDateTest {

    @Test
    public void testGetJulianDate() {
        final IndianCivilDate indianCivilDate = new IndianCivilDate(1928, 6, 9);

        assertEquals(2453978.5, indianCivilDate.getJulianDay(), 0);
    }

    @Test
    public void testSetJulianDate() {
        final IndianCivilDate indianCivilDate = new IndianCivilDate();
        indianCivilDate.setJulianDay(2453978.5);

        assertEquals(1928, indianCivilDate.getYear());
        assertEquals(6, indianCivilDate.getMonth());
        assertEquals(9, indianCivilDate.getDay());

    }

}
