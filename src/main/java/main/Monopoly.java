package main;

import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryStack.*;

public class Monopoly {

    private Window window;

    private boolean resizable = false;
    private boolean vSync = true;
    private int windowWidth = 800;
    private int windowHeight = 600;
    private String windowTitle = "Monopoly";

    private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        }
    };

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window.getId());
        glfwDestroyWindow(window.getId());

        glfwTerminate();
        errorCallback.free();
    }

    private void init() {
        // error call back prints error message to System.err
        glfwSetErrorCallback(errorCallback);

        // initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window = new Window(windowTitle, windowWidth, windowHeight, vSync).createWindow();

        glfwSetKeyCallback(window.getId(), keyCallback);

        glfwShowWindow(window.getId());
    }

    private void loop() {
        try (MemoryStack stack = stackPush()) {
            FloatBuffer buffer = stackMallocFloat(3 * 2);
            buffer.put(-0.5f).put(-0.5f);
            buffer.put(0.5f).put(-0.5f);
            buffer.put(0.0f).put(0.5f);
            buffer.flip();

            int vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);

            glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        }

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(2, GL_FLOAT, 0, 0L);

        while (!glfwWindowShouldClose(window.getId())) {
            glfwPollEvents();
            glDrawArrays(GL_TRIANGLES, 0, 3);
            glfwSwapBuffers(window.getId());
        }
    }
}
