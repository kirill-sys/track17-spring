package track.msgtest.messenger.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.msgtest.messenger.store.MessageStore;
import track.msgtest.messenger.store.UserStore;
import track.msgtest.messenger.teacher.client.MessengerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class MessengerServer {
    static Logger log = LoggerFactory.getLogger(MessengerServer.class);
    private static int port;

    public static void setPort(int port) {
        MessengerServer.port = port;
    }

    public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//            throw new IllegalArgumentException("Parameter: <Port>");
//        }
//        port = Integer.parseInt(args[0]);
//
//        ServerSocket serverSocket = new ServerSocket(port);
//        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
//        DatabaseManager databaseManager = new DatabaseManager();
//        UserStore userStore = new PostgresUserStore(databaseManager.connect());
//        MessageStore messageStore = new PostgresMessageStore(databaseManager.connect());
//
//        while (true) {
//            Socket clntSock = serverSocket.accept();
//
//            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
//            log.info("Handling client at " + clientAddress);
//            pool.execute(new Session(clntSock, new StringProtocol(), userStore, messageStore));
//            /*
//            InputStream in = clntSock.getInputStream();
//            OutputStream out = clntSock.getOutputStream();
//            while ((recvMsgSize = in.read(recieveBuf)) != -1) {
//                out.write(recieveBuf, 0, recvMsgSize);
//            }
//            clntSock.close();
//            */
//        }
    }
}
