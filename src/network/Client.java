package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final static String SERVER_ADDRESS = "localhost";
    private long comparingTime = System.currentTimeMillis();

    public Client() {
    }

    public String sendDataToServer(String object) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, 4711);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            // Create a JSON string
            // Send the JSON string to the server
            writer.println(object);

            String responseObjekt = reader.readLine();
            String response = responseObjekt;

            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}