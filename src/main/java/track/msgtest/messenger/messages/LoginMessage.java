package track.msgtest.messenger.messages;

/**
 *
 */
public class LoginMessage extends Message {
    private String login;
    private String password;

    public LoginMessage(String login, String password) {
        type = Type.MSG_LOGIN;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
