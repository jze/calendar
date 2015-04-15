package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class MayanDate extends AbstractDate {
    static final double MAYAN_COUNT_EPOCH = 584282.5;

    private int baktun;

    private int katun;

    private int tun;

    private int uinal;

    private int kin;

    public MayanDate() {

    }

    public MayanDate(double julianDay) {
        this.setJulianDay(julianDay);
    }

    public MayanDate(int baktun, int katun, int tun, int uinal, int kin) {
        this.baktun = baktun;
        this.katun = katun;
        this.tun = tun;
        this.uinal = uinal;
        this.kin = kin;
    }

    /**
     * @return the baktun
     */
    public int getBaktun() {
        return this.baktun;
    }

    /**
     * @param baktun
     *            the baktun to set
     */
    public void setBaktun(int baktun) {
        this.baktun = baktun;
    }

    /**
     * @return the katun
     */
    public int getKatun() {
        return this.katun;
    }

    /**
     * @param katun
     *            the katun to set
     */
    public void setKatun(int katun) {
        this.katun = katun;
    }

    /**
     * @return the kin
     */
    public int getKin() {
        return this.kin;
    }

    /**
     * @param kin
     *            the kin to set
     */
    public void setKin(int kin) {
        this.kin = kin;
    }

    /**
     * @return the tun
     */
    public int getTun() {
        return this.tun;
    }

    /**
     * @param tun
     *            the tun to set
     */
    public void setTun(int tun) {
        this.tun = tun;
    }

    /**
     * @return the uinal
     */
    public int getUinal() {
        return this.uinal;
    }

    /**
     * @param uinal
     *            the uinal to set
     */
    public void setUinal(int uinal) {
        this.uinal = uinal;
    }

    /**
     * Determine Julian day from Mayan long count
     * 
     */
    public double getJulianDay() {
        return MAYAN_COUNT_EPOCH + (baktun * 144000) + (katun * 7200)
                + (tun * 360) + (uinal * 20) + kin;
    }

    /**
     * Calculate Mayan long count from Julian day.
     */

    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        double d = jd - MAYAN_COUNT_EPOCH;
        baktun = (int) Math.floor(d / 144000);
        d = Astro.mod(d, 144000);
        katun = (int) Math.floor(d / 7200);
        d = Astro.mod(d, 7200);
        tun = (int) Math.floor(d / 360);
        d = Astro.mod(d, 360);
        uinal = (int) Math.floor(d / 20);
        kin = Astro.mod(d, 20);
    }
}
