package inkball.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import inkball.dao.ConfigAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author SanseYooyea
 */
@JsonAdapter(ConfigAdapter.class)
public class Config {
    private List<Level> levels;
    @SerializedName("score_increase_from_hole_capture")
    private Map<Color, Integer> scoreIncrement;
    @SerializedName("score_decrease_from_inkball_capture")
    private Map<Color, Integer> scoreDecrement;

    public Config(List<Level> levels, Map<Color, Integer> scoreIncrement, Map<Color, Integer> scoreDecrement) {
        this.levels = levels;
        this.scoreIncrement = scoreIncrement;
        this.scoreDecrement = scoreDecrement;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Map<Color, Integer> getScoreIncrement() {
        return scoreIncrement;
    }

    public Map<Color, Integer> getScoreDecrement() {
        return scoreDecrement;
    }
}
