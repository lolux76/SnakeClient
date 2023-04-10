package connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import agent.Snake;
import controller.ControllerSnakeGame;
import item.Item;
import model.Infos;
import model.SnakeGame;
import utils.AgentAction;
import utils.ColorSnake;
import utils.ItemType;
import utils.Position;
import view.PanelSnakeGame;
import view.ViewLobby;
import view.ViewSnakeGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientThread implements Runnable{
    private Socket socket;
    private BufferedReader cin;
    private ViewLobby lobby;
    private String token;
    private ControllerSnakeGame controller;
    private ViewSnakeGame vue;
    private ClientGameThread clientGameThread;

    public ClientThread(Socket socket, ViewLobby lobby) throws IOException {
        this.socket = socket;
        this.cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.lobby = lobby;
        try {
			this.clientGameThread = new ClientGameThread(socket, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
                if(json.has("message")) {
                    lobby.addChatMessage(json.getString("message"));
                }
                if(json.has("fini")) {
                	clientGameThread.stop();
                }
                if(json.has("walls")) {
                	Infos infos=new Infos();
                	ArrayList<Position>position=new ArrayList<Position>();
                	for(Object items: json.getJSONArray("items")) {
                		JSONObject jsonBis=(JSONObject)items;
                		infos.addItem(new Item(jsonBis.getInt("x"),jsonBis.getInt("y"),ItemType.values()[jsonBis.getInt("itemType")]));
                	}
                	for(Object snakes: json.getJSONArray("snakes")) {
                		JSONObject jsonBis=(JSONObject)snakes;
                		for(int i=0;i<jsonBis.getJSONArray("x").length();i++) {
                			position.add(new Position(jsonBis.getJSONArray("x").getInt(i),jsonBis.getJSONArray("y").getInt(i)));
                		}
                		infos.addSnake(new Snake(position,clientGameThread.lastAction,0,ColorSnake.Green));
                		clientGameThread.majAffichage(infos);
                	}
                	clientGameThread.majAffichage(infos);
                }
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
}
