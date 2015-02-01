package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class timerSetup implements Screen{
	
	private showerSaver appObject;
	private LabelStyle displayTimeStyle;
	private Label displayTime;
	private Stage stage;
	private SpriteBatch batch;
	private TextButton button_back, button_next, button_inc_minute,
					   button_inc_second, button_dec_minute, button_dec_second;
	private TextButtonStyle style = new TextButtonStyle();
	private Skin skin;
	private TextureAtlas atlas;
	private Texture treeOne, treeTwo, treeThree;
	
	public timerSetup(showerSaver appObject) {
		// TODO Auto-generated constructor stub
		//timeSetupInputListener listener = new timeSetupInputListener();
		//Gdx.input.getTextInput(listener, "Timer", null, "shower Time");
		this.appObject = appObject;
		displayTimeStyle = new LabelStyle(showerSaver.white,Color.BLACK);
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		displayTime = new Label(String.format("%d:%02d",showerSaver.minute, showerSaver.seconds), displayTimeStyle);
		atlas = showerSaver.loadManager.get("buttons.pack");
		
		skin = new Skin(atlas);
		style = new TextButtonStyle();
		style.up = skin.getDrawable("SSbutt");
		style.down = skin.getDrawable("SSbuttclick");
		style.over = skin.getDrawable("SSbuttclick");
		style.font = showerSaver.white;
		
		button_back = new TextButton("<--", style);
		button_next = new TextButton("-->", style);
		
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
		displayTime.setText(String.format("%d:%02d",showerSaver.minute,showerSaver.seconds));
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
		batch.begin();
		batch.draw(treeOne, Gdx.graphics.getWidth()/6 - treeOne.getWidth()/2,Gdx.graphics.getHeight()/8,2*treeOne.getWidth(),2*treeOne.getHeight());
		batch.draw(treeTwo,Gdx.graphics.getWidth()/2.2f - treeTwo.getWidth()/2,Gdx.graphics.getHeight()/8,2*treeTwo.getWidth(),2*treeTwo.getHeight());
		batch.draw(treeThree,Gdx.graphics.getWidth()*.75f - treeThree.getWidth()/2,Gdx.graphics.getHeight()/8,2*treeThree.getWidth(),2*treeThree.getHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
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
		displayTime.setY(Gdx.graphics.getHeight()*0.7f);
		displayTime.setWidth(width);
		displayTime.setAlignment(Align.center);
		displayTime.setFontScale(1.3f);
		
		button_back.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_back.setHeight(Gdx.graphics.getHeight()/8f);
		button_back.setX(Gdx.graphics.getWidth()/2 - 2*button_back.getWidth());
		button_back.setY(Gdx.graphics.getHeight()*0.8f);

		button_next.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_next.setHeight(Gdx.graphics.getHeight()/8f);
		button_next.setX(Gdx.graphics.getWidth()/2 + button_back.getWidth());
		button_next.setY(Gdx.graphics.getHeight()*0.8f);
		
		stage.addActor(displayTime);
		stage.addActor(button_back);
		stage.addActor(button_next);
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
