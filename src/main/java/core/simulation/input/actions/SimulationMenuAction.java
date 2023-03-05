package core.simulation.input.actions;

import core.graphics.core.Scene;
import core.gui.core.SimulationMenu;
import core.simulation.core.Simulation;

public class SimulationMenuAction implements InputAction
{
    private final SimulationMenu simulationMenu;

    public SimulationMenuAction(int windowWidth, int windowHeight, Simulation simulation)
    {
        simulationMenu = new SimulationMenu(windowWidth, windowHeight, simulation);
    }

    public void perform()
    {
        simulationMenu.setVisible(!simulationMenu.isVisible());
        if(simulationMenu.isVisible())
        {
            simulationMenu.toFront();
            //simulationMenu.setModal(true);
        }
    }
}