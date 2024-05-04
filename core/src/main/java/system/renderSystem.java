package system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import components.ImageComponents;
import mainHandler.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class renderSystem extends IteratingSystem {
// dili ko confident sa akong conversion pero go lang ng gow!
    // btw ni gana ni
    private final Stage stage;
    private final ComponentMapper<ImageComponents> imageCmps;

    public renderSystem(Stage stage, ComponentMapper<ImageComponents> imageCmps) {
        super(Family.all(ImageComponents.class).get());
        this.stage = stage;
        this.imageCmps = imageCmps;
    }

    // instead of onTickEntity we use processEntity ma nigga
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        imageCmps.get(entity).image.toFront();
    }

    // instead sa OnTick from fleks we use update kay we dont need it char lmao ma nigga
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }
}
