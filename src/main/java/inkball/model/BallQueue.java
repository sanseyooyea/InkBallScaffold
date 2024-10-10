package inkball.model;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author SanseYooyea
 */
public class BallQueue {
    private final Queue<Color> ballQueue;

    public BallQueue(List<Color> balls) {
        Collections.shuffle(balls);
        ballQueue = new ArrayBlockingQueue<>(balls.size());
        ballQueue.addAll(balls);
    }

    public Color getBall() {
        return ballQueue.poll();
    }

    public boolean isEmpty() {
        return ballQueue.isEmpty();
    }
}
