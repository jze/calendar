/* Based on a public domain JavaScript program 
 * by John Walker -- September, MIM http://www.fourmilab.ch/
 */
package de.zedlitz.calendar;

/**
 * @author jzedlitz
 * 
 */
public final class Astro {

    /** Julian day of J2000 epoch */
    private static final double J2000 = 2451545.0;

    /** Days in Julian century */
    private static final double JulianCentury = 36525.0;

    /** Days in Julian millennium */
    private static final double JulianMillennium = (JulianCentury * 10);

    /** Mean solar tropical year */
    static final double TropicalYear = 365.24219878;

    // Periodic terms to obtain true time
    private static final double[] EQUINOX_P_TERMS = new double[] { 485, 324.96,
            1934.136, 203, 337.23, 32964.467, 199, 342.08, 20.186, 182, 27.85,
            445267.112, 156, 73.14, 45036.886, 136, 171.52, 22518.443, 77,
            222.54, 65928.934, 74, 296.72, 3034.906, 70, 243.58, 9037.513, 58,
            119.81, 33718.147, 52, 297.17, 150.678, 50, 21.02, 2281.226, 45,
            247.54, 29929.562, 44, 325.15, 31555.956, 29, 60.93, 4443.417, 18,
            155.12, 67555.328, 17, 288.79, 4562.452, 16, 198.04, 62894.029, 14,
            199.76, 31436.921, 12, 95.39, 14577.848, 12, 287.11, 31931.756, 12,
            320.81, 34777.259, 9, 227.73, 1222.114, 8, 15.45, 16859.074 };

    private static final double[][] JDE0_TAB_1000 = new double[][] {
            new double[] { 1721139.29189, 365242.13740, 0.06134, 0.00111,
                    -0.00071 },
            new double[] { 1721233.25401, 365241.72562, -0.05323, 0.00907,
                    0.00025 },
            new double[] { 1721325.70455, 365242.49558, -0.11677, -0.00297,
                    0.00074 },
            new double[] { 1721414.39987, 365242.88257, -0.00769, -0.00933,
                    -0.00006 } };

    private static final double[][] JDE0_TAB_2000 = new double[][] {
            new double[] { 2451623.80984, 365242.37404, 0.05169, -0.00411,
                    -0.00057 },
            new double[] { 2451716.56767, 365241.62603, 0.00325, 0.00888,
                    -0.00030 },
            new double[] { 2451810.21715, 365242.01767, -0.11575, 0.00337,
                    0.00078 },
            new double[] { 2451900.05952, 365242.74049, -0.06223, -0.00823,
                    0.00032 } };

    /*
     * Periodic terms for nutation in longiude (delta \Psi) and obliquity (delta
     * \Epsilon) as given in table 21.A of Meeus, "Astronomical Algorithms",
     * first edition.
     */
    private static final int[] NUT_ARG_MULT = new int[] { 0, 0, 0, 0, 1, -2, 0,
            0, 2, 2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0,
            0, -2, 1, 0, 2, 2, 0, 0, 0, 2, 1, 0, 0, 1, 2, 2, -2, -1, 0, 2, 2,
            -2, 0, 1, 0, 0, -2, 0, 0, 2, 1, 0, 0, -1, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 1, 0, 1, 2, 0, -1, 2, 2, 0, 0, -1, 0, 1, 0, 0, 1, 2, 1, -2, 0,
            2, 0, 0, 0, 0, -2, 2, 1, 2, 0, 0, 2, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0,
            0, -2, 0, 1, 2, 2, 0, 0, 0, 2, 0, -2, 0, 0, 2, 0, 0, 0, -1, 2, 1,
            0, 2, 0, 0, 0, 2, 0, -1, 0, 1, -2, 2, 0, 2, 2, 0, 1, 0, 0, 1, -2,
            0, 1, 0, 1, 0, -1, 0, 0, 1, 0, 0, 2, -2, 0, 2, 0, -1, 2, 1, 2, 0,
            1, 2, 2, 0, 1, 0, 2, 2, -2, 1, 1, 0, 0, 0, -1, 0, 2, 2, 2, 0, 0, 2,
            1, 2, 0, 1, 0, 0, -2, 0, 2, 2, 2, -2, 0, 1, 2, 1, 2, 0, -2, 0, 1,
            2, 0, 0, 0, 1, 0, -1, 1, 0, 0, -2, -1, 0, 2, 1, -2, 0, 0, 0, 1, 0,
            0, 2, 2, 1, -2, 0, 2, 0, 1, -2, 1, 0, 2, 1, 0, 0, 1, -2, 0, -1, 0,
            1, 0, 0, -2, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0, -1, -1, 1,
            0, 0, 0, 1, 1, 0, 0, 0, -1, 1, 2, 2, 2, -1, -1, 2, 2, 0, 0, -2, 2,
            2, 0, 0, 3, 2, 2, 2, -1, 0, 2, 2 };

