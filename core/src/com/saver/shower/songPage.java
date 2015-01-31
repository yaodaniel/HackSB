package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class songPage extends history implements Screen{
	Stage stage;
	showerSaver obj;
	Music music;
	BitmapFont white;
	SpriteBatch batch;
	Texture img;
	TextButton button_start, button_history;
	TextureAtlas atlas;
	Skin skin;
	
	float totalTime, timer;
	public songPage(showerSaver obj){
		this.obj = obj;
		
		img = new Texture("SS2.bmp");
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		white = new BitmapFont(Gdx.files.internal("gamefont.fnt"));
	}

	@Override
	public void show() {
		totalTime = 30;
		timer = 30;
        music = Gdx.audio.newMusic(Gdx.files.internal("data/National Anthem of USSR.mp3"));
        music.play();
        
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.over = skin.getDrawable("button.down");
		style.font = white;
        
        button_start = new TextButton("Stop", style);
        stage.addActor(button_start);
        
		Gdx.input.setInputProcessor(stage);

        button_start.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
               music.stop();
               saveHistory(60, Math.round(timer));
               String[] res = getHistory();
               button_start.setText(res[res.length - 1]);
            };
        });
		

		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0,  0,  0,  1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
      //FileHandle[] fh = Gdx.files.internal("data/").list();
        
        if (totalTime <= 0){
            music.stop();
        }


        float deltaTime = Gdx.graphics.getDeltaTime(); 
        totalTime -= deltaTime;
        int minutes = ((int)totalTime) / 60;
        int seconds = ((int)totalTime) % 60;
		
        stage.act();
        stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
