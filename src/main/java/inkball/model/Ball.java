package inkball.model;

import org.jetbrains.annotations.NotNull;

/**
 * @author SanseYooyea
 */
public class Ball extends GameObject implements Updatable, Colorable {
    @NotNull
    private final Color color;
    private float vx, vy;
    private final boolean isCaptured;

    public Ball(double x, double y, @NotNull Color color) {
        super(GameObjectType.BALL, x, y);
        this.color = color;
        this.isCaptured = false;
    }

    @Override
    public void update() {
        x += vx;
        y += vy;
    }

    public void checkCollision(Wall wall) {
        // 检查与墙壁的碰撞并处理反射
    }

    public void checkCollision(InkLine inkLine) {
        // 检查与玩家画线的碰撞
    }

    public void checkHoleCollision(Hole hole) {
        // 检查是否接近洞口
    }

    public boolean isInHole(Hole hole) {
        // 检查是否进入洞口
        return true;
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }

    public boolean isCaptured() {
        return isCaptured;
    }
}