    private static final int[] NUT_ARG_COEFF = new int[] { -171996, -1742,
            92095, 89, /* 0, 0, 0, 0, 1 */
            -13187, -16, 5736, -31, /* -2, 0, 0, 2, 2 */
            -2274, -2, 977, -5, /* 0, 0, 0, 2, 2 */
            2062, 2, -895, 5, /* 0, 0, 0, 0, 2 */
            1426, -34, 54, -1, /* 0, 1, 0, 0, 0 */
            712, 1, -7, 0, /* 0, 0, 1, 0, 0 */
            -517, 12, 224, -6, /* -2, 1, 0, 2, 2 */
            -386, -4, 200, 0, /* 0, 0, 0, 2, 1 */
            -301, 0, 129, -1, /* 0, 0, 1, 2, 2 */
            217, -5, -95, 3, /* -2, -1, 0, 2, 2 */
            -158, 0, 0, 0, /* -2, 0, 1, 0, 0 */
            129, 1, -70, 0, /* -2, 0, 0, 2, 1 */
            123, 0, -53, 0, /* 0, 0, -1, 2, 2 */
            63, 0, 0, 0, /* 2, 0, 0, 0, 0 */
            63, 1, -33, 0, /* 0, 0, 1, 0, 1 */
            -59, 0, 26, 0, /* 2, 0, -1, 2, 2 */
            -58, -1, 32, 0, /* 0, 0, -1, 0, 1 */
            -51, 0, 27, 0, /* 0, 0, 1, 2, 1 */
            48, 0, 0, 0, /* -2, 0, 2, 0, 0 */
            46, 0, -24, 0, /* 0, 0, -2, 2, 1 */
            -38, 0, 16, 0, /* 2, 0, 0, 2, 2 */
            -31, 0, 13, 0, /* 0, 0, 2, 2, 2 */
            29, 0, 0, 0, /* 0, 0, 2, 0, 0 */
            29, 0, -12, 0, /* -2, 0, 1, 2, 2 */
            26, 0, 0, 0, /* 0, 0, 0, 2, 0 */
            -22, 0, 0, 0, /* -2, 0, 0, 2, 0 */
            21, 0, -10, 0, /* 0, 0, -1, 2, 1 */
            17, -1, 0, 0, /* 0, 2, 0, 0, 0 */
            16, 0, -8, 0, /* 2, 0, -1, 0, 1 */
            -16, 1, 7, 0, /* -2, 2, 0, 2, 2 */
            -15, 0, 9, 0, /* 0, 1, 0, 0, 1 */
            -13, 0, 7, 0, /* -2, 0, 1, 0, 1 */
            -12, 0, 6, 0, /* 0, -1, 0, 0, 1 */
            11, 0, 0, 0, /* 0, 0, 2, -2, 0 */
            -10, 0, 5, 0, /* 2, 0, -1, 2, 1 */
            -8, 0, 3, 0, /* 2, 0, 1, 2, 2 */
            7, 0, -3, 0, /* 0, 1, 0, 2, 2 */
            -7, 0, 0, 0, /* -2, 1, 1, 0, 0 */
            -7, 0, 3, 0, /* 0, -1, 0, 2, 2 */
            -7, 0, 3, 0, /* 2, 0, 0, 2, 1 */
            6, 0, 0, 0, /* 2, 0, 1, 0, 0 */
            6, 0, -3, 0, /* -2, 0, 2, 2, 2 */
            6, 0, -3, 0, /* -2, 0, 1, 2, 1 */
            -6, 0, 3, 0, /* 2, 0, -2, 0, 1 */
            -6, 0, 3, 0, /* 2, 0, 0, 0, 1 */
            5, 0, 0, 0, /* 0, -1, 1, 0, 0 */
            -5, 0, 3, 0, /* -2, -1, 0, 2, 1 */
            -5, 0, 3, 0, /* -2, 0, 0, 0, 1 */
            -5, 0, 3, 0, /* 0, 0, 2, 2, 1 */
            4, 0, 0, 0, /* -2, 0, 2, 0, 1 */
            4, 0, 0, 0, /* -2, 1, 0, 2, 1 */
            4, 0, 0, 0, /* 0, 0, 1, -2, 0 */
            -4, 0, 0, 0, /* -1, 0, 1, 0, 0 */
            -4, 0, 0, 0, /* -2, 1, 0, 0, 0 */
            -4, 0, 0, 0, /* 1, 0, 0, 0, 0 */
            3, 0, 0, 0, /* 0, 0, 1, 2, 0 */
            -3, 0, 0, 0, /* -1, -1, 1, 0, 0 */
            -3, 0, 0, 0, /* 0, 1, 1, 0, 0 */
            -3, 0, 0, 0, /* 0, -1, 1, 2, 2 */
            -3, 0, 0, 0, /* 2, -1, -1, 2, 2 */
            -3, 0, 0, 0, /* 0, 0, -2, 2, 2 */
            -3, 0, 0, 0, /* 0, 0, 3, 2, 2 */
            -3, 0, 0, 0 /* 2, -1, 0, 2, 2 */
    };

