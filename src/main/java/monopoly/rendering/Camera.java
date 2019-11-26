package monopoly.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera extends RenderableObject{

    private Matrix4f viewMatrix;

    public Camera(Vector3f position, Vector3f rotation) {
        super((Mesh) null);
        viewMatrix = new Matrix4f();
        setPosition(position.x,position.y,position.z);
        setRotation(rotation.x, rotation.y, rotation.z);
    }

    public Matrix4f getViewMatrix() {
        return this.viewMatrix;
    }

    public Matrix4f updateViewMatrix() {
        return Transformation.updateViewMatrix(getPosition(), getRotation(), viewMatrix);
    }

    public void pointAt(float x, float y, float z) {
        Vector3f position = getPosition();
        float distance = position.distance(x,y,z);
        if (distance != 0) {
            float dY = position.y - y;
            float dX = position.x - x;
            float dZ = position.z - z;

            float angleY;

            float angleX = (float) Math.asin(dY / distance);
            if (dZ == 0) {
                angleY = (float) Math.asin(dX / distance);
            } else {
                angleY = (float) Math.atan(dX / dZ);
            }
            if (dZ < 0) {
                angleY += 180;
            }
            getRotation().set(angleX, -angleY, 0);
            updateViewMatrix();
        }
    }

}
