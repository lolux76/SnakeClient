package connection;

import org.json.JSONObject;
import strategy.AgentInput;
import utils.AgentAction;
import utils.FeaturesItem;
import utils.FeaturesSnake;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ClientGameThread implements Runnable, Observer {
	private FeaturesSnake snake;
	private Socket socket;
	private String token;

	ClientGameThread(Socket socket, String token){
		this.socket = socket;
		this.token = token;

		AgentInput.getUserInput().addObserver(this);
	}
	public void run() {
		
	}

	@Override
	public void update(Observable observable, Object o) {
		AgentAction action = (AgentAction) o;
		try {
			sendMovement(action.ordinal());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void sendMovement(int action) throws IOException {
		PrintWriter cout = new PrintWriter(this.socket.getOutputStream(), true);
		JSONObject json = new JSONObject();
		json.put("token", this.token);
		json.put("direction", action);
		cout.println(json);
	}
}
