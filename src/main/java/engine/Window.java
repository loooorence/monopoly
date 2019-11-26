package engine;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL30.*;

public class Window {
    private long id;

    private String title;
    private int width;
    private int height;
    private boolean vSync;

    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
    }

    public Window createWindow() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_SAMPLES, 4);

        id = glfwCreateWindow(width, height, title, NULL, NULL);

        if (id == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create window - " + title);
        }

        glfwMakeContextCurrent(id);
        if (vSync) {
            glfwSwapInterval(1);
        }
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);


        return this;
    }

    public long getId() {
        return id;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
