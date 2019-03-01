/** 
* File: Agent.java
* Author: Emmett Burns
*Date: 05/04/2018
*/

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Agent{
	protected int x;
	protected int y;
	private Graphics g;
	private Color c;

	//CheckoutAgent constructor
	public Agent(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
	}

	//does nothing for the basic game
	public void updateState(){}

	//does nothing for the basic game
	public void isNeighbor(){}
	
	//does nothing
	public void setVisible(boolean bool){ System.out.println("Agent");}

	//returns the x position on the grid
	public int getX(){ return this.x; }
	//returns the y position on the grid
	public int getY(){ return this.y; }
		
	//draws the CheckoutAgent and all of the customers in its queue
	public void draw(Graphics g, int x0, int y0, int scale){
		Color c0 = new Color(128, 0, 255);
		g.setColor(c0);
		g.drawRect(this.x*scale, this.y*scale, scale, scale);
	}
}