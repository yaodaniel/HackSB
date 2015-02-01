package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class timerSetup implements Screen{
	
	private OrthographicCamera camera;
	private showerSaver appObject;
	private LabelStyle displayTimeStyle;
	private Label displayTime;
	private Stage stage;
	private SpriteBatch batch;
	private TextButton button_back, button_next, button_increaseTime, button_decreaseTime;
	private TextButtonStyle style, timerStyleIncrease, timerStyleDecrease;
	private Skin skin, timerSkin;
	private TextureAtlas atlas, timerAtlas;
	private Texture treeOne, treeTwo, treeThree;
	private boolean checked = false;
	
	public timerSetup(showerSaver appObject) {
		// TODO Auto-generated constructor stub
		//timeSetupInputListener listener = new timeSetupInputListener();
		//Gdx.input.getTextInput(listener, "Timer", null, "shower Time");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.appObject = appObject;
		displayTimeStyle = new LabelStyle(showerSaver.white,Color.BLACK);
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		displayTime = new Label(String.format("%d:%02d",showerSaver.minute, showerSaver.seconds), displayTimeStyle);
		atlas = showerSaver.loadManager.get("buttons.pack");
		timerAtlas = showerSaver.loadManager.get("swag.pack");
		
		skin = new Skin(atlas);
		style = new TextButtonStyle();
		style.up = skin.getDrawable("SSbutt");
		style.down = skin.getDrawable("SSbuttclick");
		style.over = skin.getDrawable("SSbuttclick");
		style.font = showerSaver.white;
		
		timerSkin = new Skin(timerAtlas);
		timerStyleIncrease = new TextButtonStyle();
		timerStyleIncrease.up = timerSkin.getDrawable("newbuttup");
		timerStyleIncrease.down = timerSkin.getDrawable("newbuttupclick");
		timerStyleIncrease.over = timerSkin.getDrawable("newbuttupclick");
		timerStyleIncrease.font = showerSaver.white;
		
		timerStyleDecrease = new TextButtonStyle();
		timerStyleDecrease.up = timerSkin.getDrawable("newbuttdown");
		timerStyleDecrease.down = timerSkin.getDrawable("newbuttdownclick");
		timerStyleDecrease.over = timerSkin.getDrawable("newbuttdownclick");
		timerStyleDecrease.font = showerSaver.white;
		
		button_back = new TextButton("<--", style);
		button_next = new TextButton("-->", style);
		button_increaseTime = new TextButton("",timerStyleIncrease);
		button_decreaseTime = new TextButton("",timerStyleDecrease);
		
		treeOne = showerSaver.loadManager.get("SS3.bmp");
		treeTwo = showerSaver.loadManager.get("SS4.bmp");
		treeThree = showerSaver.loadManager.get("SS5.bmp");
		stage = new Stage(new ScreenViewport());
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
		batch.begin();
		if(showerSaver.timer < 480)
			batch.draw(treeOne, Gdx.graphics.getWidth()/2 - treeOne.getWidth(),Gdx.graphics.getHeight()/8,2*treeOne.getWidth(),2*treeOne.getHeight());
		else if(showerSaver.timer >= 480 && showerSaver.timer < 900)
			batch.draw(treeTwo,Gdx.graphics.getWidth()/2 - treeTwo.getWidth(),Gdx.graphics.getHeight()/8,2*treeTwo.getWidth(),2*treeTwo.getHeight());
		else if(showerSaver.timer >= 900)
			batch.draw(treeThree,Gdx.graphics.getWidth()/2 - treeThree.getWidth(),Gdx.graphics.getHeight()/8,2*treeThree.getWidth(),2*treeThree.getHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        camera.update();
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

		displayTime.setX(0);
		displayTime.setY(Gdx.graphics.getHeight()*0.6f);
		displayTime.setWidth(width);
		displayTime.setAlignment(Align.center);
		displayTime.setFontScale(1.3f);
		
		button_back.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_back.setHeight(Gdx.graphics.getHeight()/8f);
		button_back.setX(Gdx.graphics.getWidth()/2 - 2*button_back.getWidth());
		button_back.setY(Gdx.graphics.getHeight()*0.8f);
		button_back.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked)
				appObject.setScreen(new mainMenu(appObject));
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_back.isPressed())
				checked = false;
				else
					checked = true;
			}
		});

		button_next.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_next.setHeight(Gdx.graphics.getHeight()/8f);
		button_next.setX(Gdx.graphics.getWidth()/2 + button_next.getWidth());
		button_next.setY(Gdx.graphics.getHeight()*0.8f);
		button_next.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked)
				appObject.setScreen(new playlistScreen(appObject));
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_next.isPressed())
				checked = false;
				else
					checked = true;
			}
		});
		
		button_increaseTime.setWidth(Gdx.graphics.getWidth()/15f);
		button_increaseTime.setHeight(Gdx.graphics.getHeight()/15f);
		button_increaseTime.setX(Gdx.graphics.getWidth()/2 - button_increaseTime.getWidth()/2);
		button_increaseTime.setY(Gdx.graphics.getHeight()*0.70f);
		button_increaseTime.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked){
					showerSaver.updateTime(showerSaver.timer + 30);
					displayTime.setText(String.format("%d:%02d",showerSaver.minute,showerSaver.seconds));
				}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_next.isPressed())
				checked = false;
				else
					checked = true;
			}
		});
		
		button_decreaseTime.setWidth(Gdx.graphics.getWidth()/15f);
		button_decreaseTime.setHeight(Gdx.graphics.getHeight()/15f);
		button_decreaseTime.setX(Gdx.graphics.getWidth()/2 - button_decreaseTime.getWidth()/2);
		button_decreaseTime.setY(Gdx.graphics.getHeight()*0.50f);
		button_decreaseTime.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y,
										int pointer, int button) {
				checked = true;
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
									int pointer, int button) {
				if(checked){
					showerSaver.updateTime(showerSaver.timer - 30);
					displayTime.setText(String.format("%d:%02d",showerSaver.minute,showerSaver.seconds));
				}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_next.isPressed())
				checked = false;
				else
					checked = true;
			}
		});
		
		
		stage.addActor(displayTime);
		stage.addActor(button_back);
		stage.addActor(button_next);
		stage.addActor(button_increaseTime);
		stage.addActor(button_decreaseTime);
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
		stage.clear();
		stage.dispose();
		batch.dispose();
		skin.dispose();
		button_back.remove();
		button_next.remove();
		
	}
}
