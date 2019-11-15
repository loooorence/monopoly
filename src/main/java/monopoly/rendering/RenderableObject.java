package monopoly.rendering;

import org.joml.Vector3f;

public class RenderableObject {

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private final Vector3f rotation;

    public RenderableObject(Mesh mesh) {
        this.mesh = mesh;
        this.position = new Vector3f(0,0,0);
        this.scale = 1f;
        this.rotation = new Vector3f(0,0,0);
    }

    public Mesh getMesh() {
        return this.mesh;
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public void setPosition(float x, float y, float z) {
        this.setPosition(x, y, z, 1.0f);
    }

    public void setPosition(float x, float y, float z, float alpha) {
        this.position.lerp(new Vector3f(x, y, z), alpha);
    }

    public void moveObject(float x, float y, float z) {
        moveObject(x, y, z, 1.0f);
    }

    public void moveObject(float x, float y, float z, float alpha) {
        setPosition(
                this.position.x + x,
                this.position.y + y,
                this.position.z + z,
                alpha
        );
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return this.rotation;
    }

    public void setRotation(float x, float y, float z) {
        setRotation(x, y, z, 1.0f);
    }

    public void setRotation(float x, float y, float z, float alpha) {
        this.rotation.lerp(new Vector3f(x, y, z), alpha);
    }

    public void rotateObject(float x, float y, float z) {
        rotateObject(x, y, z, 1.0f);
    }

    public void rotateObject(float x, float y, float z, float alpha){
        setRotation(
                this.rotation.x + x,
                this.rotation.y + y,
                this.rotation.z + z,
                alpha
        );
    }

}
