package network;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host extends Thread{
    private String highscoreList;

    public String getHighscoreList() {
        return highscoreList;
    }
@Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(4711)) {
            System.out.println("Server is listening on port " + 4711);

            //controller
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                String ipAddress = inetAddress.getHostAddress();

                System.out.println("ipAdresse: " + ipAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, this).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToHighscoreList(String object) {
        highscoreList = highscoreList + object;
    }


}
