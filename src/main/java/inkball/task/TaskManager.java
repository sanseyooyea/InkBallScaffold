package inkball.task;

import work.microhand.randomdungeon.model.time.Time;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * @author SanseYooyea
 */
public enum TaskManager {
    /**
     * 单例
     */
    INSTANCE;

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

    private final Map<String, ScheduledFuture<?>> taskMap = new HashMap<>();

    public void execute(AbstractTask task) {
        executor.execute(task);
    }

    public void schedule(AbstractTask task, Time time) {
        taskMap.put(task.getName(), executor.schedule(task, time.getTime(), time.getUnit()));
    }

    public void scheduleAtFixedRate(AbstractTask task, long initialDelay, Time period) {
        taskMap.put(task.getName(), executor.scheduleAtFixedRate(task, initialDelay, period.getTime(), period.getUnit()));
    }

    public void scheduleWithFixedDelay(AbstractTask task, long initialDelay, Time delay) {
        taskMap.put(task.getName(), executor.scheduleWithFixedDelay(task, initialDelay, delay.getTime(), delay.getUnit()));
    }

    public void cancel(String name) {
        ScheduledFuture<?> future = taskMap.get(name);
        if (future != null) {
            future.cancel(false);
            taskMap.remove(name);
        }
    }

    public void cancelForce(String name) {
        ScheduledFuture<?> future = taskMap.get(name);
        if (future != null) {
            future.cancel(true);
            taskMap.remove(name);
        }
    }

    public void cancelAll() {
        taskMap.values().forEach(future -> future.cancel(false));
    }

    public void cancelAllForce() {
        taskMap.values().forEach(future -> future.cancel(true));
    }

    public void shutdown() {
        executor.shutdown();
    }
}
