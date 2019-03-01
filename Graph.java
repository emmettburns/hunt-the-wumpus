/** 
* File: Graph.java
* Author: Emmett Burns
*Date: 05/02/2018
*/

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Graph{

	//Graph fields
	private ArrayList<Vertex> graph;

	//Graph constructor
	public Graph(){
		graph = new ArrayList<Vertex>();
	}

	//returns the number of Vertexes in the graph (Yes, I know the correct plural vertex is vertices. "Vertexes" is a stylistic choice. Don't judge me.")
	public int vertexCount(){ return graph.size(); }

	//creates a bi-directional link between the given edges (direction is in direction if second node given)
	public void addEdge(Vertex v1, Direction dir, Vertex v2){
		if(!graph.contains(v1)) graph.add(v1);
		if(!graph.contains(v2)) graph.add(v2);
		v1.connect(v2, dir);
		v2.connect(v1, v1.opposite(dir));
	}

	//finds the shortest path between two Vertexes
	//the follwing was used to find max value: https://stackoverflow.com/questions/12952024/how-to-implement-infinity-in-java
	public void shortestPath(Vertex v0){
		for(Vertex v : graph){
			v.setCost(Integer.MAX_VALUE);
			v.setMarked(false);
		}
		v0.setCost(0);
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(graph.size(), new VertexAscending());
		q.offer(v0);
		while(q.size() > 0){
			Vertex v = q.poll();
			v.setMarked(true);
			for(Vertex w : v.getNeighbors()){
				if( w.getMarked() == false && (v.cost()+1) < w.cost() ){
					//System.out.println("setting cost of vertex: " + w.getId() + " to " + (v.cost()+1));
					w.setCost(v.cost()+1);
					if(q.contains(w)) q.remove(w);
					q.offer(w);
				}
			}
		}
		//displays the results 
		// String str = ""; 
		// for(Vertex v : graph){
		// 	str += v + "\n";
		// }
		// System.out.println(str);
	}
	
	//clears the graph
	public void clear(){
		for(Vertex v : graph){
			v.reset();
		}
		graph.clear();
	}

	//returns a String indicating the contents of the graph
	public String toString(){
		String str = "";
		for(Vertex v : graph){
			str += v.toString() + "\n";
		}
		return str;
	}

	//tests the methods of the Graph class
	public static void main(String[] args){
		Graph g = new Graph();
		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(0);
		Vertex v2 = new Vertex(0);
		Vertex v3 = new Vertex(0);
		Vertex v4 = new Vertex(0);
		Vertex v5 = new Vertex(0);
		g.addEdge(v0, Direction.EAST, v1);
		g.addEdge(v0, Direction.SOUTH, v2);
		g.addEdge(v1, Direction.EAST, v3);
		g.addEdge(v2, Direction.EAST, v4);
		g.addEdge(v1, Direction.SOUTH, v4);
		g.addEdge(v4, Direction.EAST, v5);
		g.addEdge(v5, Direction.NORTH, v3);
		g.shortestPath(v5);
		System.out.println(g.toString());
	}
}

//sorts Vertexes giving highest priority to the lowest cost
class VertexAscending implements Comparator<Vertex>{
	public int compare(Vertex a, Vertex b){
		return a.cost() - b.cost();
	}
}