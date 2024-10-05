package inkball.model;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author SanseYooyea
 */
public class Hole extends GameObject{
    @Nullable
    private final Color color;

    public Hole(double x, double y) {
        super(GameObjectType.HOLE, x, y);
        this.color = null;
    }

    public Hole(double x, double y, @NotNull Color color) {
        super(x, y);
        this.color = color;
    }

    public boolean captureBall(Ball ball) {
        // 检查球的颜色并决定是否得分
    }

    @Nullable
    public Color getColor() {
        return color;
    }
}
