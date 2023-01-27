package core.simulation.core;

import core.util.TextureUtils;
import core.math.vector.Vector3;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.celestialobjects.Planet;
import core.simulation.physics.celestialobjects.Ring;
import core.simulation.physics.celestialobjects.Star;

import java.awt.*;

public final class BasicCelestialObjects
{
    /**
     * Stars.
     */
    public static CelestialObject SUN = new Star(new Vector3(0, 0, 0),  696_340, 1.989e30, new Vector3(0, 0, 0), 0, 7.25, 4.83, "Sun");
    public static CelestialObject STEPHENSON_2_18 = new Star(new Vector3(0, 0, 0),  1.497131e9, 3.1824e31, new Vector3(0, 0, 0), 0, 0, 1.734, "Stephenson 2-18");
    public static CelestialObject BETELGEUSE = new Star(new Vector3(0, 0, 0),  621_483_450, 3.53e31, new Vector3(0, 0, 0), 0, 0, 0.58,"Betelgeuse");
    public static CelestialObject QUASAR_3C_273 = new Star(new Vector3(0, 0, 0),  270_916.869, 3.53e31, new Vector3(0, 0, 0), 0, 0, 12.9,"3C 273");

    /**
     * Planets.
     */
    public static CelestialObject MERCURY = new Planet(new Vector3(-0.387, 0, 0), 2439.7, 3.285e23, new Vector3(0, 0, 47.87), 10.83 / 3.6, 0.034, 7.0, "Mercury");
    public static CelestialObject VENUS = new Planet(new Vector3(-0.723, 0, 0), 6051.8, 4.867e24, new Vector3(0, 0, -35.02), 6.52 / 3.6, 177.4, 3.4, "Venus");
    public static CelestialObject EARTH = new Planet(new Vector3(-1, 0, 0), 6378.137, 6356.752, 5.972e24, new Vector3(0, 0, 29.78), 1574 / 3.6, 23.4, 0.0, "Earth");
    public static CelestialObject MARS = new Planet(new Vector3(-1.52, 0, 0), 3396.2, 3376.2, 6.39e23, new Vector3(0, 0, 24.077), 866 / 3.6, 25.2, 1.8, "Mars");
    public static CelestialObject JUPITER = new Planet(new Vector3(-5.2, 0, 0), 71492, 66854, 1.898e27, new Vector3(0, 0, 13.07), 45_583 / 3.6, 3.1, 1.3, "Jupiter");
    public static CelestialObject SATURN = new Planet(new Vector3(-9.58, 0, 0), 60268, 54364, 5.683e26, new Vector3(0, 0, 9.69), 36_840 / 3.6, 26.7, 2.5, "Saturn");
    public static CelestialObject URANUS = new Planet(new Vector3(-19.2, 0, 0), 25559, 24973, 8.681e25, new Vector3(0, 0, 6.81), 14_794 / 3.6, 97.8, 0.8, "Uranus");
    public static CelestialObject NEPTUNE = new Planet(new Vector3(-30.05, 0, 0), 24764, 24341, 1.024e26, new Vector3(0, 0, 5.43), 9_719 / 3.6, 28.3, 1.8, "Neptune");

