package inkball.task;

/**
 * @author SanseYooyea
 */
public abstract class AbstractTask implements ITask {
    protected final String name;

    public AbstractTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
