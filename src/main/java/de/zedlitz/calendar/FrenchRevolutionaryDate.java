package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public class FrenchRevolutionaryDate extends AbstractDate {

    public static final String[] MOIS = new String[] { "Vendémiaire",
            "Brumaire", "Frimaire", "Nivôse", "Pluviôse", "Ventôse",
            "Germinal", "Floréal", "Prairial", "Messidor", "Thermidor",
            "Fructidor", "(Sans-culottides)" };

    public static final String[] DECADE = new String[] { "I", "II", "III" };

    public static final String[] JOUR = new String[] { "du Primidi",
            "du Duodi", "du Tridi", "du Quartidi", "du Quintidi", "du Sextidi",
            "du Septidi", "du Octidi", "du Nonidi", "du Décadi",
            "------------", "de la Vertu", "du Génie", "du Travail",
            "de l'Opinion", "des Récompenses", "de la Révolution" };

    private int annee;

    private int mois;

    private int decade;

    private int jour;

    public FrenchRevolutionaryDate() {

    }

    public FrenchRevolutionaryDate(double julianDay) {
        this.setJulianDay(julianDay);
    }

    public FrenchRevolutionaryDate(int an, int mois, int decade, int jour) {
        this.annee = an;
        this.mois = mois;
        this.decade = decade;
        this.jour = jour;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return this.annee;
    }

    /**
     * @param annee
     *            the annee to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * @return the decade
     */
    public int getDecade() {
        return this.decade;
    }

    /**
     * @param decade
     *            the decade to set
     */
    public void setDecade(int decade) {
        this.decade = decade;
    }

    /**
     * @return the jour
     */
    public int getJour() {
        return this.jour;
    }

    /**
     * @param jour
     *            the jour to set
     */
    public void setJour(int jour) {
        this.jour = jour;
    }

    /**
     * @return the mois
     */
    public int getMois() {
        return this.mois;
    }

    /**
     * @param mois
     *            the mois to set
     */
    public void setMois(int mois) {
        this.mois = mois;
    }

    /**
     * Determine Julian day and fraction of the September equinox at the Paris
     * meridian in a given Gregorian year.
     */
    public double equinoxe_a_paris(int year) {

        // September equinox in dynamical time
        double equJED = Astro.equinox(year, 2);

        // Correct for delta T to obtain Universal time
        double equJD = equJED - (Astro.deltat(year) / (24 * 60 * 60));

        // Apply the equation of time to yield the apparent time at Greenwich
        double equAPP = equJD + Astro.equationOfTime(equJED);

        /*
         * Finally, we must correct for the constant difference between the
         * Greenwich meridian and that of Paris, 2°20'15" to the East.
         */
        double dtParis = (2 + (20 / 60.0) + (15 / (60 * 60.0))) / 360;
        double equParis = equAPP + dtParis;

        return equParis;
    }

    /**
     * Calculate Julian day during which the September equinox, reckoned from
     * the Paris meridian, occurred for a given Gregorian year.
     */
    public double paris_equinoxe_jd(final int year) {

        double ep = equinoxe_a_paris(year);
        double epg = Math.floor(ep - 0.5) + 0.5;

        return epg;
    }

    private final double FRENCH_REVOLUTIONARY_EPOCH = 2375839.5;

    /**
     * Determine the year in the French revolutionary calendar in which a given
     * Julian day falls. Returns an array of two elements:
     * 
     * [0] Anneé de la Révolution [1] Julian day number containing equinox for
     * this year.
     */
    public AnneeDeLaRevolution annee_da_la_revolution(double jd) {
        int guess = new GregorianDate(jd).getYear() - 2;

        double lasteq = paris_equinoxe_jd(guess);
        while (lasteq > jd) {
            guess--;
            lasteq = paris_equinoxe_jd(guess);
        }
        double nexteq = lasteq - 1;
        while (!((lasteq <= jd) && (jd < nexteq))) {
            lasteq = nexteq;
            guess++;
            nexteq = paris_equinoxe_jd(guess);
        }
        int adr = (int) Math.round((lasteq - FRENCH_REVOLUTIONARY_EPOCH)
                / Astro.TropicalYear) + 1;

        return new AnneeDeLaRevolution(adr, lasteq);
    }

    /**
     * Calculate date in the French Revolutionary calendar from Julian day. The
     * five or six "sansculottides" are considered a thirteenth month in the
     * results of this public static .
     */
    public void setJulianDay(double jd) {
        jd = Math.floor(jd) + 0.5;
        final AnneeDeLaRevolution adr = annee_da_la_revolution(jd);
        this.annee = adr.year;
        this.mois = (int) Math.floor((jd - adr.equinox) / 30) + 1;
        double j = (jd - adr.equinox) % 30;
        this.decade = (int) Math.floor(j / 10.0) + 1;
        this.jour = (int) (j % 10) + 1;
    }

    /**
     * Obtain Julian day from a given French Revolutionary calendar date.
     */
    public double getJulianDay() {
        double guess = FRENCH_REVOLUTIONARY_EPOCH
                + (Astro.TropicalYear * ((annee - 1) - 1));

        AnneeDeLaRevolution adr = new AnneeDeLaRevolution(annee - 1, 0);

        while (adr.year < annee) {
            adr = annee_da_la_revolution(guess);
            guess = adr.equinox + (Astro.TropicalYear + 2);
        }

        return adr.equinox + (30 * (mois - 1)) + (10 * (decade - 1))
                + (jour - 1);
    }

    private class AnneeDeLaRevolution {
        int year;

        double equinox;

        AnneeDeLaRevolution(int year, double equinox) {
            this.year = year;
            this.equinox = equinox;
        }
    }

}
