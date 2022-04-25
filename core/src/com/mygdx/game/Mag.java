package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Mag {
    private final float size = 64;
    private final float halfSize = size / 2;

    private final  Vector2 position = new Vector2();
    private final Vector2 angle = new Vector2();

    private final Texture texture;
    private final TextureRegion textureRegion;

    public Mag(float x , float y) {
        texture = new Texture("mag.png");
        textureRegion = new TextureRegion(texture);
        position.set(x , y);
    }
    public void render(Batch batch){
        batch.draw( textureRegion,
                position.x,
                position.y,
                halfSize,
                halfSize,
                size,
                size,
                1,
                1,
                angle.angleDeg() - 90
        );

    }
    public void dispose(){
        texture.dispose();
    }

    public void moveTo(Vector2 direction) {
        position.add(direction);
    }

    public void rotateTo(Vector2 mousePos) {
        angle.set(mousePos).sub(position.x + halfSize, position.y + halfSize);
    }


}
