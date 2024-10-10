package inkball.model;

import inkball.App;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author SanseYooyea
 */
public class Ball extends GameObject implements Updatable, Colorable, Cloneable {
    private static final Random RANDOM = new Random();
    public final double radius = 12;
    @NotNull
    private final Color color;
    private boolean isCaptured;
    /**
     * 每秒移速
     */
    private double vx, vy;
    private boolean reflecting = false;

    public Ball(double x, double y, @NotNull Color color) {
        super(GameObjectType.BALL, x, y);
        this.color = color;
        this.isCaptured = false;

        boolean xPositive = RANDOM.nextBoolean();
        boolean yPositive = RANDOM.nextBoolean();

        // 球的总速度为 1 个 CELL
        vx = xPositive ? RANDOM.nextDouble(Math.sqrt(App.CELL_SIZE)) : -RANDOM.nextDouble(Math.sqrt(App.CELL_SIZE));
        vy = yPositive ? Math.sqrt(App.CELL_SIZE - vx * vx) : -Math.sqrt(App.CELL_SIZE - vx * vx);
    }

    @Override
    public double getBottom() {
        return y * App.CELL_SIZE + 2 * radius + App.TOP_BAR_HEIGHT;
    }

    @Override
    public double getRight() {
        return x * App.CELL_SIZE + 2 * radius;
    }

    @Override
    public void update() {
        if (isCaptured) {
            return;
        }

        x += vx / App.FPS;
        y += vy / App.FPS;
    }

    public void checkCollision(Wall wall) {
        // 检查与墙壁的碰撞并处理反射
        double ballRight = getRight();
        double ballLeft = getLeft();
        double ballTop = getTop();
        double ballBottom = getBottom();

        double wallLeft = wall.getLeft();
        double wallRight = wall.getRight();
        double wallTop = wall.getTop();
        double wallBottom = wall.getBottom();

        // 检测水平方向的碰撞
        boolean horizontalCollision = ballRight >= wallLeft && ballLeft <= wallRight &&
                ballBottom >= wallTop && ballTop <= wallBottom;

        // 检测垂直方向的碰撞
        boolean verticalCollision = ballBottom >= wallTop && ballTop <= wallBottom &&
                ballRight >= wallLeft && ballLeft <= wallRight;


        if (horizontalCollision || verticalCollision) {
            if (reflecting) {
                return;
            }

            if (wall.getColor() != null) {
                wall.setColor(color);
            }

            reflecting = true;
            // 计算重叠程度
            double overlapX = Math.min(ballRight - wallLeft, wallRight - ballLeft);
            double overlapY = Math.min(ballBottom - wallTop, wallBottom - ballTop);

            // 反转较小重叠的方向
            if (overlapX < overlapY) {
                vx = -vx;
                // 确保球不会嵌入墙内
                // +-1 是为了防止重复碰撞
                if (vx > 0) {
                    x = (wallRight + 1) / App.CELL_SIZE;
                } else { // 碰撞到右墙
                    x = (wallLeft - 2 * radius - 1) / App.CELL_SIZE;
                }
            } else {
                vy = -vy;
                // 确保球不会嵌入墙内
                if (vy > 0) {
                    // 碰撞到上墙
                    y = (wallBottom - App.TOP_BAR_HEIGHT) / App.CELL_SIZE;
                } else {
                    // 碰撞到下墙
                    y = (wallTop - App.TOP_BAR_HEIGHT - 2 * radius) / App.CELL_SIZE;
                }
            }

            return;
        }

        reflecting = false;
    }

    public void checkCollision(InkLine inkLine) {
        // 检查与玩家画线的碰撞
    }

    public void checkHoleCollision(Hole hole) {
        if (isCaptured) {
            return;
        }

        if (!hole.captureBall(this)) {
            return;
        }

        isCaptured = true;
        if (hole.getColor() != color) {
            App.getInstance().getGame().subtractScore(App.getInstance().getGame().getConfigService().getConfig().getScoreDecrement().get(color));
        } else {
            App.getInstance().getGame().addScore(App.getInstance().getGame().getConfigService().getConfig().getScoreIncrement().get(color));
        }
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    @Override
    public Ball clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return new Ball(x, y, color);
    }
}
