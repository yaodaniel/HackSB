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


public class mainMenu implements Screen{
	private showerSaver appObject;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private TextButton button_start, button_history;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private boolean checked;
	private LabelStyle titleLabelStyle;
	private Label titleLabel;
	
	public mainMenu(showerSaver appObject){
		this.appObject = appObject;
		//camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera = new OrthographicCamera(600,795);
		/*camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();*/
		//camera.setToOrtho(false, 800, 600);
		//img = new Texture("SS2.bmp");
		img = new Texture("SSbg.png");
		batch = new SpriteBatch();
		//Button Stuff
		atlas = showerSaver.loadManager.get("buttons.pack");
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		showerSaver.white = showerSaver.loadManager.get("gamefont.fnt", BitmapFont.class);
		//Create Label
		titleLabelStyle = new LabelStyle(showerSaver.white,Color.BLACK);
		titleLabel = new Label(String.format("Shower Saver\n"), titleLabelStyle);
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
		batch.draw(img,Gdx.graphics.getWidth()/2-img.getWidth()/2,Gdx.graphics.getHeight()/2-img.getHeight()/2);
		batch.end();
		batch.begin();
		stage.draw();
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		//camera.setToOrtho(false, width/2, height/2);
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        //camera.setToOrtho(false,600,795);
		camera.update();
		if(stage == null){
			stage.getViewport().update(width, height, true);
		}
		if(stage.getWidth()!=width || stage.getHeight()!= height){
			//stage.dispose();
			stage.getViewport().update(width, height, true);
		}
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		titleLabel.setX(0);
		titleLabel.setY(Gdx.graphics.getHeight()/2);
		titleLabel.setWidth(width);
		titleLabel.setAlignment(Align.center);
		titleLabel.setFontScale(2.5f);
		stage.addActor(titleLabel);		
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("SSbutt");
		style.down = skin.getDrawable("SSbuttclick");
		style.over = skin.getDrawable("SSbuttclick");
		style.font = showerSaver.white;
		button_start = new TextButton("Start", style);
		button_start.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_start.setHeight(Gdx.graphics.getHeight()/8);
		button_start.setX(Gdx.graphics.getWidth()/2 + button_start.getWidth());
		button_start.setY(Gdx.graphics.getHeight()/3 - 2*button_start.getHeight());
		button_start.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				checked = true;
				//Gdx.app.log(showerSaver.LOG, "Button Checked!");
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){
					//Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
					appObject.setScreen(new timerSetup(appObject));
				}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_start.isPressed()){
					checked = false;
					//Gdx.app.log(showerSaver.LOG, "Button [almost] Checked!");
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
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				checked = true;
				//Gdx.app.log(showerSaver.LOG, "Button Checked!");
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked)
				//Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
				appObject.setScreen(new StatsPage(appObject));
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_start.isPressed()){
					checked = false;
					//Gdx.app.log(showerSaver.LOG, "Button [almost] Checked!");
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
		button_history.remove();
		img.dispose();
		titleLabel.remove();
	}
}