    /**
     * Table of observed Delta T values at the beginning of even numbered years
     * from 1620 through 2002.
     */
    private static final double[] DELTA_T_TAB = new double[] { 121, 112, 103,
            95, 88, 82, 77, 72, 68, 63, 60, 56, 53, 51, 48, 46, 44, 42, 40, 38,
            35, 33, 31, 29, 26, 24, 22, 20, 18, 16, 14, 12, 11, 10, 9, 8, 7, 7,
            7, 7, 7, 7, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10,
            11, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 14, 14, 15,
            15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 15, 15, 14, 13,
            13.1, 12.5, 12.2, 12, 12, 12, 12, 12, 12, 11.9, 11.6, 11, 10.2,
            9.2, 8.2, 7.1, 6.2, 5.6, 5.4, 5.3, 5.4, 5.6, 5.9, 6.2, 6.5, 6.8,
            7.1, 7.3, 7.5, 7.6, 7.7, 7.3, 6.2, 5.2, 2.7, 1.4, -1.2, -2.8, -3.8,
            -4.8, -5.5, -5.3, -5.6, -5.7, -5.9, -6, -6.3, -6.5, -6.2, -4.7,
            -2.8, -0.1, 2.6, 5.3, 7.7, 10.4, 13.3, 16, 18.2, 20.2, 21.1, 22.4,
            23.5, 23.8, 24.3, 24, 23.9, 23.9, 23.7, 24, 24.3, 25.3, 26.2, 27.3,
            28.2, 29.1, 30, 30.7, 31.4, 32.2, 33.1, 34, 35, 36.5, 38.3, 40.2,
            42.2, 44.5, 46.5, 48.5, 50.5, 52.2, 53.8, 54.9, 55.8, 56.9, 58.3,
            60, 61.6, 63, 65, 66.6 };

    private static final double[] O_TERMS = new double[] { -4680.93, -1.55,
            1999.25, -51.38, -249.67, -39.05, 7.12, 27.87, 5.79, 2.45 };

    /** Arc-seconds to radians. */
    public static double astor(final double a) {
        return a * (Math.PI / (180.0 * 3600.0));
    }

    /**
     * Degrees to radians.
     */
    public static double dtr(final double d) {
        return (d * Math.PI) / 180.0;
    }

