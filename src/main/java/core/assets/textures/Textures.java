package core.assets.textures;

import core.settings.graphicspresets.Preset;

public final class Textures
{
    /**
     * Directories.
     */
    public static final String PLANET_TEXTURE_DIRECTORY = "src\\main\\resources\\planets\\";


    /**
     * Stars.
     */
    public static String STAR_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "sun\\sun.jpg";
    public static String STEPHENSON_2_18_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "stephenson 2-18\\stephenson 2-18.jpg";


    /**
     * Planets.
     */
    public static final String DEFAULT_PLANET_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "default\\planet.jpg";
    public static String MERCURY_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mercury\\mercury.jpg";
    public static String VENUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "venus\\venus.jpg";
    public static String EARTH_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth\\earth.jpg";
    public static String EARTH_NIGHT_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth\\earth_night_high_2.jpg";
    public static String MARS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars\\mars.jpg";
    public static String JUPITER_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter\\jupiter.jpg";
    public static String SATURN_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn\\saturn.jpg";
    public static String URANUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus\\uranus.jpg";
    public static String NEPTUNE_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "neptune\\neptune.jpg";

    public static String SATURN_RING_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn\\saturn_ring.jpg";
    public static String URANUS_RING_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus\\uranus_ring.jpg";

    public static String MERCURY_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mercury\\mercury_bump.png";
    public static String VENUS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "venus\\venus_bump.png";
    public static String EARTH_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth\\earth_bump.jpg";
    public static String MARS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars\\mars_bump.jpg";


    /**
     * Moons.
     */
    public static final String MOON_TEXTURE_DIRECTORY = "\\moons\\";
    public static String MOON_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth" + MOON_TEXTURE_DIRECTORY + "moon.jpg";
    public static String PHOBOS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars" + MOON_TEXTURE_DIRECTORY + "phobos.jpg";
    public static String DEIMOS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars" + MOON_TEXTURE_DIRECTORY + "deimos.jpg";
    public static String IO_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "io.jpg";
    public static String EUROPA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "europa.jpg";
    public static String GANYMEDE_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "ganymede.jpg";
    public static String CALLISTO_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "callisto.jpg";
    public static String AMALTHEA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "amalthea.jpg";
    public static String DIONE_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "dione.jpg";
    public static String ENCELADUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "enceladus.jpg";
    public static String HYPERION_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "hyperion.jpg";
    public static String IAPETUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "iapetus.jpg";
    public static String MIMAS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "mimas.jpg";
    public static String RHEA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "rhea.jpg";
    public static String TETHYS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "tethys.jpg";
    public static String TITAN_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "titan.jpg";
    public static String ARIEL_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "ariel.jpg";
    public static String MIRANDA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "miranda.jpg";
    public static String OBERON_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "oberon.jpg";
    public static String TITANIA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "titania.jpg";
    public static String UMBRIEL_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "umbriel.jpg";
    public static String TRITON_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "neptune" + MOON_TEXTURE_DIRECTORY + "triton.jpg";
    public static String CHARON_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "pluto" + MOON_TEXTURE_DIRECTORY + "charon.jpg";

    public static String MOON_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth" + MOON_TEXTURE_DIRECTORY + "moon_bump.jpg";
    public static String PHOBOS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars" + MOON_TEXTURE_DIRECTORY + "phobos_bump.jpg";
    public static String DEIMOS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars" + MOON_TEXTURE_DIRECTORY + "deimos_bump.jpg";
    public static String IO_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "io_bump.jpg";
    public static String AMALTHEA_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter" + MOON_TEXTURE_DIRECTORY + "amalthea_bump.jpg";
    public static String IAPETUS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "iapetus_bump.jpg";
    public static String RHEA_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn" + MOON_TEXTURE_DIRECTORY + "rhea_bump.jpg";
    public static String OBERON_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "oberon_bump.jpg";
    public static String TITANIA_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "titania_bump.jpg";
    public static String UMBRIEL_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus" + MOON_TEXTURE_DIRECTORY + "umbriel_bump.jpg";


