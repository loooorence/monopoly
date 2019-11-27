package monopoly.rendering;

import monopoly.util.Resources;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {

    private final int textureId;

    public Texture(String fileName) throws Exception {
        this(Resources.resourceToBuffer(fileName, 8000));
    }

    private Texture(int id) {
        this.textureId = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public Texture(ByteBuffer imageData) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bufferW = stack.mallocInt(1);
            IntBuffer bufferH = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer loadedImage = stbi_load_from_memory(imageData, bufferW, bufferH, channels, 4);
            int width = bufferW.get();
            int height = bufferH.get();

            this.textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, this.textureId);

            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, loadedImage);

//            glGenerateMipmap(GL_TEXTURE_2D);

            stbi_image_free(loadedImage);
        }
    }

    private static int loadTextureDirectly(String fileName) {
        int width, height;
        ByteBuffer buffer;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bufferW = stack.mallocInt(1);
            IntBuffer bufferH = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            buffer = stbi_load(fileName, bufferW, bufferH, channels, 4);
            if (buffer == null) {
                throw new RuntimeException("Failed to load image: " + fileName + " : " + stbi_failure_reason());
            }

            width = bufferW.get();
            height = bufferH.get();
        }

        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);

        stbi_image_free(buffer);

        return texId;
    }

    public void cleanup() {
        glDeleteTextures(textureId);
    }

    public int getId() {
        return textureId;
    }
}
