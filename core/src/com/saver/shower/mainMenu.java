package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class mainMenu implements Screen{
	public AssetManager loadmanager;
	public BitmapFont white;
	private showerSaver object;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private TextButton button_start, button_history;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private boolean checked;
	private int imageHeight;
	
	public mainMenu(showerSaver obj){
		this.object = obj;
		this.loadmanager = obj.loadManager;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		img = new Texture("SS2.bmp");
		batch = new SpriteBatch();
		//Button Stuff
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		white = loadmanager.get("gamefont.fnt", BitmapFont.class);
		imageHeight = img.getHeight();
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
		
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
		batch.begin();
		batch.draw(img,0,Gdx.graphics.getHeight()-imageHeight);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		//camera.setToOrtho(false, 800, 600);
		if(stage == null){
			stage.getViewport().update(width, height, true);			
		}
		if(stage.getWidth()!=width|| stage.getHeight()!=height){
			stage.clear();
			stage.getViewport().update(width, height, true);
		}
		camera.setToOrtho(false, width, height);
		camera.update();
		
		Gdx.input.setInputProcessor(stage);
				
		//Buttons:
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.over = skin.getDrawable("button.down");
		style.font = white;
		button_start = new TextButton("Start", style);
		button_start.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_start.setHeight(Gdx.graphics.getHeight()/8);
		button_start.setX(Gdx.graphics.getWidth()/2 + button_start.getWidth());
		button_start.setY(Gdx.graphics.getHeight()/3 - 2*button_start.getHeight());
		button_start.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				Gdx.app.log(showerSaver.LOG, "Button Checked!");
				//Call SongPage
				object.setScreen(new songPage(object));				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked)
				Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_start.isPressed()){
					checked = false;
					Gdx.app.log(showerSaver.LOG, "Button [almost] Checked!");
				}else{
					checked = true;
				}
			}
		});
		//button_history
		button_history = new TextButton("History", style);
		button_history.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_history.setHeight(Gdx.graphics.getHeight()/8);
		button_history.setX(Gdx.graphics.getWidth()/2 - 2*button_start.getWidth());
		button_history.setY(Gdx.graphics.getHeight()/3 - 2*button_start.getHeight());
		button_history.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				Gdx.app.log(showerSaver.LOG, "Button Checked!");
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked)
				Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_start.isPressed()){
					checked = false;
					Gdx.app.log(showerSaver.LOG, "Button [almost] Checked!");
				}else{
					checked = true;
				}
			}
		});
		stage.addActor(button_start);
		stage.addActor(button_history);
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
		atlas.dispose();
		batch.dispose();
		skin.dispose();
		stage.clear();
		stage.dispose();
		button_start.remove();
	}

}
