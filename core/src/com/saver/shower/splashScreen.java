package com.saver.shower;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.saver.shower.mainMenu;

public class splashScreen implements Screen{
	
	private showerSaver appObject;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite splashSprite;
	private Texture img, splashTexture;
	private TweenManager manager;
	private boolean tweenStatus = false;
	
	public splashScreen(showerSaver appObject) {
		this.appObject = appObject;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		img = new Texture("badlogic.jpg");
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		splashTexture = new Texture("SS10.png");
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

		if(showerSaver.loadManager.update() && tweenStatus)
			appObject.setScreen(new mainMenu(appObject));
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, 600, 795);
		//camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        //camera.update();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		splashTexture.dispose();
		batch.dispose();
	}
}
