package monopoly.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {
    private final Matrix4f projectionMatrix;

    private final Matrix4f modelViewMatrix;

    private final Matrix4f modelMatrix;

    private final Matrix4f viewMatrix;

    public Transformation() {
        modelViewMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        modelMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public final Matrix4f updateProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        float aspectRatio = width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f buildModelViewMatrix(RenderableObject renderObject, Matrix4f viewMatrix) {
        return buildModelViewMatrix(buildModelMatrix(renderObject), viewMatrix);
    }

    public Matrix4f buildModelViewMatrix(Matrix4f modelMatrix, Matrix4f viewMatrix) {
        return viewMatrix.mulAffine(modelMatrix, modelViewMatrix);
    }

    public Matrix4f buildModelMatrix(RenderableObject object) {
        return modelMatrix.translation(object.getPosition()).
                rotateXYZ(object.getRotation().x, object.getRotation().y, object.getRotation().z).
                scale(object.getScale());
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public static Matrix4f updateViewMatrix(Vector3f position, Vector3f rotation, Matrix4f viewMatrix) {
        return viewMatrix.rotationX(rotation.x).rotateY(rotation.y).
                translate(-position.x, -position.y, -position.z);
    }

}
