package monopoly.rendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {

    private Map<Mesh, List<RenderableObject>> meshesMap;
    private RenderableObject[] renderableObjects;

    private SceneLight sceneLight;

    public Scene() {
        meshesMap = new HashMap<>();
    }

    public Map<Mesh, List<RenderableObject>> getMeshesMap() {
        return meshesMap;
    }

    public RenderableObject[] getRenderableObjects() {
        return renderableObjects;
    }

    public void setRenderObjects(RenderableObject[] objects) {
        int numObjects = objects != null ? objects.length : 0;
        for (int i = 0; i < numObjects; i++) {
            RenderableObject renderObject = objects[i];
            Mesh[] meshes = renderObject.getMeshes();
            for (Mesh mesh : meshes) {
                List<RenderableObject> objectList = meshesMap.get(mesh);
                if (objectList == null) {
                    objectList = new ArrayList<>();
                    meshesMap.put(mesh, objectList);
                }
                objectList.add(renderObject);
            }
        }
        this.renderableObjects = objects;
    }

    public SceneLight getSceneLight() {
        return sceneLight;
    }

    public void setSceneLight(SceneLight sceneLight) {
        this.sceneLight = sceneLight;
    }
}
