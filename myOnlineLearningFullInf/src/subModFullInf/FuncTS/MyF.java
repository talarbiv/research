package subModFullInf.FuncTS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import javafx.scene.control.cell.MapValueFactory;

public class MyF implements iF {
	
	private double effC;
	private double effNeighbor;
	Map<Edge,Double> eToEff;
	
	public MyF(double effD,double effNeighbor) {
		this.effC=effD;
		this.effNeighbor=effNeighbor;
		this.eToEff = new HashMap<>();
	}
	
	@Override
	public Double func(int T, Graph graph, Set<Edge> cover) {
		this.eToEff.clear();
		//calc eff to all Vertex in graph
		clacEff(graph,cover);
		//Return score = sigma risk*(1-eff)
		return score(graph) ;
	}
	
	//Return score = sigma risk*(1-eff)
	private Double score(Graph graph) {
		Double myScore = 0.0;
		Set<Edge> edges= graph.getEdges();
		for (Edge edge : edges) {
			Double effEdge = eToEff.get(edge);
			myScore += edge.getWeiget()*(1-effEdge);
		}		
		return myScore;
	}
/**
 * Discounts:
 		Placing a police car as a neighbor affects the same manner without any differences(If a police car is placed or not)
   neighbor:
		Every Edge that comes out from "to" or "from" cover's Vertex
 * */
	//clac Eff max Eff its 1.0
	private void clacEff(Graph graph, Set<Edge> cover) { 
		Set<Edge> edges=graph.getEdges();
		//map between vertex to all his edges
		Map<Vertex,Set<Edge>> mapVtoAdgs = graph.getmapVtoAdgs();
		//start all effect at 0
		for (Edge edge: edges)
			eToEff.put(edge,0.0);
		
		for (Edge edge: cover) {
			Double cEff = eToEff.get(edge);
			//if sum cEff+ effC < 1 then cEff += effC 
			if((cEff+this.effC)<1){
				cEff +=this.effC;
				eToEff.put(edge,cEff);		
			}
			//max eff its 1
			else{
				eToEff.put(edge,1.0);			
			}
		//get from and to vertex of edge
		Vertex from = edge.getFrom();
		Vertex to = edge.getTo();
			//make set of neighbors Vertex
		Set<Edge> neighbors = new HashSet<>();
		neighbors.addAll(mapVtoAdgs.get(from));
		neighbors.addAll(mapVtoAdgs.get(to));
		neighbors.remove(edge);
		for (Edge e : neighbors) {
				Double nEff = eToEff.get(e);
				//if sum nEff+ effNeighbor < 1 then nEff += effNeighbor 
				if((nEff+this.effNeighbor)<1){
					nEff +=this.effNeighbor;
					eToEff.put(e,nEff);		
				}
				//max eff its 1
				else{
					eToEff.put(e,1.0);			
				}
			
			}
		
	}
	
	}

}


