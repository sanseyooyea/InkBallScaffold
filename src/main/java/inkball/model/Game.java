package inkball.model;

/**
 * @author SanseYooyea
 */
public class Game {
    private Level currentLevel;
    private int score;
    private boolean isPaused;

    public Game(String configFilePath) {
        // 初始化游戏，加载第一个关卡

        this.score = 0;
        this.isPaused = false;
    }

    public void update() {
        if (!isPaused) {
            currentLevel.update();
        }
    }

    public void draw() {
        // 绘制游戏界面
    }

    public void pause() {
        isPaused = !isPaused;
    }

    public void restartLevel() {
        // 重新开始当前关卡
    }

    public void nextLevel() {
        // 加载下一个关卡
    }
}
