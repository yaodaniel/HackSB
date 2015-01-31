package com.saver.shower;

import com.badlogic.gdx.Input.TextInputListener;

public class timeSetupInputListener implements TextInputListener{

	@Override
	public void input(String text) {
		// TODO Auto-generated method stub
		showerSaver.timer = Integer.parseInt(text);
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		showerSaver.timer = 0;
	}
}
