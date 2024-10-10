package inkball.model;

import inkball.App;
import org.jetbrains.annotations.NotNull;

/**
 * @author SanseYooyea
 */
public class Hole extends GameObject implements Colorable {
    @NotNull
    private final Color color;

    public Hole(double x, double y, @NotNull Color color) {
        super(GameObjectType.HOLE, x, y);
        this.color = color;
    }

    public boolean captureBall(Ball ball) {
        // 计算洞口和球的中心点
        double holeCenterX = getLeft() + App.CELL_SIZE;
        double holeCenterY = getTop() + App.CELL_SIZE;

        double ballCenterX = ball.getLeft() + ball.radius;
        double ballCenterY = ball.getTop() + ball.radius;

        // 计算球的半径
        double ballRadius = ball.radius;

        // 计算洞口的半径
        double holeRadius = App.CELL_SIZE;

        // 计算中心点之间的距离
        double distance = Math.sqrt(Math.pow(ballCenterX - holeCenterX, 2) + Math.pow(ballCenterY - holeCenterY, 2));

        // 判断球是否在洞口内（考虑半径）
        return distance < (holeRadius + ballRadius);
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }

    @Override
    public double getBottom() {
        return getTop() + 2 * App.CELL_SIZE;
    }

    @Override
    public double getRight() {
        return getLeft() + 2 * App.CELL_SIZE;
    }
}
