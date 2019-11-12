package monopoly.main;

import engine.IGameLogic;
import engine.Window;
import monopoly.rendering.Renderer;

import static org.lwjgl.glfw.GLFW.*;

public class MonopolyLogic implements IGameLogic {

    private Renderer renderer;

    @Override
    public void init(Window window) throws Exception {
        renderer = new Renderer(window);
        renderer.init();
    }

    @Override
    public void input(Window window, int key, int scancode, int action, int mods) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Window window) {
        renderer.render();
        glfwSwapBuffers(window.getId());
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
