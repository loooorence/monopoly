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

        vertices = new float[]{
            //front
            -0.5f, +0.5f, +0.5f, // 0-0
            -0.5f, -0.5f, +0.5f, // 1-1
            +0.5f, -0.5f, +0.5f, // 2-2
            +0.5f, +0.5f, +0.5f, // 3-3
            //back
            -0.5f, +0.5f, -0.5f, // 4-4
            +0.5f, +0.5f, -0.5f, // 5-5
            -0.5f, -0.5f, -0.5f, // 6-6
            +0.5f, -0.5f, -0.5f, // 7-7
            //right
            +0.5f, +0.5f, +0.5f, // 3-8
            +0.5f, +0.5f, -0.5f, // 5-9
            +0.5f, -0.5f, +0.5f, // 2-10
            +0.5f, -0.5f, -0.5f, // 7-11
            //left
            -0.5f, +0.5f, -0.5f, // 4-12
            -0.5f, +0.5f, +0.5f, // 0-13
            -0.5f, -0.5f, -0.5f, // 6-14
            -0.5f, -0.5f, +0.5f, // 1-15
            //bottom
            -0.5f, -0.5f, +0.5f, // 1-16
            +0.5f, -0.5f, +0.5f, // 2-17
            -0.5f, -0.5f, -0.5f, // 6-18
            +0.5f, -0.5f, -0.5f, // 7-19
            //top
            -0.5f, +0.5f, -0.5f, // 4-20
            +0.5f, +0.5f, -0.5f, // 5-21
            -0.5f, +0.5f, +0.5f, // 0-22
            +0.5f, +0.5f, +0.5f, // 3-23
        };
       textures = new float[]{
           //front
           0.00f, 0.00f,
           0.00f, 0.33f,
           0.50f, 0.33f,
           0.50f, 0.00f,
           //back
           1.00f, 0.00f,
           0.50f, 0.00f,
           1.00f, 0.33f,
           0.50f, 0.33f,
           //right
           0.00f, 0.33f,
           0.50f, 0.33f,
           0.00f, 0.66f,
           0.50f, 0.66f,
           //left
           0.50f, 0.33f,
           1.00f, 0.33f,
           0.50f, 0.66f,
           1.00f, 0.66f,
           //bottom
           0.00f, 0.66f,
           0.50f, 0.66f,
           0.00f, 1.00f,
           0.50f, 1.00f,
           //top
           0.50f, 0.66f,
           1.00f, 0.66f,
           0.50f, 1.00f,
           1.00f, 1.00f
       };
        indices = new int[]{
           0,  1,  3,  3,  1,  2,
           4,  5,  6,  5,  7,  6,
           9, 10, 11,  9,  8, 10,
          13, 14, 15, 13, 12, 14,
          17, 18, 19, 17, 16, 18,
          21, 22, 23, 21, 20, 22
        };
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

        Texture texture = new Texture(Resources.getResourcePath("/textures/dice-texture.jpg"));
        mesh = new Mesh(vertices, indices, textures, texture);
        objects = new RenderableObject[1];
        objects[0] = new RenderableObject(mesh);
        objects[0].setRotation(45, 45, 0);
        objects[0].setPosition(0, 0, -3);
    }

    public void render(double alpha) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();

        projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        shaderProgram.setUniform("texture_sampler", 0);
        for (RenderableObject objectToRender : objects) {
            objectToRender.rotateObject(1, 1, 0, (float)alpha);


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
        mesh.cleanup();
    }
}
