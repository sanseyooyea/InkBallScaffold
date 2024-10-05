package inkball.dao;

import inkball.model.Layout;

import java.io.File;

/**
 * @author SanseYooyea
 */
public class LayoutDAO {
    public Layout load(int level) {
        File levelFile = new File("level" + level + ".txt");
        String config = FileUtils.readFile(levelFile);

        String[] lines = config.split("\\r?\\n");

    }
}
