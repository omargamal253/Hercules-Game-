package Tools;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Intro.StartMenu;
import Screens.PlayScreen;

public class InputHandle {

    public PlayScreen screen;
    public Stage buttonStage;
    private ImageButton rightButton;
    private ImageButton leftButton;
    private ImageButton upButton;
    private ImageButton downButton;
    private ImageButton push1Button;
    private ImageButton push2Button;
    private ImageButton sword1Button;
    private ImageButton sword2Button;
    private ImageButton pauseBtn;

    private boolean swords;
    public boolean powerPunch, normalPunch;
    public  boolean Hright ; public static float HRightX ,HRightY;
    public boolean Hleft ;  public static float HLeftX ,HLeftY;
    public boolean Hup ;  public static float HUpX ,HUpY;
    public boolean Hdown ;  public static float HDownX ,HDownY;
    public boolean Hpush1 ;  public static float HPush1X ,HPush1Y;
    public boolean Hpush2 ; public static float HPush2X ,HPush2Y;
    public boolean Hsword1 ; public static float HSword1X ,HSword1Y;
    public boolean Hsword2 ; public static float HSword2X ,HSword2Y;
    public static int mode;
    
    public InputHandle(PlayScreen screen){
        this.screen = screen;
        buttonStage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT));
        swords = false;
        if (!screen.noSwords){
            createSword1DirectionButtons(); Hsword1 = false;
            createSword2DirectionButtons(); Hsword2 = false;
            swords = true;
        }

        createRightDirectionButtons();  Hright = false ;
        createLeftDirectionButtons();  Hleft = false ;
        createUpDirectionButtons();  Hup = false ;
        createDownDirectionButtons();  Hdown = false ;
        createPush1DirectionButtons(); Hpush1 =false ;
        createPush2DirectionButtons(); Hpush2 =false ;
        createPauseButton();

        Gdx.input.setInputProcessor(buttonStage);
    }

    public void resetSettings(){
        buttonStage.clear();
        swords = false;
        screen.paused = false;
        screen.pauseWindow.setVisible(true);
        if (!screen.noSwords){
            createSword1DirectionButtons(); Hsword1 = false;
            createSword2DirectionButtons(); Hsword2 = false;
            swords = true;
        }

        createRightDirectionButtons();  Hright = false ;
        createLeftDirectionButtons();  Hleft = false ;
        createUpDirectionButtons();  Hup = false ;
        createDownDirectionButtons();  Hdown = false ;
        createPush1DirectionButtons(); Hpush1 =false ;
        createPush2DirectionButtons(); Hpush2 =false ;
        createPauseButton();
        buttonStage.addActor(screen.pauseWindow);

        Gdx.input.setInputProcessor(buttonStage);
    }

    private void createPauseButton() {
        pauseBtn = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\pause.png"))));
        pauseBtn.setPosition( 50, 660); // 50 660
        pauseBtn.setSize(100f,100f);
        pauseBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                screen.pauseWindow.setVisible(true);
                screen.paused = true;
            }
        });

        buttonStage.addActor(pauseBtn);
    }


    public void createRightDirectionButtons(){


        rightButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\right.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\rightClicked2.png"))));

        // rightButton.setPosition(Gdx.graphics.getWidth()/1.3f , 170); // BUTTON POSITION

        HRightX = Main.WIDTH/1.1f ; HRightY = Main.HEIGHT/4.7f;

        if(mode==1)   rightButton.setPosition(Main.WIDTH/1.1f , Main.HEIGHT/4.7f);
        else if(mode==2)    rightButton.setPosition(Main.WIDTH/1.2f + 150f, Main.HEIGHT/5f);
        else     rightButton.setPosition(Main.WIDTH/1.1f , Main.HEIGHT/4.7f);



        buttonStage.addActor(rightButton);

        rightButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Hright = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hright = false;
            }
        });


    }

    public void createLeftDirectionButtons(){


        leftButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\left.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\leftClicked2.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code

        //  leftButton.setPosition(Gdx.graphics.getWidth()/1.5f , 170); // BUTTON POSITION
        HLeftX = Main.WIDTH/1.26f ; HLeftY = Main.HEIGHT/4.7f;
        leftButton.setPosition(Main.WIDTH/1.26f , Main.HEIGHT/4.7f);

        if(mode==1)    leftButton.setPosition(Main.WIDTH/1.26f , Main.HEIGHT/4.7f);
        else if(mode==2)   leftButton.setPosition(Main.WIDTH/1.2f - 30f, Main.HEIGHT/5f);
        else       leftButton.setPosition(Main.WIDTH/1.26f , Main.HEIGHT/4.7f);


        buttonStage.addActor(leftButton);

        leftButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                // leftButton.setPosition(vector3.x,vector3.y);
               /* final Vector3 vector3 =new Vector3();

        PlayScreen.gameCam.unproject(   vector3.set(Gdx.input.getX(),Gdx.input.getY(),0));

        leftButton.setPosition(vector3.x,vector3.y);*/
                //  leftButton.setPosition(Main.WIDTH/2,Main.HEIGHT/2);
                Hleft = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hleft = false;
            }
        });



    }

    public void createUpDirectionButtons(){


        upButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\upClicked2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\up.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code

        // upButton.setPosition(Gdx.graphics.getWidth()/1.4f +5 , 280); // BUTTON POSITION

        HUpX = Main.WIDTH/1.18f ; HUpY = Main.HEIGHT/2.857f;

        upButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/2.857f);

        if(mode==1)    upButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/2.857f);
        else if(mode==2)     upButton.setPosition(Main.WIDTH/20f , Main.HEIGHT/3f - 30f);
        else        upButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/2.857f);


        buttonStage.addActor(upButton);

        upButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hup = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hup = false;
            }
        });


    }

    public void createDownDirectionButtons(){


        downButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\downClicked2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\down.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code


        // downButton.setPosition(Gdx.graphics.getWidth()/1.4f +5, 60); // BUTTON POSITION
        HDownX = Main.WIDTH/1.18f ;  HDownY = Main.HEIGHT/13.3f;

        downButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/13.3f);

        if(mode==1)     downButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/13.3f);
        else if(mode==2)   downButton.setPosition(Main.WIDTH/20f , Main.HEIGHT/6f - 50f);
        else        downButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/13.3f);


        buttonStage.addActor(downButton);

        downButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hdown = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hdown = false;
            }
        });


    }

    public void createPush1DirectionButtons(){

        push1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\push1.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\push1clicked.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code


        //push1Button.setPosition(Gdx.graphics.getWidth()/1.4f +5, 400);

        HPush1X = Main.WIDTH/1.15f  ; HPush1Y = Main.HEIGHT/1.8f;
        push1Button.setPosition(Main.WIDTH/1.15f , Main.HEIGHT/1.8f);
        if(mode==1)        push1Button.setPosition(Main.WIDTH/1.15f , Main.HEIGHT/1.8f);
        else if(mode==2)  push1Button.setPosition(Main.WIDTH/3f + 600f , Main.HEIGHT/25f);
        else           push1Button.setPosition(Main.WIDTH/1.15f , Main.HEIGHT/1.8f);

        buttonStage.addActor(push1Button);

        push1Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                powerPunch = false;
                Hpush1 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                powerPunch = true;
                Hpush1 = false;
            }
        });
    }

    public void createPush2DirectionButtons(){


        push2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\push2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\push2clicked.png"))));

        // push2Button.setPosition(100, 70);
        HPush2X = Main.WIDTH/20f ; HPush2Y=Main.HEIGHT/11.43f;
        push2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/11.43f);

        if(mode==1)        push2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/11.43f);
        else if(mode==2)  push2Button.setPosition(Main.WIDTH/3f + 400f, Main.HEIGHT/25f);
        else          push2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/11.43f);


        buttonStage.addActor(push2Button);

        push2Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                normalPunch = false;
                Hpush2 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                normalPunch = true;
                Hpush2 = false;
            }
        });

    }

    public void createSword1DirectionButtons(){


        sword1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\sword1.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\sword1clicked.png"))));

        // push2Button.setPosition(100, 70);
        HSword1X = Main.WIDTH/20f ; HSword1Y= Main.HEIGHT/3.3f;
        sword1Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/3.3f);

        if(mode==1)         sword1Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/3.3f);
        else if(mode==2)   sword1Button.setPosition(Main.WIDTH/4f, Main.HEIGHT/25f);
        else          sword1Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/3.3f);

        buttonStage.addActor(sword1Button);

        sword1Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hsword1 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hsword1 = false;
            }
        });


    }

    public void createSword2DirectionButtons(){


        sword2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\sword2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\Level 1\\Buttons\\sword2clicked2.png"))));

        // push2Button.setPosition(100, 70);
        HSword2X = Main.WIDTH/20f ; HSword2Y= Main.HEIGHT/1.95f;
        sword2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/1.95f);

        if(mode==1)       sword2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/1.95f);
        else if(mode==2)          sword2Button.setPosition(Main.WIDTH/4f + 200f , Main.HEIGHT/25f);
        else         sword2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/1.95f);

        buttonStage.addActor(sword2Button);

        sword2Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hsword2 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hsword2 = false;
            }
        });


    }

}
