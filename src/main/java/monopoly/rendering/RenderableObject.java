package monopoly.rendering;

import org.joml.Vector3f;

public class RenderableObject {

    private final Vector3f position;
    private Vector3f targetPosition;
    private float speedMovement;

    private float scale;
    private float targetScale;
    private float speedScale;

    private final Vector3f rotation;
    private Vector3f targetRotation;
    private float speedRotation;

    private final Mesh[] meshes;

    public RenderableObject(Mesh mesh) {
        this.meshes = new Mesh[]{mesh};
        this.position = new Vector3f(0,0,0);
        this.scale = 1f;
        this.rotation = new Vector3f(0,0,0);
    }

    public RenderableObject(Mesh[] meshes) {
        this.meshes = meshes;
        this.position = new Vector3f(0,0,0);
        this.scale = 1f;
        this.rotation = new Vector3f(0,0,0);
    }

    public Mesh[] getMeshes() { return this.meshes; }

    public void updateTransformations(float alpha) {
        if (this.targetPosition != null) {
            if (updateVector(position, targetPosition, alpha, speedMovement)) {
                this.targetPosition = null;
                this.speedMovement = 0;
            }
        }
        if (this.scale != targetScale) {
            float delta = scale - targetScale;
            if (Math.abs(delta) <= alpha * speedScale) {
                this.scale = targetScale;
            } else {
                this.scale += alpha * (speedScale / delta);
            }
        }
        if (this.targetRotation != null) {
            if (updateVector(rotation, targetRotation, alpha, speedRotation)) {
                this.targetRotation = null;
                this.speedRotation = 0;
            }
        }
    }

    public boolean isMoving() {
        return this.targetPosition != null;
    }

    public boolean isScaling() {
        return this.targetScale != this.scale;
    }

    public boolean isRotating() {
        return this.targetRotation != null;
    }

    private boolean updateVector(Vector3f current, Vector3f target, float alpha, float speed) {
        float distance = target.distance(current);
        if (distance <= alpha * speed) {
            current.set(target);
            if (this instanceof Camera) {
                ((Camera)this).updateViewMatrix();
            }
            return true;
        } else {
            current.lerp(target, alpha * (speed / distance));
            if (this instanceof Camera) {
                ((Camera)this).updateViewMatrix();
            }
            return false;
        }
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
        if (this instanceof Camera) {
            ((Camera)this).updateViewMatrix();
        }
    }

    public void moveObject(float deltaX, float deltaY, float deltaZ) {
        setPosition(
                this.position.x + deltaX,
                this.position.y + deltaY,
                this.position.z + deltaZ
        );
    }

    public void moveObjectToTarget(float targetX, float targetY, float targetZ, float speed) {
        this.targetPosition = new Vector3f(targetX, targetY, targetZ);
        this.speedMovement = speed;
    }

    public void moveObjectWithSpeed(float deltaX, float deltaY, float deltaZ, float speed) {
        moveObjectToTarget(
                this.position.x + deltaX,
                this.position.y + deltaY,
                this.position.z + deltaZ,
                speed
        );
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void scaleObject(float deltaScale) {
        this.scale += deltaScale;
    }

    public void scaleObjectToTarget(float targetScale, float speed) {
        this.targetScale = targetScale;
        this.speedScale = speed;
    }

    public Vector3f getRotation() {
        return this.rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.set(
                (float)Math.toRadians(x),
                (float)Math.toRadians(y),
                (float)Math.toRadians(z)
        );
        if (this instanceof Camera) {
            ((Camera)this).updateViewMatrix();
        }
    }

    public void rotateObject(float deltaX, float deltaY, float deltaZ) {
        setRotation(
                (float)Math.toDegrees(this.rotation.x) + deltaX,
                (float)Math.toDegrees(this.rotation.y) + deltaY,
                (float)Math.toDegrees(this.rotation.z) + deltaZ
        );
    }

    public void rotateObjectToTarget(float targetX, float targetY, float targetZ, float speed) {
        this.targetRotation = new Vector3f(
                (float)Math.toRadians(targetX),
                (float)Math.toRadians(targetY),
                (float)Math.toRadians(targetZ)
        );
        this.speedRotation = (float)Math.toRadians(speed);
    }

    public void rotateObjectWithSpeed(float deltaX, float deltaY, float deltaZ, float speed) {
        rotateObjectToTarget(
                (float)Math.toDegrees(this.rotation.x) + deltaX,
                (float)Math.toDegrees(this.rotation.y) + deltaY,
                (float)Math.toDegrees(this.rotation.z) + deltaZ,
                speed
        );
    }

    public void cleanup() {
        for (Mesh mesh : meshes) {
            mesh.cleanup();
        }
    }

}
