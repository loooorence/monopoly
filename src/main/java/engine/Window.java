package engine;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

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
