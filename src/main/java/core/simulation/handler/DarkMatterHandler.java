package core.simulation.handler;

import core.assets.textures.Textures;
import core.simulation.physics.darkmatter.DarkMatter;
import core.simulation.starsystems.StarSystem;
import core.util.TextureUtils;

import java.awt.image.BufferedImage;

public class DarkMatterHandler extends Handler<DarkMatter>
{
    public static final BufferedImage GRADIENT = TextureUtils.readImage(Textures.GRADIENT_IMAGE_PATH);

    public void initDarkMatter(StarSystem starSystem)
    {
        this.clear();

        for(int i = 0; i < starSystem.getBodyHandler().getSize(); i++)
        {
            //double density = Math.random() * 1000;
            double density = 0;
            this.add(new DarkMatter(density, 0, 0, 300));
        }
    }
}