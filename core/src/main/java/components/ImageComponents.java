package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ImageComponents implements Comparable<ImageComponents>, Component {

    public Image image;
    private List<ImageComponentListener> listeners = new ArrayList<>();

    public ImageComponents(Image image) {
        this.image = image;
    }

    @Override
    public int compareTo(ImageComponents other) {
        int yDiff = (int) (image.getY() - other.image.getY());
        return yDiff != 0 ? yDiff : (int) (other.image.getX() - image.getX());
    }

    public void addListener(ImageComponentListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ImageComponentListener listener) {
        listeners.remove(listener);
    }

    public void notifyComponentAdded(Entity entity, ImageComponents component) {
        for (ImageComponentListener listener : listeners) {
            listener.onComponentAdded(entity, component);
        }
    }

    public void notifyComponentRemoved(Entity entity, ImageComponents component) {
        for (ImageComponentListener listener : listeners) {
            listener.onComponentRemoved(entity, component);
        }
    }

    public static class ImageComponentListener {
        private Stage stage;

        public ImageComponentListener(Stage stage) {
            this.stage = stage;
        }

        public void onComponentAdded(Entity entity, ImageComponents component) {
            stage.addActor(component.image);
        }

        public void onComponentRemoved(Entity entity, ImageComponents component) {
            stage.getRoot().removeActor(component.image);
        }
    }
}