    /**
     * Moons.
     */
    public static CelestialObject MOON = new Planet(new Vector3(-0.00257, 0, 0), 1738.1, 1736.0, 7.348e22, new Vector3(0, 0, 1.022), 16.7 / 3.6, 6.7, 5.1, "Moon");
    public static CelestialObject PHOBOS = new Planet(new Vector3(-0.000040107, 0, 0), new Vector3(27, 22, 18), 1.0659e16, new Vector3(0, 0, 2.138), 9.16 / 3.6, 0, 26.04, "Phobos");
    public static CelestialObject DEIMOS = new Planet(new Vector3(-0.00015682, 0, 0), new Vector3(15, 12.2, 11), 1.4762e15, new Vector3(0, 0, 1.3513), 1.32 / 3.6, 0, 27.58, "Deimos");
    public static CelestialObject IO = new Planet(new Vector3(-0.002819, 0, 0), new Vector3(3660.0, 3637.4, 3630.6), 8.931938e22, new Vector3(0, 0, 17.334), 271 / 3.6, 0, 2.213, "Io");
    public static CelestialObject EUROPA = new Planet(new Vector3(-0.00448469, 0, 0), 1560.8, 4.799844e22, new Vector3(0, 0, 13.74336), 116.7 / 3.6, 0.1, 1.791, "Europa");
    public static CelestialObject GANYMEDE = new Planet(new Vector3(-0.0071552, 0, 0), 2634.1, 1.4819e23, new Vector3(0, 0, 10.880), 96.3 / 3.6, 0.33, 2.214, "Ganymede");
    public static CelestialObject CALLISTO = new Planet(new Vector3(-0.0125851, 0, 0), 2410.3, 1.075938e23, new Vector3(0, 0, 8.204), 37.12 / 3.6, 0, 2.017, "Callisto");
    public static CelestialObject DIONE = new Planet(new Vector3(-0.00252274, 0, 0), new Vector3(1128.8, 1122.6, 1119.2), 1.095452e21, new Vector3(0, 0, 16.72), 217.84 / 3.6, 0, 4.58, "Dione");
    public static CelestialObject ENCELADUS = new Planet(new Vector3(-0.00159058, 0, 0), new Vector3(513.2, 502.8, 496.6), 1.08022e20, new Vector3(0, 0, 12.64), 48.15 / 3.6, 0, 0.02, "Enceladus");
    public static CelestialObject HYPERION = new Planet(new Vector3(-0.00159058, 0, 0), new Vector3(360.2, 266.0, 205.4), 1.77e19, new Vector3(0, 0, 5.07), 174.17 / 3.6, 0, 0.02, "Hyperion");
    public static CelestialObject IAPETUS = new Planet(new Vector3(-0.0238026, 0, 0), new Vector3(1492.0, 1492.0, 1424), 1.805635e21, new Vector3(0, 0, 3.26), 2.409 / 3.6, 0, 14.72, "Iapetus");
    public static CelestialObject MIMAS = new Planet(new Vector3(-0.00124025, 0, 0), new Vector3(415.6, 393.4, 381.2), 3.7493e19, new Vector3(0, 0, 14.28), 55.104 / 3.6, 0, 1.53, "Mimas");
    public static CelestialObject RHEA = new Planet(new Vector3(-0.0035235, 0, 0), new Vector3(1532.4, 1525.6, 1524.4), 2.306518e21, new Vector3(0, 0, 8.48), 44.26 / 3.6, 0, 0.35, "Rhea");
    public static CelestialObject TETHYS = new Planet(new Vector3(-0.00196941, 0, 0), new Vector3(1076.8, 1057.4, 1052.6), 6.17449e20, new Vector3(0, 0, 11.35), 73.657 / 3.6, 0, 1.09, "Tethys");
    public static CelestialObject TITAN = new Planet(new Vector3(-0.0081677, 0, 0), 2574.73, 1.3452e23, new Vector3(0, 0, 5.57), 42.273 / 3.6, 0, 0.0292, "Titan");
    public static CelestialObject ARIEL = new Planet(new Vector3(-0.001277, 0, 0), new Vector3(1162.2, 1155.8, 1155.4), 1.251e21, new Vector3(0, 0, 5.51), 60.132 / 3.6, 0, 0.31, "Ariel");
    public static CelestialObject MIRANDA = new Planet(new Vector3(-0.0008675, 0, 0), new Vector3(480, 468.4, 465.8), 6.33e19, new Vector3(0, 0, 6.68), 43.674 / 3.6, 0, 4.22, "Miranda");
    public static CelestialObject OBERON = new Planet(new Vector3(-0.00390059, 0, 0), 761.4, 3.03e21, new Vector3(0, 0, 3.15), 14.806 / 3.6, 0, 0.1, "Oberon");
    public static CelestialObject TITANIA = new Planet(new Vector3(-0.0029139, 0, 0), 788.9, 3.49e21, new Vector3(0, 0, 3.64), 23.723 / 3.6, 0, 0.14, "Titania");
    public static CelestialObject UMBRIEL = new Planet(new Vector3(-0.0017779, 0, 0), 584.7, 1.275e21, new Vector3(0, 0, 4.67), 36.937 / 3.6, 0, 0.36, "Umbriel");
    public static CelestialObject TRITON = new Planet(new Vector3(-0.0023714, 0, 0), 1353.4, 2.1390e22, new Vector3(0, 0, 4.39), -60.291 / 3.6, 0, 157.35, "Triton");
    public static CelestialObject CHARON = new Planet(new Vector3(-0.0001309, 0, 0), 635, 1.586e21, new Vector3(0, 0, 0.23), 26.027 / 3.6, 0, 98.80, "Charon");

