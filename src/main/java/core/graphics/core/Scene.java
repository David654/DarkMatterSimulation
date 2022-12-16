/**
 * @author David Lapidus
 * @version 1.0
 */

package core.graphics.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import core.graphics.hud.HUD;
import core.graphics.hud.Parameter;
import core.graphics.shaders.Shader;
import core.graphics.textures.TextureManager;
import core.math.vector.Vector2;
import core.math.vector.Vector3;
import core.settings.InputSettings;
import core.settings.graphicspresets.HighPreset;
import core.settings.graphicspresets.Preset;
import core.simulation.core.Simulation;
import core.simulation.input.core.InputProcessor;
import core.simulation.physics.body.Body;
import core.simulation.physics.PhysicsConstants;
import org.lwjgl.opengl.GL20;

public class Scene extends ScreenAdapter implements Runnable
{
    /**
     * Simulation.
     * @see Simulation
     **/
    private Simulation simulation;
    private final Vector3 camera = new Vector3(0, 20, 0);
    private Vector3 position = new Vector3(0, 0, -10);
    private float time;
    private float zoom = -2;

    /**
     * Update Thread.
     **/
    private Thread thread;
    private boolean running = false;

    /**
     * Mouse and keyboard input.
     * @see InputProcessor
     */
    private InputProcessor inputProcessor;

    /**
     * Graphics Utilities.
     **/
    private OrthographicCamera orthographicCamera;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private FrameBuffer frameBuffer;
    private HUD hud;
    private Preset preset;
    private TextureManager textureManager;

    /**
     * Shader.
     * @see Shader
     **/
    private Shader shader;
    private ShaderProgram shaderProgram;
    private Texture canvasTexture;
    private Texture backgroundTexture;

    private boolean enableLighting = true;
    private boolean showGrid = true;

    private final double scale = 0.00000001;

    /**
     * Constructor.
     * @see #createSimulation()
     * @see #initGraphicsUtilities()
     * @see #loadShaders()
     * @see #loadTextures()
     * @see #start()
     */
    public Scene()
    {
        initGraphicsUtilities();
        createSimulation();
        initInputProcessor();
        loadShaders();
        loadTextures();
        start();
    }

    public float getZoom()
    {
        return zoom;
    }

    public void setZoom(float zoom)
    {
        this.zoom = zoom;
    }

    public Simulation getSimulation()
    {
        return simulation;
    }

    public boolean isLightingEnabled()
    {
        return enableLighting;
    }

    public boolean isGridShown()
    {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid)
    {
        this.showGrid = showGrid;
    }

    public void setLightingEnabled(boolean enableLighting)
    {
        this.enableLighting = enableLighting;
    }

    /**
     * Creates a simulation.
     */
    private void createSimulation()
    {
        simulation = new Simulation();
        hud = new HUD();
        initHUD();
    }

    private void initInputProcessor()
    {
        inputProcessor = new InputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
        Gdx.input.setCursorCatched(true);
    }

    /**
     * Initializes necessary graphics utilities and tools.
     */
    private void initGraphicsUtilities()
    {
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.setToOrtho(false);
        orthographicCamera.position.set(0, 0, 0);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        font = new BitmapFont();

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        preset = new HighPreset();
        textureManager = new TextureManager(preset);

        Gdx.graphics.setVSync(preset.isVSYNCEnabled());
        Gdx.graphics.setForegroundFPS(1000);
    }

    /**
     * Loads all the shaders, creates and compiles a Shader program.
     * @see Shader
     */
    private void loadShaders()
    {
        String[] vertexShaderFilePaths = new String[] {"vertex.glsl"};
        String[] fragmentShaderFilePaths = new String[] {"uniforms.glsl", "sdf.glsl", "util.glsl", "planets.glsl", "map.glsl", "raymarching.glsl", "texturing.glsl", "lighting.glsl", "fragment.glsl"};

        shader = new Shader(vertexShaderFilePaths, fragmentShaderFilePaths);
        String vertexShader = shader.getVertexShader();
        String fragmentShader = shader.getFragmentShader();

        ShaderProgram.pedantic = false;
        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        if(!shaderProgram.isCompiled()) System.err.println(shaderProgram.getLog());
    }

    /**
     * Loads necessary textures.
     */
    private void loadTextures()
    {
        canvasTexture = new Texture(TextureManager.MILKY_WAY_TEXTURE_PATH);

        backgroundTexture = new Texture(TextureManager.MILKY_WAY_TEXTURE_PATH);
        backgroundTexture.bind(1);

        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            Texture texture = body.getTexture();
            texture.bind(i + 2);
        }

        canvasTexture.bind(0);

