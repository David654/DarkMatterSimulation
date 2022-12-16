package core.graphics.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import core.math.vector.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class HUD
{
    public static final Color COLOR = Color.WHITE;
    public static final Font FONT = new Font("Segoe UI", Font.PLAIN, 12);

    public static final int UPPER_LEFT_CORNER = 1;
    public static final int UPPER_RIGHT_CORNER = 2;
    public static final int LOWER_RIGHT_CORNER = 3;
    public static final int LOWER_LEFT_CORNER = 4;

    private Color color;
    private Font font;
    private int renderPosition;
    private final ArrayList<Parameter> parameters;

    public HUD()
    {
        this(COLOR, FONT);
    }

    public HUD(Color color, Font font)
    {
        this.color = color;
        this.font = font;
        renderPosition = UPPER_LEFT_CORNER;
        parameters = new ArrayList<>();
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Font getFont()
    {
        return font;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    public void setRenderPosition(int renderPosition)
    {
        this.renderPosition = renderPosition;
    }

    public void addParameter(Parameter parameter)
    {
        parameters.add(parameter);
    }

    public void setParameter(Parameter parameter)
    {
        for(int i = 0; i < parameters.size(); i++)
        {
            Parameter currParameter = parameters.get(i);
            if(parameter.getTitle().equals(currParameter.getTitle()))
            {
                parameters.set(i, parameter);
                break;
            }
        }
    }

    private Vector2 getRenderPosition()
    {
        Vector2 renderPosition = null;

        switch(this.renderPosition)
        {
            case UPPER_LEFT_CORNER -> renderPosition = new Vector2(10, 10);
            case UPPER_RIGHT_CORNER -> renderPosition = new Vector2(Gdx.graphics.getWidth(), 10);
            case LOWER_RIGHT_CORNER -> renderPosition = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            case LOWER_LEFT_CORNER -> renderPosition = new Vector2(10, Gdx.graphics.getHeight());
        }
        return renderPosition;
    }

    public void render(SpriteBatch spriteBatch, BitmapFont bitmapFont)
    {
        bitmapFont.setColor(color);

        Vector2 renderPosition = getRenderPosition();

        for(int i = 0; i < parameters.size(); i++)
        {
            Parameter parameter = parameters.get(i);
            String text = parameter.getTitle() + " " + parameter.getValue() + " " + parameter.getUnits();
            float x = (float) renderPosition.getX();
            float y = (float) renderPosition.getY() + i * 20;
            bitmapFont.draw(spriteBatch, text, x, Gdx.graphics.getHeight() - y);
        }
    }
}