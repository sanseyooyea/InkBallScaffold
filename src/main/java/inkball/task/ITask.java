package inkball.task;

/**
 * @author SanseYooyea
 */
public interface ITask extends Runnable {
    /**
     * 将任务提交至线程管理器
     */
    void submit();
}
