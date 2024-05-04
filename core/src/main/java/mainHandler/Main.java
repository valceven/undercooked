package mainHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {


    GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        gameScreen.show();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
    }
}
