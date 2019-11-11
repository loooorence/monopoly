package monopoly.main;

import engine.IGameLogic;
import engine.Window;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.glfw.GLFW.*;

public class MonopolyLogic implements IGameLogic {

    @Override
    public void init() {
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
    }

    @Override
    public void input(Window window, int key, int scancode, int action, int mods) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Window window) {
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glfwSwapBuffers(window.getId());
    }
}
