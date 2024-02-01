package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Game2D;

import static util.Constants.SCALE;

public class Player {
    Vector2 pos = new Vector2(15,32);
    World world;
    Game2D game;
    Texture img;
    OrthographicCamera camera;
    TextureRegion[] idleSprites, jumpSprites, runSprites;
    Animation idleAnimation, jumpAnimation, runAnimation;
    Body body;


    float width = scale(96f);
    float height = scale(96f);
    float stateTime, y ,dy;
    boolean idle, jumping, moving, falling = true;
    int action;


    public Player(World world, Game2D game, OrthographicCamera camera){
        this.world = world;
        this.game = game;
        this.camera = camera;
        this.body = createPlayer(world);
        create();
    }
    public Body createPlayer (World world) {
        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        def.linearDamping = 0.5f;
        def.fixedRotation = true;
        pBody = world.createBody(def);
        PolygonShape p = new PolygonShape();
        p.setAsBox(width/2,height/2);
        pBody.createFixture(p, 1.0f);
        return pBody;
    }
    public void create(){
        jumpSprites = new TextureRegion[8];
        idleSprites = new TextureRegion[6];
        runSprites =  new TextureRegion[6];
        img = new Texture("PlayerIdle/WarriorSpriteSheet.png");
        TextureRegion [][] tmpFrames = TextureRegion.split(img, img.getWidth()/6, img.getHeight()/17);
        int index = 0;
        for(int i = 0; i < 6; i++){
            idleSprites[index++] = tmpFrames[0][i];
        }
        index = 1;
            jumpSprites[0] = tmpFrames[6][5];
            for(int i = 0; i < 6; i++){
                jumpSprites[index++]= tmpFrames[7][i];
            }
            jumpSprites[7] = tmpFrames[8][0];
        index = 0;
        for(int i = 0; i < 6; i++){
            runSprites[index++] = tmpFrames[1][i];
        }
        idleAnimation = new Animation<TextureRegion>(0.15f, idleSprites);
        jumpAnimation = new Animation<TextureRegion>(0.1f, jumpSprites);
        runAnimation = new Animation<TextureRegion>(0.1f, runSprites);
        stateTime = 0f;
    }
    public void draw(){
        stateTime += Gdx.graphics.getDeltaTime();
        switch(action) {
            case 0:
                TextureRegion currentFrame = (TextureRegion) idleAnimation.getKeyFrame(stateTime, true);
                game.batch.draw(currentFrame, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
                break;
            case 1:
                TextureRegion jumpingFrame = (TextureRegion) jumpAnimation.getKeyFrame(stateTime, true);
                game.batch.draw(jumpingFrame, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
                break;
            case 2:
                TextureRegion runningFrame = (TextureRegion) runAnimation.getKeyFrame(stateTime, true);
                game.batch.draw(runningFrame, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
                break;
        }
        dy = body.getPosition().y;
    }
    public void update(){
        y = body.getPosition().y;
        if(jumping){
            action = 1;
        } else if (moving) {
            action = 2;
        } else {
            action = 0;
        }
        if(y == dy){
            idle = true;
            falling = false;
            jumping = false;
            moving = false;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !jumping){
            body.applyForceToCenter(0,2500, false);
            jumping = true;
            idle = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            body.applyForceToCenter(50,0,false);
            moving = true;
            idle = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            body.applyForceToCenter(-50,0,false);
            moving = true;
            idle = false;
        }
    }
    public void dispose(){
        img.dispose();
    }
    public float scale(float valueToBeScaled) {
        return valueToBeScaled/SCALE;
    }
    public float getPosX(){
        return body.getPosition().x;
    }
    public float getPosY(){
        return body.getPosition().y;
    }
}

