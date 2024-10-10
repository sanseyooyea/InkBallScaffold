package inkball.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author SanseYooyea
 */
public class Wall extends GameObject implements Colorable {
    @Nullable
    private Color color;
    @Nullable
    private Color originalColor;

    public Wall(double x, double y) {
        super(GameObjectType.WALL, x, y);
    }

    public Wall(double x, double y, @NotNull Color color) {
        super(GameObjectType.WALL, x, y);
        this.color = color;
        this.originalColor = color;
    }

    @Override
    @Nullable
    public Color getColor() {
        return color;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
    }

    public void resetColor() {
        this.color = originalColor;
    }
}
