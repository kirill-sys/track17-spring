package track.msgtest.messenger.net.handlers;

public class HandlingException extends  Exception{
    public HandlingException(String msg) {
        super(msg);
    }

    public HandlingException(Throwable ex) {
        super(ex);
    }
}
