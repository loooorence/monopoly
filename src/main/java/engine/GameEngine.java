package engine;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public class GameEngine{

    private Window window;
    private IGameLogic gameLogic;

    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long windowID, int key, int scancode, int action, int mods) {
            gameLogic.input(window, key, scancode, action, mods);
        }
    };

    public GameEngine(String title, int width, int height, boolean vSync, IGameLogic gameLogic) {
        this.window = new Window(title, width, height, vSync);
        this.gameLogic = gameLogic;
    }

    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private void init() throws  Exception {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window.createWindow();

        glfwSetKeyCallback(window.getId(), keyCallback);

        glfwShowWindow(window.getId());

        gameLogic.init(window);
    }

    private void loop() {
        while (!glfwWindowShouldClose(window.getId())) {
            glfwPollEvents();
            gameLogic.update();
            gameLogic.render(window);
        }
    }

    public void cleanup() {
        gameLogic.cleanup();

        glfwFreeCallbacks(window.getId());
        glfwDestroyWindow(window.getId());

        glfwTerminate();
    }
}
