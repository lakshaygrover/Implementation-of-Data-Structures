package graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {


	ArrayList<String> reference;
	ArrayList<Vertex> vertices;

	Graph(){
		vertices = new ArrayList<>();

		reference = new ArrayList<>();
	}

	public void print(){
		for(Vertex v : vertices){
			v.print();
		}
		System.out.println();
	}

	public int numVertices(){
		return vertices.size();
	}

	public int numEdges(){
		int count = 0;
		for(Vertex v : vertices){
			count += v.numEdges();
		}
		return count / 2;
	}

	public void addVertex(String name){
		if(isVertexPresent(name)){
			return;
		}
		Vertex v = new Vertex(name);
		vertices.add(v);
	}

	public void addEdge(String name1, String name2){
		if(!isVertexPresent(name1) || !isVertexPresent(name2)){
			return;
		}
		if(areAdjacent(name1,name2)){
			return;
		}
		Vertex first = getVertex(name1);
		Vertex second = getVertex(name2);
		Edge e = new Edge(first,second);
		first.addEdge(e);
		second.addEdge(e);
	}

	public void removeEdge(String name1, String name2){
		if(!isVertexPresent(name1) || !isVertexPresent(name2)){
			return;
		}
		if(!areAdjacent(name1,name2)){
			return;
		}
		Vertex first = getVertex(name1);
		Vertex second = getVertex(name2);	
		first.removeEdgeWith(second);
		second.removeEdgeWith(first);
	}

	public void removeVertex(String name){
		Vertex v = getVertex(name);
		if(v == null){
			return;
		}

		ArrayList<Vertex> adjacentVertices = v.getAdjacentVertices();
		for(Vertex adjVertex : adjacentVertices){
			adjVertex.removeEdgeWith(v);
		}
		vertices.remove(v);
	}


	public  boolean areAdjacent(String name1, String name2) {
		if(!isVertexPresent(name1) || !isVertexPresent(name2)){
			return false;
		}
		Vertex first = getVertex(name1);
		Vertex second = getVertex(name2);
		return first.isAdjacentTo(second);	
	}

	public Vertex getVertex(String name) {
		for(Vertex v : vertices){
			if(v.getName().equals(name)){
				return v;
			}
		}
		return null;
	}

	private boolean isVertexPresent(String name) {
		for(Vertex v : vertices){
			if(v.getName().equals(name)){
				return true;
			}
		}
		return false;
	}

	public boolean havePath(String name1, String name2){
		HashMap<Vertex, Boolean> map = new HashMap<>();
		Vertex v1 = getVertex(name1);
		map.put(v1, true);
		return havePath(name1, name2, map);


	}

	public boolean havePath(String name1, String name2, HashMap<Vertex,Boolean> map){

		Vertex v1 = getVertex(name1);
		Vertex v2 = getVertex(name2);
		if(v1.isAdjacentTo(v2)){
			return true;
		}
		ArrayList<Vertex> v1al = v1.getAdjacentVertices();
		for(Vertex temp:v1al){
			String tempName = temp.getName();
			if(!map.containsKey(temp)){
				map.put(temp, true);
				if(havePath(tempName, name2, map)){
					return true;
				}
			}
		}
		return false;
	}


	public boolean isConnected(){
		for(Vertex v:vertices){
			String vName = v.getName();
			for(Vertex v1:vertices){
				String v1Name = v1.getName();
				if(!havePath(vName, v1Name)){
					return false;
				}	
			}	
		}
		return true;
	}
}