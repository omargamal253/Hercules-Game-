
package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Setting  implements Screen{
    
    private Main game;
    private ImageButton back;
    public Stage stage;
    private Skin skin;
    private Texture background;
    private Viewport viewport;

    public Setting(final Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\000.jpg"));
        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT,  new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
          createBasicSkin(); 
          Buttons();
        back = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\Back.png"))));
        back.setPosition(80f, Main.HEIGHT/1.2f);
        back.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y){
               game.setScreen(new StartMenu(game));
               stage.dispose();
           }
        });
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
    }

    private void createBasicSkin() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        skin = new Skin();
        skin.add("default", font);
        
        // CREATE A TEXTURE
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin.add("Background", new Texture(pixmap));
        
        // CREATE A BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("Background", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("Background", Color.YELLOW);
        textButtonStyle.checked = skin.newDrawable("Background", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("Background", Color.BLUE);  // HOVER
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }
    void Buttons() {
        TextButton controlerkeys  = new TextButton("Controller keys",skin);
        controlerkeys.setPosition(game.WIDTH / 3, game.HEIGHT / 2 + 150);
        controlerkeys.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ButtonsSetting(game));
                stage.dispose();
            }
        });
        stage.addActor(controlerkeys);
        TextButton MusicandSound = new TextButton("Music and Sound", skin);
        MusicandSound.setPosition(controlerkeys.getX() , controlerkeys.getY()-140);
        MusicandSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new SoundAndMusicSetting(game));
                stage.dispose();
            }
        });
        stage.addActor(MusicandSound);
        

        TextButton back = new TextButton("Back", skin);
        back.setPosition(MusicandSound.getX(),MusicandSound.getY()-140);
        
        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) { 
                game.setScreen(new StartMenu(game));
                stage.dispose();
            }
        });
        stage.addActor(back);
        
    }
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       Main.batch.begin();
        Main.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        Main.batch.end(); 
       stage.act();
       stage.draw();
    }

    @Override
    public void show() {
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
