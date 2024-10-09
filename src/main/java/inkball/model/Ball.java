package inkball.model;

import inkball.App;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author SanseYooyea
 */
public class Ball extends GameObject implements Updatable, Colorable, Cloneable {
    @NotNull
    private final Color color;
    private final boolean isCaptured;
    private final Random RANDOM = new Random();
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
        double ballLeft =getLeft();
        double ballTop = getTop();
        double ballBottom = getBottom();

        double wallLeft = wall.getLeft();
        double wallRight = wall.getRight();
        double wallTop = wall.getTop();
        double wallBottom = wall.getBottom();



        // 检测水平方向的碰撞
        if (ballRight >= wallLeft && ballLeft <= wallRight) {
            if (ballTop <= wallBottom && ballBottom >= wallTop) {
                // 进行碰撞处理
                if (reflecting) {
                    return;
                }

                handleCollision(ballLeft, ballRight, ballTop, ballBottom, wallLeft, wallRight, wallTop, wallBottom);
                reflecting = true;
                System.out.println("Ball collided with wall");
                System.out.println("Ball x: " + x + " y: " + y);
                System.out.println("Ball vx: " + vx + " vy: " + vy);
                System.out.println("Wall x: " + wall.getX() + " y: " + wall.getY());
                return;
            }

            reflecting = false;
        }
    }

    private void handleCollision(double ballLeft, double ballRight, double ballTop, double ballBottom,
                                 double wallLeft, double wallRight, double wallTop, double wallBottom) {
        // 水平碰撞检测（左或右墙）
        if ((ballRight >= wallLeft && vx > 0) || (ballLeft <= wallRight && vx < 0)) {
            // 反转水平速度
            vx = -vx;
        }

        // 垂直碰撞检测（上或下墙）
        if ((ballTop <= wallBottom && vy < 0) || (ballBottom >= wallTop && vy > 0)) {
            // 反转垂直速度
            vy = -vy;
        }
    }

    public void checkCollision(InkLine inkLine) {
        // 检查与玩家画线的碰撞
    }

    public void checkHoleCollision(Hole hole) {
        // 检查是否接近洞口
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
