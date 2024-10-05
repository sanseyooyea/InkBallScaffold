package inkball.model;

/**
 * @author SanseYooyea
 */
public class Ball extends GameObject implements Updatable {
    private float vx, vy;
    private Color color;
    private boolean isCaptured;

    public Ball(double x, double y, Color color) {
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

    public void checkCollision(Line line) {
        // 检查与玩家画线的碰撞
    }

    public void checkHoleCollision(Hole hole) {
        // 检查是否接近洞口
    }

    public boolean isInHole(Hole hole) {
        // 检查是否进入洞口
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCaptured() {
        return isCaptured;
    }
}