    /**
     * Dwarf planets.
     */
    public static CelestialObject CERES = new Planet(new Vector3(-2.8, 0, 0), new Vector3(964.4, 964.2, 891.8), 9.3835e20, new Vector3(0, 0, 17.9), 327.93 / 3.6, 4.0, 10.6, "Ceres");
    public static CelestialObject PLUTO = new Planet(new Vector3(-39.48, 0, 0), 1188, 1.3e22, new Vector3(0, 0, -4.7), 47.18 / 3.6, 122.5, 17.2, "Pluto");
    public static CelestialObject MAKEMAKE = new Planet(new Vector3(-45.8, 0, 0), 1434, 1420, 3.1e21, new Vector3(0, 0, 4.419), 199.67 / 3.6, 0.0, 28.9, "Makemake");
    public static CelestialObject ERIS = new Planet(new Vector3(-67.68, 0, 0), 1163, 1.6e22, new Vector3(0, 0, 3.434), 282.14 / 3.6, 78.0, 44.0, "Eris");
    public static CelestialObject HAUMEA = new Planet(new Vector3(-43.13, 0, 0), new Vector3(2100, 1680, 1074), 1.6e22, new Vector3(0, 0, 4.531), 973.89 / 3.6, 0, 28.2, "Haumea");

    static
    {
        /**
         * Colors.
         */
        // Stars.
        SUN.setColor(new Color(229, 79, 0));
        STEPHENSON_2_18.setColor(new Color(243, 36, 13));
        BETELGEUSE.setColor(new Color(220, 90, 1));

        // Planets.
        MERCURY.setColor(TextureUtils.MERCURY_THUMBNAIL_PATH);
        VENUS.setColor(TextureUtils.VENUS_THUMBNAIL_PATH);
        EARTH.setColor(TextureUtils.EARTH_THUMBNAIL_PATH);
        MARS.setColor(TextureUtils.MARS_THUMBNAIL_PATH);
        JUPITER.setColor(TextureUtils.JUPITER_THUMBNAIL_PATH);
        SATURN.setColor(TextureUtils.SATURN_THUMBNAIL_PATH);
        URANUS.setColor(TextureUtils.URANUS_THUMBNAIL_PATH);
        NEPTUNE.setColor(TextureUtils.NEPTUNE_THUMBNAIL_PATH);

        // Moons.
        MOON.setColor(TextureUtils.MOON_THUMBNAIL_PATH);
        MOON.setColor(TextureUtils.MOON_THUMBNAIL_PATH);
        PHOBOS.setColor(TextureUtils.PHOBOS_THUMBNAIL_PATH);
        DEIMOS.setColor(TextureUtils.DEIMOS_THUMBNAIL_PATH);
        IO.setColor(TextureUtils.IO_THUMBNAIL_PATH);
        EUROPA.setColor(TextureUtils.EUROPA_THUMBNAIL_PATH);
        GANYMEDE.setColor(TextureUtils.GANYMEDE_THUMBNAIL_PATH);
        CALLISTO.setColor(TextureUtils.CALLISTO_THUMBNAIL_PATH);
        DIONE.setColor(TextureUtils.DIONE_THUMBNAIL_PATH);
        ENCELADUS.setColor(TextureUtils.ENCELADUS_THUMBNAIL_PATH);
        HYPERION.setColor(TextureUtils.HYPERION_THUMBNAIL_PATH);
        IAPETUS.setColor(TextureUtils.IAPETUS_THUMBNAIL_PATH);
        MIMAS.setColor(TextureUtils.MIMAS_THUMBNAIL_PATH);
        RHEA.setColor(TextureUtils.RHEA_THUMBNAIL_PATH);
        TETHYS.setColor(TextureUtils.TETHYS_THUMBNAIL_PATH);
        TITAN.setColor(TextureUtils.TITAN_THUMBNAIL_PATH);
        ARIEL.setColor(TextureUtils.ARIEL_THUMBNAIL_PATH);
        MIRANDA.setColor(TextureUtils.MIRANDA_THUMBNAIL_PATH);
        OBERON.setColor(TextureUtils.OBERON_THUMBNAIL_PATH);
        TITANIA.setColor(TextureUtils.TITANIA_THUMBNAIL_PATH);
        UMBRIEL.setColor(TextureUtils.UMBRIEL_THUMBNAIL_PATH);
        TRITON.setColor(TextureUtils.TRITON_THUMBNAIL_PATH);
        CHARON.setColor(TextureUtils.CHARON_THUMBNAIL_PATH);

        // Dwarf planets.
        CERES.setColor(TextureUtils.CERES_THUMBNAIL_PATH);
        PLUTO.setColor(TextureUtils.PLUTO_THUMBNAIL_PATH);
        MAKEMAKE.setColor(TextureUtils.MAKEMAKE_THUMBNAIL_PATH);
        ERIS.setColor(TextureUtils.ERIS_THUMBNAIL_PATH);
        HAUMEA.setColor(TextureUtils.HAUMEA_THUMBNAIL_PATH);

        /**
         * Textures.
         */
        // Stars.
        SUN.setTexture(TextureUtils.STAR_TEXTURE_PATH);
        STEPHENSON_2_18.setTexture(TextureUtils.STAR_TEXTURE_PATH);
        BETELGEUSE.setTexture(TextureUtils.STAR_TEXTURE_PATH);

        // Planets.
        MERCURY.setTexture(TextureUtils.MERCURY_TEXTURE_PATH);
        VENUS.setTexture(TextureUtils.VENUS_TEXTURE_PATH);
        EARTH.setTexture(TextureUtils.EARTH_TEXTURE_PATH);
        MARS.setTexture(TextureUtils.MARS_TEXTURE_PATH);
        JUPITER.setTexture(TextureUtils.JUPITER_TEXTURE_PATH);
        SATURN.setTexture(TextureUtils.SATURN_TEXTURE_PATH);
        URANUS.setTexture(TextureUtils.URANUS_TEXTURE_PATH);
        NEPTUNE.setTexture(TextureUtils.NEPTUNE_TEXTURE_PATH);

        // Moons.
        MOON.setTexture(TextureUtils.MOON_TEXTURE_PATH);
        PHOBOS.setTexture(TextureUtils.PHOBOS_TEXTURE_PATH);
        DEIMOS.setTexture(TextureUtils.DEIMOS_TEXTURE_PATH);
        IO.setTexture(TextureUtils.IO_TEXTURE_PATH);
        EUROPA.setTexture(TextureUtils.EUROPA_TEXTURE_PATH);
        GANYMEDE.setTexture(TextureUtils.GANYMEDE_TEXTURE_PATH);
        CALLISTO.setTexture(TextureUtils.CALLISTO_TEXTURE_PATH);
        DIONE.setTexture(TextureUtils.DIONE_TEXTURE_PATH);
        ENCELADUS.setTexture(TextureUtils.ENCELADUS_TEXTURE_PATH);
        HYPERION.setTexture(TextureUtils.HYPERION_TEXTURE_PATH);
        IAPETUS.setTexture(TextureUtils.IAPETUS_TEXTURE_PATH);
        MIMAS.setTexture(TextureUtils.MIMAS_TEXTURE_PATH);
        RHEA.setTexture(TextureUtils.RHEA_TEXTURE_PATH);
        TETHYS.setTexture(TextureUtils.TETHYS_TEXTURE_PATH);
        TITAN.setTexture(TextureUtils.TITAN_TEXTURE_PATH);
        ARIEL.setTexture(TextureUtils.ARIEL_TEXTURE_PATH);
        MIRANDA.setTexture(TextureUtils.MIRANDA_TEXTURE_PATH);
        OBERON.setTexture(TextureUtils.OBERON_TEXTURE_PATH);
        TITANIA.setTexture(TextureUtils.TITANIA_TEXTURE_PATH);
        UMBRIEL.setTexture(TextureUtils.UMBRIEL_TEXTURE_PATH);
        TRITON.setTexture(TextureUtils.TRITON_TEXTURE_PATH);
        CHARON.setTexture(TextureUtils.CHARON_TEXTURE_PATH);

        // Dwarf planets.
        CERES.setTexture(TextureUtils.CERES_TEXTURE_PATH);
        PLUTO.setTexture(TextureUtils.PLUTO_TEXTURE_PATH);
        MAKEMAKE.setTexture(TextureUtils.MAKEMAKE_TEXTURE_PATH);
        ERIS.setTexture(TextureUtils.ERIS_TEXTURE_PATH);
        HAUMEA.setTexture(TextureUtils.HAUMEA_TEXTURE_PATH);


        /**
         * Bump textures.
         */
        // Planets.
        MERCURY.setBumpTexture(TextureUtils.MERCURY_BUMP_TEXTURE_PATH);
        VENUS.setBumpTexture(TextureUtils.VENUS_BUMP_TEXTURE_PATH);
        EARTH.setBumpTexture(TextureUtils.EARTH_BUMP_TEXTURE_PATH);
        MARS.setBumpTexture(TextureUtils.MARS_BUMP_TEXTURE_PATH);

        // Moons.
        MOON.setBumpTexture(TextureUtils.MOON_BUMP_TEXTURE_PATH);

        // Dwarf planets.
        PLUTO.setBumpTexture(TextureUtils.PLUTO_BUMP_TEXTURE_PATH);

        /**
         * Rings.
         */
        // Planets.
        SATURN.setRing(new Ring(SATURN.getPosition(), 66_900_000, 139_826_000, TextureUtils.SATURN_RING_TEXTURE_PATH));
        URANUS.setRing(new Ring(URANUS.getPosition(), 41_837_000, 51_149_000, TextureUtils.URANUS_RING_TEXTURE_PATH));


        /*SUN.setColor(new Color(228, 91, 25));
        MERCURY.setColor(new Color(153, 153, 153));
        VENUS.setColor(new Color(180, 116, 33));
        EARTH.setColor(new Color(50, 87, 129));
        MARS.setColor(new Color(239, 123, 86));
        JUPITER.setColor(new Color(177, 126, 91));
        SATURN.setColor(new Color(196, 167, 111));
        URANUS.setColor(new Color(191, 228, 231));
        NEPTUNE.setColor(new Color(60, 89, 229));
        MOON.setColor(new Color(233, 218, 216));
        CERES.setColor(new Color(150, 137, 121));
        PLUTO.setColor(new Color(170, 136, 108));
        MAKEMAKE.setColor(new Color(181, 114, 97));
        ERIS.setColor(new Color(153, 156, 163));
        HAUMEA.setColor(new Color(150, 150, 150));**/
    }
}