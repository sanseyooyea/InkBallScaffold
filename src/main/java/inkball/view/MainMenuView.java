package inkball.view;

import processing.core.PApplet;

/**
 * @author SanseYooyea
 */
public class MainMenuView extends View {
    private Button start;
    private Button quit;

    private int textSize = 20;

    public MainMenuView(PApplet sketch, GameView gameView, int width, int height) {
        super(sketch, gameView, width, height);
    }

    @Override
    public void init() {
        setTitle("InkBall");

        start = new Button(sketch, "Start game", textSize, width / 4f, height / 5f, width / 2f, height / 10f);
        start.setAction(this::startGame);

        quit = new Button(sketch, "Quit", textSize, width / 4f, height / 5f * 3, width / 2f, height / 10f);
        quit.setAction(this.gameView::quit);
    }

    private void startGame() {
        if (gameView != null) {
            gameView.setView(1);
        }
    }

    @Override
    public void update() {
        start.draw();
        quit.draw();
    }

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        start.isPressed();
        quit.isPressed();
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        start.released();
        quit.released();
    }
}
