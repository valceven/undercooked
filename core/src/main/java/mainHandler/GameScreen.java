package mainHandler;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import components.AnimationComponent;
import components.AnimationType;
import components.ImageComponents;
import com.badlogic.ashley.core.Family;
import system.AnimationSystem;
import system.renderSystem;

public class GameScreen implements Screen {
    private final Family imageComponentFamily = Family.all(ImageComponents.class).get();
    private final SpriteBatch batch = new SpriteBatch();
    private final Stage stage = new Stage(new ExtendViewport(16f, 9f));

    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("assets/atlas_sprites/gameObject.atlas"));

// since ge atlas man no need na mag texture texture lmao
//    private final Texture PlayerTexture = new Texture("assets_raw/entity_sprites/valChar.png");
//    private final Texture PlayerTexture2 = new Texture("assets_raw/entity_sprites/Chef1.png");

    private final Engine engine;
    private final ComponentMapper<ImageComponents> imageComponentMapper = ComponentMapper.getFor(ImageComponents.class);
    private renderSystem renderSystem;

    public GameScreen() {
        engine = new Engine();
        configureEngine();
    }
    // we use Engine instance instead sa World nga gikan sa yt part 03 41:10
    // since Engine is the main proccessing unit in Ashley ECS, waman ta ga
    // gamit ug fleks kay only for kotlin rana! ma nigga

    private void configureEngine() {
        // in kotlin they used system<AnimationSystem>
        engine.addSystem(new AnimationSystem(atlas));
        renderSystem = new renderSystem(stage, imageComponentMapper);
        engine.addSystem(renderSystem);
        //engine.addSystem(AnimationUtil.add());
    }

    @Override
    public void show() {
        addEntity(atlas.findRegion("Chef1"), 3f, 3f, 5f, 5f);
        addEntity(atlas.findRegion("Chef2"),6f, 6f, 5f, 5f);

        // finally ni gana na!!!! AHHAAHHAHAHAHAAHAH
        // for easy adding of entity!
    }

    private class AnimationUtil {
        public void add(AnimationComponent animationComponent) {
            animationComponent.nextAnimation("Chef1", AnimationType.RUN);
        }
    }

    private void addEntity(TextureAtlas.AtlasRegion atlasRegion, float posX, float posY, float sizeX, float sizeY) {
        if (atlasRegion != null) {
            ImageComponents imageComponent = new ImageComponents(new Image(atlasRegion));
            stage.addActor(imageComponent.image);
            imageComponent.image.setPosition(posX, posY);
            imageComponent.image.setSize(sizeX, sizeY);
            imageComponent.image.setScaling(Scaling.fill);
            Entity entity = new Entity();
            entity.add(imageComponent);
            engine.addEntity(entity);
        } else {
            Gdx.app.error("GameScreen", "Atlas region is null for Chef1 or valChar");
        }
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
        atlas.dispose();
    }
}
