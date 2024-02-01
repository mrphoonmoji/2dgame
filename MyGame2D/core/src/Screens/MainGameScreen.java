package Screens;

import Entities.Background;
import WorldHandler.WorldHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Game2D;

public class MainGameScreen implements Screen {
    Game2D game;
    WorldHandler wh;
    public MainGameScreen (Game2D game){
        this.game = game;
    }
    @Override
    public void show() {
        wh = new WorldHandler(game, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        wh.camera.update();
        game.batch.getProjectionMatrix().set(wh.camera());
        game.batch.begin();
        wh.draw();
        game.batch.end();
        wh.worldStep();
     }

    @Override
    public void resize(int width, int height) {

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
        wh.dispose();
        game.batch.dispose();
    }
}
