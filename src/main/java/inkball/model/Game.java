package inkball.model;

import inkball.App;
import inkball.service.IConfigService;
import inkball.service.IInkLineService;
import inkball.service.impl.ConfigServiceImpl;
import inkball.service.impl.InkLineServiceImpl;
import processing.core.PApplet;

import java.util.List;

/**
 * @author SanseYooyea
 */
public class Game {
    private double score;
    private final IConfigService configService;
    private final IInkLineService inkLineService;
    private int currentLevelNumber;
    private Level currentLevel;
    private float timeLeft;
    private BallQueue ballQueue;
    private float ballSpawnTime;
    private boolean isPaused;
    private boolean end;

    public Game(PApplet sketch) {
        // 初始化游戏，加载第一个关卡
        configService = new ConfigServiceImpl();
        inkLineService = new InkLineServiceImpl(sketch);
        currentLevelNumber = 1;
        currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
        timeLeft = currentLevel.getTime();
        ballQueue = new BallQueue(currentLevel.getBalls());
        ballSpawnTime = currentLevel.getSpawnInterval();
        this.score = 0;
        this.isPaused = false;
        end = false;
    }

    public List<Color> getLeftBalls() {
        return ballQueue.getLeftBalls();
    }

    public double getScore() {
        return score;
    }

    public void addScore(double score) {
        // 增加分数
        this.score += currentLevel.getScoreIncrementModifier() * score;
    }

    public void subtractScore(double score) {
        // 减少分数
        this.score -= currentLevel.getScoreDecrementModifier() * score;
    }

    public IConfigService getConfigService() {
        return configService;
    }

    public IInkLineService getInkLineService() {
        return inkLineService;
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

    public float getTimeLeft() {
        return timeLeft;
    }

    public void update() {
        if (end) {
            return;
        }

        if (isPaused) {
            return;
        }

        currentLevel.update();

        if (currentLevel.getLayout().isAllBallsCaptured() && ballQueue.isEmpty()) {
            nextLevel();
            return;
        }

        timeLeft -= (float) 1 / App.FPS;
        if (timeLeft <= 0) {
            end();
        }

        ballSpawnTime -= (float) 1 / App.FPS;
        if (ballSpawnTime <= 0) {
            for (Spawner spawner : currentLevel.getLayout().getSpawners()) {
                if (ballQueue.isEmpty()) {
                    break;
                }

                Color color = ballQueue.getBall();
                if (color != null) {
                    Ball ball = spawner.spawnBall(color);
                    currentLevel.getLayout().getBalls().add(ball);
                }
            }

            ballSpawnTime = currentLevel.getSpawnInterval();
        }
    }

    public void pause() {
        isPaused = !isPaused;
    }

    public void restartLevel() {
        currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
        ballQueue = new BallQueue(currentLevel.getBalls());
        timeLeft = currentLevel.getTime();
        inkLineService.stopDraw();
        inkLineService.clear();
    }

    public void nextLevel() {
        if (currentLevelNumber < configService.getConfig().getLevels().size()) {
            currentLevelNumber++;
            currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
            timeLeft = currentLevel.getTime();
        } else {
            end();
        }
    }

    public void end() {
        end = true;
    }

    public void restart() {
        currentLevelNumber = 1;
        currentLevel = configService.getConfig().getLevels().get(currentLevelNumber).clone();
        ballQueue = new BallQueue(currentLevel.getBalls());
        timeLeft = currentLevel.getTime();
        end = false;
    }
}
