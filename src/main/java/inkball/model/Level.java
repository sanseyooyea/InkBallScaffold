package inkball.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author SanseYooyea
 */
public class Level {
    private final Layout layout;
    private final int time;
    @SerializedName("spawn_interval")
    private final int spawnInterval;
    @SerializedName("score_increase_from_hole_capture_modifier")
    private final double scoreIncrementModifier;
    @SerializedName("score_decrease_from_wrong_hole_modifier")
    private final double scoreDecrementModifier;
    private final List<String> balls;

    public Level(Layout layout, int time, int spawnInterval, double scoreIncrementModifier, double scoreDecrementModifier, List<String> balls) {
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

    public List<String> getBalls() {
        return balls;
    }
}
