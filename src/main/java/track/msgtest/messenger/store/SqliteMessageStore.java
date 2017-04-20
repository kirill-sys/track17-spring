package track.msgtest.messenger.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.msgtest.messenger.User;
import track.msgtest.messenger.messages.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteMessageStore implements MessageStore {
    static Logger log = LoggerFactory.getLogger(SqliteMessageStore.class);

    private DbManager dbManager;

    public SqliteMessageStore(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Long> getChatsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Long> getMessagesFromChat(Long chatId) {
        return null;
    }

    @Override
    public Message getMessageById(Long messageId) {
        return null;
    }

    @Override
    public List<Long> getUsersIdFromChat(Long chatId) {
        try {
            String query = "SELECT user_id FROM chat_membership WHERE chat_id = '" + chatId.toString() + "';";
            Statement statement = dbManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<Long> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSet.getLong(1));
            }
            return result;
        } catch (SQLException e) {
            log.error("Db error, {}", e);
            return null;
        }

    }

    @Override
    public void addMessage(Long chatId, Message message) {

    }

    @Override
    public void addUserToChat(Long userId, Long chatId) {

    }
}
