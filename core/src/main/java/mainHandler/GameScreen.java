package mainHandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import components.ImageComponents;
import com.badlogic.ashley.core.Family;
import system.renderSystem;

public class GameScreen implements Screen {
    private final Family imageComponentFamily = Family.all(ImageComponents.class).get();
    private final SpriteBatch batch = new SpriteBatch();
    private final Stage stage = new Stage(new ExtendViewport(16f, 9f));
    private final Texture PlayerTexture = new Texture("assets/entity_sprites/valChar.png");
    private final Texture PlayerTexture2 = new Texture("assets/Modern_Interiors_RPG_Maker_Version/RPG_MAKER_MV/Characters/single characters (old)/Character_1_48x48.png");

    private final Engine engine = new Engine();
    private final ComponentMapper<ImageComponents> imageComponentMapper = ComponentMapper.getFor(ImageComponents.class);
    private final renderSystem renderSystem = new renderSystem(stage, imageComponentMapper);

    public GameScreen() {
        engine.addSystem(renderSystem);
    }
    // we use Engine instance instead sa World nga gikan sa yt part 03 41:10
    // since Engine is the main proccessing unit in Ashley ECS, waman ta ga
    // gamit ug fleks kay only for kotlin rana! ma nigga

    @Override
    public void show() {
        addEntity(PlayerTexture, 32, 64, 3f, 3f, 5f, 5f);
        addEntity(PlayerTexture2, 50, 100, 6f, 6f, 2f, 2f, 90f);

        // finally ni gana na!!!! AHHAAHHAHAHAHAAHAH
        //for easy adding of entity!
    }

    private void addEntity(Texture texture, int regionX, int regionY, float posX, float posY, float sizeX, float sizeY) {
        ImageComponents imageComponent = new ImageComponents(new Image(new TextureRegion(texture, regionX, regionY)));
        stage.addActor(imageComponent.image);
        imageComponent.image.setPosition(posX, posY);
        imageComponent.image.setSize(sizeX, sizeY);
        imageComponent.image.setScaling(Scaling.fill);
        Entity entity = new Entity();
        entity.add(imageComponent);
        engine.addEntity(entity);
    }

    private void addEntity(Texture texture, int regionX, int regionY, float posX, float posY, float sizeX, float sizeY, float rotation) {
        addEntity(texture, regionX, regionY, posX, posY, sizeX, sizeY);
        ImageComponents imageComponent = imageComponentMapper.get(engine.getEntitiesFor(imageComponentFamily).get(engine.getEntitiesFor(imageComponentFamily).size() - 1));
        imageComponent.image.rotateBy(rotation);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.end();
        stage.act(delta);
        stage.draw();
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        batch.dispose();
        stage.dispose();
        PlayerTexture.dispose();
        PlayerTexture2.dispose();
    }
}
