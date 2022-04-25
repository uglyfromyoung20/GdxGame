package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Mag  me;
	private KeyboardAdapter inputProcessor = new KeyboardAdapter();

	@Override
	public void create () {
		Gdx.input.setInputProcessor(inputProcessor);
		batch = new SpriteBatch();
 me = new Mag(100,200);
	}

	@Override
	public void render () {
		me.moveTo(inputProcessor.getDirection());
		me.rotateTo(inputProcessor.getMousePos());
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		me.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
me.dispose();
	}
}
