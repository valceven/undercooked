package system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import components.AnimationComponent;
import components.ImageComponents;

import java.util.HashMap;

public class AnimationSystem extends IteratingSystem {
    private final TextureAtlas textureAtlas;
    private final ComponentMapper<AnimationComponent> animationCmps;
    private final ComponentMapper<ImageComponents> imageCmps;
    private static final float DEFAULT_FRAME_DURATION = 1 / 8f;
    private final HashMap<String, Animation<TextureRegionDrawable>> cachedAnimations = new HashMap<>();

    public AnimationSystem(TextureAtlas textureAtlas) {
        super(Family.all(AnimationComponent.class, ImageComponents.class).get());
        this.textureAtlas = textureAtlas;
        this.animationCmps = ComponentMapper.getFor(AnimationComponent.class);
        this.imageCmps = ComponentMapper.getFor(ImageComponents.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = animationCmps.get(entity);
        ImageComponents imageComponent = imageCmps.get(entity);

        Animation<TextureRegionDrawable> animation = animationComponent.animation;
        if (animation != null) {
            if (animationComponent.nextAnimation.equals(AnimationComponent.NO_ANIMATION)) {
                animationComponent.stateTime += deltaTime;
            } else {
                animationComponent.animation = animation(animationComponent.nextAnimation);
                animationComponent.stateTime = 0f;
            }

            animation.setPlayMode(animationComponent.playMode);
            animation.getKeyFrame(animationComponent.stateTime).getRegion();
        }
    }

    private Animation<TextureRegionDrawable> animation(String aniKeyPath) {
        return cachedAnimations.computeIfAbsent(aniKeyPath, key -> {
            Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions(aniKeyPath);
            if (regions.isEmpty()) {
                Gdx.app.error("AnimationSystem", "There are no texture regions for " + aniKeyPath);
                return null; // Return null or handle the error according to your application's logic
            }

            Array<TextureRegionDrawable> drawables = new Array<>();
            for (TextureAtlas.AtlasRegion region : regions) {
                drawables.add(new TextureRegionDrawable(region));
            }

            return new Animation<>(DEFAULT_FRAME_DURATION, drawables);
        });
    }

}
