package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class playlistScreen implements Screen{
	private showerSaver object;
	private LabelStyle displayPlaylistTitle;
	private Label displayPlaylist;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextButton button_p1,button_p2, button_p3, button_p4;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private boolean checked;
	private int avgMin, avgSec, longMin, longSec, shortMin, shortSec;
	private int resLength, minVal, maxVal, sum, avgVal;
	
	public playlistScreen(showerSaver obj){
		this.object = obj;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		
		//Button Stuff
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		showerSaver.white = showerSaver.loadManager.get("gamefont.fnt", BitmapFont.class);
		//Create Label
		displayPlaylistTitle = new LabelStyle(showerSaver.white,Color.WHITE);
		displayPlaylist = new Label(String.format("Select A Playlist:\n"), displayPlaylistTitle);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		if(stage == null){
			stage = new Stage(new ScreenViewport());
			stage.getViewport().update(width, height, true);
			stage.clear();
		}
		if(stage.getWidth()!=width || stage.getHeight()!= height){
			//stage.dispose();
			stage.getViewport().update(width, height, true);
		}
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		displayPlaylist.setX(0);
		displayPlaylist.setY(Gdx.graphics.getHeight()- displayPlaylist.getHeight());
		displayPlaylist.setWidth(width);
		displayPlaylist.setAlignment(Align.center);
		displayPlaylist.setFontScale(1.0f);
		stage.addActor(displayPlaylist);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.over = skin.getDrawable("button.down");
		style.font = showerSaver.white;
		
		button_p1 = new TextButton("Swag Song List1", style);
		button_p2 = new TextButton("Playlist num 2", style);
		button_p3 = new TextButton("Shower Time", style);
		button_p4 = new TextButton("Homework Playlist", style);

		button_p1.setWidth(Gdx.graphics.getWidth()/1.5f);
		button_p2.setWidth(Gdx.graphics.getWidth()/1.5f);
		button_p3.setWidth(Gdx.graphics.getWidth()/1.5f);
		button_p4.setWidth(Gdx.graphics.getWidth()/1.5f);
		
		button_p1.setHeight(Gdx.graphics.getHeight()/10);
		button_p2.setHeight(Gdx.graphics.getHeight()/10);
		button_p3.setHeight(Gdx.graphics.getHeight()/10);
		button_p4.setHeight(Gdx.graphics.getHeight()/10);
		
		button_p1.setX(Gdx.graphics.getWidth()/2 - button_p1.getWidth()/2);
		button_p2.setX(Gdx.graphics.getWidth()/2 - button_p2.getWidth()/2);
		button_p3.setX(Gdx.graphics.getWidth()/2 - button_p3.getWidth()/2);
		button_p4.setX(Gdx.graphics.getWidth()/2 - button_p4.getWidth()/2);
		
		button_p1.setY(Gdx.graphics.getHeight() - 3*button_p1.getHeight());
		button_p2.setY(Gdx.graphics.getHeight() - 4*button_p2.getHeight());
		button_p3.setY(Gdx.graphics.getHeight() - 5*button_p3.getHeight());
		button_p4.setY(Gdx.graphics.getHeight() - 6*button_p4.getHeight());
		
		button_p1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {checked = true;return true;}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){object.setScreen(new mainMenu(object));}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer){
				if(!(button_p1).isPressed()){checked = false;}else{checked = true;}
			}
		});

		button_p2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {checked = true;return true;}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){object.setScreen(new mainMenu(object));}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer){
				if(!(button_p2).isPressed()){checked = false;}else{checked = true;}
			}
		});

		button_p3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {checked = true;return true;}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){object.setScreen(new mainMenu(object));}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer){
				if(!(button_p3).isPressed()){checked = false;}else{checked = true;}
			}
		});

		
		button_p4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {checked = true;return true;}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){object.setScreen(new mainMenu(object));}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer){
				if(!(button_p4).isPressed()){checked = false;}else{checked = true;}
			}
		});

		stage.addActor(button_p1);
		stage.addActor(button_p2);
		stage.addActor(button_p3);
		stage.addActor(button_p4);
		
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
