package controller;

import java.util.ArrayList;
import java.util.Observable;

import agent.Snake;
import item.Item;
import model.SnakeGame;
import strategy.AgentInput;
import model.InputMap;
import utils.AgentAction;
import view.PanelSnakeGame;
import view.ViewSnakeGame;
import view.ViewCommand;



public class ControllerSnakeGame extends AbstractController {

	

	private ViewSnakeGame viewSnakeGame;
	
	public ControllerSnakeGame(SnakeGame snakeGame) {
		
		this.game = snakeGame;
	}
	
	public ControllerSnakeGame() {
		

		String layoutName = "layouts/alone/smallNoWall_alone.lay";
		
		
		InputMap inputMap = null;
		
		try {
			inputMap = new InputMap(layoutName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		PanelSnakeGame panelSnakeGame = new PanelSnakeGame(inputMap.getSizeX(), inputMap.getSizeY(), inputMap.get_walls(), inputMap.getStart_snakes(), inputMap.getStart_items());
		
		
		viewSnakeGame = new ViewSnakeGame(this, AgentInput.getUserInput(), panelSnakeGame);
		
		
	}
	
	public ViewSnakeGame getView() {
		return viewSnakeGame;
	}

}
