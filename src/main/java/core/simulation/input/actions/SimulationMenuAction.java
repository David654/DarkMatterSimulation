package core.simulation.input.actions;

import core.graphics.core.Scene;
import core.gui.core.SimulationMenu;
import core.simulation.core.Simulation;

public class SimulationMenuAction implements InputAction
{
    private final SimulationMenu simulationMenu;

    public SimulationMenuAction(int windowWidth, int windowHeight, Scene scene)
    {
        simulationMenu = new SimulationMenu(windowWidth, windowHeight, scene);
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