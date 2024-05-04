package system;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.quillraven.fleks.*;
import mainHandler.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class renderSystem extends IteratingSystem {

    Stage stage = new Stage();

    public renderSystem(@NotNull Family family, @NotNull Comparator<Entity> comparator, @NotNull SortingType sortingType, @NotNull Interval interval, boolean enabled) {
        super(family, comparator, sortingType, interval, enabled);
    }


    @Override
    public void onTickEntity(@NotNull Entity entity) {
        stage.getViewport().update(16,9,true);
        stage.draw();
    }
}
