package connection;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import view.ViewLobby;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class Connection {
    private PrintWriter cout;
    private Socket socket;
    public Connection(String serverAddress, int port, String name, ViewLobby lobby) {
        String reply = name;
        try {
            socket = new Socket(serverAddress, port);
            this.cout = new PrintWriter(socket.getOutputStream(), true);

            ClientThread clientThread = new ClientThread(socket, lobby);
            new Thread(clientThread).start(); // start thread to receive message
            cout.println(reply + ": has joined the game.");
            String message = (name + " : ");
            if (reply.equals("/logout")) {
                cout.println("/logout");
            }
            /*cout.println(message + reply);*/
            lobby.setConnected();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void sendMessage(String message, String token){
		this.cout.println(message);
    }
}
