package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class songPage extends history implements Screen{
	private Stage stage;
	private showerSaver obj;
	private Music music, alerts;
	private LabelStyle displayTimeStyle;
	private Label displayTime;
	private BitmapFont white;
	private SpriteBatch batch;
	private TextButton button_start;
	private TextureAtlas atlas;
	private Skin skin;
	private boolean checked;
	private int musicNum;
	
	float totalTime, timer;
	public songPage(showerSaver obj){
		this.obj = obj;
		//img = new Texture("SS2.bmp");
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("button.pack"));
		skin = new Skin(atlas);
		//white = new BitmapFont(Gdx.files.internal("gamefont.fnt"));
		white = showerSaver.loadManager.get("gamefont.fnt", BitmapFont.class);
		displayTimeStyle = new LabelStyle(white,Color.WHITE);
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		displayTime = new Label("l", displayTimeStyle);
	}

	@Override
	public void show() {
		totalTime = showerSaver.timer;
		timer = showerSaver.timer;
		musicNum = 0;
        music = Gdx.audio.newMusic(Gdx.files.internal("data/"+ musicNum + ".mp3"));
        music.play();
        alerts = Gdx.audio.newMusic(Gdx.files.internal("data/alarm.mp3"));
        alerts.setLooping(true);
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("button.up");
		style.down = skin.getDrawable("button.down");
		style.over = skin.getDrawable("button.down");
		style.font = white;
        
        button_start = new TextButton("Done Early?", style);
		button_start.setWidth(Gdx.graphics.getWidth()/2.5f);
		button_start.setHeight(Gdx.graphics.getHeight()/8);
		button_start.setX(Gdx.graphics.getWidth()/2 + button_start.getWidth()/4);
		button_start.setY(Gdx.graphics.getHeight() - 1.5f * button_start.getHeight());
		
		displayTime.setX(Gdx.graphics.getWidth()/6	);
		displayTime.setY(Gdx.graphics.getHeight()/2);
		//displayTime.setWidth(width);
		displayTime.setAlignment(Align.center);
		displayTime.setFontScale(1.3f);
		stage.addActor(displayTime);

        stage.addActor(button_start);
		Gdx.input.setInputProcessor(stage);
		
		music.setOnCompletionListener(new Music.OnCompletionListener() {
			
			@Override
			public void onCompletion(Music music) {
				musicNum += 1;
				musicNum %= 4;
				if (!checked){
					music = Gdx.audio.newMusic(Gdx.files.internal("data/"+ musicNum + ".mp3"));
					music.play();	
				}
			}
		});

		button_start.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				checked = true;
				Gdx.app.log(showerSaver.LOG, "Button Checked!");
				saveHistory(Math.round(timer), Math.round(timer - totalTime));
				
				//Old Debugging Code:
				//saveHistory(60, Math.round(timer));
				//String[] res = getHistory();
				//button_start.setText(res[res.length - 1]);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(checked)
					//Gdx.app.log(showerSaver.LOG, "Button Not Checked!");
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
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0,  0,  0,  1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
      //FileHandle[] fh = Gdx.files.internal("data/").list();
        
        if (checked){
        	alerts.setLooping(false);
        	alerts.dispose();
        	music.dispose();
        	
        	this.obj.setScreen(new StatsPage(obj));
        	//redirect stats
        }
        
        if (totalTime <= 0){
            music.stop();
            this.obj.setScreen(new splashScreen(obj));
            //redirect fail
        }
        
        if (totalTime >= 55 && totalTime <= 65){
        	if (!alerts.isPlaying()){
        		music.pause();
        		alerts.play();
        	}
        }
        else if(totalTime < 55){
        	if (alerts.isPlaying()){
        		alerts.setLooping(false);
        		alerts.pause();
        		music.play();
        	}
        }


        float deltaTime = Gdx.graphics.getDeltaTime(); 
        totalTime -= deltaTime;
        int minutes = ((int)totalTime) / 60;
        int seconds = ((int)totalTime) % 60;
        displayTime.setText(String.format("%d:%02d",minutes, seconds));
        stage.act();
        batch.begin();
        stage.draw();
        batch.end();
		
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
