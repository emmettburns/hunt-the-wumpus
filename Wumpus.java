/** 
* File: Wumpus.java
* Author: Emmett Burns
*Date: 05/05/2018
*/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Wumpus extends Agent{

	//Wumpus fields
	private Vertex home;
	//0 represents hiding. 1 represents victory pose. 2 represents dead.
	private int status;

	//Wumpus constructor
	public Wumpus(int xPos, int yPos, Vertex vert){
		super(xPos, yPos);
		this.home = vert;
	}

	//returns true of the inputed Vertex is the Wumpus' home
	public boolean isHome(Vertex vert){
		return home == vert;
	}

	//updates the status of the wumpus
	public void updateStatus(int newStat){
		this.status = newStat;
	}
	
	//returns the status of the Wumpus
	public int status(){
		return this.status;
	}
	
	//resets the wumpus to the given coordinates and Vertex;
	public void restart(int newX, int newY, Vertex newVert){
		this.x = newX;
		this.y = newY;
		this.home = newVert;
	}

	//hides until either the Wumpus kills the Hunter or the Hunter kills the Wumpus
	public void draw(Graphics g, int x0, int y0, int scale){
		if (this.status == 0) return;
		
		int half = scale/2;
		int fourth = scale/4;
		int eigth = scale/8;
		int sixteenth = scale/16;
		int small = scale/32;
		
		int xpos = x0 + this.x*scale;
		int ypos = y0 + this.y*scale;
		
		//eyes
		g.setColor(Color.black);
		if(this.status == 1){
			g.drawOval(xpos+(5*sixteenth), ypos+sixteenth, eigth, 5*sixteenth);
			g.drawOval(xpos+(9*sixteenth), ypos+sixteenth, eigth, 5*sixteenth);
			g.fillOval(xpos+(11*small), ypos+eigth, sixteenth, 3*sixteenth);
			g.fillOval(xpos+(19*small), ypos+eigth, sixteenth, 3*sixteenth);
		}
		else{
			g.drawLine(xpos+(5*sixteenth), ypos+(sixteenth), xpos+(7*sixteenth), ypos+(3*sixteenth) );
			g.drawLine(xpos+(5*sixteenth), ypos+(3*sixteenth), xpos+(7*sixteenth), ypos+(sixteenth) );
			g.drawLine(xpos+(9*sixteenth), ypos+(sixteenth), xpos+(11*sixteenth), ypos+(3*sixteenth) );
			g.drawLine(xpos+(9*sixteenth), ypos+(3*sixteenth), xpos+(11*sixteenth), ypos+(sixteenth) );
		}
		//face
		g.setColor(Color.gray);
		g.fillOval(xpos+eigth, ypos+(2*eigth), 3*fourth, 5*eigth);
		//mouth
		g.setColor(Color.black);
		g.fillOval(xpos+(3*sixteenth), ypos+(5*sixteenth), 21*small, 4*eigth);
		//teeth
		if(this.status == 1) g.setColor(Color.red);
		else g.setColor(Color.white);
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0] = xpos + (5*sixteenth);
		xPoints[1] = xpos + (6*sixteenth);
		xPoints[2] = xpos + (7*sixteenth);
		yPoints[0] = ypos + (11*small);
		yPoints[1] = ypos + (half);
		yPoints[2] = ypos + (10*small);
		g.fillPolygon(xPoints, yPoints, 3);
		if(this.status == 1){
			xPoints[0] = xpos + (11*sixteenth);
			xPoints[1] = xpos + (10*sixteenth);
			xPoints[2] = xpos + (9*sixteenth);
			yPoints[0] = ypos + (11*small);
			yPoints[1] = ypos + (half);
			yPoints[2] = ypos + (10*small);
			g.fillPolygon(xPoints, yPoints, 3);
			xPoints[0] = xpos + (5*sixteenth);
			xPoints[1] = xpos + (6*sixteenth);
			xPoints[2] = xpos + (7*sixteenth);
			yPoints[0] = ypos + (25*small);
			yPoints[1] = ypos + (5*eigth);
			yPoints[2] = ypos + (26*small);
			g.fillPolygon(xPoints, yPoints, 3);
			xPoints[0] = xpos + (11*sixteenth);
			xPoints[1] = xpos + (10*sixteenth);
			xPoints[2] = xpos + (9*sixteenth);
			yPoints[0] = ypos + (25*small);
			yPoints[1] = ypos + (5*eigth);
			yPoints[2] = ypos + (26*small);
			g.fillPolygon(xPoints, yPoints, 3);
		}
	}	
}