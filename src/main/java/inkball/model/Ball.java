package inkball.model;

import inkball.App;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
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

    public boolean checkCollision(InkLine inkLine) {
        boolean colliding = false;
        for (InkLine.Line lineSegment : inkLine.line) {
            // 检查与当前线段的碰撞
            if (isCollidingWithLine(lineSegment)) {
                reflectFromLine(lineSegment);
                colliding = true;
                break; // 只处理一次碰撞
            }
        }

        return colliding;
    }

    private boolean isCollidingWithLine(InkLine.Line lineSegment) {
        // 获取球心位置
        double ballX = x * App.CELL_SIZE + radius;
        double ballY = y * App.CELL_SIZE + radius + App.TOP_BAR_HEIGHT;
        double radius = this.radius;

        // 线段的起点和终点
        Point2D start = lineSegment.start;
        Point2D end = lineSegment.end;

        // 计算线段的长度
        double lineLength = start.distance(end);
        // 线段无效
        if (lineLength == 0) return false;

        // 计算参数t以获取最近点
        double t = ((ballX - start.getX()) * (end.getX() - start.getX()) +
                (ballY - start.getY()) * (end.getY() - start.getY())) /
                (lineLength * lineLength);

        // 限制t的范围在0到1之间
        t = Math.max(0, Math.min(1, t));

        // 计算最近点的坐标
        double closestX = start.getX() + t * (end.getX() - start.getX());
        double closestY = start.getY() + t * (end.getY() - start.getY());

        // 计算球心到最近点的距离
        double distance = Point2D.distance(ballX, ballY, closestX, closestY);
        // 如果距离小于半径，则发生碰撞
        return distance < radius;
    }

    private void reflectFromLine(InkLine.Line lineSegment) {
        // 线段的方向向量
        double dx = lineSegment.end.getX() - lineSegment.start.getX();
        double dy = lineSegment.end.getY() - lineSegment.start.getY();

        // 线段的法线向量
        double normalX = -dy;
        double normalY = dx;

        // 归一化法线
        double length = Math.sqrt(normalX * normalX + normalY * normalY);
        normalX /= length;
        normalY /= length;

        // 计算反射向量
        double dotProduct = vx * normalX + vy * normalY;
        vx -= 2 * dotProduct * normalX;
        vy -= 2 * dotProduct * normalY;

        // 确保球不会嵌入线段内
        // 将球移到线段外侧
        // 假设要移出的距离为半径
//        double overlap = radius;
//        x += normalX * overlap;
//        y += normalY * overlap;
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
