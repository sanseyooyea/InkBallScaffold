package inkball.model;

/**
 * @author SanseYooyea
 */
public class Spawner extends GameObject {
    protected Spawner(double x, double y) {
        super(x, y);
    }

    public Ball spawnBall(Color color) {
        // 从生成器位置生成一个新的球，并随机分配颜色和速度
        return new Ball(x, y, color);
    }
}
