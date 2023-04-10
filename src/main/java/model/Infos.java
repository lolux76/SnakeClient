package model;

import java.io.Serializable;
import java.util.ArrayList;

import agent.Snake;
import item.Item;

public class Infos implements Serializable{
	ArrayList<Snake> snakes;
	ArrayList<Item> items;
	public Infos(){
		snakes=new ArrayList<Snake>();
		items=new ArrayList<Item>();
	}
	public void addSnake(Snake snake) {
		snakes.add(snake);
	}
	public void addItem(Item item) {
		items.add(item);
	}
	public ArrayList<Snake> getSnakes(){
		return snakes;
	}
	public ArrayList<Item> getItems(){
		return items;
	}
}
