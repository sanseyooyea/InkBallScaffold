package inkball;

import inkball.model.Game;
import inkball.model.GameObjectType;
import inkball.view.Image;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class App extends PApplet {

    public static final int CELL_SIZE = 32;
    public static final int TOP_BAR_HEIGHT = 64;
    public static final int FPS = 30;
    public static int WIDTH = 576;
    public static int HEIGHT = 640;
    private static App instance;
    private Game game;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        PApplet.main("inkball.App");
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player and map elements.
     */
    @Override
    public void setup() {
        surface.setTitle("Inkball");
        frameRate(FPS);
        game = new Game(this);
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKey() == 'p') {
            game.pause();
        } else if (event.getKey() == 'r') {
            if (game.isEnd()) {
                game.restart();
            } else {
                game.restartLevel();
            }
        } else if (event.getKey() == 'n') {
            game.nextLevel();
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // create a new player-drawn line object
        int button = e.getButton();
        if (button != LEFT) {
            return;
        }

        // start drawing a line
        game.getInkLineService().startDraw(mouseX, mouseY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // add line segments to player-drawn line object if left mouse button is held

        // remove player-drawn line object if right mouse button is held
        // and mouse position collides with the line
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // stop drawing a line
        game.getInkLineService().stopDraw();
    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {
        if (game.isPaused()) {
            return;
        }

        game.update();

        fill(206, 206, 206);
        rect(0, 0, WIDTH, TOP_BAR_HEIGHT);

        // draw queue
        fill(0, 0, 0);
        rect(8, 8, 160, 32);
        game.getLeftBalls().forEach(color -> image(Image.getImage(GameObjectType.BALL, color.getNumber()).getImage(), 8 + 32 * game.getLeftBalls().indexOf(color), 8));


        textSize(16);
        text(String.valueOf(game.getCurrentLevel().getScoreIncrementModifier()), 192, 40);

        textSize(20);
        text("Score: " + game.getScore(), 448, 20);
        // time取整
        text("Time: " + String.valueOf(game.getTimeLeft()).split("\\.")[0], 448, 40);

        // draw tiles
        for (int x = 0; x < WIDTH / CELL_SIZE; x++) {
            for (int y = 0; y < (HEIGHT - TOP_BAR_HEIGHT) / CELL_SIZE; y++) {
                image(Image.TILE.getImage(), (float) x * CELL_SIZE, TOP_BAR_HEIGHT + (float) y * CELL_SIZE);
            }
        }

        // draw balls
        game.getCurrentLevel().getLayout().getBalls().stream().filter(ball -> !ball.isCaptured()).forEach(ball -> image(Image.getImage(ball.getType(), ball.getColor().getNumber()).getImage(), (float) ball.getX() * CELL_SIZE, TOP_BAR_HEIGHT + (float) ball.getY() * CELL_SIZE));

        // draw walls
        game.getCurrentLevel().getLayout().getWalls().forEach(wall -> {
            PImage wallImage = Image.WALL0.getImage();
            if (wall.getColor() != null) {
                wallImage = Image.getImage(wall.getType(), wall.getColor().getNumber()).getImage();
            }

            image(wallImage, (float) wall.getX() * CELL_SIZE, TOP_BAR_HEIGHT + (float) wall.getY() * CELL_SIZE);
        });

        // draw holes
        game.getCurrentLevel().getLayout().getHoles().forEach(hole -> image(Image.getImage(hole.getType(), hole.getColor().getNumber()).getImage(), (float) hole.getX() * CELL_SIZE, TOP_BAR_HEIGHT + (float) hole.getY() * CELL_SIZE));

        // draw spawners
        game.getCurrentLevel().getLayout().getSpawners().forEach(spawner -> image(Image.getImage(spawner.getType()).getImage(), (float) spawner.getX() * CELL_SIZE, TOP_BAR_HEIGHT + (float) spawner.getY() * CELL_SIZE));

        game.getInkLineService().draw(mouseX, mouseY, pmouseX, pmouseY);
        game.getInkLineService().update();

        if (game.isEnd()) {
            fill(0, 0, 0);
            rect(160, 256, 192, 160);
            fill(255, 255, 255);
            textSize(20);
            text("Game Over", 208, 326);
        }
    }

    public Game getGame() {
        return game;
    }
}
