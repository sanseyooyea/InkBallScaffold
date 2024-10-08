package inkball.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author SanseYooyea
 */
public class Wall extends GameObject implements Colorable {
    @Nullable
    private Color color;

    public Wall(double x, double y) {
        super(GameObjectType.WALL, x, y);
    }

    public Wall(double x, double y, @NotNull Color color) {
        super(GameObjectType.WALL, x, y);
        this.color = color;
    }

    public void reflectBall(Ball ball) {
        // 处理与球的反射逻辑
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    @Nullable
    public Color getColor() {
        return color;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
    }
}
