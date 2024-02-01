package Entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Game2D;

import static util.Constants.*;

public class Background {
    Game2D game;
    Texture [] img;
    Player player;

    public Background (Game2D game, Player player){
        this.game = game;
        this.player = player;
        create();
    }
    public void create(){
        img = new Texture[4];
        img [0] = new Texture("ParallaxBG/parallax_bg.png");
        img [1] = new Texture("ParallaxBG/parallax_close_trees.png");
        img [2] = new Texture("ParallaxBG/parallax_mid_trees.png");
        img [3] = new Texture("ParallaxBG/parallax_far_trees.png");
    }
    public void draw(){

            game.batch.draw(img[0], (0*scale(WIDTH)+dist(1f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[0], (1*scale(WIDTH)+dist(1f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[0], (2*scale(WIDTH)+dist(1f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[3], (0*scale(WIDTH)+dist(1.5f)),0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[3], (1*scale(WIDTH)+dist(1.5f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[3], (2*scale(WIDTH)+dist(1.5f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[2], (0*scale(WIDTH)+dist(1.8f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[2], (1*scale(WIDTH)+dist(1.8f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[2], (2*scale(WIDTH)+dist(1.8f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[1], (0*scale(WIDTH)+dist(2f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[1], (1*scale(WIDTH)+dist(2f)) ,0,scale(WIDTH), scale(HEIGHT));
            game.batch.draw(img[1], (2*scale(WIDTH)+dist(2f)) ,0,scale(WIDTH), scale(HEIGHT));

    }
    public float scale(float valueToBeScaled) {
        return valueToBeScaled/SCALE;
    }
    public float dist (float parallaxEffect){
        return (player.getPosX()/8)* parallaxEffect;
    }
}
