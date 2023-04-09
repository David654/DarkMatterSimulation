package gui;

import core.graphics.core.Scene;
import core.gui.core.SimulationMenu;
import core.simulation.core.Simulation;
import core.simulation.starsystems.EarthMoonSystem;

import java.awt.*;

public class ControlPanelTester extends GUITester
{
    public ControlPanelTester()
    {
        super("Control Panel Tester");
    }

    protected void initWindow()
    {
        window.setLayout(new BorderLayout());
    }

    protected void initComponents()
    {
        Simulation simulation = new Simulation(new EarthMoonSystem());
        SimulationMenu simulationMenu = new SimulationMenu(windowWidth, windowHeight, new Scene());
        simulationMenu.setVisible(true);
        window.add(simulationMenu, BorderLayout.CENTER);
    }
}