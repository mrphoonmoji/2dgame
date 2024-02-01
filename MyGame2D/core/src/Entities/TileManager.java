package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Game2D;

import static util.Constants.SCALE;

public class TileManager {
    Vector2 pos = new Vector2(scale(768f),scale(96f));
    Game2D game;
    Texture[] img;
    World world;
    Body body;
    float tileSize;

    public TileManager (World world, Game2D game){
        this.game = game;
        this.world = world;
        this.body = createTiles(world);
        this.tileSize = scale(game.tileSize);
        create();
    }
    public Body createTiles(World world){
        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(pos);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape p = new PolygonShape();
        p.setAsBox(768,scale(1f));
        pBody.createFixture(p, 1.0f);
        return pBody;
    }
    public void create(){
        img = new Texture[3];
        img[0] = new Texture("Tiles/tile054.png");
        img[1] = new Texture("Tiles/tile089.png");
        img[2] = new Texture("Tiles/tile115.png");
    }
    public void draw (){
        for(int i = 0; i <= 160; i++) {
            game.batch.draw(img[1], tileSize * i, 0, tileSize, tileSize);
        }
    }
    public float scale(float valueToBeScaled) {
        return valueToBeScaled/SCALE;
    }
}
