/** 
* File: Vertex.java
* Author: Emmett Burns
*Date: 04/30/2018
*/

import java.util.Comparator;
import java.util.HashMap;
import java.util.Collection;
import java.awt.Graphics;
import java.awt.Color;


enum Direction {NORTH, SOUTH, EAST, WEST};

public class Vertex extends Agent implements Comparable<Vertex>{

	private HashMap<Direction, Vertex> neighbors;
	private int cost;
	private boolean marked;
	private boolean visible;
	private String id;
	
	//constructs a Vertex given just cost
	public Vertex(int cost){
		super(0, 0);
		neighbors = new HashMap<Direction, Vertex>();
		this.cost = cost;
		marked = false;
		visible = false;
		id = "";
	} 
	
	//constructs a Vertex given cost and a position
	public Vertex(int cost, int xPos, int yPos, String id){
		super(xPos, yPos);
		neighbors = new HashMap<Direction, Vertex>();
		this.cost = cost;
		marked = false;
		visible = false;
		this.id = id;
	} 

	//returns the opposite Direction as the input
	public Direction opposite(Direction d){
		if(d == Direction.NORTH) return Direction.SOUTH;
		else if(d == Direction.SOUTH) return Direction.NORTH;
		else if(d == Direction.EAST) return Direction.WEST;
		else return Direction.EAST;
	}

	//stores the edge and vertex in the map
	public void connect(Vertex other, Direction dir){
		if(neighbors.containsKey(dir)) System.out.println("Vertex already exists in given location");
		else neighbors.put(dir, other);
	}

	//returns the Vertex in the specified direction or nullif there is nosuch Vertex
	public Vertex getNeighbor(Direction dir){
		if(neighbors.containsKey(dir)){
			return neighbors.get(dir);
		}
		else return this;
	}

	//returns an Collection of all the object's neighbors
	public Collection<Vertex> getNeighbors(){
		return neighbors.values();
	}
	
	//sets the marked of the Vertex
	public void setMarked(boolean status){
		this.marked = status;
	}

	//returns the marked status 
	public boolean getMarked(){
		return this.marked;
	}

	//sets the cost of the Vertex to the given int
	public void setCost(int c){
		this.cost = c;
	}

	//returns the cost
	public int cost(){
		return this.cost;
	}

	//returns the difference between the cost f this Vertex and the Vertx passed in
	public int compareTo(Vertex other){
		return this.cost() - other.cost();
	}

	//sets the visible status of the current Vertex
	public void setVisible(boolean vis){
		this.visible = vis;
	}

	//returns the identifier String
	public String getId(){
		return this.id;
	}
	
	//resests all the fields of the Vertex
	public void reset(){
		this.neighbors.clear();
		this.cost = 0;
		this.marked = false;
		this.visible = false;
	}

	//draws the room with doors in the direction of other rooms
	public void draw(Graphics g, int x0, int y0, int scale) {
		if (!this.visible) return;
		
		int xpos = x0 + this.x*scale;
		int ypos = y0 + this.y*scale;
		int border = 2;
		int half = scale / 2;
		int eighth = scale / 8;
		int sixteenth = scale / 16;
		
		// draw rectangle for the walls of the cave
		if (this.cost <= 2)
			// wumpus is nearby
			g.setColor(Color.red);
		else
			// wumpus is not nearby
			g.setColor(Color.black);
			
		g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
		
		// draw doorways as boxes
		g.setColor(Color.black);
		if (this.neighbors.containsKey(Direction.NORTH))
			g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
		if (this.neighbors.containsKey(Direction.SOUTH))
			g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), eighth, eighth + sixteenth);
		if (this.neighbors.containsKey(Direction.WEST))
			g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
		if (this.neighbors.containsKey(Direction.EAST))
			g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
	}

	//returns a String indicating the cost, number of neighbors and marked status
	public String toString(){
		String str = "";
		Collection<Vertex> neighbList = this.getNeighbors();
		str += "number of neighbors: " + neighbList.size() + "\n";
		//the following line returns the toStrings of all the neighbors
		//str += "neighbors: " + neighbList.toString() + "\n"; 
		str += "id: " + this.id + "\n";
		str += "marked status: " + this.getMarked() + "\n";
		str += "cost: " + cost + "\n";
		str += "(x, y): (" + this.x + ", " + this.y + " )" + "\n \n";
		return str;
	}

	//tests the methods of the Vertex class
	public static void main(String[] args){
		Vertex v0 = new Vertex(1);
		Vertex v1 = new Vertex(2);
		Vertex v2 = new Vertex(3);
		System.out.println("testing opposite(). should be South: " + v0.opposite(Direction.NORTH));
		System.out.println("testing opposite(). should be North: " + v0.opposite(Direction.SOUTH));
		System.out.println("testing opposite(). should be West: " + v0.opposite(Direction.EAST));
		System.out.println("testing opposite(). should be East: " + v0.opposite(Direction.WEST));
		v0.connect(v1, Direction.NORTH);
		v0.connect(v2, Direction.NORTH);
		v0.connect(v2, Direction.EAST);
		System.out.println(v0.toString());
	}
}