/** 
* File: Hunter.java
* Author: Emmett Burns
*Date: 05/05/2018
*/

import java.awt.Graphics;
import java.awt.Color;

public class Hunter extends Agent{

	//Hunter fields
	private Vertex curPos;
	private int curRow;
	private int curCol;
	private boolean armed;

	//Hunter constructor
	public Hunter(int xPos, int yPos, Vertex vert){
		super(xPos, yPos);
		curPos = vert;
		armed = false;
		curPos.setVisible(true);
	}
	
	//sets the current position of the hunter
	public void setPos(int xPos, int yPos, Vertex v){
		this.x = xPos;
		this.y = yPos;
		this.curPos = v;
		curPos.setVisible(true);
	}

	//returns the armed status of the Hunter
	public boolean isArmed(){
		return armed;
	}

	//sets the armed status of the Hunter
	public void setArmed(boolean status){
		armed = status;
	}

	//changes the Vertex on which the Hunter resides
	public void move(Direction dir){
		if(dir == Direction.NORTH){
			if(   (curPos==curPos.getNeighbor(Direction.NORTH)) == false  ) {
				curPos = curPos.getNeighbor(Direction.NORTH);
				this.y--;
			}
		}
		else if(dir == Direction.SOUTH){
			if(   (curPos==curPos.getNeighbor(Direction.SOUTH)) == false  ) {
				curPos = curPos.getNeighbor(Direction.SOUTH);
				this.y++;
			}
		}
		else if(dir == Direction.EAST){
			if(   (curPos==curPos.getNeighbor(Direction.EAST)) == false  ) {
				curPos = curPos.getNeighbor(Direction.EAST);
				this.x++;
			}
		}
		else{
			if(   (curPos==curPos.getNeighbor(Direction.WEST)) == false  ) {
				curPos = curPos.getNeighbor(Direction.WEST);
				this.x--;
			}
		}
	}

	//returns the Vertex on which the Hunter currently resides
	public Vertex getLocation(){
		return curPos;
	}
	
	//returns the x-coordinate of the Hunter
	public int getX(){
		return this.x;
	}
	
	//returns the y-coordinate of the Hunter
	public int getY(){
		return this.y;
	}

	//returns the neighbor in the given direction
	public Vertex getNeighbor(Direction dir){
		if(dir == Direction.NORTH) return curPos.getNeighbor(Direction.NORTH);
		else if(dir == Direction.SOUTH) return curPos.getNeighbor(Direction.SOUTH);
		else if(dir == Direction.EAST) return curPos.getNeighbor(Direction.EAST);
		else return curPos.getNeighbor(Direction.WEST);
	}

	//returns the toString of the current Vertex
	public String toString(){ return curPos.toString(); }

	//draws the room with the hunter in it
	public void draw(Graphics g, int x0, int y0, int scale) {

		curPos.setVisible(true);

		int xpos = x0 + this.x*scale;
		int ypos = y0 + this.y*scale;

		int half = scale/2;
		int fourth = scale/4;

		curPos.draw(g, this.x, this.y, scale);
		//if armed draws red else draws green
		if(this.armed == true) g.setColor(Color.red);
		else g.setColor(Color.green);
		g.fillOval( xpos + fourth, ypos + fourth, half, half );
	}
}