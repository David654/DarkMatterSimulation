package launcher;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.formdev.flatlaf.FlatDarculaLaf;
import core.assets.icons.Icons;
import core.assets.textures.Textures;
import core.settings.core.GeneralSettings;
import log.Logger;

public class DesktopLauncher
{
    private static void initGraphics(boolean visible)
    {
        Logger.getLog("Initializing graphics...");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setWindowedMode(GeneralSettings.WIDTH, GeneralSettings.HEIGHT);
        config.setTitle(GeneralSettings.TITLE);
        config.setMaximized(true);
        config.setInitialVisible(visible);
        config.setDecorated(false);
        config.setWindowIcon(Icons.APPLICATION_ICON_PATH);

        new Lwjgl3Application(new Boot(), config);
    }

    public static void launch(boolean visible)
    {
        Logger.getLog("Application launched.");
        FlatDarculaLaf.setup();
        initGraphics(visible);
    }

    public static void main(String[] args)
    {
        DesktopLauncher.launch(true);
    }
}