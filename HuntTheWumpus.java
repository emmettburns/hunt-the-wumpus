/** 
* File: HuntTheWumpus.java
* Author: Emmett Burns
*Date: 05/05/2018
*/

public class HuntTheWumpus{

	//HuntTheWumpus fields
	private Landscape scape;
	private LandscapeDisplay display;
	private Graph graph;
	private Hunter hunter;
	private Wumpus wumpus;
	private Vertex v0;
	private Vertex v1;
	private Vertex v2;
	private Vertex v3;
	private Vertex v4;
	private Vertex v5;
	private Vertex v6;
	private Vertex v7;
	private Vertex v8;
	private Vertex v9;
	private Vertex v10;
	private Vertex v11;
	private Vertex v12;
	private Vertex v13;
	private Vertex v14;
	private Vertex v15;
	private Vertex v16;
	private Vertex v17;
	private Vertex v18;
	private Vertex v19;
	private Vertex v20;
	private Vertex v21;
	private Vertex v22;
	private Vertex v23;
	private Vertex v24;
	private Vertex v25;
	private Vertex v26;
	private Vertex v27;
	private Vertex v28;
	private Vertex v29;
	private Vertex v30;
	private Vertex v31;
	private Vertex v32;
	private Vertex v33;
	private Vertex v34;
	private Vertex v35;
	
	//fields for coordinates adn Vertexes of wumpus and hunter
	private int wumpX;
	private int wumpY;
	private Vertex wumpVert;
	private int huntX;
	private int huntY;
	private Vertex huntVert;
	
	//tells the game when to exit
	private enum Playing { PLAY, STOP }
	private Playing play = Playing.PLAY;

	//HuntTheWumpus constructor
	public HuntTheWumpus(){

		//Vertexes constructed and put into graph
		v0 = new Vertex(0, 0, 0, "v0");
		v1 = new Vertex(0, 1, 0, "v1");
		v2 = new Vertex(0, 2, 0, "v2");
		v3 = new Vertex(0, 3, 0, "v3");
		v4 = new Vertex(0, 4, 0, "v4");
		v5 = new Vertex(0, 5, 0, "v5");
		v6 = new Vertex(0, 0, 1, "v6");
		v7 = new Vertex(0, 1, 1, "v7");
		v8 = new Vertex(0, 2, 1, "v8");
		v9 = new Vertex(0, 3, 1, "v9");
		v10 = new Vertex(0, 4, 1, "v10");
		v11 = new Vertex(0, 5, 1, "v11");
		v12 = new Vertex(0, 0, 2, "v12");
		v13 = new Vertex(0, 1, 2, "v13");
		v14 = new Vertex(0, 2, 2, "v14");
		v15 = new Vertex(0, 3, 2, "v15");
		v16 = new Vertex(0, 4, 2, "v16");
		v17 = new Vertex(0, 5, 2, "v17");
		v18 = new Vertex(0, 0, 3, "v18");
		v19 = new Vertex(0, 1, 3, "v19");
		v20 = new Vertex(0, 2, 3, "v20");
		v21 = new Vertex(0, 3, 3, "v21");
		v22 = new Vertex(0, 4, 3, "v22");
		v23 = new Vertex(0, 5, 3, "v23");
		v24 = new Vertex(0, 0, 4, "v24");
		v25 = new Vertex(0, 1, 4, "v25");
		v26 = new Vertex(0, 2, 4, "v26");
		v27 = new Vertex(0, 3, 4, "v27");
		v28 = new Vertex(0, 4, 4, "v28");
		v29 = new Vertex(0, 5, 4, "v29");
		v30 = new Vertex(0, 0, 5, "v30");
		v31 = new Vertex(0, 1, 5, "v31");
		v32 = new Vertex(0, 2, 5, "v32");
		v33 = new Vertex(0, 3, 5, "v33");
		v34 = new Vertex(0, 4, 5, "v34");
		v35 = new Vertex(0, 5, 5, "v35");
		
		//sets fields for Hunter and Wumpus coordinates and Vertexes
		this.wumpX = 4;
		this.wumpY = 2;
		this.wumpVert = v16;
		this.huntX = 0;
		this.huntY = 2;
		this.huntVert = v12;

		//integral fields
		scape = new Landscape();
		display = new LandscapeDisplay(scape, this);
		graph = new Graph();
		hunter = new Hunter(huntX, huntY, huntVert);
		wumpus = new Wumpus(wumpX, wumpY, wumpVert);

		//adds the agents to the appropriate foreground or background list
		scape.addBack(v0);
		scape.addBack(v1);
		scape.addBack(v2);
		scape.addBack(v3);
		scape.addBack(v4);
		scape.addBack(v5);
		scape.addBack(v6);
		scape.addBack(v7);
		scape.addBack(v8);
		scape.addBack(v9);
		scape.addBack(v10);
		scape.addBack(v11);
		scape.addBack(v12);
		scape.addBack(v13);
		scape.addBack(v14);
		scape.addBack(v15);
		scape.addBack(v16);
		scape.addBack(v17);
		scape.addBack(v18);
		scape.addBack(v19);
		scape.addBack(v20);
		scape.addBack(v21);
		scape.addBack(v22);
		scape.addBack(v23);
		scape.addBack(v24);
		scape.addBack(v25);
		scape.addBack(v26);
		scape.addBack(v27);
		scape.addBack(v28);
		scape.addBack(v29);
		scape.addBack(v30);
		scape.addBack(v31);
		scape.addBack(v32);
		scape.addBack(v33);
		scape.addBack(v34);
		scape.addBack(v35);

		scape.addFore(hunter);
		scape.addFore(wumpus);

		//connects the Vertexes of the graph
		this.levelOne();
	}
	
