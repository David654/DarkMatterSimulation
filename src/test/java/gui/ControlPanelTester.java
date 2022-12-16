package gui;

import core.gui.core.SimulationMenu;
import core.simulation.core.Simulation;

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
        Simulation simulation = new Simulation();
        SimulationMenu simulationMenu = new SimulationMenu(windowWidth, windowHeight, simulation);
        window.add(simulationMenu, BorderLayout.CENTER);
    }
}