package monopoly.main;

import engine.IGameLogic;
import engine.Window;
import monopoly.rendering.Renderer;

import static org.lwjgl.glfw.GLFW.*;

public class MonopolyLogic implements IGameLogic {

    private Renderer renderer;
    private Window window;

    @Override
    public void init(Window window) throws Exception {
        this.window = window;
        renderer = new Renderer(window);
        renderer.init();
    }

    @Override
    public void inputKeyboard(int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(window.getId(), true);
        }
    }

    @Override
    public void inputMouseButton(int button, int action, int mods) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(double alpha) {
        renderer.render(alpha);
        glfwSwapBuffers(window.getId());
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
