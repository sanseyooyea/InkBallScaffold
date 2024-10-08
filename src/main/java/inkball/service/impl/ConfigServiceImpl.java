package inkball.service.impl;

import com.google.gson.Gson;
import inkball.dao.FileUtils;
import inkball.model.Config;
import inkball.service.IConfigService;

import java.io.File;

/**
 * @author SanseYooyea
 */
public class ConfigServiceImpl implements IConfigService {
    private final String CONFIG_PATH = "config.json";
    private final Config config;

    public ConfigServiceImpl() {
        File file = new File(CONFIG_PATH);
        if (!file.exists()) {
            throw new IllegalArgumentException("Config file not found.");
        }

        String content = FileUtils.readFile(new File(CONFIG_PATH));
        if (content == null) {
            throw new IllegalArgumentException("Config file is empty.");
        }

        this.config = new Gson().fromJson(content, Config.class);
    }

    @Override
    public Config getConfig() {
        return config;
    }
}
