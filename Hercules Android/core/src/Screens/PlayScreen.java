package Screens;

import StaticGraphics.StaticLightSword;
import StaticGraphics.StaticSonicSword;
import StaticGraphics.StaticFireBallSword;
import MovingObjects.Hercules;
import Sprites.*;
import Scenes.*;
import HealthAttacker.*;
import MovingObjects.SecondaryCharacter;
import StaticGraphics.*;
import Tools.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* LEVEL ONE SCREEN  */
public class PlayScreen implements Screen , InputProcessor {

    //  Some Essential Variables
    private Main game;
    public float swordTimer = 0;
    int normalcounter;
    boolean c, v;
    public Timer timer;
    public Sandal sandal;

    //Basic PlayScreen Variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private HUD hud;

    //Tiled Map Variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Atlas
    private TextureAtlas FlameAtlas;
    private TextureAtlas TotalAtlas;
    private TextureAtlas atlas_run;
    private TextureAtlas atlas_jumb;
    private TextureAtlas atlas_pillar;

    //Box2d Variables
    private World world;
    private Box2DDebugRenderer debuger;
    private B2WorldCreator creator;
    public WorldContactListener worldContactListener;

    //Sounds Variables
    private Music Game, GameOver, Pillarmusic;
    private Sound sound;

    //Sprites
    private DrawClass staticGraphics;
    public Swords staticlightiningsword;
    public Swords staticfireballsword, leftfirball, rightfireball;
    public Swords staticsonicsword, sonicsword, lightningsword;
    private Hercules player;
    public TallPiller piller;
   
     private ArrayList<GoldenCoin> goldcoin=new ArrayList<>();
    private ArrayList<SilverCoin> silvercoin=new ArrayList<>();
    private ArrayList<Cannons> filreball=new ArrayList<>();

    private ProtectedShield Shield;
    List<HealthAttacker.FeatherSack> featherList;
    List<MovingFeather> MovingfeatherList;
    List<Block> BlockList;
    Hill hill;
    Herculad juice;
        private  Music m;

        ImageButton RightButton  ;