        /*for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            Ring bodyRing = body.getRing();
            if(bodyRing != null)
            {
                Texture ringTexture = bodyRing.getTexture();
                ringTexture.bind(i + simulation.getBodyHandler().getSize());
            }
        }**/


    }

    /**
     * Initializes Head-Up Display (HUD).
     * @see #hud
     */
    private void initHUD()
    {
        hud.addParameter(new Parameter("FPS: ", String.valueOf(Gdx.graphics.getFramesPerSecond()), ""));
        hud.addParameter(new Parameter("Frame Time: ", String.valueOf(Gdx.graphics.getDeltaTime()), "ms"));
        hud.addParameter(new Parameter("Bodies Total: ", String.valueOf(simulation.getBodyHandler().getSize()), ""));
    }

    /**
     * Updates Head-Up Display (HUD).
     * @see #hud
     */
    private void updateHUD()
    {
        hud.setParameter(new Parameter("FPS: ", String.valueOf(Gdx.graphics.getFramesPerSecond()), ""));
        hud.setParameter(new Parameter("Frame Time: ", String.valueOf(Gdx.graphics.getDeltaTime()), "ms"));
        hud.setParameter(new Parameter("Bodies Total: ", String.valueOf(simulation.getBodyHandler().getSize()), ""));
    }

    /**
     * Updates simulation components.
     */
    private void updateShader()
    {
        Vector2 mousePos = new Vector2(Gdx.input.getX() * InputSettings.MOUSE_SENSITIVITY_X, Gdx.graphics.getHeight() - Gdx.input.getY() * InputSettings.MOUSE_SENSITIVITY_Y);

        if(!simulation.isPaused())
        {
            time += Gdx.graphics.getDeltaTime();
        }

        position.setZ(zoom * 10);

        shaderProgram.setUniformf("uResolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shaderProgram.setUniformf("uCameraPos", (float) camera.getX(), (float) camera.getY(), (float) camera.getZ());
        shaderProgram.setUniformf("uMaxDist", preset.getMaxDist());
        shaderProgram.setUniformf("uMaxSteps", preset.getMaxSteps());
        shaderProgram.setUniformf("uFov", 1f / preset.getFOV() * 100f);
        shaderProgram.setUniformf("uMousePos", (float) mousePos.getX(), (float) mousePos.getY());
        shaderProgram.setUniformf("uTime", time);
        shaderProgram.setUniformf("uPos", (float) position.getX(), (float) position.getY(), (float) position.getZ());
        shaderProgram.setUniformi("uEnableLighting", enableLighting ? 1 : 0);
        shaderProgram.setUniformi("uShowGrid", showGrid ? 1 : 0);
        shaderProgram.setUniformi("uBackgroundTexture", 1);

        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            Vector3 position = body.getPosition().multiply(30 / PhysicsConstants.AU);
            double radius = body.getRadius() * scale;
            double rotationSpeed = body.getRotationSpeed() * PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS) * 0.000008;

            shaderProgram.setUniformf("uPositions[" + i + "]", (float) position.getX(), (float) position.getY(), (float) position.getZ());
            shaderProgram.setUniformf("uRadiuses[" + i + "]", (float) radius);
            shaderProgram.setUniformf("uRotationSpeeds[" + i + "]",(float) rotationSpeed);
            shaderProgram.setUniformf("uAxisInclinations[" + i + "]", (float) (body.getAxisInclination() * Math.PI / 180));
            shaderProgram.setUniformi("uTextures[" + i + "]", i + 2);
            shaderProgram.setUniformi("uRings[" + i + "]", body.getRing() == null ? 0 : 1);
            shaderProgram.setUniformf("uRingRadiuses[" + i + "]", body.getRing() == null ? new com.badlogic.gdx.math.Vector2() : new com.badlogic.gdx.math.Vector2((float) (body.getRing().getRadius1() * scale), (float) (body.getRing().getRadius2() * scale)));
        }

        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            //shaderProgram.setUniformi("uRingTextures[" + i + "]", i + simulation.getBodyHandler().getSize());
        }
    }

    private void update()
    {
        simulation.update();
        updateHUD();
    }

    /**
     * Renders the simulation.
     * @param delta delta time.
     *              The time the last frame took to be rendered.
     */
    public void render(float delta)
    {
        updateShader();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //frameBuffer.begin();

        spriteBatch.setShader(shaderProgram);
        spriteBatch.begin();
        spriteBatch.draw(canvasTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        //frameBuffer.end();

        //spriteBatch.setShader(null);
        //spriteBatch.begin();
        //spriteBatch.draw(frameBuffer.getColorBufferTexture(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //hud.render(spriteBatch, font);
        //spriteBatch.end();
    }

    public void dispose()
    {
        shaderProgram.dispose();
        System.exit(0);
    }

    /**
     * Method that starts a thread.
     **/
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Method that stops a thread.
     **/
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Main update method that is called on each thread tick.
     **/
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = PhysicsConstants.TICK_RATE;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                if(!simulation.isPaused())
                {
                    update();
                }
                delta--;
            }
            if(running)
            {
                //render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //hud.FPS = frames;
                frames = 0;
            }
        }
        stop();
    }
}