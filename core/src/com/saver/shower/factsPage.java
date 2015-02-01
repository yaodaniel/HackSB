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

public class factsPage implements Screen{
	private showerSaver object;
	private LabelStyle factsStyle;
	private Label factsDisplay;
	private Label titleDisplay;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextButton button_back;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private boolean checked;


	public factsPage(showerSaver obj){
		this.object = obj;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		//Button Stuff
		atlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		
		factsStyle = new LabelStyle(showerSaver.white,Color.BLACK);
		factsDisplay = new Label(String.format("'Taking a 5-minute shower instead of a 10-minute shower saves 12.5 gallons with a low-flow shower head, and 25 gallons with a standard 5 gallon-per-minute shower head..'\n --mercurynews.com\n\n"+
		"'Put a bucket in the shower while you're waiting for the water to warm up, and use the water you catch for watering plants, flushing the toilet or cleaning.'\n--gracelinks.org\n\n"+
		"'Think of baths as an occasional treat and stick to showers. The average bath uses 35 to 50 gallons of water, whereas a 10-minute shower with a low-flow showerhead only uses 25 gallons.'\n--gracelinks.org\n"),factsStyle);
		factsDisplay.setWidth(Gdx.graphics.getWidth());
		factsDisplay.setWrap(true);
		titleDisplay = new Label("Facts", factsStyle);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		
		factsDisplay.setX(20);
		factsDisplay.setY(Gdx.graphics.getHeight()- 2*(factsDisplay.getHeight()+25));
		factsDisplay.setWidth(Gdx.graphics.getWidth()-40);
		factsDisplay.setAlignment(Align.center);
		factsDisplay.setFontScale(1.0f);

		titleDisplay.setX(Gdx.graphics.getWidth()/2 - 3.5f*titleDisplay.getWidth());
		titleDisplay.setY(Gdx.graphics.getHeight()/2 + 7*(titleDisplay.getHeight()+25));
		titleDisplay.setWidth(Gdx.graphics.getWidth()-40);
		titleDisplay.setAlignment(Align.center);
		titleDisplay.setFontScale(1.5f);

		stage.addActor(factsDisplay);
		stage.addActor(titleDisplay);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("SSbutt");
		style.down = skin.getDrawable("SSbuttclick");
		style.over = skin.getDrawable("SSbuttclick");
		style.font = showerSaver.white;
		style.font.setScale(1.0f);
		
		button_back = new TextButton("<", style);
		button_back.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_back.setHeight(Gdx.graphics.getHeight()/12);
		button_back.setX(Gdx.graphics.getWidth()/2 - 2f*button_back.getWidth());
		button_back.setY(Gdx.graphics.getHeight()/2 + 3*(button_back.getHeight()+60));
		button_back.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				checked = true;
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){
					object.setScreen(new StatsPage(object));
				}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_back.isPressed()){
					checked = false;
				}else{
					checked = true;
				}
			}
		});

		stage.addActor(button_back);
		
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
