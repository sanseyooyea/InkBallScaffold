package inkball;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class App extends PApplet {

    public static final int CELLSIZE = 32; //8;
    public static final int CELLHEIGHT = 32;

    public static final int CELLAVG = 32;
    public static final int TOPBAR = 64;
    public static final int BOARD_HEIGHT = 20;
    public static final int INITIAL_PARACHUTES = 1;
    public static final int FPS = 30;
    public static int WIDTH = 576;
    public static final int BOARD_WIDTH = WIDTH / CELLSIZE;
    public static int HEIGHT = 640;
    public static Random random = new Random();
    public String configPath;

    // Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
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
        frameRate(FPS);
        //See PApplet javadoc:
        //loadJSONObject(configPath)
        // the image is loaded from relative path: "src/main/resources/inkball/..."
        PImage result = loadImage(URLDecoder.decode(this.getClass().getResource("ball0" + ".png").getPath(), StandardCharsets.UTF_8));
        image(result, 0, 0);
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {

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
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // add line segments to player-drawn line object if left mouse button is held

        // remove player-drawn line object if right mouse button is held
        // and mouse position collides with the line
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {


        //----------------------------------
        //display Board for current level:
        //----------------------------------
        //TODO

        //----------------------------------
        //display score
        //----------------------------------
        //TODO

        //----------------------------------
        //----------------------------------
        //display game end message

    }

}
