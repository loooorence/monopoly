package monopoly.util;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Resources {

    public static String loadResource(String fileName) throws Exception{
        String result;

        try (InputStream in = Resources.class.getResourceAsStream(fileName);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }

        return result;
    }

    public static String getResourcePath(String fileName) throws Exception {
        Path path = Paths.get(Resources.class.getResource(fileName).toURI());
        return path.toFile().getPath();
    }

    public static ByteBuffer resourceToBuffer(String resourcePath, int bufferSize) throws Exception {
        ByteBuffer buffer;
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
        if (url == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        File file = new File(url.getFile());
        if (file.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 FileChannel fileChannel = fileInputStream.getChannel() ) {
                buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            }
        } else {
            buffer = BufferUtils.createByteBuffer(bufferSize);
            InputStream source = url.openStream();
            if (source == null)
                throw new FileNotFoundException(resourcePath);
            try {
                byte[] buf = new byte[8192];
                while (true) {
                    int bytes = source.read(buf, 0, buf.length);
                    if (bytes == -1)
                        break;
                    if (buffer.remaining() < bytes)
                        buffer = resizeBuffer(buffer, Math.max(buffer.capacity() * 2, buffer.capacity() - buffer.remaining() + bytes));
                    buffer.put(buf, 0, bytes);
                }
                buffer.flip();
            } finally {
                source.close();
            }
        }
        return buffer;
    }

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
}
