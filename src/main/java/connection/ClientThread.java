package connection;

import org.json.JSONException;
import org.json.JSONObject;

import controller.ControllerSnakeGame;
import model.SnakeGame;
import view.PanelSnakeGame;
import view.ViewLobby;
import view.ViewSnakeGame;

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
    private ControllerSnakeGame controller;
    private ViewSnakeGame vue;

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
                System.out.println(json);
                if(this.token == null){
                    this.token = json.getString("token");
                }
                lobby.addChatMessage(json.getString("message"));
            }
        } catch (SocketException e) {
        } catch (IOException exception) {
            System.out.println(exception);
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                cin.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
    public void clientSnake(ControllerSnakeGame controllerSnakeGame,SnakeGame snakeGame,PanelSnakeGame panelSnakeGame) {
    	ViewSnakeGame viewSnakeGame = new ViewSnakeGame(controllerSnakeGame, snakeGame, panelSnakeGame);
    }
}
