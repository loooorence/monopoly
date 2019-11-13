package monopoly.rendering;

import engine.Window;
import monopoly.util.Resources;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private ShaderProgram shaderProgram;

    private Window window;

    private float[] vertices;
    private float[] colors;
    private int[] indices;

    private Matrix4f projectionMatrix;
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;

    private Mesh mesh;

    public Renderer(Window window) {
        this.window = window;

        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);

        vertices = new float[]{
            -0.5f, +0.5f, -2.0f,
            -0.5f, -0.5f, -2.0f,
            +0.5f, -0.5f, -2.0f,
            +0.5f, +0.5f, -2.0f,
        };
        colors = new float[]{
            +1.0f, +1.0f, +0.0f,
            +1.0f, +0.0f, +1.0f,
            +1.0f, +1.0f, +0.0f,
            +1.0f, +0.0f, +1.0f
        };
        indices = new int[]{
          0, 1, 3,
          3, 1, 2
        };
    }

    public void init() throws Exception {
        glClearColor(0, 0, 0, 0);

        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Resources.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Resources.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");

        mesh = new Mesh(vertices, indices, colors);
    }

    public void render(double alpha) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
        mesh.cleanup();
    }
}