	//resets the current level
	public void restart(){
		scape.restart();
		wumpus.restart(this.wumpX, this.wumpY, this.wumpVert);
		wumpus.updateStatus(0);
		hunter.setPos(this.huntX, this.huntY, this.huntVert);
		hunter.setArmed(false);
		display.requestFocus();
		display.repaint();
	}
	
	//sets the game up for level 1
	public void levelOne(){
		graph.clear();
		graph.addEdge(v2, Direction.EAST, v3);
		graph.addEdge(v3, Direction.EAST, v4);
		graph.addEdge(v4, Direction.EAST, v5);
		graph.addEdge(v4, Direction.SOUTH, v10);
		graph.addEdge(v10, Direction.SOUTH, v16);
		graph.addEdge(v16, Direction.SOUTH, v22);
		graph.addEdge(v22, Direction.SOUTH, v28);
		graph.addEdge(v28, Direction.WEST, v27);
		graph.addEdge(v27, Direction.WEST, v26);
		graph.addEdge(v26, Direction.NORTH, v20);
		graph.addEdge(v20, Direction.NORTH, v14);
		graph.addEdge(v14, Direction.NORTH, v8);
		graph.addEdge(v8, Direction.NORTH, v2);
		graph.addEdge(v14, Direction.WEST, v13);
		graph.addEdge(v13, Direction.WEST, v12);
		graph.addEdge(v12, Direction.SOUTH, v18);
		graph.addEdge(v18, Direction.SOUTH, v24);
		graph.addEdge(v24, Direction.SOUTH, v30);
		graph.addEdge(v30, Direction.EAST, v31);
		graph.shortestPath(v16);
		this.wumpX = 4;
		this.wumpY = 2;
		this.wumpVert = v16;
		this.huntX = 0;
		this.huntY = 2;
		this.huntVert = v12;
		scape.restart();
		wumpus.restart(this.wumpX, this.wumpY, this.wumpVert);
		wumpus.updateStatus(0);
		hunter.setPos(this.huntX, this.huntY, this.huntVert);
		hunter.setArmed(false);
		display.requestFocus();
		display.repaint();
	}
	
