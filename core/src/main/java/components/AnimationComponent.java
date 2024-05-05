package components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimationComponent implements Component {

    private String atlasKey;
    public static final String NO_ANIMATION = "NO_ANIMATION";
    public float stateTime;
    public Animation.PlayMode playMode;
    public Animation<TextureRegionDrawable> animation;
    public String nextAnimation;

    public AnimationComponent() {
        atlasKey = "";
        stateTime = 0f;
        playMode = Animation.PlayMode.LOOP;
        nextAnimation = "";
    }

    //part 5 ni tanan!
    public void nextAnimation(String atlasKey, AnimationType type) {
        this.atlasKey = atlasKey;
        nextAnimation = String.format("%s/%s", atlasKey, type.getAtlasKey());
    }



}

