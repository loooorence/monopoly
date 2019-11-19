package monopoly.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {
    private final Matrix4f projectionMatrix;

    private final Matrix4f modelViewMatrix;

    private final Matrix4f viewMatrix;

    public Transformation() {
        modelViewMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        float aspectRatio = width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f getModelViewMatrix(RenderableObject renderObject, Matrix4f viewMatrix) {
        Vector3f objRotation = renderObject.getRotation();
        modelViewMatrix.identity().
                translate(renderObject.getPosition()).
                rotateX((float)Math.toRadians(-objRotation.x)).
                rotateY((float)Math.toRadians(-objRotation.y)).
                rotateZ((float)Math.toRadians(-objRotation.z)).
                scale(renderObject.getScale());
        return (new Matrix4f(viewMatrix)).mul(modelViewMatrix);
    }

    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPosition = camera.getPosition();
        Vector3f cameraRotation = camera.getRotation();

        viewMatrix.identity();
        viewMatrix.rotate((float)Math.toRadians(cameraRotation.x), new Vector3f(1,0,0));
        viewMatrix.rotate((float)Math.toRadians(cameraRotation.y), new Vector3f(0,1,0));
        viewMatrix.translate(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);

        return viewMatrix;
    }

}
