package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Host host;
    private Socket socket;

    public ClientHandler(Socket socket, Host host) {
        this.host = host;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            // Create input and output streams to read from and write to the client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read a message sent by the client
            String request = in.readLine();

            host.addToHighscoreList(request);

            // Send a response to the client
            String response = host.getHighscoreList();
            out.println(response);

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }


}