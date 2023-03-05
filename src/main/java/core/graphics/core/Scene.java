/**
 * @author David Lapidus
 * @version 1.0
 */

package core.graphics.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import core.graphics.hud.core.HUD;
import core.graphics.hud.core.Parameter;
import core.graphics.shaders.Shader;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.handler.CelestialObjectHandler;
import core.util.FontUtils;
import core.util.TextureUtils;
import core.util.MathUtils;
import core.math.vector.Vector2;
import core.math.vector.Vector3;
import core.settings.InputSettings;
import core.settings.graphicspresets.HighPreset;
import core.settings.graphicspresets.Preset;
import core.simulation.core.Simulation;
import core.simulation.input.camera.Camera;
import core.simulation.input.core.InputProcessor;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.PhysicsConstants;
import core.simulation.physics.celestialobjects.Ring;
import core.simulation.physics.celestialobjects.Star;
import core.simulation.physics.darkmatter.DarkMatter;
import core.util.TimeUtils;
import log.Logger;
import org.lwjgl.opengl.GL20;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public final class Scene extends ScreenAdapter implements Runnable
{
    /**
     * Simulation.
     * @see Simulation
     **/
    public static Simulation simulation;
    private Camera camera;
    private double time;
    private double simulationTime;
    private double zoom = 1;
    private double scale = 14;
    public static int SELECTED_BODY_INDEX = 0;
    private Vector3 offset;

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
    private SpriteBatch spriteBatch2;
    private Mesh mesh;
    private BitmapFont titleFont;
    private BitmapFont basicFont;
    private FrameBuffer frameBuffer;
    private HUD hud;
    private Preset preset;
    private TextureUtils textureUtils;

    /**
     * Shader.
     * @see Shader
     **/
    private Shader shader;
    private ShaderProgram shaderProgram;
    private Sprite sprite;
    private Sprite sprite2;
    private Texture canvasTexture;
    private Texture backgroundTexture;

    private boolean enableLighting = true;
    private boolean showGrid = false;
    private final Calendar calendar = Calendar.getInstance();
    private LocalDateTime dateTime = LocalDateTime.now();

    private Skin skin;


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
        calendar.setTime(new Date());

        Logger.getLog("Graphics initialized.");
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public double getZoom()
    {
        return zoom;
    }

    public void setZoom(double zoom)
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
        simulation.distributeBodiesRandomly();
        hud = new HUD();
        initHUD();
    }

    private void initInputProcessor()
    {
        inputProcessor = new InputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
        //Gdx.input.setCursorCatched(true);
    }

    /**
     * Initializes necessary graphics utilities and tools.
     */
    private void initGraphicsUtilities()
    {
        camera = new Camera();

        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.setToOrtho(false);
        orthographicCamera.position.set(0, 0, 0);

        spriteBatch = new SpriteBatch();
        //spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        spriteBatch2 = new SpriteBatch();
       // spriteBatch2.setProjectionMatrix(orthographicCamera.combined);

        sprite = new Sprite();
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite2 = new Sprite();
        sprite2.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        titleFont = FontUtils.createFont("src\\main\\resources\\fonts\\nasalization.otf", 160);
        basicFont = FontUtils.createFont("src\\main\\resources\\fonts\\nasalization.otf", 14);

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        preset = new HighPreset();
        textureUtils = new TextureUtils(preset);

        Gdx.graphics.setVSync(preset.isVSYNCEnabled());
        Gdx.graphics.setForegroundFPS(1000);


        float[] verts = new float[30];
        int i = 0;
        float x,y; // Mesh location in the world
        float width,height; // Mesh width and height

        x = y = 50f;
        width = height = 300f;

        //Top Left Vertex Triangle 1
        verts[i++] = x;   //X
        verts[i++] = y + height; //Y
        verts[i++] = 0;    //Z
        verts[i++] = 0f;   //U
        verts[i++] = 0f;   //V

        //Top Right Vertex Triangle 1
        verts[i++] = x + width;
        verts[i++] = y + height;
        verts[i++] = 0;
        verts[i++] = 1f;
        verts[i++] = 0f;

        //Bottom Left Vertex Triangle 1
        verts[i++] = x;
        verts[i++] = y;
        verts[i++] = 0;
        verts[i++] = 0f;
        verts[i++] = 1f;

        //Top Right Vertex Triangle 2
        verts[i++] = x + width;
        verts[i++] = y + height;
        verts[i++] = 0;
        verts[i++] = 1f;
        verts[i++] = 0f;

        //Bottom Right Vertex Triangle 2
        verts[i++] = x + width;
        verts[i++] = y;
        verts[i++] = 0;
        verts[i++] = 1f;
        verts[i++] = 1f;

        //Bottom Left Vertex Triangle 2
        verts[i++] = x;
        verts[i++] = y;
        verts[i++] = 0;
        verts[i++] = 0f;
        verts[i] = 1f;

        // Create a mesh out of two triangles rendered clockwise without indices
        mesh = new Mesh( true, 6, 0,
                new VertexAttribute( VertexAttributes.Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE ),
                new VertexAttribute( VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ) );

        mesh.setVertices(verts);

        //skin = new Skin(new FileHandle("src\\main\\java\\core\\gui\\font\\skins\\skin.json"));
        //Label label = new Label("Hello", skin, "default");
    }

    /**
     * Loads all the shaders, creates and compiles a Shader program.
     * @see Shader
     */
    private void loadShaders()
    {
        Logger.getLog("Loading shaders...");
        String[] vertexShaderFilePaths = new String[] {"vertex.glsl"};
        String[] fragmentShaderFilePaths = new String[] {"uniforms.glsl", "sdf.glsl", "util.glsl", "texturing.glsl", "darkmatter.glsl", "bodies.glsl", "map.glsl", "lighting.glsl", "raymarching.glsl" , "fragment.glsl"};

        shader = new Shader(vertexShaderFilePaths, fragmentShaderFilePaths);
        String vertexShader = shader.getVertexShader();
        String fragmentShader = shader.getFragmentShader();

        ShaderProgram.pedantic = false;
        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        if(!shaderProgram.isCompiled()) System.err.println(shaderProgram.getLog());

        Logger.getLog("Shaders loaded within " + Logger.getTimeAfterLastLog() / 10e9 + " seconds.");
    }

    /**
     * Loads necessary textures.
     */
    public synchronized void loadTextures()
    {
        Logger.getLog("Loading textures...");
        canvasTexture = new Texture(TextureUtils.MILKY_WAY_TEXTURE_PATH);
        sprite.setTexture(canvasTexture);
        sprite2.setTexture(canvasTexture);

        backgroundTexture = new Texture(TextureUtils.MILKY_WAY_TEXTURE_PATH);
        backgroundTexture.bind(1);

        int index = 0;

        for(int i = 0; i < BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size(); i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);
            Texture texture = celestialObject.getTexture();
            BasicCelestialObjects.TEXTURES.add(texture);
            texture.bind(i + 2);
        }

        /*for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(i);
            Texture texture = celestialObject.getTexture();
            texture.bind(i + 2 + index);

            Ring ring = celestialObject.getRing();
            if(ring != null)
            {
                index++;
                Texture ringTexture = ring.getTexture();
                ringTexture.bind(i + 2 + index);
            }

            if(celestialObject.getBumpTexture() != null)
            {
                index++;
                celestialObject.getBumpTexture().bind(i + 2 + index);
            }
        }**/

        for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(i);

            Ring ring = celestialObject.getRing();
            if(ring != null)
            {
                index++;
                Texture ringTexture = ring.getTexture();
                ringTexture.bind(i + 2 + BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size() + index);
            }

            if(celestialObject.getBumpTexture() != null)
            {
                index++;
                celestialObject.getBumpTexture().bind(i + 2 + BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size() + index);
            }
        }

        canvasTexture.bind(0);
        //canvasTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Logger.getLog("Textures loaded within " + Logger.getTimeAfterLastLog() / 10e9 + " seconds.");
    }

    /**
     * Initializes Head-Up Display (HUD).
     * @see #hud
     */
    private void initHUD()
    {
        hud.addParameter(new Parameter("FPS: ", String.valueOf(Gdx.graphics.getFramesPerSecond())));
        hud.addParameter(new Parameter("Frame Time: ", String.valueOf(Gdx.graphics.getDeltaTime()), "ms"));
        hud.addBlank();
        String[] timeStep = TimeUtils.getTimeStep(PhysicsConstants.DAYS);
        hud.addParameter(new Parameter("Time Step: ", timeStep[0], timeStep[1]));
        hud.addParameter(new Parameter("Current Date: ", TimeUtils.getCurrentDate(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS))));
        hud.addBlank();
        hud.addParameter(new Parameter("Star System Name: ", simulation.getStarSystem().getName()));
        hud.addParameter(new Parameter("Celestial Objects: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getSize())));
        hud.addParameter(new Parameter("Stars: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getNumStars())));
        hud.addParameter(new Parameter("Planets: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getNumPlanets())));
    }

    /**
     * Updates Head-Up Display (HUD).
     * @see #hud
     */
    private void updateHUD()
    {
        hud.setParameter(new Parameter("Current Date: ", TimeUtils.getCurrentDate(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS))));
    }

    private void updateHUDData()
    {
        hud.setParameter(new Parameter("FPS: ", String.valueOf(Gdx.graphics.getFramesPerSecond())));
        hud.setParameter(new Parameter("Frame Time: ", String.valueOf(Gdx.graphics.getDeltaTime()), "ms"));
        hud.setParameter(new Parameter("Star System Name: ", simulation.getStarSystem().getName()));
        hud.setParameter(new Parameter("Celestial Objects: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getSize())));
        hud.setParameter(new Parameter("Stars: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getNumStars())));
        hud.setParameter(new Parameter("Planets: ", String.valueOf(simulation.getStarSystem().getBodyHandler().getNumPlanets())));
        String[] timeStep = TimeUtils.getTimeStep(PhysicsConstants.DAYS);
        hud.setParameter(new Parameter("Time Step: ", timeStep[0], timeStep[1]));
    }

    /**
     * Updates simulation components.
     */
    private void updateShader()
    {
        Vector2 mousePos = new Vector2(inputProcessor.getMouseDragX(), inputProcessor.getMouseDragY());
        //System.out.println(inputProcessor.getMouseDragY());
        inputProcessor.update();

        //simulation.setPaused(PhysicsConstants.DAYS == 0);

        if(!simulation.isPaused())
        {
            time += Gdx.graphics.getDeltaTime();
        }

        CelestialObject selectedCelestialObject = simulation.getStarSystem().getBodyHandler().get(SELECTED_BODY_INDEX);
        offset = MathUtils.applyUnaryOperator(selectedCelestialObject.getPosition(), simulation.getStarSystem().getPositionScale()).negate();
        double selectedCelestialObjectRadius = simulation.getStarSystem().getRadiusScale().apply(selectedCelestialObject.getRadius());
        double selectedCelestialObjectZoom = -selectedCelestialObjectRadius * 200 / 126.135712;

        Vector3 cameraPosition = camera.getCameraPosition();
        cameraPosition.setZ((selectedCelestialObjectZoom + zoom) * InputSettings.ZOOM_SENSITIVITY);

        //position.setY(-zoom * InputSettings.ZOOM_SENSITIVITY);

        shaderProgram.setUniformf("uResolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shaderProgram.setUniformf("uMaxDist", preset.getMaxDist());
        shaderProgram.setUniformf("uMaxSteps", preset.getMaxSteps());
        shaderProgram.setUniformf("uFov", 1f / preset.getFOV() * 100f);
        shaderProgram.setUniformf("uMousePos", (float) mousePos.getX(), (float) (mousePos.getY()));
        shaderProgram.setUniformf("uTime", (float) time);
        shaderProgram.setUniformf("uPos", (float) cameraPosition.getX(), (float) cameraPosition.getY(), (float) cameraPosition.getZ());
        shaderProgram.setUniformf("uOffset", (float) offset.getX(), (float) offset.getY(), (float) offset.getZ());
        shaderProgram.setUniformf("uSelectedBodyIndex", SELECTED_BODY_INDEX + 1);
        shaderProgram.setUniformMatrix("uView", MathUtils.toFloatMatrix(camera.getView()));
        shaderProgram.setUniformi("uEnableLighting", enableLighting ? 1 : 0);
        shaderProgram.setUniformi("uShowGrid", showGrid ? 1 : 0);
        shaderProgram.setUniformi("uBackgroundTexture", 1);
        shaderProgram.setUniformi("uBodyNum", simulation.getStarSystem().getBodyHandler().getSize());
        shaderProgram.setUniformi("uLightSourcesNum", Math.max(1, simulation.getStarSystem().getBodyHandler().getNumStars()));

        int index = 0;
        //double radiusScale = 0.0000001;
        Vector3 maxPos = simulation.getStarSystem().getBodyHandler().getMaxPos();
        Vector3 minPos = simulation.getStarSystem().getBodyHandler().getMinPos();

        for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(i);

            //Vector3 bodyPosition = new Vector3(Math.log(body.getPosition().getX()), Math.log(body.getPosition().getY()), Math.log(body.getPosition().getZ()));
           // Vector3 bodyPosition = body.getPosition().multiply(30 / PhysicsConstants.AU);
            //double radius = body.getRadius() * radiusScale * (i == 0 ? 0.1 : 1);
            //double radius = MathUtils.logAB(2.72, body.getRadius());
            Vector3 dimensions = MathUtils.applyUnaryOperator(celestialObject.getDimensions(), simulation.getStarSystem().getRadiusScale());
            //double radius = celestialObject.getRadius() / 1000000;
            //Vector3 bodyPosition = MathUtils.applyUnaryOperator(celestialObject.getPosition(), simulation.getStarSystem().getPositionScale()).add(offset);
            Vector3 bodyPosition = MathUtils.applyUnaryOperator(celestialObject.getPosition(), simulation.getStarSystem().getPositionScale()).add(offset);
            //System.out.println(bodyPosition);
           // System.out.println(celestialObject.getName() + ": " + bodyPosition);
            //bodyPosition = MathUtils.rotateZ(bodyPosition, Math.toRadians(celestialObject.getOrbitalInclination()));
            //bodyPosition.setX(bodyPosition.getX() * sigmoid.apply((bodyPosition.getX() - minPos.getX()) / (maxPos.getX() - minPos.getX())));
            //bodyPosition.setY(bodyPosition.getY() * sigmoid.apply((bodyPosition.getY() - minPos.getY()) / (maxPos.getY() - minPos.getY())));
            //bodyPosition.setZ(bodyPosition.getZ() * sigmoid.apply((bodyPosition.getZ() - minPos.getZ()) / (maxPos.getZ() - minPos.getZ())));
            //Vector3 bodyPosition = body.getPosition().multiply(30 / PhysicsConstants.AU);
            //double radius = MathUtils.logAB(5, body.getRadius());
            double mass = celestialObject.getMass();
            double rotationSpeed = celestialObject.getRotationSpeed() * PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS) * 0.000008;
            Color color = celestialObject.getColor();

            shaderProgram.setUniformi("uVisibilities[" + i + "]", celestialObject.isVisible() ? 1 : 0);
            shaderProgram.setUniformf("uIDs[" + i + "]", celestialObject instanceof Star ? 1 : 0);
            shaderProgram.setUniformf("uPositions[" + i + "]", (float) bodyPosition.getX(), (float) bodyPosition.getY(), (float) bodyPosition.getZ());
            shaderProgram.setUniformf("uDimensions[" + i + "]", (float) dimensions.getX(), (float) dimensions.getY(), (float) dimensions.getZ());
            shaderProgram.setUniformf("uMasses[" + i + "]", (float) mass);
            shaderProgram.setUniformf("uRotationSpeeds[" + i + "]",(float) rotationSpeed);
            shaderProgram.setUniformf("uAxisInclinations[" + i + "]", (float) (celestialObject.getObliquity() * Math.PI / 180));
            shaderProgram.setUniformf("uApparentMagnitudes[" + i + "]", celestialObject instanceof Star ? (float) ((Star) celestialObject).getApparentMagnitude() : 0);
            shaderProgram.setUniformf("uColors[" + i + "]", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
            shaderProgram.setUniformi("uTextures[" + i + "]", 2 + BasicCelestialObjects.TEXTURES.indexOf(celestialObject.getTexture()));

            if(celestialObject.getRing() != null)
            {
                index++;
                shaderProgram.setUniformi("uRingTextures[" + i + "]", i + 2 + BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size() + index);
            }

            shaderProgram.setUniformf("uBump[" + i + "]", celestialObject.getBumpTexture() == null ? 0 : 1);

            if(celestialObject.getBumpTexture() != null)
            {
                index++;
                shaderProgram.setUniformi("uBumpTextures[" + i + "]", i + 2 + BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size() + index);
            }

            shaderProgram.setUniformi("uRings[" + i + "]", celestialObject.getRing() == null ? 0 : 1);

            double ringRadius1 = 0;
            double ringRadius2 = 0;
            if(celestialObject.getRing() != null)
            {
                ringRadius1 = simulation.getStarSystem().getRadiusScale().apply(celestialObject.getRing().getRadius1());
                ringRadius2 = simulation.getStarSystem().getRadiusScale().apply(celestialObject.getRing().getRadius2());
            }
            shaderProgram.setUniformf("uRingRadiuses[" + i + "]", new com.badlogic.gdx.math.Vector2((float) ringRadius1, (float) ringRadius2));
        }

        for(int i = 0; i < simulation.getStarSystem().getDarkMatterHandler().getSize(); i++)
        {
            DarkMatter darkMatter = simulation.getStarSystem().getDarkMatterHandler().get(i);
            double radius1 = darkMatter.getRadius1();
            double radius2 = darkMatter.getRadius2();
            //System.out.println(radius1 + ", " + radius2);

            shaderProgram.setUniformf("uDarkMatterRadiuses1[" + i + "]", (float) radius1);
            shaderProgram.setUniformf("uDarkMatterRadiuses2[" + i + "]", (float) radius2);
            shaderProgram.setUniformf("uDarkMatterDensities[" + i + "]", (float) darkMatter.getDensity());
        }
    }

    private void update()
    {
        simulation.update();
        updateHUD();


        /*if(simulationTime / 3600 / 24 >= 365)
        {
            for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize() - 1; i++)
            {
                CelestialObject celestialObject1 = simulation.getStarSystem().getBodyHandler().get(i);
                CelestialObject celestialObject2 = simulation.getStarSystem().getBodyHandler().get(i + 1);
                double x1 = celestialObject1.getPosition().distance(celestialObject2.getPosition());
                double x2 = celestialObject1.getInitialPosition().distance(celestialObject2.getInitialPosition());
                System.out.println("error: " + Math.abs(x1 - x2) / Math.abs(x2) * 100 + "%");
            }
            System.exit(0);
        }**/

        simulationTime += PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS);


    }

    /**
     * Renders the simulation.
     * @param delta delta time.
     *              The time the last frame took to be rendered.
     */
    public void render(float delta)
    {
        updateShader();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        /*if(time < 5)
        {
            spriteBatch.begin();
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(titleFont, "Dark Matter Simulation");
            titleFont.draw(spriteBatch, glyphLayout, Gdx.graphics.getWidth() / 2f - glyphLayout.width / 2f, Gdx.graphics.getHeight() / 2f + glyphLayout.height / 2f);
            spriteBatch.end();
        }
        else
        {
            spriteBatch.setShader(null);
            spriteBatch.begin();
            spriteBatch.draw(sprite2, 0, 0, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
            hud.render(spriteBatch, basicFont);
            spriteBatch.end();

            spriteBatch.setShader(shaderProgram);
            spriteBatch.begin();
            spriteBatch.draw(sprite, Gdx.graphics.getWidth() / 8f, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            spriteBatch.end();
        }**/

        /*spriteBatch.setShader(null);
        spriteBatch.begin();
        spriteBatch.draw(sprite2, 0, 0, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        hud.render(spriteBatch, basicFont);
        spriteBatch.end();**/



        spriteBatch.setShader(shaderProgram);
        shaderProgram.bind();
        spriteBatch.begin();
        spriteBatch.draw(sprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        //spriteBatch.setShader(null);
        spriteBatch.begin();
        hud.render(spriteBatch, basicFont);
        spriteBatch.end();

       // mesh.render(shaderProgram, GL20.GL_TRIANGLES);



        //spriteBatch.setShader(null);
        //spriteBatch2.begin();
       // hud.render(spriteBatch2, font);
       // spriteBatch2.end();
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
                updateHUDData();
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