package launcher;

import com.formdev.flatlaf.FlatDarculaLaf;
import core.gui.core.Window;

public class DesktopLauncher
{
    public void launch()
    {
        Window window = new Window();
        window.launch();
    }

    public static void main(String[] args)
    {
        FlatDarculaLaf.setup();
        DesktopLauncher launcher = new DesktopLauncher();
        launcher.launch();
    }
}