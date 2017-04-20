package track.msgtest.messenger.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.msgtest.messenger.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteUserStore implements UserStore {
    static Logger log = LoggerFactory.getLogger(SqliteUserStore.class);

    private DbManager dbManager;

    public SqliteUserStore(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUser(String login, String pass) throws SQLException {
        try {
            String query = "SELECT id, login, password FROM users WHERE login = '";
            query += login + "' AND password = '" + pass + "';";
//            log.info(dbManager.getUrl());
            Statement statement = dbManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User(login, pass);
                user.setId(resultSet.getLong(1));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error("Db error, {}", e);
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        try {
            String query = "SELECT id, login, password FROM Users WHERE id = '" + id.toString() + "';";
            ResultSet resultSet = dbManager.getConnection().createStatement().executeQuery(query);
            if (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3));
                user.setId(resultSet.getLong(1));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error("Db error, {}", e);
            return null;
        }
    }
}
