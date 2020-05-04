package Tools;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class InputHandle {
    public Stage buttonStage;
    public ImageButton rightButton;
    public ImageButton leftButton;
    public ImageButton upButton;
    public ImageButton downButton;
    public ImageButton push1Button;
    public ImageButton push2Button;
    public ImageButton sword1Button;
    public ImageButton sword2Button;
    public  boolean Hright ;
    public boolean Hleft ;
    public boolean Hup ;
    public boolean Hdown ;
    public boolean Hpush1 ;
    public boolean Hpush2 ;
    public boolean Hsword1 ;
    public boolean Hsword2 ;

    public InputHandle(){
        buttonStage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT)); //bitktp mara wa7da lakn law atchal mch haitrsmo

        createRightDirectionButtons();  Hright = false ;
        createLeftDirectionButtons();  Hleft = false ;
        createUpDirectionButtons();  Hup = false ;
        createDownDirectionButtons();  Hdown = false ;
        createPush1DirectionButtons(); Hpush1 =false ;
        createPush2DirectionButtons(); Hpush2 =false ;
        createSword1DirectionButtons(); Hsword1 =false ;
        createSword2DirectionButtons(); Hsword2 =false ;
    }

    public void createRightDirectionButtons(){


        rightButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\right.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\rightClicked2.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code

       // rightButton.setPosition(Gdx.graphics.getWidth()/1.3f , 170); // BUTTON POSITION
        rightButton.setPosition(Main.WIDTH/1.1f , Main.HEIGHT/4.7f);


        buttonStage.addActor(rightButton); // kul button htzwdlo el satr da

        Gdx.input.setInputProcessor(buttonStage);


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


        leftButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\left.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\leftClicked2.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code

      //  leftButton.setPosition(Gdx.graphics.getWidth()/1.5f , 170); // BUTTON POSITION
        leftButton.setPosition(Main.WIDTH/1.26f , Main.HEIGHT/4.7f);

        buttonStage.addActor(leftButton); // kul button htzwdlo el satr da

        Gdx.input.setInputProcessor(buttonStage);


        leftButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hleft = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hleft = false;
            }
        });
    }

    public void createUpDirectionButtons(){


        upButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\upClicked2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\up.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code

       // upButton.setPosition(Gdx.graphics.getWidth()/1.4f +5 , 280); // BUTTON POSITION
        upButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/2.857f);

        buttonStage.addActor(upButton); // kul button htzwdlo el satr da

        Gdx.input.setInputProcessor(buttonStage);


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


        downButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\downClicked2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\down.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code


       // downButton.setPosition(Gdx.graphics.getWidth()/1.4f +5, 60); // BUTTON POSITION
        downButton.setPosition(Main.WIDTH/1.18f , Main.HEIGHT/13.3f);
        buttonStage.addActor(downButton); // kul button htzwdlo el satr da

        Gdx.input.setInputProcessor(buttonStage);


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


        push1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\push1.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\push1clicked.png")))); // Mawgoden le awl mara bs w mmkn mtkpch el stor dol w tst8dm el satr ly t7t law 3ayz t2ll code



        //push1Button.setPosition(Gdx.graphics.getWidth()/1.4f +5, 400);
                push1Button.setPosition(Main.WIDTH/1.15f , Main.HEIGHT/1.8f);
        buttonStage.addActor(push1Button);

        Gdx.input.setInputProcessor(buttonStage);


        push1Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hpush1 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hpush1 = false;
            }
        });


    }


    public void createPush2DirectionButtons(){


        push2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\push2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\push2clicked.png"))));

       // push2Button.setPosition(100, 70);
        push2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/11.43f);

        buttonStage.addActor(push2Button);

        Gdx.input.setInputProcessor(buttonStage);


        push2Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Hpush2 = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                Hpush2 = false;
            }
        });


    }


    public void createSword1DirectionButtons(){


        sword1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\sword1.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\sword1clicked.png"))));

        // push2Button.setPosition(100, 70);
        sword1Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/3.3f);

        buttonStage.addActor(sword1Button);

        Gdx.input.setInputProcessor(buttonStage);


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


        sword2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\sword2.png"))) ,
                new TextureRegionDrawable(new TextureRegion(new Texture("Sprites\\sword2clicked2.png"))));

        // push2Button.setPosition(100, 70);
        sword2Button.setPosition(Main.WIDTH/20f , Main.HEIGHT/1.95f);

        buttonStage.addActor(sword2Button);

        Gdx.input.setInputProcessor(buttonStage);


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
