package core.simulation.core;

import com.badlogic.gdx.graphics.Texture;
import core.assets.textures.Textures;
import core.math.vector.Vector3;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.celestialobjects.Planet;
import core.simulation.physics.celestialobjects.Ring;
import core.simulation.physics.celestialobjects.Star;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class BasicCelestialObjects
{
    public static ArrayList<BufferedImage> THUMBNAILS = new ArrayList<>();
    public static ArrayList<String> TEXTURE_PATHS = new ArrayList<>();
    public static ArrayList<Texture> TEXTURES = new ArrayList<>();
    public static ArrayList<CelestialObject> BASIC_CELESTIAL_OBJECTS = new ArrayList<>();

    /**
     * Stars.
     */
    public static CelestialObject SUN = new Star(new Vector3(0, 0, 0),  696_340, 1.989e30, new Vector3(0, 0, 0), 0, 7.25, 4.83, "Sun");
    public static CelestialObject STEPHENSON_2_18 = new Star(new Vector3(0, 0, 0),  1.497131e9, 3.1824e31, new Vector3(0, 0, 0), 0, 0, 1.73, "Stephenson 2-18");
    public static CelestialObject BETELGEUSE = new Star(new Vector3(0, 0, 0),  621_483_450, 3.53e31, new Vector3(0, 0, 0), 0, 0, 0.58, "Betelgeuse");
    public static CelestialObject UY_SCUTI = new Star(new Vector3(0, 0, 0),  1.18935e9, 1.69065e31, new Vector3(0, 0, 0), 0, 0, 10.56, "UY Scuti");
    public static CelestialObject PROXIMA_CENTAURI = new Star(new Vector3(0, 0, 0),  107_375.628, 2.4286e29, new Vector3(0, 0, 0), 0, 0, 0.0116, "Proxima Centauri");

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
    public static CelestialObject AMALTHEA = new Planet(new Vector3(-0.0012124, 0, 0), new Vector3(250, 146, 128), 7.17e18, new Vector3(0, 0, 26.57), 46.16 / 3.6, 0, 0.4, "Amalthea");
    public static CelestialObject IO = new Planet(new Vector3(-0.002819, 0, 0), new Vector3(3660.0, 3637.4, 3630.6), 8.931938e22, new Vector3(0, 0, 17.334), 271 / 3.6, 0, 2.213, "Io");
    public static CelestialObject EUROPA = new Planet(new Vector3(-0.00448469, 0, 0), 1560.8, 4.799844e22, new Vector3(0, 0, 13.74336), 116.7 / 3.6, 0.1, 1.791, "Europa");
    public static CelestialObject GANYMEDE = new Planet(new Vector3(-0.0071552, 0, 0), 2634.1, 1.4819e23, new Vector3(0, 0, 10.880), 96.3 / 3.6, 0.33, 2.214, "Ganymede");
    public static CelestialObject CALLISTO = new Planet(new Vector3(-0.0125851, 0, 0), 2410.3, 1.075938e23, new Vector3(0, 0, 8.204), 37.12 / 3.6, 0, 2.017, "Callisto");
    public static CelestialObject DIONE = new Planet(new Vector3(-0.00252274, 0, 0), new Vector3(1128.8, 1122.6, 1119.2), 1.095452e21, new Vector3(0, 0, 16.72), 217.84 / 3.6, 0, 4.58, "Dione");
    public static CelestialObject ENCELADUS = new Planet(new Vector3(-0.00159058, 0, 0), new Vector3(513.2, 502.8, 496.6), 1.08022e20, new Vector3(0, 0, 12.64), 48.15 / 3.6, 0, 0.02, "Enceladus");
    public static CelestialObject HYPERION = new Planet(new Vector3(-0.00159058, 0, 0), new Vector3(360.2, 266.0, 205.4), 1.77e19, new Vector3(0, 0, 5.07), 174.17 / 3.6, 0, 0.02, "Hyperion");
    public static CelestialObject IAPETUS = new Planet(new Vector3(-0.0238026, 0, 0), new Vector3(1492.0, 1492.0, 1424), 1.805635e21, new Vector3(0, 0, 3.26), 2.409 / 3.6, 0, 17.28, "Iapetus");
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
    public static CelestialObject GONGGONG = new Planet(new Vector3(-67.485, 0, 0), 615, 1.75e21, new Vector3(0, 0, 3.626), 973.89 / 3.6, 0, 30.6273, "Gonggong");
    public static CelestialObject ORCUS = new Planet(new Vector3(-39.174, 0, 0), 910, 917, 6.348e20, new Vector3(0, 0, 4.759), 569.41 / 3.6, 0, 20.592, "Orcus");
    public static CelestialObject QUAOAR = new Planet(new Vector3(-43.694, 0, 0), 1138, 1036, 3e20, new Vector3(0, 0, 4.506), 772.66 / 3.6, 0, 7.9895, "Quaoar");
    public static CelestialObject SEDNA = new Planet(new Vector3(-525.606, 0, 0), 1490, 3.9e21, new Vector3(0, 0, 1.04), 936.16 / 3.6, 0, 11.934, "Sedna");
    public static CelestialObject IXION = new Planet(new Vector3(-39.802, 0, 0), 650, 3e20, new Vector3(0, 0, 4.66), 256.86 / 3.6, 0, 19.584, "Ixion");

    /**
     * Asteroids.
     */
    public static CelestialObject VESTA = new Planet(new Vector3(-2.36179, 0, 0), new Vector3(572.6, 557.2, 446.4), 2.59076e20, new Vector3(0, 0, 19.34), 308.961 / 3.6, 29, 7.14043, "Vesta");
    public static CelestialObject PALLAS = new Planet(new Vector3(-2.77, 0, 0), new Vector3(550, 516, 476), 2.04e20, new Vector3(0, 0, 17.885), 206.673 / 3.6, 84, 34.93, "Pallas");
    public static CelestialObject JUNO = new Planet(new Vector3(-2.6707, 0, 0), new Vector3(320, 267, 200), 28.6e18, new Vector3(0, 0, 17.93), 114.306 / 3.6, 0, 12.9817, "Juno");
    public static CelestialObject HYGIEA = new Planet(new Vector3(-3.1415, 0, 0), new Vector3(450, 430, 424), 87.4e18, new Vector3(0, 0, 16.76), 98.769 / 3.6, 0, 3.8316, "Hygiea");
    public static CelestialObject CHARIKLO = new Planet(new Vector3(-15.822, 0, 0), new Vector3(287.6, 270.4, 198.2), 87.4e18, new Vector3(0, 0, 16.76), 113.063 / 3.6, 0, 23.382, "Chariklo");

    /**
     * Exoplanets.
     */
    public static CelestialObject GJ_504_B = new Planet(new Vector3(-43.5, 0, 0), 80_240.68, 7.592e27, new Vector3(0, 0, 4.989), 45_583 / 3.6, 0, 141, "GJ 504 b");
    public static CelestialObject KEPLER_7_B = new Planet(new Vector3(-0.06067, 0, 0), 112_198.606, 8.37018e26, new Vector3(0, 0, 134.7), 45_583 / 3.6, 0, 86.5, "Kepler-7 b");
    public static CelestialObject KEPLER_22_B = new Planet(new Vector3(-0.849, 0, 0), 14_664.676, 2.15e26, new Vector3(0, 0, 31.86), 1574 / 3.6, 0, 89.764, "Kepler-22 b");
    public static CelestialObject KEPLER_452_B = new Planet(new Vector3(-1.046, 0, 0), 10_378.9, 1.9647e25, new Vector3(0, 0, 29.57), 1574 / 3.6, 0, 0, "Kepler-142 b");
    public static CelestialObject HAT_P_11_B = new Planet(new Vector3(-0.05254, 0, 0), 26_908.297, 1.5944e26, new Vector3(0, 0, 116.65), 9_719 / 3.6, 0, 89.05, "HAT-P-11 b");
    public static CelestialObject HD_189733 = new Planet(new Vector3(-0.03126, 0, 0), 75_905.49, 1.806e27, new Vector3(0, 0, 154.58), 45_583 / 3.6, 0, 85.76, "HD 189733 b");
    public static CelestialObject OGLE_2005_BLG_390L_B = new Planet(new Vector3(-2.6, 0, 0), 14_072.05, 3.285e25, new Vector3(0, 0, 8.61), 9_719 / 3.6, 0, 0, "OGLE 2005 BLG 390L b");
    public static CelestialObject PROXIMA_CENTAURI_B = new Planet(new Vector3(-0.04856, 0, 0), 6558.48, 6.39e24, new Vector3(0, 0, 47.17), 1574 / 3.6, 0, 0, "Proxima Centauri b");
    public static CelestialObject YZ_CETI_D = new Planet(new Vector3(-0.02851, 0, 0), 6558.47, 6.509e24, new Vector3(0, 0, 65.99), 1574 / 3.6, 0, 0, "YZ Ceti d");

    static
    {
        /**
         * Colors.
         */
        // Stars.
        SUN.setColor(Textures.SUN_THUMBNAIL_PATH);
        STEPHENSON_2_18.setColor(Textures.SUN_THUMBNAIL_PATH);
        BETELGEUSE.setColor(Textures.SUN_THUMBNAIL_PATH);
        UY_SCUTI.setColor(Textures.SUN_THUMBNAIL_PATH);
        PROXIMA_CENTAURI.setColor(Textures.SUN_THUMBNAIL_PATH);

        SUN.setColor(new Color(229, 79, 0));
        STEPHENSON_2_18.setColor(new Color(243, 36, 13));
        BETELGEUSE.setColor(new Color(220, 90, 1));
        UY_SCUTI.setColor(new Color(254, 74, 23));
        PROXIMA_CENTAURI.setColor(new Color(225, 43, 0));

        // Planets.
        MERCURY.setColor(Textures.MERCURY_THUMBNAIL_PATH);
        VENUS.setColor(Textures.VENUS_THUMBNAIL_PATH);
        EARTH.setColor(Textures.EARTH_THUMBNAIL_PATH);
        MARS.setColor(Textures.MARS_THUMBNAIL_PATH);
        JUPITER.setColor(Textures.JUPITER_THUMBNAIL_PATH);
        SATURN.setColor(Textures.SATURN_THUMBNAIL_PATH);
        URANUS.setColor(Textures.URANUS_THUMBNAIL_PATH);
        NEPTUNE.setColor(Textures.NEPTUNE_THUMBNAIL_PATH);

        // Moons.
        MOON.setColor(Textures.MOON_THUMBNAIL_PATH);
        PHOBOS.setColor(Textures.PHOBOS_THUMBNAIL_PATH);
        DEIMOS.setColor(Textures.DEIMOS_THUMBNAIL_PATH);
        AMALTHEA.setColor(Textures.AMALTHEA_THUMBNAIL_PATH);
        IO.setColor(Textures.IO_THUMBNAIL_PATH);
        EUROPA.setColor(Textures.EUROPA_THUMBNAIL_PATH);
        GANYMEDE.setColor(Textures.GANYMEDE_THUMBNAIL_PATH);
        CALLISTO.setColor(Textures.CALLISTO_THUMBNAIL_PATH);
        DIONE.setColor(Textures.DIONE_THUMBNAIL_PATH);
        ENCELADUS.setColor(Textures.ENCELADUS_THUMBNAIL_PATH);
        HYPERION.setColor(Textures.HYPERION_THUMBNAIL_PATH);
        IAPETUS.setColor(Textures.IAPETUS_THUMBNAIL_PATH);
        MIMAS.setColor(Textures.MIMAS_THUMBNAIL_PATH);
        RHEA.setColor(Textures.RHEA_THUMBNAIL_PATH);
        TETHYS.setColor(Textures.TETHYS_THUMBNAIL_PATH);
        TITAN.setColor(Textures.TITAN_THUMBNAIL_PATH);
        ARIEL.setColor(Textures.ARIEL_THUMBNAIL_PATH);
        MIRANDA.setColor(Textures.MIRANDA_THUMBNAIL_PATH);
        OBERON.setColor(Textures.OBERON_THUMBNAIL_PATH);
        TITANIA.setColor(Textures.TITANIA_THUMBNAIL_PATH);
        UMBRIEL.setColor(Textures.UMBRIEL_THUMBNAIL_PATH);
        TRITON.setColor(Textures.TRITON_THUMBNAIL_PATH);
        CHARON.setColor(Textures.CHARON_THUMBNAIL_PATH);

        // Dwarf planets.
        CERES.setColor(Textures.CERES_THUMBNAIL_PATH);
        PLUTO.setColor(Textures.PLUTO_THUMBNAIL_PATH);
        MAKEMAKE.setColor(Textures.MAKEMAKE_THUMBNAIL_PATH);
        ERIS.setColor(Textures.ERIS_THUMBNAIL_PATH);
        HAUMEA.setColor(Textures.HAUMEA_THUMBNAIL_PATH);
        GONGGONG.setColor(Textures.GONGGONG_THUMBNAIL_PATH);
        ORCUS.setColor(Textures.ORCUS_THUMBNAIL_PATH);
        QUAOAR.setColor(Textures.QUAOAR_THUMBNAIL_PATH);
        SEDNA.setColor(Textures.SEDNA_THUMBNAIL_PATH);
        IXION.setColor(Textures.IXION_THUMBNAIL_PATH);

        // Asteroids.
        VESTA.setColor(Textures.VESTA_THUMBNAIL_PATH);
        PALLAS.setColor(Textures.PALLAS_THUMBNAIL_PATH);
        JUNO.setColor(Textures.JUNO_THUMBNAIL_PATH);
        HYGIEA.setColor(Textures.HYGIEA_THUMBNAIL_PATH);
        CHARIKLO.setColor(Textures.CHARIKLO_THUMBNAIL_PATH);

        // Exoplanets.
        GJ_504_B.setColor(Textures.GJ_504_B_THUMBNAIL_PATH);
        KEPLER_7_B.setColor(Textures.KEPLER_7_B_THUMBNAIL_PATH);
        KEPLER_22_B.setColor(Textures.KEPLER_22_B_THUMBNAIL_PATH);
        KEPLER_452_B.setColor(Textures.KEPLER_452_B_THUMBNAIL_PATH);
        HAT_P_11_B.setColor(Textures.HAT_P_11_B_THUMBNAIL_PATH);
        HD_189733.setColor(Textures.HD_189733_THUMBNAIL_PATH);
        OGLE_2005_BLG_390L_B.setColor(Textures.OGLE_2005_BLG_390L_B_THUMBNAIL_PATH);
        PROXIMA_CENTAURI_B.setColor(Textures.PROXIMA_CENTAURI_B_THUMBNAIL_PATH);
        YZ_CETI_D.setColor(Textures.YZ_CETI_D_THUMBNAIL_PATH);

        /**
         * Textures.
         */
        // Stars.
        SUN.setTexturePath(Textures.STAR_TEXTURE_PATH);
        STEPHENSON_2_18.setTexturePath(Textures.STAR_TEXTURE_PATH);
        BETELGEUSE.setTexturePath(Textures.STAR_TEXTURE_PATH);
        UY_SCUTI.setTexturePath(Textures.STAR_TEXTURE_PATH);
        PROXIMA_CENTAURI.setTexturePath(Textures.STAR_TEXTURE_PATH);

        // Planets.
        MERCURY.setTexturePath(Textures.MERCURY_TEXTURE_PATH);
        VENUS.setTexturePath(Textures.VENUS_TEXTURE_PATH);
        EARTH.setTexturePath(Textures.EARTH_TEXTURE_PATH);
        MARS.setTexturePath(Textures.MARS_TEXTURE_PATH);
        JUPITER.setTexturePath(Textures.JUPITER_TEXTURE_PATH);
        SATURN.setTexturePath(Textures.SATURN_TEXTURE_PATH);
        URANUS.setTexturePath(Textures.URANUS_TEXTURE_PATH);
        NEPTUNE.setTexturePath(Textures.NEPTUNE_TEXTURE_PATH);

        // Moons.
        MOON.setTexturePath(Textures.MOON_TEXTURE_PATH);
        PHOBOS.setTexturePath(Textures.PHOBOS_TEXTURE_PATH);
        DEIMOS.setTexturePath(Textures.DEIMOS_TEXTURE_PATH);
        AMALTHEA.setTexturePath(Textures.AMALTHEA_TEXTURE_PATH);
        IO.setTexturePath(Textures.IO_TEXTURE_PATH);
        EUROPA.setTexturePath(Textures.EUROPA_TEXTURE_PATH);
        GANYMEDE.setTexturePath(Textures.GANYMEDE_TEXTURE_PATH);
        CALLISTO.setTexturePath(Textures.CALLISTO_TEXTURE_PATH);
        DIONE.setTexturePath(Textures.DIONE_TEXTURE_PATH);
        ENCELADUS.setTexturePath(Textures.ENCELADUS_TEXTURE_PATH);
        HYPERION.setTexturePath(Textures.HYPERION_TEXTURE_PATH);
        IAPETUS.setTexturePath(Textures.IAPETUS_TEXTURE_PATH);
        MIMAS.setTexturePath(Textures.MIMAS_TEXTURE_PATH);
        RHEA.setTexturePath(Textures.RHEA_TEXTURE_PATH);
        TETHYS.setTexturePath(Textures.TETHYS_TEXTURE_PATH);
        TITAN.setTexturePath(Textures.TITAN_TEXTURE_PATH);
        ARIEL.setTexturePath(Textures.ARIEL_TEXTURE_PATH);
        MIRANDA.setTexturePath(Textures.MIRANDA_TEXTURE_PATH);
        OBERON.setTexturePath(Textures.OBERON_TEXTURE_PATH);
        TITANIA.setTexturePath(Textures.TITANIA_TEXTURE_PATH);
        UMBRIEL.setTexturePath(Textures.UMBRIEL_TEXTURE_PATH);
        TRITON.setTexturePath(Textures.TRITON_TEXTURE_PATH);
        CHARON.setTexturePath(Textures.CHARON_TEXTURE_PATH);

        // Dwarf planets.
        CERES.setTexturePath(Textures.CERES_TEXTURE_PATH);
        PLUTO.setTexturePath(Textures.PLUTO_TEXTURE_PATH);
        MAKEMAKE.setTexturePath(Textures.MAKEMAKE_TEXTURE_PATH);
        ERIS.setTexturePath(Textures.ERIS_TEXTURE_PATH);
        HAUMEA.setTexturePath(Textures.HAUMEA_TEXTURE_PATH);
        GONGGONG.setTexturePath(Textures.GONGGONG_TEXTURE_PATH);
        ORCUS.setTexturePath(Textures.ORCUS_TEXTURE_PATH);
        QUAOAR.setTexturePath(Textures.QUAOAR_TEXTURE_PATH);
        SEDNA.setTexturePath(Textures.SEDNA_TEXTURE_PATH);
        IXION.setTexturePath(Textures.IXION_TEXTURE_PATH);

        // Asteroids.
        VESTA.setTexturePath(Textures.VESTA_TEXTURE_PATH);
        PALLAS.setTexturePath(Textures.PALLAS_TEXTURE_PATH);
        JUNO.setTexturePath(Textures.JUNO_TEXTURE_PATH);
        HYGIEA.setTexturePath(Textures.HYGIEA_TEXTURE_PATH);
        CHARIKLO.setTexturePath(Textures.CHARIKLO_TEXTURE_PATH);

        // Exoplanets.
        GJ_504_B.setTexturePath(Textures.GJ_504_B_TEXTURE_PATH);
        KEPLER_7_B.setTexturePath(Textures.KEPLER_7_B_TEXTURE_PATH);
        KEPLER_22_B.setTexturePath(Textures.KEPLER_22_B_TEXTURE_PATH);
        KEPLER_452_B.setTexturePath(Textures.KEPLER_452_B_TEXTURE_PATH);
        HAT_P_11_B.setTexturePath(Textures.HAT_P_11_B_TEXTURE_PATH);
        HD_189733.setTexturePath(Textures.HD_189733_TEXTURE_PATH);
        OGLE_2005_BLG_390L_B.setTexturePath(Textures.OGLE_2005_BLG_390L_B_TEXTURE_PATH);
        PROXIMA_CENTAURI_B.setTexturePath(Textures.PROXIMA_CENTAURI_B_TEXTURE_PATH);
        YZ_CETI_D.setTexturePath(Textures.YZ_CETI_D_TEXTURE_PATH);

        /**
         * Bump textures.
         */
        // Planets.
        MERCURY.setBumpTexturePath(Textures.MERCURY_BUMP_TEXTURE_PATH);
        VENUS.setBumpTexturePath(Textures.VENUS_BUMP_TEXTURE_PATH);
        EARTH.setBumpTexturePath(Textures.EARTH_BUMP_TEXTURE_PATH);
        MARS.setBumpTexturePath(Textures.MARS_BUMP_TEXTURE_PATH);

        // Moons.
        MOON.setBumpTexturePath(Textures.MOON_BUMP_TEXTURE_PATH);
        PHOBOS.setBumpTexturePath(Textures.PHOBOS_BUMP_TEXTURE_PATH);
        DEIMOS.setBumpTexturePath(Textures.DEIMOS_BUMP_TEXTURE_PATH);
        AMALTHEA.setBumpTexturePath(Textures.AMALTHEA_BUMP_TEXTURE_PATH);
        IO.setBumpTexturePath(Textures.IO_BUMP_TEXTURE_PATH);
        IAPETUS.setBumpTexturePath(Textures.IAPETUS_BUMP_TEXTURE_PATH);
        RHEA.setBumpTexturePath(Textures.RHEA_BUMP_TEXTURE_PATH);
        OBERON.setBumpTexturePath(Textures.OBERON_BUMP_TEXTURE_PATH);
        TITANIA.setBumpTexturePath(Textures.TITANIA_BUMP_TEXTURE_PATH);
        UMBRIEL.setBumpTexturePath(Textures.UMBRIEL_BUMP_TEXTURE_PATH);

        // Dwarf planets.
        PLUTO.setBumpTexturePath(Textures.PLUTO_BUMP_TEXTURE_PATH);
        GONGGONG.setBumpTexturePath(Textures.GONGGONG_BUMP_TEXTURE_PATH);
        ORCUS.setBumpTexturePath(Textures.ORCUS_BUMP_TEXTURE_PATH);
        QUAOAR.setBumpTexturePath(Textures.QUAOAR_BUMP_TEXTURE_PATH);

        // Asteroids.
        VESTA.setBumpTexturePath(Textures.VESTA_BUMP_TEXTURE_PATH);

        /**
         * Rings.
         */
        // Planets.
        SATURN.setRing(new Ring(SATURN.getPosition(), 122_340, 480_000, Textures.SATURN_RING_TEXTURE_PATH));
        URANUS.setRing(new Ring(URANUS.getPosition(), 41_837, 51_149, Textures.URANUS_RING_TEXTURE_PATH));

        // Dwarf planets.
        HAUMEA.setRing(new Ring(HAUMEA.getPosition(), 2217, 2287, Textures.URANUS_RING_TEXTURE_PATH));

        // Asteroids.
        CHARIKLO.setRing(new Ring(CHARIKLO.getPosition(), 390.6, 408.8, Textures.URANUS_RING_TEXTURE_PATH));


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