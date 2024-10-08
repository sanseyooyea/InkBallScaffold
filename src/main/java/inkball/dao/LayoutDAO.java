package inkball.dao;

import inkball.model.Color;
import inkball.model.GameObject;
import inkball.model.GameObjectType;
import inkball.model.Layout;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class LayoutDAO {
    public Layout load(int level) {
        File levelFile = new File("level" + level + ".txt");
        String content = FileUtils.readFile(levelFile);
        if (content == null) {
            return null;
        }

        List<GameObject> gameObjects = new ArrayList<>();
        String[] lines = content.split("\\r?\\n");
        for (int y = 0; y < lines.length; y++) {
            char[] charArray = lines[y].toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                char b = charArray[x];
                GameObjectType gameObjectType = GameObjectType.fromSymbol(b);
                if (gameObjectType == null) {
                    throw new IllegalArgumentException("Invalid character in level file: " + b);
                }

                if (gameObjectType == GameObjectType.SPACE) {
                    continue;
                }

                int number = -1;
                char nextCharacter = charArray[x + 1];
                if (Character.isDigit(nextCharacter)) {
                    number = Character.getNumericValue(nextCharacter);
                    x++;
                }

                GameObject gameObject;
                if (number == -1) {
                    gameObject = GameObjectType.createGameObject(gameObjectType, x, y);
                } else {
                    gameObject = GameObjectType.createGameObject(gameObjectType, x, y, Color.getColorByNumber(number));
                }

                gameObjects.add(gameObject);
            }
        }

        return new Layout(gameObjects);
    }
}
