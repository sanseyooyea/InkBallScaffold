package inkball.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class Layout {
    private final List<Ball> balls = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private final List<Hole> holes = new ArrayList<>();
    private final List<Spawner> spawners = new ArrayList<>();

    public Layout(List<GameObject> gameObjects) {
        // 从游戏对象列表中提取球、墙、洞、生成器
        gameObjects.forEach(gameObject -> {
            if (gameObject.getType() == GameObjectType.BALL) {
                balls.add((Ball) gameObject);
            } else if (gameObject.getType() == GameObjectType.WALL) {
                walls.add((Wall) gameObject);
            } else if (gameObject.getType() == GameObjectType.HOLE) {
                holes.add((Hole) gameObject);
            } else if (gameObject.getType() == GameObjectType.SPAWNER) {
                spawners.add((Spawner) gameObject);
            }
        });
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
}
