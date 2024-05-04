package mainHandler;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.github.quillraven.fleks.World;


public class GameScreen implements Screen {
    private final SpriteBatch batch = new SpriteBatch();
    private final Stage stage = new Stage(new ExtendViewport(16f, 9f));
    private final Texture image =
        new Texture("assets/Modern_Interiors_RPG_Maker_Version/RPG_MAKER_MV/" +
            "Characters/single characters (old)/Character_1_48x48.png");

    private World world = new World(64);

    @Override
    public void show() {
        Image imageActor = new Image(image);
        stage.addActor(imageActor);
        imageActor.setPosition(1f, 1f);
        imageActor.setSize(2f, 2f);
        imageActor.setScaling(Scaling.fill);
    }

    @Override
    public void render(float v) {
        batch.begin();
        batch.end();
        world.update(v);
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
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
        world.dispose();
        stage.dispose();
    }
}

