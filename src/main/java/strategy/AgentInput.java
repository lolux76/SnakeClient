package strategy;

import utils.AgentAction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class AgentInput extends Observable implements KeyListener{

    private static AgentInput instance;
    private AgentAction lastAction;

    private AgentInput(){
        lastAction = AgentAction.MOVE_LEFT;
    }

    public static AgentInput getUserInput() {
        if(instance == null){
            instance = new AgentInput();
        }
        return instance;
    }

    /**
     * @param keyEvent
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_Q) {
            lastAction = AgentAction.MOVE_LEFT;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            lastAction = AgentAction.MOVE_RIGHT;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_Z) {
            lastAction = AgentAction.MOVE_UP;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            lastAction = AgentAction.MOVE_DOWN;
        }

        setChanged();
        notifyObservers(lastAction);
    }

    /**
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public AgentAction getLastInput(){
        return lastAction;
    }
}
