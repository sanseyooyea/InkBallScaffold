package inkball.model;

import inkball.service.IConfigService;
import inkball.service.impl.ConfigServiceImpl;

/**
 * @author SanseYooyea
 */
public class Game {
    private final int score;
    private final IConfigService configService;
    private int currentLevelNumber;
    private Level currentLevel;
    private final BallQueue ballQueue;
    private boolean isPaused;
    private boolean end;

    public Game() {
        // 初始化游戏，加载第一个关卡
        configService = new ConfigServiceImpl();
        currentLevelNumber = 1;
        currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
        ballQueue = new BallQueue(currentLevel.getBalls());
        this.score = 0;
        this.isPaused = false;
        end = false;
    }

    public int getScore() {
        return score;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isEnd() {
        return end;
    }

    public void update() {
        if (end) {
            return;
        }

        if (!isPaused) {
            currentLevel.update();
        }

        if (currentLevel.getLayout().isAllBallsCaptured()) {
            nextLevel();
        }
    }

    public void pause() {
        isPaused = !isPaused;
    }

    public void restartLevel() {
        currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
    }

    public void nextLevel() {
        if (currentLevelNumber < configService.getConfig().getLevels().size()) {
            currentLevelNumber++;
            currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
        } else {
            end();
        }
    }

    public void end() {
        end = true;
    }
}
