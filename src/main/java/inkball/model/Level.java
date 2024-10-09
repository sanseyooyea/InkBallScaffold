package inkball.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author SanseYooyea
 */
public class Level implements Cloneable {
    private final Layout layout;
    private final int time;
    @SerializedName("spawn_interval")
    private final int spawnInterval;
    @SerializedName("score_increase_from_hole_capture_modifier")
    private final double scoreIncrementModifier;
    @SerializedName("score_decrease_from_wrong_hole_modifier")
    private final double scoreDecrementModifier;
    private final List<Color> balls;

    public Level(Layout layout, int time, int spawnInterval, double scoreIncrementModifier, double scoreDecrementModifier, List<Color> balls) {
        this.layout = layout;
        this.time = time;
        this.spawnInterval = spawnInterval;
        this.scoreIncrementModifier = scoreIncrementModifier;
        this.scoreDecrementModifier = scoreDecrementModifier;
        this.balls = balls;
    }

    public Layout getLayout() {
        return layout;
    }

    public int getTime() {
        return time;
    }

    public int getSpawnInterval() {
        return spawnInterval;
    }

    public double getScoreIncrementModifier() {
        return scoreIncrementModifier;
    }

    public double getScoreDecrementModifier() {
        return scoreDecrementModifier;
    }

    public List<Color> getBalls() {
        return balls;
    }

    public void update() {

    }

    @Override
    public Level clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return new Level(layout.clone(), time, spawnInterval, scoreIncrementModifier, scoreDecrementModifier, balls);
    }
}
