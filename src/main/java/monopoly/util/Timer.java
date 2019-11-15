package monopoly.util;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {

    private double lastTime;

    public void init() {
        lastTime = glfwGetTime();
    }

    public double getDeltaTime() {
        double currentTime = glfwGetTime();
        double deltaTime = currentTime - lastTime;
        lastTime = currentTime;
        return deltaTime;
    }
}
