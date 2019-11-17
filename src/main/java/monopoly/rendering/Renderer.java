package monopoly.rendering;

import engine.Window;
import monopoly.util.Resources;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private ShaderProgram shaderProgram;

    private Window window;

    private float[] vertices;
    private float[] textures;
    private int[] indices;

    private Transformation transformation;

    private Matrix4f projectionMatrix;
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;

    private RenderableObject[] objects;

    private Mesh mesh;

    public Renderer(Window window) {
        this.window = window;

        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);

        transformation = new Transformation();


    }

    public void init() throws Exception {
        glClearColor(0, 0, 0, 0);

        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Resources.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Resources.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");
        shaderProgram.createUniform("texture_sampler");
    }

    public void render(double alpha, RenderableObject[] objects) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();

        projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        shaderProgram.setUniform("texture_sampler", 0);
        for (RenderableObject objectToRender : objects) {
            objectToRender.updateTransformations((float)alpha);
            Matrix4f worldMatrix = transformation.getWorldMatrix(
                    objectToRender.getPosition(),
                    objectToRender.getRotation(),
                    objectToRender.getScale()
            );

            shaderProgram.setUniform("worldMatrix", worldMatrix);

            objectToRender.getMesh().render();
        }
        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
