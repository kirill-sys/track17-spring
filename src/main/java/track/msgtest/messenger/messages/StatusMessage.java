package track.msgtest.messenger.messages;

public class StatusMessage extends Message {
    private Type type = Type.MSG_STATUS;
    private boolean status;


    public StatusMessage(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Type getType() {
        return type;
    }
}
