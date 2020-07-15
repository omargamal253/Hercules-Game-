package Intro;

import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SoundAndMusicSetting implements Screen {

    private Main game;
    private Texture background;
    private Sprite backgroundd;
    public Stage stage;
    private ImageButton back;
    private Skin skin;
    private Viewport viewport;
    private BitmapFont FONT;
    private Label.LabelStyle font;

    public SoundAndMusicSetting(final Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\0.jpg"));
        backgroundd=new Sprite(background);
        backgroundd.setPosition(-100, 0);
        backgroundd.setSize( game.WIDTH+550, game.HEIGHT+300);

        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        FONT = new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);

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
        Label MusicAndSound = new Label("Music And Sound Volume", font);
        MusicAndSound.setPosition(Gdx.graphics.getWidth() / 2 - MusicAndSound.getWidth()/2, Gdx.graphics.getHeight() / 2+100);
        stage.addActor(MusicAndSound);

        final TextButton volumeup = new TextButton("volume up", skin);
        final TextButton volumedown = new TextButton("volume down", skin);
        volumeup.setPosition(MusicAndSound.getX() +500, MusicAndSound.getY() - 200);

        volumeup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Main.vol< 1) {
                    Main.vol += 0.1f;
                    volumedown.setText("volume down");
                    volumedown.setTouchable(Touchable.enabled);
                } else {
                    volumeup.setText("Maximum volume");
                    volumeup.setTouchable(Touchable.disabled);
                }

            }
        });
        stage.addActor(volumeup);

        volumedown.setPosition(MusicAndSound.getX()-200, MusicAndSound.getY() - 200);

        volumedown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Main.vol > 0.2f) {
                    Main.vol -= 0.1f;
                    volumeup.setText("volume up");
                    volumeup.setTouchable(Touchable.enabled);
                } else {
                    volumedown.setText("Minimum volume");
                    volumedown.setTouchable(Touchable.disabled);
                }

            }
        });
        stage.addActor(volumedown);

        final TextButton MuteBtn = new TextButton(Main.MuteBtnName, skin);
        MuteBtn.setPosition(MusicAndSound.getX()-200, MusicAndSound.getY() - 350);
        MuteBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Main.TestMuteNum%2==0){
                    Main.vol=0;
                    Main.MuteBtnName="Unmute";
                }
                else{
                    Main.vol=1;
                    Main.MuteBtnName="mute";
                }
                Main.TestMuteNum++;
                MuteBtn.setText(Main.MuteBtnName);
            }
        });
        stage.addActor(MuteBtn);


        TextButton save = new TextButton("Save Changes", skin);
        save.setPosition(MusicAndSound.getX()+500, MusicAndSound.getY() - 350);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StartMenu(game));
            }
        });
        stage.addActor(save);


        TextButton back = new TextButton("Back", skin);
        back.setPosition(MusicAndSound.getX() + 200,  MusicAndSound.getY() - 500);

        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Setting(game));
                stage.dispose();
            }
        });
        stage.addActor(back);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        // Main.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        backgroundd.draw(game.batch);
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