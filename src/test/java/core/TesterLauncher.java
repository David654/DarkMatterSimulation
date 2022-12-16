package core;

import com.formdev.flatlaf.FlatDarculaLaf;
import gui.ControlPanelTester;
import launcher.DesktopLauncher;

public class TesterLauncher
{
    public static void main(String[] args)
    {
        //DesktopLauncher.launch(false);
        FlatDarculaLaf.setup();
        Tester tester = new ControlPanelTester();
        tester.test();
    }
}