package inkball.dao;

import inkball.model.Color;
import inkball.model.GameObject;
import inkball.model.GameObjectType;
import inkball.model.Layout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class LayoutDAO {
    public Layout load(File levelFile) {
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
                    // 只有数字时的处理，代表墙的颜色
                    if (Character.isDigit(b)) {
                        gameObjectType = GameObjectType.WALL;
                        int number = Character.getNumericValue(b);
                        gameObjects.add(GameObjectType.createGameObject(gameObjectType, x, y, Color.getColorByNumber(number)));
                        continue;
                    } else {
                        throw new IllegalArgumentException("Invalid character in level file: " + levelFile.getName() + " at position " + x + ", " + y);
                    }
                }

                if (gameObjectType == GameObjectType.TILE) {
                    continue;
                }

                // X + 数字 时的处理
                if (gameObjectType == GameObjectType.WALL) {
                    GameObject gameObject = GameObjectType.createGameObject(gameObjectType, x, y);
                    gameObjects.add(gameObject);
                    continue;
                }

                int number = -1;
                if (x + 1 > charArray.length - 1) {
                    GameObject gameObject = GameObjectType.createGameObject(gameObjectType, x, y);
                    gameObjects.add(gameObject);
                    continue;
                }

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