    /**
     * Radians to degrees.
     */
    public static double rtd(final double r) {
        return (r * 180.0) / Math.PI;
    }

    /**
     * Range reduce angle in degrees.
     */
    public static double fixangle(final double a) {
        return a - 360.0 * (Math.floor(a / 360.0));
    }

    /**
     * Range reduce angle in radians.
     */
    public static double fixangr(final double a) {
        return a - (2 * Math.PI) * (Math.floor(a / (2 * Math.PI)));
    }

    /**
     * Sine of an angle in degrees
     */
    public static double dsin(final double d) {
        return Math.sin(dtr(d));
    }

    /**
     * Cosine of an angle in degrees
     */
    public static double dcos(final double d) {
        return Math.cos(dtr(d));
    }

    /**
     * Modulus public static double which works for non-integers.
     */
    public static int mod(final double a, final double b) {
        return (int) (a - (b * Math.floor(a / b)));
    }

    /**
     * Modulus public static double which returns numerator if modulus is zero
     */
    public static int amod(final double a, final double b) {
        return mod(a - 1, b) + 1;
    }

    /**
     * Convert Julian time to hour, minutes, and seconds, returned as a
     * three-element array.
     */
    public static int[] jhms(final double julianTime) {
        double ij;

        double j = julianTime + 0.5; /* Astronomical to civil */
        ij = ((j - Math.floor(j)) * 86400.0) + 0.5;
        return new int[] { (int) Math.floor(ij / 3600),
                (int) Math.floor((ij / 60) % 60), (int) Math.floor(ij % 60) };
    }

    /**
     * Calculate day of week from Julian day
     */
    public static int jwday(final double j) {
        return mod(Math.floor((j + 1.5)), 7);
    }

    /**
     * Calculate the obliquity of the ecliptic for a given Julian date. This
     * uses Laskar's tenth-degree polynomial fit (J. Laskar, Astronomy and
     * Astrophysics, Vol. 157, page 68 [1986]) which is accurate to within 0.01
     * arc second between AD 1000 and AD 3000, and within a few seconds of arc
     * for +/-10000 years around AD 2000. If we're outside the range in which
     * this fit is valid (deep time) we simply return the J2000 value of the
     * obliquity, which happens to be almost precisely the mean.
     */
    public static double obliqeq(final double jd) {

        final double u = (jd - J2000) / (JulianCentury * 100);
        double v = u;

        double eps = 23 + (26 / 60.0) + (21.448 / 3600.0);

        if (Math.abs(u) < 1.0) {
            for (int i = 0; i < 10; i++) {
                eps += (O_TERMS[i] / 3600.0) * v;
                v *= u;
            }
        }
        return eps;
    }

    /**
     * Calculate the nutation in longitude, deltaPsi, and obliquity,
     * deltaEpsilon for a given Julian date jd. Results are returned as a two
     * element Array giving (deltaPsi, deltaEpsilon) in degrees.
     */
    public static double[] nutation(final double jd) {
        double t = (jd - 2451545.0) / 36525.0;
        final double t2 = t * t;
        final double t3 = t * t2;

        /*
         * Calculate angles. The correspondence between the elements of our
         * array and the terms cited in Meeus are:
         * 
         * ta[0] = D ta[0] = M ta[2] = M' ta[3] = F ta[4] = \Omega
         * 
         */
        double[] ta = new double[5];
        ta[0] = dtr(297.850363 + 445267.11148 * t - 0.0019142 * t2 + t3
                / 189474.0);
        ta[1] = dtr(357.52772 + 35999.05034 * t - 0.0001603 * t2 - t3
                / 300000.0);
        ta[2] = dtr(134.96298 + 477198.867398 * t + 0.0086972 * t2 + t3
                / 56250.0);
        ta[3] = dtr(93.27191 + 483202.017538 * t - 0.0036825 * t2 + t3 / 327270);
        ta[4] = dtr(125.04452 - 1934.136261 * t + 0.0020708 * t2 + t3
                / 450000.0);

        /*
         * Range reduce the angles in case the sine and cosine public static
         * double s don't do it as accurately or quickly.
         */
        for (int i = 0; i < 5; i++) {
            ta[i] = fixangr(ta[i]);
        }

        final double to10 = t / 10.0;
        double dp = 0;
        double de = 0;
        for (int i = 0; i < 63; i++) {
            double ang = 0;
            for (int j = 0; j < 5; j++) {
                if (NUT_ARG_MULT[(i * 5) + j] != 0) {
                    ang += NUT_ARG_MULT[(i * 5) + j] * ta[j];
                }
            }
            dp += (NUT_ARG_COEFF[(i * 4) + 0] + NUT_ARG_COEFF[(i * 4) + 1]
                    * to10)
                    * Math.sin(ang);
            de += (NUT_ARG_COEFF[(i * 4) + 2] + NUT_ARG_COEFF[(i * 4) + 3]
                    * to10)
                    * Math.cos(ang);
        }

        /*
         * Return the result, converting from ten thousandths of arc seconds to
         * radians in the process.
         */
        final double deltaPsi = dp / (3600.0 * 10000.0);
        final double deltaEpsilon = de / (3600.0 * 10000.0);

        return new double[] { deltaPsi, deltaEpsilon };
    }

