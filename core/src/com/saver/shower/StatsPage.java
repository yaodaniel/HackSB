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

public class StatsPage extends history implements Screen{
	private showerSaver object;
	private LabelStyle displayStatsStyle;
	private Label displayStats;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private TextButton button_restart;
	private TextureAtlas atlas;
	private Skin skin;
	private Stage stage;
	private boolean checked;
	private int avgMin, avgSec, longMin, longSec, shortMin, shortSec;
	private int resLength, minVal, maxVal, sum, avgVal;

	
	public StatsPage(showerSaver obj){
		this.object = obj;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		img = new Texture("SS2.bmp");
		batch = new SpriteBatch();
		//Button Stuff
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin = new Skin(atlas);
		stage = new Stage(new ScreenViewport());
		showerSaver.white = showerSaver.loadManager.get("gamefont.fnt", BitmapFont.class);
		
		//Get History and Calculations
		String[] res = getHistory();
		resLength = res.length;
		
		if(resLength==0){
			minVal = 0;
			maxVal = 0;
		}else{
			minVal = Integer.parseInt(res[0].split(" ",-1)[2]);
			maxVal = minVal;			
		
		
			//Gdx.app.log(showerSaver.LOG, "resLength" + resLength);
			for(int i=0; i< resLength; i++){
				String[] timeShowered = res[i].split(" ",-1);
				//Gdx.app.log(showerSaver.LOG, "timeShowered" + timeShowered[2]);
				int t = Integer.parseInt(timeShowered[2]);//time in seconds
				if(minVal > t){
					//if t is minimum
					minVal = t;
				}
				if(maxVal < t){
					//if t is maximum
					maxVal = t;
				}
				sum += t;	
			}
			avgVal = sum/resLength;
		}
		//Convert From Seconds into Min and Secs
		avgMin = (int)(avgVal/60);
		avgSec = (int)(avgVal%60);
		longMin = (int)(maxVal/60);
		longSec = (int)(maxVal%60);
		shortMin = (int)(minVal/60);
		shortSec = (int)(minVal%60);

		//Create Label
		displayStatsStyle = new LabelStyle(showerSaver.white,Color.WHITE);
		displayStats = new Label(String.format("Average Shower Time: %d min %d sec\n Longest Shower: %d min %d sec\n Shortest Shower: %d min %d sec\n", avgMin, avgSec, longMin, longSec, shortMin, shortSec), displayStatsStyle);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
		
		displayStats.setX(0);
		displayStats.setY(Gdx.graphics.getHeight()/2);
		displayStats.setWidth(width);
		displayStats.setAlignment(Align.center);
		displayStats.setFontScale(1.0f);
		stage.addActor(displayStats);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.over = skin.getDrawable("button.down");
		style.font = showerSaver.white;
		button_restart = new TextButton("-->", style);
		button_restart.setWidth(Gdx.graphics.getWidth()/4.5f);
		button_restart.setHeight(Gdx.graphics.getHeight()/8);
		button_restart.setX(Gdx.graphics.getWidth()/2 + button_restart.getWidth());
		button_restart.setY(Gdx.graphics.getHeight()/3 - 2*button_restart.getHeight());
		button_restart.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				checked = true;
				//Gdx.app.log(showerSaver.LOG, "Button Checked!");
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked){
					//Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
					object.setScreen(new mainMenu(object));
				}
				checked = false;
			}
			public void touchDragged(InputEvent event, float x, float y, int pointer)
			{
				if(!button_restart.isPressed()){
					checked = false;
					//Gdx.app.log(showerSaver.LOG, "Button [almost] Checked!");
				}else{
					checked = true;
				}
			}
		});
		stage.addActor(button_restart);
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
