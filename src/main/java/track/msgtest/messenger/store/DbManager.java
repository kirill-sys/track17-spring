package track.msgtest.messenger.store;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
@Component
public class DbManager {
    private String url;
    private String login;
    private String password;

    private Connection connection;

    public DbManager() {
    }

    public void setUrl(String url) {
        this.url = "jdbc:sqlite:" + url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return password;
    }


    public Connection connect() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }
}
