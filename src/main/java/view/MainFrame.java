package view;

import javax.swing.JFrame;

import controller.AbstractController;
import controller.ControllerSnakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class MainFrame extends JFrame implements KeyListener{

    ControllerSnakeGame controller;
    
    public MainFrame(ControllerSnakeGame controller){
        this.controller = controller;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}