    /**
     * Convert celestial (ecliptical) longitude and latitude into right
     * ascension (in degrees) and declination. We must supply the time of the
     * conversion in order to compensate correctly for the varying obliquity of
     * the ecliptic over time. The right ascension and declination are returned
     * as a two-element Array in that order.
     */
    public static double[] ecliptoeq(final double jd, double Lambda, double Beta) {

        /* Obliquity of the ecliptic. */
        final double eps = dtr(obliqeq(jd));

        final double Ra = fixangle(rtd(Math
                .atan2((Math.cos(eps) * Math.sin(dtr(Lambda)) - (Math
                        .tan(dtr(Beta)) * Math.sin(eps))), Math
                        .cos(dtr(Lambda)))));
        final double Dec = rtd(Math
                .asin((Math.sin(eps) * Math.sin(dtr(Lambda)) * Math
                        .cos(dtr(Beta)))
                        + (Math.sin(dtr(Beta)) * Math.cos(eps))));

        return new double[] { Ra, Dec };
    }

    /**
     * Determine the difference, in seconds, between Dynamical time and
     * Universal time.
     */
    public static double deltat(int year) {
        double deltaT;
        if ((year >= 1620) && (year <= 2000)) {
            int i = (int) Math.floor((year - 1620) / 2);
            double f = ((year - 1620) / 2) - i; /* Fractional part of year */
            deltaT = DELTA_T_TAB[i]
                    + ((DELTA_T_TAB[i + 1] - DELTA_T_TAB[i]) * f);
        } else {
            double t = (year - 2000) / 100;
            if (year < 948) {
                deltaT = 2177 + (497 * t) + (44.1 * t * t);
            } else {
                deltaT = 102 + (102 * t) + (25.3 * t * t);
                if ((year > 2000) && (year < 2100)) {
                    deltaT += 0.37 * (year - 2100);
                }
            }
        }
        return deltaT;
    }

    /**
     * Determine the Julian Ephemeris Day of an equinox or solstice. The "which"
     * argument selects the item to be computed:
     * 
     * 0 March equinox 1 June solstice 2 September equinox 3 December solstice
     * 
     */
    public static double equinox(int year, int which) {
        /*
         * Initialise terms for mean equinox and solstices. We have two sets:
         * one for years prior to 1000 and a second for subsequent years.
         */
        final double[][] JDE0tab;
        final double Y;
        if (year < 1000) {
            JDE0tab = JDE0_TAB_1000;
            Y = year / 1000.0;
        } else {
            JDE0tab = JDE0_TAB_2000;
            Y = (year - 2000.0) / 1000.0;
        }

        final double JDE0 = JDE0tab[which][0] + (JDE0tab[which][1] * Y)
                + (JDE0tab[which][2] * Y * Y) + (JDE0tab[which][3] * Y * Y * Y)
                + (JDE0tab[which][4] * Y * Y * Y * Y);

        final double T = (JDE0 - 2451545.0) / 36525;
        final double W = (35999.373 * T) - 2.47;
        final double deltaL = 1 + (0.0334 * dcos(W)) + (0.0007 * dcos(2 * W));

        // Sum the periodic terms for time T
        double S = 0;
        int j = 0;
        for (int i = 0; i < 24; i++) {
            S += EQUINOX_P_TERMS[j]
                    * dcos(EQUINOX_P_TERMS[j + 1]
                            + (EQUINOX_P_TERMS[j + 2] * T));
            j += 3;
        }

        return JDE0 + ((S * 0.00001) / deltaL);
    }

