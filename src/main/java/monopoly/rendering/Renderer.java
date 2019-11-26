package monopoly.rendering;

import engine.Window;
import monopoly.util.Resources;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private ShaderProgram shaderProgram;

    private Window window;

    private Transformation transformation;

    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;

    private float specularPower;
    private static final int MAX_POINT_LIGHTS = 2;
    private static final int MAX_SPOT_LIGHTS = 1;

    public Renderer(Window window) {
        this.window = window;
        specularPower = 10f;
        transformation = new Transformation();
    }

    public void init() throws Exception {
        glClearColor(0, 0, 0, 0);

        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Resources.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Resources.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelMatrix");
        shaderProgram.createUniform("viewMatrix");
        shaderProgram.createUniform("texture_sampler");
        shaderProgram.createUniform("normalMap");

        shaderProgram.createMaterialUniform("material");

        shaderProgram.createUniform("specularPower");
        shaderProgram.createUniform("ambientLight");
        shaderProgram.createDirectionalLightUniform("directionalLight");
        shaderProgram.createPointLightListUniform("pointLights", MAX_POINT_LIGHTS);
        shaderProgram.createSpotLightListUniform("spotLights", MAX_SPOT_LIGHTS);

        transformation.updateProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
    }

    public void renderSceneLighting(Matrix4f viewMatrix, SceneLight sceneLight) {
        shaderProgram.setUniform("ambientLight", sceneLight.getAmbientLight());
        shaderProgram.setUniform("specularPower", specularPower);

        PointLight[] currentPointLights = sceneLight.getPointLights();
        int numLights = currentPointLights != null ? currentPointLights.length : 0;
        for (int i = 0; i < numLights; i++) {
            PointLight currentPointLight = new PointLight(currentPointLights[i]);
            Vector3f lightPos = currentPointLight.getPosition();
            Vector4f aux = new Vector4f(lightPos, 1);
            aux.mul(viewMatrix);
            lightPos.x = aux.x;
            lightPos.y = aux.y;
            lightPos.z = aux.z;
            shaderProgram.setUniform("pointLights", currentPointLight, i);
        }

        SpotLight[] currentSpotLights = sceneLight.getSpotLights();
        numLights = currentSpotLights != null ? currentSpotLights.length : 0;
        for (int i = 0; i < numLights; i++) {
            SpotLight currentSpotLight = new SpotLight(currentSpotLights[i]);
            Vector4f direction = new Vector4f(currentSpotLight.getConeDirection(), 0);
            direction.mul(viewMatrix);
            currentSpotLight.setConeDirection(new Vector3f(direction.x, direction.y, direction.z));

            Vector3f lightPosition = currentSpotLight.getPointLight().getPosition();
            Vector4f aux = new Vector4f(lightPosition, 1);
            aux.mul(viewMatrix);
            lightPosition.x = aux.x;
            lightPosition.y = aux.y;
            lightPosition.z = aux.z;
            shaderProgram.setUniform("spotLights", currentSpotLight, i);
        }

        DirectionalLight currentDirectionalLight = new DirectionalLight(sceneLight.getDirectionalLight());
        Vector4f direction = new Vector4f(currentDirectionalLight.getDirection(), 0);
        direction.mul(viewMatrix);
        currentDirectionalLight.setDirection(new Vector3f(direction.x, direction.y, direction.z));
        shaderProgram.setUniform("directionalLight", currentDirectionalLight);
    }

    public void render(double alpha, Scene scene, Camera camera) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shaderProgram.bind();

        camera.updateTransformations((float)alpha);

        Matrix4f projectionMatrix = transformation.getProjectionMatrix();
        Matrix4f viewMatrix = camera.getViewMatrix();
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        shaderProgram.setUniform("viewMatrix", viewMatrix);


        renderSceneLighting(viewMatrix, scene.getSceneLight());

        shaderProgram.setUniform("texture_sampler", 0);
        shaderProgram.setUniform("normalMap", 1);
        for (RenderableObject renderableObject : scene.getRenderableObjects()) {
            renderableObject.updateTransformations((float)alpha);
        }

        Map<Mesh, List<RenderableObject>> meshesMap = scene.getMeshesMap();
        for (Mesh mesh : meshesMap.keySet()) {
            shaderProgram.setUniform("material", mesh.getMaterial());
            mesh.renderList(meshesMap.get(mesh), (RenderableObject renderObject) -> {
                Matrix4f modelMatrix = transformation.buildModelMatrix(renderObject);
                shaderProgram.setUniform("modelMatrix", modelMatrix);
            }
            );
        }
        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
