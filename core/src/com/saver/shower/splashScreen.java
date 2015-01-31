package com.saver.shower;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class splashScreen implements Screen{
	public AssetManager loadManager;
	public BitmapFont white;
	
	private showerSaver object;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite splashSprite;
	private Texture img, splashTexture;
	private TweenManager manager;
	private boolean tweenStatus = false;
	
	public splashScreen(showerSaver obj) {
		this.loadManager = obj.loadManager;
		this.object = obj;
		camera = new OrthographicCamera();
		img = new Texture("badlogic.jpg");
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		splashTexture = new Texture("splashscreen.jpeg");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1,1,1,0);
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		manager = new TweenManager();
		TweenCallback cb = new TweenCallback(){

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweencompleted();
			}
		};
		
		Tween.to(splashSprite, SpriteTween.ALPHA, 2f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1,2.5f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
		
	}
	
	private void tweencompleted(){
		tweenStatus = true;
		Gdx.app.log(showerSaver.LOG, "Tween Complete");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//batch.draw(img,Gdx.graphics.getWidth()/2-img.getWidth()/2,Gdx.graphics.getHeight()/2-img.getHeight()/2);
		splashSprite.draw(batch);
		batch.end();
		if(loadManager.update() && tweenStatus)
			//object.setScreen(new mainMenu(this));
			object.setScreen(new mainMenu(object));
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, 800, 600);
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void dispose() {
		splashTexture.dispose();
		batch.dispose();
	}
}
