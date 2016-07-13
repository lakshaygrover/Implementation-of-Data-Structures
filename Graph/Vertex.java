package graphs;

import java.util.ArrayList;

public class Vertex {
	private 	String name;
	private 	ArrayList<Edge> edges;

	Vertex(String name){
		this.name = name;
		edges = new ArrayList<>();
	}

	public int numEdges(){
		return edges.size();
	}

	public String getName(){
		return name;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public boolean isAdjacentTo(Vertex second) {
		for(Edge e : edges){
			if(e.first == second || e.second == second){
				return true;
			}
		}
		return false;
	}

	public void removeEdgeWith(Vertex second) {
		for(Edge e : edges){
			if(e.first == second || e.second == second){
				this.edges.remove(e);
				return;
			}
		}
	}

	public ArrayList<Vertex> getAdjacentVertices() {
		ArrayList<Vertex> adjacentVertices = new ArrayList<>();
		for(Edge e : edges){
			if(e.first == this ){
				adjacentVertices.add(e.second);
			}
			else{
				adjacentVertices.add(e.first);
			}
		}
		return adjacentVertices;
	}

	public void print() {
		String output = this.name +" : ";
		for(Edge e : edges){
			if(e.first == this ){
				output += e.second.getName()+" , ";
			}
			else{
				output += e.first.getName() +" , ";
			}
		}
		System.out.println(output);
	}
}