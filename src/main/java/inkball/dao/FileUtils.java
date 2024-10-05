package inkball.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author SanseYooyea
 */
public class FileUtils {
    private FileUtils() {
    }

    public static void createFile(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(File file) {
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            byte[] buffer = new byte[(int) channel.size()];
            channel.read(ByteBuffer.wrap(buffer));
            return new String(buffer);
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeFile(File file, String content) {
        try (FileChannel channel = new FileOutputStream(file).getChannel()) {
            channel.write(ByteBuffer.wrap(content.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
