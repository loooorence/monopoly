package monopoly.main;

import engine.IGameLogic;
import engine.Window;
import monopoly.board.Board;
import monopoly.rendering.Mesh;
import monopoly.rendering.RenderableObject;
import monopoly.rendering.Renderer;
import monopoly.rendering.Texture;
import monopoly.util.Resources;

import static org.lwjgl.glfw.GLFW.*;

public class MonopolyLogic implements IGameLogic {

    private Renderer renderer;
    private Window window;
    private RenderableObject[] objects;

    @Override
    public void init(Window window) throws Exception {
        this.window = window;
        renderer = new Renderer(window);
        renderer.init();
        initObjects();
        Board board = new Board(null);
    }

    public void initObjects() throws Exception {
        float[] vertices = new float[]{
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
        float[] textures = new float[]{
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
        int[] indices = new int[]{
                0,  1,  3,  3,  1,  2,
                4,  5,  6,  5,  7,  6,
                9, 10, 11,  9,  8, 10,
                13, 14, 15, 13, 12, 14,
                17, 18, 19, 17, 16, 18,
                21, 22, 23, 21, 20, 22
        };

        Texture texture = new Texture(Resources.getResourcePath("/textures/dice-texture.jpg"));
        Mesh mesh = new Mesh(vertices, indices, textures, texture);

        objects = new RenderableObject[2];
        objects[0] = new RenderableObject(mesh);
        objects[0].setPosition(-1f, 0, -3);
        objects[0].rotateObjectToTarget(720, 0, 0, 6);

        objects[1] = new RenderableObject(mesh);
        objects[1].setPosition(1f, 0, -3);
        objects[1].rotateObjectToTarget(-720, 0, 0, 6);
    }

    @Override
    public void inputKeyboard(int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(window.getId(), true);
        }
    }

    @Override
    public void inputMouseButton(int button, int action, int mods) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(double alpha) {
        renderer.render(alpha, objects);
        glfwSwapBuffers(window.getId());
    }

    @Override
    public void cleanup() {
        for (RenderableObject object : objects) {
            object.cleanup();
        }
        renderer.cleanup();
    }
}
