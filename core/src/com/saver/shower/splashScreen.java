package com.saver.shower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class splashScreen implements Screen{
	private showerSaver object;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	//private TweenManager manager;
	
	public splashScreen(showerSaver obj) {
		this.object = obj;
		camera = new OrthographicCamera();
		img = new Texture("badlogic.jpg");
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img,Gdx.graphics.getWidth()/2-img.getWidth()/2,Gdx.graphics.getHeight()/2-img.getHeight()/2);
		batch.end();
		
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
		
		
	}

	@Override
	public void dispose() {
		
		
	}
}
