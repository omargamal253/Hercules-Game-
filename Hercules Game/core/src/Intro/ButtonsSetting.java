package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Tools.InputHandle;

public class ButtonsSetting implements Screen {
    Main game ;
    Sprite BackGround, correct;
    InputHandle inputHandle;
    public Stage buttonStage;
    public ImageButton Mode1Button , Mode2Button , SaveButton, BackButton;


    public ButtonsSetting(Main game){
        this.game = game ;
        buttonStage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT));
        BackGround = new Sprite(new Texture("Intros\\blueSky.jpeg"), 0, 0, 1365, 768);
        BackGround.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        correct = new Sprite(new Texture("Intros\\green_Ic2.png"), 0, 0, 60, 60);
        correct.setSize(100,100);
        if(InputHandle.mode==1)
            correct.setPosition(680, 110f);
        else if(InputHandle.mode==2)   ;

        else  correct.setPosition(680, 110f);

        inputHandle = new InputHandle();
        createMode1Button();
        createMode2Button();
        createSaveButton();
        createBackButton();

    }


    public void createMode1Button(){
        Mode1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\mode1.png"))));

        //  Mode1Button.setPosition(320f , 200f);
        Mode1Button.setPosition(Main.WIDTH/5.3f, 200f);

        buttonStage.addActor(Mode1Button);
        Gdx.input.setInputProcessor(buttonStage);


        Mode1Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                InputHandle.mode=1;
                correct.setPosition(Mode1Button.getX() + (Mode1Button.getWidth()/2), 110f);

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }


    public void createMode2Button(){

        Mode2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\mode2.jpg"))));

        // Mode2Button.setPosition(1100f , 200f);
        Mode2Button.setPosition(Main.WIDTH/1.8f, 200f);

        buttonStage.addActor(Mode2Button);

        Gdx.input.setInputProcessor(buttonStage);

        Mode2Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                InputHandle.mode=2;
                //    correct.setPosition(1610, 110f);
                correct.setPosition(Mode2Button.getX()+(Mode2Button.getWidth()/2), 110f);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

    }

    public void createSaveButton(){
        SaveButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\savebtn.png"))));
        SaveButton.setSize(SaveButton.getWidth(),SaveButton.getHeight());

        SaveButton.setPosition(1080f , 650f);

        buttonStage.addActor(SaveButton);

        Gdx.input.setInputProcessor(buttonStage);

        SaveButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

    }


    public void createBackButton(){
        BackButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\backbtn.png"))));
        BackButton.setSize(SaveButton.getWidth()+20,SaveButton.getHeight()+20);
        BackButton.setPosition(750f , 640f);

        buttonStage.addActor(BackButton);

        Gdx.input.setInputProcessor(buttonStage);

        BackButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new StartMenu(game));
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(51, 51, 0, 2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        BackGround.draw(game.batch);
        correct.draw(game.batch);
        //  BackGround.draw(game.batch);

        game.batch.end();
        buttonStage.draw();
        // inputHandle.buttonStage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
