package inkball.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class Layout implements Cloneable {
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

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public List<Spawner> getSpawners() {
        return spawners;
    }

    public void update() {
        // 更新所有球的位置，检查碰撞
        for (Ball ball : balls) {
            ball.update();
            checkCollisions(ball);
        }
    }

    private void checkCollisions(Ball ball) {
        // 检查球与墙壁、洞口的碰撞
        walls.forEach(ball::checkCollision);
        holes.forEach(ball::checkHoleCollision);
    }

    public boolean isAllBallsCaptured() {
        // 检查所有球是否都被洞吸收
        return balls.stream().allMatch(Ball::isCaptured);
    }

    @Override
    public Layout clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        List<GameObject> gameObjects = new ArrayList<>();
        balls.forEach(ball -> gameObjects.add(ball.clone()));
        walls.forEach(Wall::resetColor);
        gameObjects.addAll(walls);
        gameObjects.addAll(holes);
        gameObjects.addAll(spawners);
        return new Layout(gameObjects);
    }
}
