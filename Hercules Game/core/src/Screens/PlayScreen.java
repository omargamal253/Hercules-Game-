
package Screens;

import com.main.Main;
import Intro.StartMenu;
import MovingObjects.Hercules;
import MovingObjects.SecondaryCharacter;
import StaticGraphics.*;
import Sprites.*;
import Scenes.*;
import HealthAttacker.*;
import Tools.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class PlayScreen implements Screen{

    //  Some Essential Variables
    public Main game;
    public float swordTimer;
    public boolean stopHercAction;
    public boolean noSwords;
    public boolean paused;
    public boolean restart;
    public Timer timer;

    //Basic PlayScreen Variables
    protected OrthographicCamera gameCam;
    protected Viewport gamePort;
    public HUD hud;
    public HUD2 hud2;
    public HUD3 hud3;

    public Window pauseWindow;

    //Tiled Map Variables
    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    //Atlas
    protected TextureAtlas FlameAtlas;
    protected TextureAtlas TotalAtlas;
    protected TextureAtlas Wagon;
    protected TextureAtlas atlas_run;
    protected TextureAtlas atlas_jumb;

    //Box2d Variables
    protected World world;
    protected Box2DDebugRenderer debuger;
    public WorldCreator creator;
    public WorldContactListener worldContactListener;

    //Sounds Variables
    public Music Game, GameOver, concentrate, Victory;
    protected Music m;

    //Sprites
    protected DrawClass staticGraphics;
    public Swords staticlightiningsword;
    public Swords staticfireballsword, leftfirball, rightfireball;
    public Swords staticsonicsword, sonicsword, lightningsword;
    protected Hercules player;

    //Helping Variables and Objects
    protected ProtectingShield Shield;
    protected Herculad juice;
    public InputHandle inputhandle ;

    public PlayScreen(Main game, float HercPosX, String mapPath) {

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);

        FlameAtlas = new TextureAtlas("Sprites\\Level 1\\Main\\Flame.atlas");
        TotalAtlas = new TextureAtlas("Sprites\\Level 1\\Main\\Total.pack");
        Wagon = new TextureAtlas("Sprites\\Level 2\\Wagon\\Wagon.pack");
        atlas_run = new TextureAtlas("Sprites\\Level 1\\HERCULES\\Run600_75.pack");
        atlas_jumb = new TextureAtlas("Sprites\\Level 1\\HERCULES\\H_Jump.pack");
        staticGraphics = new DrawClass(this);

        //CREATING THE BOX2D AND WORLD PHYSICS
        world = new World(new Vector2(0, -10f), true);
        worldContactListener = new WorldContactListener();
        world.setContactListener(worldContactListener);
        debuger = new Box2DDebugRenderer();
        player = new Hercules(world, this, HercPosX);
        creator = new WorldCreator(this);
        worldContactListener.player = player;

        staticlightiningsword = new StaticLightSword(15555f / Main.PPM, 300f / Main.PPM, player);
        staticfireballsword = new StaticFireBallSword(10400f / Main.PPM, 300f / Main.PPM, player);
        leftfirball = rightfireball = staticfireballsword;
        staticsonicsword = new StaticSonicSword(20112f / Main.PPM, 336f / Main.PPM, player);
        sonicsword = new SonicSword(0, 0, player);
        lightningsword = staticsonicsword;

        stopHercAction = restart = paused = false;

        //Extra Objects
        Shield = new ProtectingShield(player, hud, 4512f, 176f);
        juice = new Herculad(this, 18080, 432);

    }


    /*Start Constructor Methods*/
    protected void adaptSounds(){
        String path = (noSwords)?"Audio//Hercules - sounds//Game Over 2.mp3":"Audio//Hercules - sounds//Game Over.mp3";
        GameOver = game.manager.get(path, Music.class);
        Game = game.manager.get("Audio//Hercules - sounds//Nature Sound.wav", Music.class);
        Victory = game.manager.get("Audio//Hercules - sounds//Victory.mp3", Music.class);
        concentrate = game.manager.get("Audio//Hercules - Voices//Phil//Concentrate.wav", Music.class);

        Game.setLooping(true);
        Game.setVolume(Main.vol);
    }

    protected void createPauseMenu(Stage stage, Skin skin){
        pauseWindow = new Window("PAUSE", skin);
        /****************************************************/
        // Pause Menu TextButtons
        TextButton continueBtn = new TextButton("Continue", skin);
        final TextButton soundBtn = new TextButton("Mute Sounds", skin);
        TextButton mode1 = new TextButton("Mode 1 Buttons", skin);
        TextButton mode2 = new TextButton("Mode 2 Buttons", skin);
        TextButton exitBtn = new TextButton("Exit", skin);
        mode1.getLabel().setFontScale(0.5f, 1f);
        mode2.getLabel().setFontScale(0.5f, 1f);
        soundBtn.getLabel().setFontScale(0.65f, 1f);

        continueBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseWindow.setVisible(false);
                paused=false;
            }
        });
        soundBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String s = soundBtn.getText().toString();
                if(s.equals("Mute Sounds")){
                    game.vol = 0f;
                    Game.setVolume(0f);
                    soundBtn.setText("Unmute Sounds");
                }
                else {
                    game.vol = 1f;
                    Game.setVolume(1f);
                    soundBtn.setText("Mute Sounds");
                }
            }
        });
        mode1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InputHandle.mode=1;
                inputhandle.resetSettings();
            }
        });
        mode2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InputHandle.mode=2;
                inputhandle.resetSettings();
            }
        });
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.stop();
                game.setScreen(new StartMenu(game));
                dispose();
            }
        });
        /****************************************************/
        // Independent Pause Menu

        pauseWindow.padTop(200);
        pauseWindow.getTitleLabel().setAlignment(Align.center); pauseWindow.getTitleLabel().setFontScale(0.6f);

        pauseWindow.add(continueBtn).padTop(-100f).size(300, 70).row();
        pauseWindow.add(soundBtn).padTop(-5f).size(300, 70).row();
        pauseWindow.add(mode1).padTop(10f).size(300, 70).row();
        pauseWindow.add(mode2).padTop(10f).size(300, 70).row();
        pauseWindow.add(exitBtn).padTop(10).size(300, 70);

        pauseWindow.setSize(stage.getWidth() / 1.5f, stage.getHeight() / 1.2f);
        pauseWindow.setPosition(stage.getWidth() / 2 - pauseWindow.getWidth() /2, stage.getHeight() / 2 - pauseWindow.getHeight() /2);
        pauseWindow.setMovable(false);
        pauseWindow.setVisible(false);
        /****************************************************/
        Gdx.input.setInputProcessor(stage);
        stage.addActor(pauseWindow);
    }
    /*End Constructor Methods*/

    /*Start Objects GETTERS*/
    public TextureAtlas getFlameAtlas() { return FlameAtlas;}

    public TextureAtlas getTotalAtlas() { return TotalAtlas;}

    public TextureAtlas getTotal2Atlas() { return Wagon;}

    public Hercules getPlayer() { return player;}

    public TextureAtlas getAtlas_Run() {return atlas_run;}

    public TextureAtlas getAtlas_jumb() {return atlas_jumb;}

    public World getWorld() {return world;}

    public TiledMap getMap() {return map;}
    /*End Objects GETTERS*/


    /*Start Some Helping Methods*/
    protected void handleInput() {
        if (!stopHercAction){ // MAKES HERCULES DOESN'T RESPOND TO USER ACTIONS
            //control our player using immediate impulses
            if (inputhandle.Hup == true && player.onGround) {
                player.body.applyLinearImpulse(new Vector2(0, 1.4f), player.body.getWorldCenter(), true);
                HerculesActionSound("Audio//Hercules - Voices//Hercules//Jumb2.wav");
            } else if (inputhandle.Hdown == true)
                player.body.applyLinearImpulse(new Vector2(0, -2.5f), player.body.getWorldCenter(), true);
             else if (inputhandle.Hright == true && player.body.getLinearVelocity().x <= player.HerculesMaxSpeed)
                player.body.applyForceToCenter(new Vector2(3, 0), true);
             else if (inputhandle.Hleft == true && player.body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed)
                player.body.applyForceToCenter(new Vector2(-3, 0), true);

            if (inputhandle.Hpush2 == true) {
                player.hercules_Smallpush = true;
                HerculesActionSound("Audio//Hercules - sounds//Punch.wav");
            }
            else if (inputhandle.Hpush1 == true ) {
                player.hercules_push = true;
                HerculesActionSound("Audio//Hercules - sounds//a2.wav");
            }
            else if (!noSwords){ // (NO SWORDS IN LEVEL 2)
                if (inputhandle.Hsword2 == true)
                    handleSword();
                else if (inputhandle.Hsword1 == true){
                    player.hercules_sword2 = true;
                HerculesActionSound("Audio//Hercules - sounds//sword.wav");
                }
            }
        }
    }

    protected void handleSword() {
        if (player.pickedlightsword == true) {
            if (timer.statetimer1 > 0) {
                player.hercules_sword = true;
                lightningsword = new LightiningSword(0, 0, player);
            }

        } else if (player.pickedfireballsword == true) {

            if (timer.statetimer2 > 0) {
                if (player.isRunningRight()) {
                    rightfireball = new RightFireBallSword(0, 0, player);
                } else {
                    leftfirball = new LeftFireBallSword(0, 0, player);
                }
            }

        } else if (player.pickedsonicsword == true) {
            if (timer.statetimer3 > 0) {
                sonicsword = new SonicSword(0, 0, player);
            }

        } else {
            player.hercules_sword = true;
            HerculesActionSound("Audio//Hercules - sounds//a3.wav");

        }
    }

    protected void updateCoins() {
        for(Coin coin : creator.getCoins())
            coin.update(player);
    }
    protected void renderCoins() {
        for(Coin coin : creator.getCoins())
            coin.draw(game.batch);
    }

    protected void updateFeathers(float dt) {

        for (FeatherSack feather : creator.getFeathers()){
            feather.child=false; feather.update(dt);
            if(feather instanceof MovingFeather){feather.child=true;feather.Update();}
        }
    }
    protected void renderFeathers() {
        for (FeatherSack feather : creator.getFeathers()){
            if(feather.Rope!=null)feather.Rope.draw(game.batch);
            feather.draw(game.batch);
        }
    }

    protected void updateCharacters(float dt) {
        creator.getPhill().update(dt);
        for (SecondaryCharacter birds : creator.getBirds()) {
            birds.update(dt);
        }
        for (SecondaryCharacter deers : creator.getDeers()) {
            deers.update(dt);
        }
        for (SecondaryCharacter apes : creator.getApes()) {
            apes.update(dt);
        }
        for (Flame flame : staticGraphics.getFlames()) {
            flame.update(dt);
        }
        for (Enemy enemy : creator.getBabyDragons()) {
            enemy.update(dt);
        }

    }
    protected void renderCharacters() {
        creator.getPhill().draw(game.batch);
        for (SecondaryCharacter bird : creator.getBirds()) {
            bird.draw(game.batch);
        }
        for (SecondaryCharacter deer : creator.getDeers()) {
            deer.draw(game.batch);
        }
        for (SecondaryCharacter apes : creator.getApes()) {
            apes.draw(game.batch);
        }
        for (Flame flame : staticGraphics.getFlames()) {
            flame.draw(game.batch);
        }
        for (Enemy enemy : creator.getBabyDragons()) {
            enemy.draw(game.batch);
        }

    }

    protected void updateSwords() {
        staticlightiningsword.update();
        lightningsword.update();
        staticfireballsword.update();
        leftfirball.update();
        rightfireball.update();
        staticsonicsword.update();
        sonicsword.update();
    }
    protected void renderSwords() {
        if(staticlightiningsword.draw)staticlightiningsword.draw(game.batch);
        lightningsword.draw(game.batch);
        if(staticfireballsword.draw) staticfireballsword.draw(game.batch);
        leftfirball.draw(game.batch);
        rightfireball.draw(game.batch);
        if(staticsonicsword.draw2)staticsonicsword.draw(game.batch);
        sonicsword.leftsonic.draw(game.batch);
        sonicsword.rightsonic.draw(game.batch);
        sonicsword.upsonic.draw(game.batch);
    }

    protected void updateFireBalls() {
        for(Fireball fireball : creator.getFireballs())
            fireball.update();
    }
    protected void renderFireballs(){
        for(Fireball fireball : creator.getFireballs())
            fireball.draw(game.batch);
    }

    protected void  HerculesActionSound (String MusicPath){
        m = Main.manager.get(MusicPath,Music.class);
        m.setVolume(Main.vol);
        m.play();
    }

    public abstract void restart();

    protected boolean gameOver() {

        if (player.currentState == Hercules.State.die)  {
            if ((player.getStateTimer() > 1.35f)){
                GameOver.setVolume(Main.vol);
                GameOver.play();
                return true;
            }
            stopHercAction=true;
        }
        return false;
    }

    protected void cameraScoop(float startX, float endX){
         if (player.body.getPosition().x>startX/Main.PPM  && player.body.getPosition().x<endX/Main.PPM)
            gameCam.position.x = player .body.getPosition().x ;

        if (player.body.getPosition().y<470/Main.PPM )
            gameCam.position.y = player .body.getPosition().y+255/Main.PPM ;
     }
    /*End Some Helping Methods*/

    @Override
    public void show() { }

    @Override
    public void render(float dt) { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
