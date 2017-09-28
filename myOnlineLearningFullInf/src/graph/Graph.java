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
    private Map<String,Set<Edge>> mapVtoAdgs;
    
    //def C'tor
    public Graph() {
    	super();
    	this.vertices = new HashSet<>();
    	this.edges = new HashSet<>();
    	this.mapVtoAdgs= new HashMap<>();
    }
    //copy C'tor
    public Graph(Graph otherG){
    	super();
    	this.vertices = new HashSet<>();
    	this.edges = new HashSet<>();
    	this.mapVtoAdgs= new HashMap<>();
    	for (Vertex vertex : otherG.vertices) {
			this.addVertex(vertex);
		}
    	for (Edge edge: otherG.edges) {
			this.addEdge(edge);
		}
    	
    }
    //C'tor
    public Graph(Set<Vertex> vertices, Set<Edge> edges, Map<String, Set<Edge>> vAdg) {
        super();
        this.vertices = vertices;
        this.edges = edges;
        this.mapVtoAdgs= vAdg;
    }
    //return the new vertex if the vertex is new and don't have a same name else return null
    public Vertex addVertex(Vertex v){
    	//check Vertex contain graph
    	if(this.vertices.contains(v))
    		return null;
    	//check Vertex has a same name
    		for (Vertex vertex : vertices) {
				if(vertex.getName().equals(v.getName()))
					return null;
			}
    	Vertex vertex = new Vertex(v.getName());
    	this.vertices.add(vertex);
    	this.mapVtoAdgs.put(vertex.getName(), new HashSet<>());
    	return vertex;
    }
    
    //return the new Edge if the from and to contains and the edge not in set edges else return null 
    public Edge addEdge(Edge edg){
    	if(edges.contains(edg))
    		return null;
    	//from and to contains in graph
    	Vertex from=null;
    	Vertex to=null;
    	    	
    	for (Vertex vertex : this.vertices) {
			if(edg.getFrom().getName().equals(vertex.getName()))
				from = vertex;
			if(edg.getTo().getName().equals(vertex.getName()))
				to = vertex;
		}
    	if(from==null||from==null){
    		System.out.println("line 75 Graph");
    		return null;
    	}
    	Edge e = new Edge(from, to, edg.getWeiget(),edg.getName());
    	if(this.vertices.contains(from)&&this.vertices.contains(to)){
    		this.edges.add(e);
    		this.mapVtoAdgs.get(from.getName()).add(e);
    		return e;
    	}   		
    	return null;
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
	public Map<String, Set<Edge>> getmapVtoAdgs() {
		return mapVtoAdgs;
	}
	public void setvAdg(Map<String, Set<Edge>> mapVtoAdgs) {
		this.mapVtoAdgs = mapVtoAdgs;
	}
	
}
