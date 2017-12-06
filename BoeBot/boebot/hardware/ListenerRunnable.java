package boebot.hardware;

/**
 * Customized Runnable interface so we can add listeners to Runnables
 *
 * @author Paul Hobbel
 * @version 05-12-2017
 */
public interface ListenerRunnable<T> extends Runnable
{
    /**
     * Will update the current listener of this Runnable
     * 
     * @param listener The new listener
     */
    public void setListener(T listener);
}
