package inkball.model;

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
        return ball.getColor() == color;
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }
}
