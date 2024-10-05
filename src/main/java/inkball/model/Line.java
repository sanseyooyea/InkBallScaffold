package inkball.model;

/**
 * @author SanseYooyea
 */
public class Line {
    private float x1, y1, x2, y2; // 线的起始和结束坐标

    public Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isBallColliding(Ball ball) {
        // 计算球是否与该线段碰撞
    }

    public void reflectBall(Ball ball) {
        // 反射球的轨迹
    }

    // Getters and Setters
}
