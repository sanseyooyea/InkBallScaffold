package inkball.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author SanseYooyea
 */
public class Config {
    private List<Layout> layouts;
    @SerializedName("score_increase_from_hole_capture")
    private Map<Color, Integer> scoreIncrement;
    @SerializedName("score_decrease_from_inkball_capture")
    private Map<Color, Integer> scoreDecrement;
}
