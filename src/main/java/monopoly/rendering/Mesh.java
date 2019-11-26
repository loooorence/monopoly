package monopoly.rendering;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {

    private final int vaoId;

    private final int vertVboId;
    private final int textureVboId;
    private final int normalVboId;
    private final int indexVboId;
    private final int tangentVboId;

    private final int vertexCount;

    private Material material;

    public Mesh(float[] positions, float[] textures, float[] normals, int[] indices, float[] tangents) {
        FloatBuffer verticesBuffer = null;
        FloatBuffer textureBuffer = null;
        FloatBuffer normalBuffer = null;
        IntBuffer indicesBuffer = null;
        FloatBuffer tangentBuffer = null;

        System.out.printf("%d %d %d %d %d\n", positions.length, textures.length, normals.length, indices.length, tangents.length);

        try {
            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            vertVboId = glGenBuffers();
            verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
            verticesBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vertVboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            textureVboId = glGenBuffers();
            textureBuffer = MemoryUtil.memAllocFloat(textures.length);
            if (textureBuffer.capacity() > 0) {
                textureBuffer.put(textures).flip();
            } else {
                textureBuffer = MemoryUtil.memAllocFloat(positions.length);
                textureBuffer.put(new float[positions.length]).flip();
            }
            glBindBuffer(GL_ARRAY_BUFFER, textureVboId);
            glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            normalVboId = glGenBuffers();
            normalBuffer = MemoryUtil.memAllocFloat(normals.length);
            if (normalBuffer.capacity() > 0) {
                normalBuffer.put(normals).flip();
            } else {
                throw new IllegalStateException("Mesh tried to load with no normal data...");
            }
            glBindBuffer(GL_ARRAY_BUFFER, normalVboId);
            glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

            tangentVboId = glGenBuffers();
            tangentBuffer = MemoryUtil.memAllocFloat(tangents.length);
            if (tangentBuffer.capacity() > 0) {
                tangentBuffer.put(tangents).flip();
            } else {
                tangentBuffer = MemoryUtil.memAllocFloat(positions.length);
                tangentBuffer.put(new float[positions.length]).flip();
            }
            glBindBuffer(GL_ARRAY_BUFFER, tangentVboId);
            glBufferData(GL_ARRAY_BUFFER, tangentBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(3, 3, GL_FLOAT, false, 0, 0);

            indexVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
            if (textureBuffer != null) {
                MemoryUtil.memFree(textureBuffer);
            }
            if (normalBuffer != null) {
                MemoryUtil.memFree(normalBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    public Mesh(float[] positions, float[] textures, float[] normals, int[] indices) {
        this(positions, textures, normals, indices, new float[0]);

    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void render() {
        setRenderState();

        glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);

        restoreRenderState();
    }

    public void renderList(List<RenderableObject> objects, Consumer<RenderableObject> consumer) {
        setRenderState();

        for (RenderableObject objectToRender : objects) {
            consumer.accept(objectToRender);
            glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
        }

        restoreRenderState();
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    private void setRenderState() {
        Texture texture = material != null ? material.getTexture() : null;
        if (texture != null) {
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture.getId());
        }
        Texture normalMap = material != null ? material.getNormalMap() : null;
        if (normalMap != null) {
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, normalMap.getId());
        }

        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
    }

    private void restoreRenderState() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glBindVertexArray(0);

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanup() {
        glDisableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vertVboId);
        glDeleteBuffers(textureVboId);
        glDeleteBuffers(normalVboId);
        glDeleteBuffers(indexVboId);

        Texture texture = material.getTexture();
        if (texture != null) {
            texture.cleanup();
        }

        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}