    /**
     * Position of the Sun. Please see the comments on the return statement at
     * the end of this public static double which describe the array it returns.
     * We return intermediate values because they are useful in a variety of
     * other contexts.
     */
    public static double[] sunpos(double jd) {
        final double T = (jd - J2000) / JulianCentury;
        final double T2 = T * T;
        final double L0 = fixangle(280.46646 + (36000.76983 * T)
                + (0.0003032 * T2));

        final double M = fixangle(357.52911 + (35999.05029 * T)
                + (-0.0001537 * T2));

        final double e = 0.016708634 + (-0.000042037 * T)
                + (-0.0000001267 * T2);
        final double C = ((1.914602 + (-0.004817 * T) + (-0.000014 * T2)) * dsin(M))
                + ((0.019993 - (0.000101 * T)) * dsin(2 * M))
                + (0.000289 * dsin(3 * M));
        final double sunLong = L0 + C;
        final double sunAnomaly = M + C;
        final double sunR = (1.000001018 * (1 - (e * e)))
                / (1 + (e * dcos(sunAnomaly)));
        final double Omega = 125.04 - (1934.136 * T);
        final double Lambda = sunLong + (-0.00569) + (-0.00478 * dsin(Omega));
        final double epsilon0 = obliqeq(jd);
        final double epsilon = epsilon0 + (0.00256 * dcos(Omega));
        final double Alpha = fixangle(rtd(Math.atan2(dcos(epsilon0)
                * dsin(sunLong), dcos(sunLong))));

        final double Delta = rtd(Math.asin(dsin(epsilon0) * dsin(sunLong)));
        final double AlphaApp = fixangle(rtd(Math.atan2(dcos(epsilon)
                * dsin(Lambda), dcos(Lambda))));

        final double DeltaApp = rtd(Math.asin(dsin(epsilon) * dsin(Lambda)));

        return new double[] { // Angular quantities are expressed in
        // decimal degrees
                L0, // [0] Geometric mean longitude of the Sun
                M, // [1] Mean anomaly of the Sun
                e, // [2] Eccentricity of the Earth's orbit
                C, // [3] Sun's equation of the Centre
                sunLong, // [4] Sun's true longitude
                sunAnomaly, // [5] Sun's true anomaly
                sunR, // [6] Sun's radius vector in AU
                Lambda, // [7] Sun's apparent longitude at true equinox
                // of the date
                Alpha, // [8] Sun's true right ascension
                Delta, // [9] Sun's true declination
                AlphaApp, // [10] Sun's apparent right ascension
                DeltaApp // [11] Sun's apparent declination
        };
    }

    /**
     * Compute equation of time for a given moment. Returns the equation of time
     * as a fraction of a day.
     */
    public static double equationOfTime(double jd) {

        final double tau = (jd - J2000) / JulianMillennium;
        final double L0 = fixangle(280.4664567 + (360007.6982779 * tau)
                + (0.03032028 * tau * tau) + ((tau * tau * tau) / 49931)
                + (-((tau * tau * tau * tau) / 15300))
                + (-((tau * tau * tau * tau * tau) / 2000000)));

        final double alpha = sunpos(jd)[10];
        final double deltaPsi = nutation(jd)[0];
        final double epsilon = obliqeq(jd) + nutation(jd)[1];
        double E = L0 + (-0.0057183) + (-alpha) + (deltaPsi * dcos(epsilon));
        E = E - 20.0 * (Math.floor(E / 20.0));
        E = E / (24 * 60);

        return E;
    }

}