    /**
     * Dwarf planets.
     */
    public static String PLUTO_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "pluto\\pluto.jpg";
    public static String CERES_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "ceres\\ceres.jpg";
    public static String MAKEMAKE_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "makemake\\makemake.jpg";
    public static String ERIS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "eris\\eris.jpg";
    public static String HAUMEA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "haumea\\haumea.jpg";
    public static String GONGGONG_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "gonggong\\gonggong.jpg";
    public static String ORCUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "orcus\\orcus.jpg";
    public static String QUAOAR_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "quaoar\\quaoar.jpg";
    public static String SEDNA_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "sedna\\sedna.jpg";
    public static String IXION_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "ixion\\ixion.jpg";

    public static String PLUTO_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "pluto\\pluto_bump.png";
    public static String GONGGONG_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "gonggong\\gonggong_bump.jpg";
    public static String ORCUS_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "orcus\\orcus_bump.jpg";
    public static String QUAOAR_BUMP_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "quaoar\\quaoar_bump.jpg";


    /**
     * Asteroids.
     */
    public static final String ASTEROID_TEXTURE_DIRECTORY = PLANET_TEXTURE_DIRECTORY + "\\asteroids\\";
    public static String VESTA_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "vesta\\vesta.jpg";
    public static String PALLAS_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "pallas\\pallas.jpg";
    public static String JUNO_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "juno\\juno.jpg";
    public static String HYGIEA_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "hygiea\\hygiea.jpg";
    public static String CHARIKLO_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "chariklo\\chariklo.jpg";

    public static String VESTA_BUMP_TEXTURE_PATH = ASTEROID_TEXTURE_DIRECTORY + "vesta\\vesta_bump.jpg";

    /**
     * Exoplanets.
     */
    public static final String EXOPLANET_TEXTURE_DIRECTORY = PLANET_TEXTURE_DIRECTORY + "\\exoplanets\\";
    public static String GJ_504_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "gj 504 b\\gj_504_b.jpg";
    public static String HAT_P_11_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "hat p 11 b\\hat_p_11_b.jpg";
    public static String HD_189733_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "hd 189733 b\\hd_189733_b.jpg";
    public static String KEPLER_7_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "kepler 7 b\\kepler_7_b.jpg";
    public static String KEPLER_22_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "kepler 22 b\\kepler_22_b.jpg";
    public static String KEPLER_452_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "kepler 452 b\\kepler_452_b.jpg";
    public static String OGLE_2005_BLG_390L_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "ogle 2005 blg 390l b\\ogle_2005_blg_390l_b.jpg";
    public static String PROXIMA_CENTAURI_B_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "proxima centauri b\\proxima_centauri_b.jpg";
    public static String YZ_CETI_D_TEXTURE_PATH = EXOPLANET_TEXTURE_DIRECTORY + "yz ceti d\\yz_ceti_d.jpg";

    /**
     * Backgrounds.
     */
    public static String MILKY_WAY_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "background\\milky_way.jpg";


    /**
     * Thumbnails.
     */
    public static final String THUMBNAIL_DIRECTORY = PLANET_TEXTURE_DIRECTORY + "\\thumbnails\\";
    // Stars.
    public static String SUN_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "sun.jpg";
    public static String STEPHENSON_2_18_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "stephenson 2-18.jpg";

    // Planets.
    public static String MERCURY_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "mercury.jpg";
    public static String VENUS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "venus.jpg";
    public static String EARTH_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "earth.jpg";
    public static String EARTH_NIGHT_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "earth_night.jpg";
    public static String MARS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "mars.jpg";
    public static String JUPITER_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "jupiter.jpg";
    public static String SATURN_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "saturn.jpg";
    public static String URANUS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "uranus.jpg";
    public static String NEPTUNE_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "neptune.jpg";

    // Moons.
    public static String MOON_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "moon.jpg";
    public static String PHOBOS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "phobos.jpg";
    public static String DEIMOS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "deimos.jpg";
    public static String IO_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "io.jpg";
    public static String EUROPA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "europa.jpg";
    public static String GANYMEDE_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "ganymede.jpg";
    public static String CALLISTO_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "callisto.jpg";
    public static String AMALTHEA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "amalthea.jpg";
    public static String DIONE_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "dione.jpg";
    public static String ENCELADUS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "enceladus.jpg";
    public static String HYPERION_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "hyperion.jpg";
    public static String IAPETUS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "iapetus.jpg";
    public static String MIMAS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "mimas.jpg";
    public static String RHEA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "rhea.jpg";
    public static String TETHYS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "tethys.jpg";
    public static String TITAN_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "titan.jpg";
    public static String ARIEL_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "ariel.jpg";
    public static String MIRANDA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "miranda.jpg";
    public static String OBERON_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "oberon.jpg";
    public static String TITANIA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "titania.jpg";
    public static String UMBRIEL_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "umbriel.png";
    public static String TRITON_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "triton.jpg";
    public static String CHARON_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "charon.jpg";