	//sets the game up for level 2
	public void levelTwo(){
		graph.clear();
		graph.addEdge(v1, Direction.EAST, v2);
		graph.addEdge(v6, Direction.SOUTH, v12);
		graph.addEdge(v12, Direction.SOUTH, v18);
		graph.addEdge(v18, Direction.SOUTH, v24);
		graph.addEdge(v24, Direction.SOUTH, v30);
		graph.addEdge(v30, Direction.EAST, v31);
		graph.addEdge(v31, Direction.EAST, v32);
		graph.addEdge(v32, Direction.EAST, v33);
		graph.addEdge(v33, Direction.EAST, v34);
		graph.addEdge(v34, Direction.EAST, v35);
		graph.addEdge(v32, Direction.NORTH, v26);
		graph.addEdge(v26, Direction.NORTH, v20);
		graph.addEdge(v20, Direction.NORTH, v14);
		graph.addEdge(v14, Direction.NORTH, v8);
		graph.addEdge(v14, Direction.EAST, v15);
		graph.addEdge(v15, Direction.EAST, v16);
		graph.addEdge(v8, Direction.NORTH, v2);
		graph.addEdge(v34, Direction.NORTH, v28);
		graph.addEdge(v28, Direction.NORTH, v22);
		graph.addEdge(v22, Direction.EAST, v23);
		graph.addEdge(v22, Direction.NORTH, v16);
		graph.addEdge(v16, Direction.NORTH, v10);
		graph.addEdge(v10, Direction.NORTH, v4);
		graph.addEdge(v4, Direction.EAST, v5);
		graph.addEdge(v4, Direction.WEST, v3);
		graph.addEdge(v3, Direction.WEST, v2);
		graph.shortestPath(v3);
		this.wumpX = 3;
		this.wumpY = 0;
		this.wumpVert = v3;
		this.huntX = 0;
		this.huntY = 1;
		this.huntVert = v6;
		scape.restart();
		wumpus.restart(this.wumpX, this.wumpY, this.wumpVert);
		wumpus.updateStatus(0);
		hunter.setPos(this.huntX, this.huntY, this.huntVert);
		hunter.setArmed(false);
		display.requestFocus();
		display.repaint();
	}
	
	//returns true when the game is in progress
	public boolean inProgress(){
		return wumpus.status() == 0;
	}

	//moves the hunter and updates the Vertexes
	//if necessary, arms the hunter and shoots or disarms
	public void update(String action){
		//repaints the display when there is a change
		display.repaint();
		//if not armed, hunter will either arm themself or move
		if(!hunter.isArmed()){
			if(action.equalsIgnoreCase("w")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.NORTH))){
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
				else hunter.move(Direction.NORTH);
			}
			else if(action.equalsIgnoreCase("s")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.SOUTH))){
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
				else hunter.move(Direction.SOUTH);
			}
			else if(action.equalsIgnoreCase("a")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.WEST))){
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
				else{
					hunter.move(Direction.WEST);
				}
			}
			else if(action.equalsIgnoreCase("d")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.EAST))){
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
				else hunter.move(Direction.EAST);
			}
			else if(action.equalsIgnoreCase(" ")){
				hunter.setArmed(true);
			}
		}
		else{
			if(action.equalsIgnoreCase("w")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.NORTH))){
					wumpus.updateStatus(2);
				}
				else{
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
			}
			else if(action.equalsIgnoreCase("s")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.SOUTH))){
					wumpus.updateStatus(2);
				}
				else{
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
			}
			else if(action.equalsIgnoreCase("a")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.WEST))){
					wumpus.updateStatus(2);
				}
				else{
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
			}
			else if(action.equalsIgnoreCase("d")){
				if(wumpus.isHome(hunter.getNeighbor(Direction.EAST))){
					wumpus.updateStatus(2);
				}
				else{
					wumpus.restart(hunter.getX(), hunter.getY(), hunter.getLocation());
					wumpus.updateStatus(1);
					System.out.println("You lose.");
				}
			}
			else if(action.equalsIgnoreCase(" ")){
				hunter.setArmed(false);
			}
		}
	}
	
	//sets Playing to STOP
	public void quit(){
		this.play = Playing.STOP;
	}
	
	//closes the LandscapeDisplay
	public void close(){
		System.out.println("Thanks for playing!");
		System.exit(0);
	}

	//plays the HuntTheWumpus game
	public static void main(String[] args) throws InterruptedException {
		HuntTheWumpus h = new HuntTheWumpus();
		while(h.play == Playing.PLAY){
			Thread.sleep(100);

		}
		h.close();
	}    
}
