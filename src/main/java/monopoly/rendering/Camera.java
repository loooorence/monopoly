package monopoly.rendering;

import org.joml.Vector3f;

public class Camera extends RenderableObject{

    public Camera(Vector3f position, Vector3f rotation) {
        super(null);
        setPosition(position.x,position.y,position.z);
        setRotation(rotation.x,rotation.y,rotation.z);
    }

    public void pointAt(float x, float y, float z) {
        Vector3f position = getPosition();
        float distance = position.distance(x,y,z);
        if (distance != 0) {
            float dY = position.y - y;
            float dX = position.x - x;
            float dZ = position.z - z;

            float angleY;

            float angleX = (float) Math.toDegrees(Math.asin(dY / distance));
            if (dZ == 0) {
                angleY = (float) Math.toDegrees(Math.asin(dX / distance));
            } else {
                angleY = (float) Math.toDegrees(Math.atan(dX / dZ));
            }
            if (dZ < 0) {
                angleY += 180;
            }
            setRotation(angleX, -angleY, 0);
        }
    }

}
