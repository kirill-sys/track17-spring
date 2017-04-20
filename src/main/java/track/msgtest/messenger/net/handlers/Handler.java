package track.msgtest.messenger.net.handlers;

import track.msgtest.messenger.messages.Message;
import track.msgtest.messenger.net.ProtocolException;
import track.msgtest.messenger.net.Session;

import java.io.IOException;
import java.sql.SQLException;

public interface Handler {
    void handle(Session session, Message msg) throws HandlingException, IOException, ProtocolException, SQLException;
}