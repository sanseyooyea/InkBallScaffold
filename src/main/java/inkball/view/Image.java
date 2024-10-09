package inkball.view;

import inkball.App;
import inkball.model.GameObjectType;
import processing.core.PImage;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author SanseYooyea
 */
public enum Image {
    BALL0,
    BALL1,
    BALL2,
    BALL3,
    BALL4,
    ENTRYPOINT,
    HOLE0,
    HOLE1,
    HOLE2,
    HOLE3,
    HOLE4,
    TILE,
    WALL0,
    WALL1,
    WALL2,
    WALL3,
    WALL4;

    // EnumMap 用于存储不同 GameObjectType 类型对应的 Image 映射
    private static final Map<GameObjectType, Image[]> objectImageMap = new EnumMap<>(GameObjectType.class);

    // 静态初始化块，用于预先加载不同类型的映射关系
    static {
        objectImageMap.put(GameObjectType.BALL, new Image[]{BALL0, BALL1, BALL2, BALL3, BALL4});
        objectImageMap.put(GameObjectType.HOLE, new Image[]{HOLE0, HOLE1, HOLE2, HOLE3, HOLE4});
        objectImageMap.put(GameObjectType.WALL, new Image[]{WALL0, WALL1, WALL2, WALL3, WALL4});
    }

    private PImage image;

    Image() {
        try {
            String imagePath = App.class.getResource(this.name().toLowerCase() + ".png").getPath();
            if (imagePath != null) {
                image = App.getInstance().loadImage(URLDecoder.decode(imagePath, StandardCharsets.UTF_8));
            } else {
                throw new IllegalArgumentException("Image resource not found for: " + this.name());
            }
        } catch (Exception e) {
            e.printStackTrace(); // 处理加载图片时的异常
        }
    }

    // 返回与类型相关的默认图片
    public static Image getImage(GameObjectType type) {
        return switch (type) {
            case TILE -> TILE;
            case SPAWNER -> ENTRYPOINT;
            default -> null;
        };
    }

    // 根据 GameObjectType 和颜色编号获取图片
    public static Image getImage(GameObjectType type, int colorNumber) {
        if (objectImageMap.containsKey(type)) {
            Image[] images = objectImageMap.get(type);
            if (colorNumber >= 0 && colorNumber < images.length) {
                return images[colorNumber];
            }
        }
        return null;
    }

    public PImage getImage() {
        return image;
    }
}
