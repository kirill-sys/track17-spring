package track.msgtest.messenger.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.msgtest.messenger.store.*;
import track.msgtest.messenger.teacher.client.MessengerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class MessengerServer {
    static Logger log = LoggerFactory.getLogger(MessengerServer.class);
    private static int port;
    private static int threadNum = 8;

    public static void setThreadNum(int threadNum) {
        MessengerServer.threadNum = threadNum;
    }

    public static void setPort(int port) {
        MessengerServer.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Parameter: <Port>");
        }
        port = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        DbManager dbManager = new DbManager();
        dbManager.setUrl("track.db");
        dbManager.connect();
        UserStore userStore = new SqliteUserStore(dbManager);
        MessageStore messageStore = new SqliteMessageStore(dbManager);

        while (true) {
            Socket clntSock = serverSocket.accept();

            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            log.info("Handling client at " + clientAddress);
            pool.execute(new Session(clntSock, new StringProtocol(), userStore, messageStore));
        }
    }
}
