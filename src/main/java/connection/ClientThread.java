package connection;

import org.json.JSONObject;
import view.ViewLobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread implements Runnable{
    private Socket socket;
    private BufferedReader cin;
    private ViewLobby lobby;
    private String token;

    public ClientThread(Socket socket, ViewLobby lobby) throws IOException {
        this.socket = socket;
        this.cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.lobby = lobby;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = cin.readLine();
                JSONObject json = new JSONObject(message);
                if(this.token == null){
                    this.token = json.getString("token");
                }
                lobby.addChatMessage(message);
            }
        } catch (SocketException e) {
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                cin.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