        // kareem
        InputHandle inputhandle ;
    public PlayScreen(Main game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps\\Level One\\HerculesMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        FlameAtlas = new TextureAtlas("Sprites\\Main\\Flame.atlas");
        TotalAtlas = new TextureAtlas("Sprites\\Main\\Total.pack");
        atlas_run = new TextureAtlas("Sprites\\Run600_75.pack");
        atlas_jumb = new TextureAtlas("Sprites\\H_Jump.pack");
        atlas_pillar = new TextureAtlas("Sprites\\tallpillar.pack");
        staticGraphics = new DrawClass(this);

        //CREATING THE BOX2D AND WORLD PHYSICS
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new WorldContactListener());
        debuger = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);

        player = new Hercules(world, this);
        staticlightiningsword = new StaticLightSword(15555f / Main.PPM, 300f / Main.PPM, player);
        staticfireballsword = new StaticFireBallSword(10400f / Main.PPM, 300f / Main.PPM, player);
        leftfirball = rightfireball = staticfireballsword;
        staticsonicsword = new StaticSonicSword(20112f / Main.PPM, 336f / Main.PPM, player);
        sonicsword = new SonicSword(0, 0, player);
        lightningsword = staticsonicsword;
        hud = new HUD(player, game.batch);
        piller = new TallPiller(world, this, 6660, 50);

        /*Coins*/
        
        goldcoin.add(new GoldenCoin (this,2192,288,player,hud));
        goldcoin.add(new GoldenCoin (this,2240,336,player,hud));
        goldcoin.add(new GoldenCoin (this,2288,384,player,hud));
        goldcoin.add(new GoldenCoin (this,18520,750,player,hud));
        goldcoin.add(new GoldenCoin (this,18670,750,player,hud));
        goldcoin.add(new GoldenCoin (this,18820,750,player,hud));

        silvercoin.add(new SilverCoin (this,13120,272,player,hud));
          
        silvercoin.add(new SilverCoin (this,13120,352,player,hud));
        silvercoin.add(new SilverCoin (this,13120,416,player,hud));
        //Cannons Fireballs
     
      filreball.add(new Cannons(2256,1104,player,hud));
        filreball.add(new Cannons(13120,1050,player,hud));
        filreball.add(new Cannons(20448,680,player,hud));
        filreball.add(new Cannons(20720,700,player,hud));
        filreball.add(new Cannons(20976,730,player,hud));
        filreball.add(new Cannons(21248,750,player,hud));
        filreball.add(new Cannons(21456,790,player,hud));
        filreball.add(new Cannons(21728,810,player,hud));
        filreball.add(new Cannons(21888,890,player,hud));
        filreball.add(new Cannons(22160,920,player,hud));
        filreball.add(new Cannons(21888,980,player,hud));
        filreball.add(new Cannons(22160,1104,player,hud));
        //Protected Shield
        Shield = new ProtectedShield(player, hud);
        //Feathers
        featherList = new LinkedList<>();
        MovingfeatherList = new LinkedList<>();
        BlockList = new LinkedList<>();
        define_featherSack();
        define_MovingfeatherSack();
        define_Blocks();

        hill = new Hill(world, this, this.map);
        juice = new Herculad(world, this, 18080, 432);
        normalcounter = 0;
        c = v = false;
        timer = new Timer(player, hud);
        sandal = new Sandal(new Texture("Sprites\\sandal.png"), 6000 / Main.PPM, 300 / Main.PPM, player);

        Pillarmusic = game.manager.get("Audio//Hercules - sounds//Tall pillar Cracked.wav");
        sound = game.manager.get("Audio//Hercules - Voices//Phil//Excellenty.wav", Sound.class);
        GameOver = game.manager.get("Audio//Hercules - sounds//Game Over.mp3", Music.class);
        Game = game.manager.get("Audio//Hercules - sounds//Nature Sound.wav", Music.class);
        Game.setLooping(true);
        Game.play();
       inputhandle = new InputHandle();
      /*  Texture Right = new Texture("Sprites\\button_up.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(Right));
        RightButton =new ImageButton(drawable) ;
        RightButton.setPosition(300, 300);
        RightButton.setBounds(0,0, 100 , 100);
        RightButton.setSize(200,200);
   hud.stage.addActor(RightButton);*/

    }
    public TextureAtlas getFlameAtlas() {
        return FlameAtlas;
    }

    public TextureAtlas getTotalAtlas() {
        return TotalAtlas;
    }

    public TextureAtlas getAtlas_pillar() {
        return atlas_pillar;
    }

    public Hercules getPlayer()   {
        return player;
    }

    public TextureAtlas getAtlas_Run() {
        return atlas_run;
    }

    public TextureAtlas getAtlas_jumb() {
        return atlas_jumb;
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
    public void  HerculesActionSound (String MusicPath){
         m = Main.manager.get(MusicPath,Music.class);
                m.setVolume(1f); 
                m.play();
    }

    /**
     * ********* SOME HELPING METHOD **************
     */
    private void handleInput(float dt) {

        /*Gdx.input.setInputProcessor(hud.stage);
        RightButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (player.b2body.getLinearVelocity().x <= player.HerculesMaxSpeed)
                    player.b2body.applyForceToCenter(new Vector2(3, 0), true);

                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            @Override
            public boolean handle(Event event) {
                return false;
            }
        });*/
        //control our player using immediate impulses
        if (inputhandle.Hup == true && player.b2body.getPosition().y <= player.HerculesMaxSpeedHigh) {
            player.b2body.applyLinearImpulse(new Vector2(0, 2.5f), player.b2body.getWorldCenter(), true);
                    HerculesActionSound("Audio//Hercules - Voices//Hercules//Jumb2.wav");
 
        } else if (inputhandle.Hdown == true) {
            player.b2body.applyLinearImpulse(new Vector2(0, -2.5f), player.b2body.getWorldCenter(), true);
        } else if (inputhandle.Hright == true && player.b2body.getLinearVelocity().x <= player.HerculesMaxSpeed) {
            player.b2body.applyForceToCenter(new Vector2(3, 0), true);
        } else if (inputhandle.Hleft == true && player.b2body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed) {
            player.b2body.applyForceToCenter(new Vector2(-3, 0), true);
        }

        if (inputhandle.Hpush1 == true ) {
             player.hercules_push = true;
            c = true;
            handleTallPillarCrash();
            HerculesActionSound("Audio//Hercules - sounds//a2.wav");
        
        } else if (inputhandle.Hpush2 == true) {
            
            v = true;
            player.hercules_Smallpush = true;
            handleTallPillarCrash();
                HerculesActionSound("Audio//Hercules - sounds//Punch.wav");
        } else if (inputhandle.Hsword2 == true) {
            handleSword();
        } else if (inputhandle.Hsword1 == true) {
            v = true;
            player.hercules_sword2 = true;
            handleTallPillarCrash();
                        HerculesActionSound("Audio//Hercules - sounds//sword.wav");

            
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }

        handleJuice();
        v = c = false;
        
        
               // if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
                   // System.out.println(Input.Keys.toString(Input.Keys.ANY_KEY));
                 
        
    
               // }

    }

    public void define_featherSack() {
        HealthAttacker.FeatherSack feather1, feather2, feather3, feather4, feather5;
        feather1 = new HealthAttacker.FeatherSack(3160 / Main.PPM, 320 / Main.PPM, world, this);
        feather2 = new HealthAttacker.FeatherSack(3420 / Main.PPM, 350 / Main.PPM, world, this);
        feather3 = new HealthAttacker.FeatherSack(3685 / Main.PPM, 350 / Main.PPM, world, this);
        feather4 = new HealthAttacker.FeatherSack(3950 / Main.PPM, 320 / Main.PPM, world, this);

        featherList.add(feather1);
        featherList.add(feather2);
        featherList.add(feather3);
        featherList.add(feather4);
    }

    public void define_MovingfeatherSack() {
        MovingFeather m1 = new MovingFeather(5200 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m2 = new MovingFeather(5600 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m3 = new MovingFeather(8000 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m4 = new MovingFeather(8400 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m5 = new MovingFeather(9800 / Main.PPM, 320 / Main.PPM, world, this, 2);
        MovingfeatherList.add(m1);
        MovingfeatherList.add(m2);
        MovingfeatherList.add(m3);
        MovingfeatherList.add(m4);
        MovingfeatherList.add(m5);
    }

    public void define_Blocks() {
        Block b1 = new Block(4360, 60, world);
        BlockList.add(b1);
    }

    private void handleSword() {
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

    private void handleTallPillarCrash() {

        /**
         * *****Allow Crash Animation to start*******
         */
        if (piller.crashed == false) {
            if (piller.getBoundingRectangle().overlaps(player.getBoundingRectangle())) {
                Pillarmusic.setVolume(0.5f);
                Pillarmusic.setLooping(false);
                Pillarmusic.play();

                if (c == true || (v == true && normalcounter == 3)) {

                    sound.play();

                    piller.STATE = true;
                    world.destroyBody(piller.b2body);
                    piller.crashed = true;
                } else if (v) {
                    normalcounter++;
                    v = false;
                }

            }

        }
    }

    private void handleJuice() {
        if (juice.rect.overlaps(player.getBoundingRectangle())) {
            if (!juice.Catch) {
                juice.Catch = true;
                player.hercules_Drink = true;
            }
        }

    }

    private void updateCoins() {
     
        //golden coins
        for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).update(player);
        }
        //silver coins
        for(int i=0;i<silvercoin.size();i++){
            silvercoin.get(i).update(player);
        }
    }

    private void updateCharacters(float dt) {
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

    private void updateSwords() {
        staticlightiningsword.update();
        lightningsword.update();
        staticfireballsword.update();
        leftfirball.update();
        rightfireball.update();
        staticsonicsword.update();
        sonicsword.update();
    }

    private void updateFireBalls() {
      
      for(int i=0;i<filreball.size();i++){
            filreball.get(i).update();
        }
    }

    private void FeathersAndBlock(float dt) {
        for (int i = 0; i < featherList.size(); i++) {
            featherList.get(i).update(dt);
        }

        for (int i = 0; i < MovingfeatherList.size(); i++) {
            MovingfeatherList.get(i).update(dt);
        }

        for (int i = 0; i < BlockList.size(); i++) {
            BlockList.get(i).update(dt);
            BlockList.get(i).Block_Moving(4);
        }

        for (int i = 0; i < BlockList.size(); i++) {
            if (BlockList.get(i).blockDown == false) {
                if (BlockList.get(i).blockFinish == false) {
                    world.destroyBody(BlockList.get(i).b2body);
                    BlockList.get(i).blockFinish = true;
                }

            }
        }
    }

    public void getbaby(B2WorldCreator creator) {
        for (int i = 0; i < creator.getBabyDragons().size; i++) {
            Rectangle recbaby = creator.getBabyDragons().get(i).getBoundingRectangle();
            if (recbaby.overlaps(lightningsword.getBoundingRectangle())) {
                if (lightningsword.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }
            } else if (recbaby.overlaps(rightfireball.getBoundingRectangle())) {
                if (rightfireball.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(leftfirball.getBoundingRectangle())) {
                if (leftfirball.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(sonicsword.rightsonic.getBoundingRectangle()) || recbaby.overlaps(sonicsword.leftsonic.getBoundingRectangle()) || recbaby.overlaps(sonicsword.upsonic.getBoundingRectangle())) {
                if (sonicsword.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }
            }
        }
    }

    public boolean gameOver() {
        if (player.currentState == Hercules.State.die && player.getStateTimer() > 1.4) {
            GameOver.play();
            return true;
        }
        return false;
    }


    public void update(float dt) {
        handleInput(dt);
        hud.update(dt);
        world.step(1 / 60f, 6, 2);

        updateCharacters(dt);

        getbaby(creator);
        sandal.update();
        timer.update();

        player.update(dt);
        updateSwords();
        updateFireBalls();
        piller.update(dt);
        updateCoins();
        juice.update(dt);
        Shield.update(dt);

        FeathersAndBlock(dt);

        gameCam.update();
        renderer.setView(gameCam);

        if (player.b2body.getPosition().x > 1000 / Main.PPM && player.b2body.getPosition().x < 23000 / Main.PPM) {
            gameCam.position.x = player.b2body.getPosition().x;
        }
        if (player.b2body.getPosition().y < 470 / Main.PPM) {
            gameCam.position.y = player.b2body.getPosition().y + 255 / Main.PPM;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        debuger.render(world,gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        renderCharacters();
        player.draw(game.batch);
        piller.draw(game.batch);
        juice.draw(game.batch);
        sandal.draw(game.batch);
        Shield.draw(game.batch);
        renderSwords();
        rederCoinsAndFire();
        renderFeatherSacks();

       // RightButton.draw(game.batch ,0);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
      /*  hud.stage.act();*/
        hud.stage.draw();

        if (gameOver()) {
            Game.stop();
            game.setScreen(new GameOver(game));
            dispose();
        }


        game.batch.setProjectionMatrix(inputhandle.buttonStage.getCamera().combined);
        inputhandle.buttonStage.draw();

        
       
    }

    private void renderCharacters() {

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

    private void renderSwords() {
        staticlightiningsword.draw(game.batch);
        lightningsword.draw(game.batch);
        staticfireballsword.draw(game.batch);
        leftfirball.draw(game.batch);
        rightfireball.draw(game.batch);
        staticsonicsword.draw(game.batch);
        sonicsword.leftsonic.draw(game.batch);
        sonicsword.rightsonic.draw(game.batch);
        sonicsword.upsonic.draw(game.batch);
    }

    private void rederCoinsAndFire() {
       
       for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).draw(game.batch);
        }
       //silver coins
        for(int i=0;i<silvercoin.size();i++){
            silvercoin.get(i).draw(game.batch);
        }
       //Cannons fireballs 
        for(int i=0;i<filreball.size();i++){
            filreball.get(i).draw(game.batch);
        }
    }

    private void renderFeatherSacks() {

        for (int i = 0; i < featherList.size(); i++) {

            if (featherList.get(i).featherCollsoin(player) == 1) {
           //     HerculesActionSound("Audio//Hercules - sounds//featherFinish.wav");
                /* SilverCoin     silver3 = new SilverCoin (this,featherList.get(i).getX() *Main.PPM -100  ,featherList.get(i).getY() *Main.PPM +100 ,player,hud); silver3.move=true;
                silvercoin.add(silver3);*/
                featherList.remove(i);
                HealthAttacker.FeatherSack.Num_of_feather_Destroyed++;
                hud.score+=10;
                
            } else {

                if (featherList.get(i).featherCollsoin(player) == 2) {
                    hud.featherHit();
                    featherList.get(i).draw(game.batch);
                } else {
                    featherList.get(i).draw(game.batch);
                }
            }

        }

        for (int i = 0; i < MovingfeatherList.size(); i++) {
            if (MovingfeatherList.get(i).order == 1 || MovingfeatherList.get(i).order == 2) {
                MovingfeatherList.get(i).Rope.draw(game.batch);
            }
            MovingfeatherList.get(i).featherMoving(player);
            if (MovingfeatherList.get(i).featherCollsoin(player) == 2) {
                hud.featherHit();
                MovingfeatherList.get(i).draw(game.batch);
            } else {
                if (MovingfeatherList.get(i).featherCollsoin(player) == 1) {
                                    //HerculesActionSound("Audio//Hercules - sounds//featherFinish.wav");
         
             SilverCoin     silver3 = new SilverCoin (this,MovingfeatherList.get(i).getX() *Main.PPM -100  ,MovingfeatherList.get(i).getY() *Main.PPM +200 ,player,hud); silver3.move=true;
                silvercoin.add(silver3);
                MovingfeatherList.remove(i);
                                    hud.score+=15;

                } else {
                    MovingfeatherList.get(i).draw(game.batch);
                }
            }

        }

        for (int i = 0; i < BlockList.size(); i++) {
            BlockList.get(i).draw(game.batch);
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debuger.dispose();
        hud.dispose();
    }
    
     
  
    @Override
    public boolean keyDown(int arg0) {
         
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char arg0) {
        System.out.println(arg0);
                        System.out.println("oooooooooooooooooooooooooooooooooo");

        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mouseMoved(int arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
