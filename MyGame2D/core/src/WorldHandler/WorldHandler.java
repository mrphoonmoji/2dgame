package WorldHandler;

import Entities.Background;
import Entities.Player;
import Entities.TileManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Game2D;

import static util.Constants.*;

public class WorldHandler {
    Game2D game;
    Player player;
    TileManager tileM;
    Background bg;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    public final OrthographicCamera camera;

    public WorldHandler(Game2D game, float width, float height){
        this.game = game;
        camera = new OrthographicCamera();
        world = new World(new Vector2(0, -20), false);
        player = new Player(world, game, camera);
        tileM = new TileManager(world, game);
        bg = new Background(game, player);
        debugRenderer = new Box2DDebugRenderer();
        player.create();

    }
    public void draw(){
        cameraSettings();
        player.update();
        bg.draw();
        tileM.draw();
        player.draw();
    }
    public void dispose(){
        debugRenderer.dispose();
        world.dispose();
        player.dispose();
    }
    public void worldStep(){
        world.step(1/60f,6,2);
    }
    public void cameraSettings (){
        camera.setToOrtho(false, scale(WIDTH), scale(HEIGHT));
        camera.position.x = player.getPosX();
        camera.position.y = player.getPosY()+7;
    }
    public float scale(float valueToBeScaled) {
        return valueToBeScaled/SCALE;
    }
    public Matrix4 camera(){
        return camera.combined;
    }
}
