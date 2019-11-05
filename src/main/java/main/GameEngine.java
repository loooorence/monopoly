package main;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public class GameEngine implements Runnable {

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

    @Override
    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window.getId());
        glfwDestroyWindow(window.getId());

        glfwTerminate();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window.createWindow();

        glfwSetKeyCallback(window.getId(), keyCallback);

        glfwShowWindow(window.getId());

        gameLogic.init();
    }

    private void loop() {
        while (!glfwWindowShouldClose(window.getId())) {
            glfwPollEvents();
            gameLogic.update();
            gameLogic.render(window);

        }
    }
}
