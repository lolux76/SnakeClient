package connection;

import org.json.JSONObject;

import controller.ControllerSnakeGame;
import strategy.AgentInput;
import model.Infos;
import model.InputMap;
import utils.AgentAction;
import utils.FeaturesItem;
import utils.FeaturesSnake;
import view.PanelSnakeGame;
import view.ViewSnakeGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ClientGameThread implements Runnable, Observer {
	private FeaturesSnake snake;
	private Socket socket;
	private String token;
	private ControllerSnakeGame controllerSnakeGame;
	public AgentAction lastAction;
	
	ClientGameThread(Socket socket, String token) throws Exception{
		this.socket = socket;
		this.token = token;
		AgentInput.getUserInput().addObserver(this);
		lastAction=AgentAction.MOVE_LEFT;
		controllerSnakeGame= new ControllerSnakeGame();
	}
	public void run() {
		
	}

	@Override
	public void update(Observable observable, Object o) {
		AgentAction action = (AgentAction) o;
		lastAction=action;
		try {
			sendMovement(action.ordinal());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void majAffichage(Infos infos) {
		controllerSnakeGame.getView().update(null, infos);
	}
	
	public void stop() {
		this.stop();
	}
	
	private void sendMovement(int action) throws IOException {
		System.out.println("test");
		PrintWriter cout = new PrintWriter(this.socket.getOutputStream(), true);
		JSONObject json = new JSONObject();
		json.put("token", this.token);
		json.put("direction", action);
		cout.println(json);
	}
}
