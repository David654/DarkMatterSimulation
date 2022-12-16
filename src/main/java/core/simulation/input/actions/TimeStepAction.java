package core.simulation.input.actions;

import core.simulation.physics.PhysicsConstants;

public class TimeStepAction implements InputAction
{
    private double days;

    public double getDays()
    {
        return days;
    }

    public void setDays(double days)
    {
        this.days = days;
    }

    public void perform()
    {
        PhysicsConstants.DAYS = days;
        PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS);
    }
}