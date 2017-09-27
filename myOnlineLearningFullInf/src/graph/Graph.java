package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Set<Vertex>vertices;
    private Set<Edge>edges;
    //input  Vertex return list of Edge
    private Map<Vertex,Set<Edge>> mapVtoAdgs;
    
    //def C'tor
    public Graph() {
    	super();
    	this.vertices = new HashSet<>();
    	this.edges = new HashSet<>();
    	this.mapVtoAdgs= new HashMap<>();
    }
    //C'tor
    public Graph(Set<Vertex> vertices, Set<Edge> edges, Map<Vertex, Set<Edge>> vAdg) {
        super();
        this.vertices = vertices;
        this.edges = edges;
        this.mapVtoAdgs= vAdg;
    }
    //return true if the vertex is new and don't have a same name
    public boolean addVertex(Vertex v){
    	//check Vertex contain graph
    	if(this.vertices.contains(v))
    		return false;
    	//check Vertex has a same name
    		for (Vertex vertex : vertices) {
				if(vertex.getName().equals(v.getName()))
					return false;
			}
    	
    	this.vertices.add(v);
    	this.mapVtoAdgs.put(v, new HashSet<>());
    	return true;
    }
    
    //return true if the from and to contains and the edge not in set edges 
    public boolean addEdge(Edge edg){
    	if(edges.contains(edg))
    		return false;
    	//from and to contains in graph
    	Vertex from =edg.getFrom();
    	Vertex to = edg.getTo();
    	if(this.vertices.contains(from)&&this.vertices.contains(to)){
    		this.edges.add(edg);
    		this.mapVtoAdgs.get(from).add(edg);
    		return true;
    	}   		
    	return false;
    }
    
    //get's && set's
	public Set<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(Set<Vertex> vertices) {
		this.vertices = vertices;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	public Map<Vertex, Set<Edge>> getmapVtoAdgs() {
		return mapVtoAdgs;
	}
	public void setvAdg(Map<Vertex, Set<Edge>> mapVtoAdgs) {
		this.mapVtoAdgs = mapVtoAdgs;
	}
	
}
