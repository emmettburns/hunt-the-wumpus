/** 
* File: Landscape.java
* Author: Emmett Burns
*Date: 03/10/2018
*/

import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math;
import java.util.LinkedList;

public class Landscape{

	//Landscape fields
	private Graphics g;
	private int height;
	private int width;
	private int scale;
	private Math m;
	private LinkedList<Agent> foreground;
	private LinkedList<Agent> background;


	//Landscape constructor
	public Landscape(){
		height = 606;
		width = 606;
		scale = 100;
		foreground = new LinkedList<Agent>();
		background = new LinkedList<Agent>();
	}

	//returns height
	public int getHeight(){
		return height;
	}

	//returns width
	public int getWidth(){
		return width;
	}

	//adds the given vertex to the foreground
	public void addFore(Agent a){
		foreground.add(a);
	}

	//adds the given vertex to the backGround
	public void addBack(Agent a){
		background.add(a);
	}
	
	//resets the visibility status of the Vertexes
	public void restart(){
		for(Agent a : background){
			a.setVisible(false);
		}
	}

	//returns a string discribing the contents of the Landscape
	public void draw(Graphics g){
		//draws background
		for(Agent a : background){
			a.draw(g, a.getX(), a.getY(), 100);
		}
		//draws foreground
		for(Agent a : foreground){
			a.draw(g, a.getX(), a.getY(), 100);
		}
	}

	//tests the methods of the Landscape class
	public static void main(String[] args){
	}
}