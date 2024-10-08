package inkball.dao;

import com.google.gson.*;
import inkball.model.Color;
import inkball.model.Config;
import inkball.model.Layout;
import inkball.model.Level;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SanseYooyea
 */
public class ConfigAdapter implements JsonDeserializer<Config> {
    @Override
    public Config deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<Level> levels = new ArrayList<>();
        JsonArray levelsJsonArray = jsonObject.getAsJsonArray("levels");
        levelsJsonArray.forEach(levelJson -> {
            JsonObject levelJsonObject = levelJson.getAsJsonObject();
            String layoutPath = levelJsonObject.get("layout").getAsString();
            File layoutFile = new File(layoutPath);
            if (!layoutFile.exists()) {
                throw new IllegalArgumentException("Layout file not found: " + layoutPath);
            }

            Layout layout = new LayoutDAO().load(layoutFile);
            int time = levelJsonObject.get("time").getAsInt();
            int spawnInterval = levelJsonObject.get("spawn_interval").getAsInt();
            double scoreIncrementModifier = levelJsonObject.get("score_increase_from_hole_capture_modifier").getAsDouble();
            double scoreDecrementModifier = levelJsonObject.get("score_decrease_from_wrong_hole_modifier").getAsDouble();
            JsonArray ballsJsonArray = levelJsonObject.getAsJsonArray("balls");
            List<Color> balls = new ArrayList<>();
            ballsJsonArray.forEach(ballJson -> {
                String ballColor = ballJson.getAsString();
                balls.add(Color.getColor(ballColor));
            });

            Level level = new Level(layout, time, spawnInterval, scoreIncrementModifier, scoreDecrementModifier, balls);
            levels.add(level);
        });

        Map<Color, Integer> scoreIncrement = new HashMap<>();
        JsonObject scoreIncreaseFromHoleCapture = jsonObject.getAsJsonObject("score_increase_from_hole_capture");
        scoreIncreaseFromHoleCapture.entrySet().forEach(entry -> {
            Color color = Color.getColor(entry.getKey());
            int score = entry.getValue().getAsInt();
            scoreIncrement.put(color, score);
        });

        Map<Color, Integer> scoreDecrement = new HashMap<>();
        JsonObject scoreDecreaseFromWrongHole = jsonObject.getAsJsonObject("score_decrease_from_wrong_hole");
        scoreDecreaseFromWrongHole.entrySet().forEach(entry -> {
            Color color = Color.getColor(entry.getKey());
            int score = entry.getValue().getAsInt();
            scoreDecrement.put(color, score);
        });

        return new Config(levels, scoreIncrement, scoreDecrement);
    }
}
