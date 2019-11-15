package monopoly.rendering;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {

    private final int textureId;

    public Texture(String fileName) {
        this(loadTextute(fileName));
    }

    private Texture(int id) {
        this.textureId = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    private static int loadTextute(String fileName) {
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