    // Dwarf planets.
    public static String PLUTO_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "pluto.jpg";
    public static String CERES_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "ceres.jpg";
    public static String MAKEMAKE_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "makemake.jpg";
    public static String ERIS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "eris.jpg";
    public static String HAUMEA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "haumea.jpg";
    public static String GONGGONG_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "gonggong.jpg";
    public static String ORCUS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "orcus.jpg";
    public static String QUAOAR_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "quaoar.jpg";
    public static String SEDNA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "sedna.jpg";
    public static String IXION_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "ixion.jpg";

    // Asteroids.
    public static String VESTA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "vesta.jpg";
    public static String PALLAS_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "pallas.jpg";
    public static String JUNO_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "juno.jpg";
    public static String HYGIEA_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "hygiea.jpg";
    public static String CHARIKLO_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "chariklo.jpg";

    // Exoplanets.
    public static String GJ_504_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "gj_504_b.jpg";
    public static String HAT_P_11_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "hat_p_11_b.jpg";
    public static String HD_189733_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "hd_189733_b.jpg";
    public static String KEPLER_7_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "kepler_7_b.jpg";
    public static String KEPLER_22_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "kepler_22_b.jpg";
    public static String KEPLER_452_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "kepler_452_b.jpg";
    public static String OGLE_2005_BLG_390L_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "ogle_2005_blg_390l_b.jpg";
    public static String PROXIMA_CENTAURI_B_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "proxima_centauri_b.jpg";
    public static String YZ_CETI_D_THUMBNAIL_PATH = THUMBNAIL_DIRECTORY + "yz_ceti_d.jpg";

    public Textures(Preset preset)
    {
        switch(preset.getTextureQuality())
        {
            case HIGH ->
            {
                STAR_TEXTURE_PATH += "_high.jpg";
                STEPHENSON_2_18_TEXTURE_PATH += "_high.jpg";

                MILKY_WAY_TEXTURE_PATH += "_high.jpg";

                MERCURY_TEXTURE_PATH += "_high.jpg";
                VENUS_TEXTURE_PATH += "_high.jpg";
                EARTH_TEXTURE_PATH += "_high.jpg";
                MARS_TEXTURE_PATH += "_high.jpg";
                JUPITER_TEXTURE_PATH += "_high.jpg";
                SATURN_TEXTURE_PATH += "_high.jpg";
                SATURN_RING_TEXTURE_PATH += "_high_2.jpg";
                URANUS_RING_TEXTURE_PATH += "_high_2.jpg";

                MOON_TEXTURE_PATH += "_high.jpg";
                CERES_TEXTURE_PATH += "_high.jpg";
                MAKEMAKE_TEXTURE_PATH += "_high.jpg";
                ERIS_TEXTURE_PATH += "_high.jpg";
                HAUMEA_TEXTURE_PATH += "_high.jpg";
            }

            case LOW ->
            {
                STAR_TEXTURE_PATH += "_low.jpg";
                STEPHENSON_2_18_TEXTURE_PATH += "_low.jpg";

                MILKY_WAY_TEXTURE_PATH += "_low.jpg";

                MERCURY_TEXTURE_PATH += "_low.jpg";
                VENUS_TEXTURE_PATH += "_low.jpg";
                EARTH_TEXTURE_PATH += "_low.jpg";
                MARS_TEXTURE_PATH += "_low.jpg";
                JUPITER_TEXTURE_PATH += "_low.jpg";
                SATURN_TEXTURE_PATH += "_low.jpg";
                SATURN_RING_TEXTURE_PATH += "_low.png";

                MOON_TEXTURE_PATH += "_low.jpg";
                CERES_TEXTURE_PATH += "_low.jpg";
                MAKEMAKE_TEXTURE_PATH += "_low.jpg";
                ERIS_TEXTURE_PATH += "_low.jpg";
                HAUMEA_TEXTURE_PATH += "_low.jpg";
            }
        }
    }
}