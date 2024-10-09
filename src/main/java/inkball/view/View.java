package inkball.view;

import processing.core.PApplet;

/**
 * @author SanseYooyea
 */
public abstract class View {
    protected PApplet sketch;
    protected GameView gameView;
    protected int width, height;

    public View(PApplet sketch, GameView gameView, int width, int height) {
        this.sketch = sketch;
        this.gameView = gameView;
        this.width = width;
        this.height = height;
    }

    protected void setTitle(String title) {
        if (gameView != null) {
            gameView.setTitle(title);
        }
    }

    public void init() {
    }

    public void update() {
    }

    public void mousePressed(int mouseX, int mouseY) {
    }

    public void mouseReleased(int mouseX, int mouseY) {
    }

    public void keyPressed(int keyCode) {
    }
}