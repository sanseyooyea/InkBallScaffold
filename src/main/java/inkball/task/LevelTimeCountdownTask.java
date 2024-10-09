package inkball.task;

import inkball.model.Game;

/**
 * @author SanseYooyea
 */
public class LevelTimeCountdownTask extends AbstractTask {
    private Game game;
    private int timeLeft;

    public LevelTimeCountdownTask(Game game, int timeLeft) {
        super("level-time-countdown");
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    @Override
    public void submit() {

    }

    @Override
    public void run() {
        if (timeLeft > 0) {
            timeLeft--;
            return;
        }

        game.end();
    }
}
