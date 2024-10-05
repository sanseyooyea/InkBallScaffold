package inkball.model;

/**
 * @author SanseYooyea
 */
public class Wall {
    private int x, y; // 墙的位置
    private int color; // 墙的颜色

    public Wall(int x, int y, int color) {
        this.x = x;
        this.y = y;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
