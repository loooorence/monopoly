package monopoly.main;

import engine.IGameLogic;
import engine.Window;
import monopoly.board.Board;
import monopoly.rendering.*;
import monopoly.states.BoardState;
import monopoly.states.IState;
import monopoly.states.PlayerState;
import monopoly.states.StateNull;
import monopoly.util.Resources;
import monopoly.util.Stack;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class MonopolyLogic implements IGameLogic {

    private Renderer renderer;
    private Window window;
    private Scene scene;
    private RenderableObject[] objects;

    private Stack<IState> stateStack;

    private Camera camera;

    @Override
    public void init(Window window) throws Exception {
        this.window = window;
        renderer = new Renderer(window);
        renderer.init();
        initObjects();
        Board board = new Board(null);
        stateStack = new Stack<>();
        stateStack.push(new StateNull());
        stateStack.peek().beginState();

        scene = new Scene();
        SceneLight sceneLight = new SceneLight();

        PointLight[] pointLights = new PointLight[2];

        pointLights[0] = new PointLight(new Vector3f(1,1,1), new Vector3f(0,3,-5), 0.0f);
        pointLights[0].setAttenuation(new PointLight.Attenuation(0f, 0f, 1f));
        pointLights[1] = new PointLight(new Vector3f(1, 1, 1), new Vector3f(0, 5.5f, -2), 0.0f);
        pointLights[1].setAttenuation(new PointLight.Attenuation(0f, 0f, 1f));
        sceneLight.setPointLights(pointLights);

        Vector3f ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
        sceneLight.setAmbientLight(ambientLight);

        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), new Vector3f(0, 1, 0), 0.5f);
        sceneLight.setDirectionalLight(directionalLight);

        SpotLight[] spotLights = new SpotLight[1];
        PointLight pointLightSL = new PointLight(new Vector3f(1,1,1), new Vector3f(0,5,0), 0f);
        pointLightSL.setAttenuation(new PointLight.Attenuation(0, 0, 0.02f));
        spotLights[0] = new SpotLight(pointLightSL, new Vector3f(0,0.45f,-1), 20);
        sceneLight.setSpotLights(spotLights);

        scene.setSceneLight(sceneLight);
        scene.setRenderObjects(objects);

        camera = new Camera(new Vector3f(0,3,2), new Vector3f(0,0,0));
        camera.pointAt(0, 0, -5);
    }

    public void initObjects() throws Exception {
        Mesh[] meshes = StaticMeshesLoader.load(Resources.getResourcePath("/models/board/board5.obj"), "models/board/");

        objects = new RenderableObject[1];
        objects[0] = new RenderableObject(meshes);
        objects[0].setPosition(0, 0, -5);
        objects[0].setRotation(0, 90, 0);
    }

    @Override
    public void inputKeyboard(int key, int scancode, int action, int mods) {
        IState currentState = stateStack.peek();
        if (currentState instanceof PlayerState) {
            ((PlayerState) currentState).inputKeyboard(key, scancode, action, mods);
        }
    }

    @Override
    public void inputMouseButton(int button, int action, int mods, float xPos, float yPos) {
        IState currentState = stateStack.peek();
        if (currentState instanceof PlayerState) {
            ((PlayerState) currentState).inputMouse(button, action, mods, xPos, yPos);
        }
    }

    @Override
    public void update() {
        IState currentState = stateStack.peek();
        IState nextState = currentState.update();
        if (nextState == null) {
            stateStack.pop();
        } else {
            if (!currentState.equals(nextState)) {
                if (!nextState.isStacked()) {
                    stateStack.pop();
                }
                stateStack.push(nextState);
                nextState.beginState();
            }
        }
    }

    @Override
    public void render(double alpha) {
        IState currentState = stateStack.peek();
        if (currentState instanceof BoardState) {
            ((BoardState) currentState).renderUpdate((float)alpha);
        }
        renderer.render(alpha, scene, camera);
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
