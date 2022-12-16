package core.graphics.hud;

public class Parameter
{
    private String title;
    private String value;
    private String units;

    public Parameter(String title, String value, String units)
    {
        this.title = title;
        this.value = value;
        this.units = units;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getUnits()
    {
        return units;
    }

    public void setUnits(String units)
    {
        this.units = units;
    }
}