package inkball.task;

import java.util.concurrent.TimeUnit;

/**
 * @author SanseYooyea
 */
public class Time {
    private long time;

    private TimeUnit unit;

    public Time() {
    }

    public Time(long time, TimeUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    public long getTime() {
        return time;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public Time time(long time) {
        this.time = time;
        return this;
    }

    public Time unit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }
}
