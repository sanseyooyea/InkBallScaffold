package inkball.model;

import java.util.List;

/**
 * @author SanseYooyea
 */
public class Layout {
    private List<GameObject> gameObjects;
    private List<Ball> balls;
    private List<Wall> walls;
    private List<Hole> holes;
    private List<Spawner> spawners;
    private int timer;

    public Layout(String configFilePath) {
        // 从配置文件读取布局并初始化关卡
    }

    public void update() {
        // 更新所有球的位置，检查碰撞
        for (Ball ball : balls) {
            ball.update();
            checkCollisions(ball);
        }
    }

    private void checkCollisions(Ball ball) {
        // 检查球与墙、线、洞的碰撞
    }

    public void spawnBall() {
        // 随机选择生成器生成新的球
    }

    // Getters and Setters
}
