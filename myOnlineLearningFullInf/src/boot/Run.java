package boot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import subModFullInf.MyAlgFollowPerLead;
import subModFullInf.iAlgFollowPerLead;
import subModFullInf.FuncTS.MyF;
import subModFullInf.FuncTS.iF;

public class Run {

	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		//Second graph
		//create Vertexes
		Vertex v1 = new Vertex("v1");
		Vertex v2= new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex v5 = new Vertex("v5");
		//add Vertex to graph 
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		
		//create Edges
		Edge e1 = new Edge(v2, v1, 2.0/3.0,"e1");
		Edge e2 = new Edge(v2, v3, 2.0/5.0,"e2");
		Edge e3 = new Edge(v3, v2, 1.0/9.0,"e3");
		Edge e4 = new Edge(v3, v5, 0.1,"e4");
		Edge e5 = new Edge(v4, v5, 3.0/18.0,"e5");
		Edge e6 = new Edge(v3, v4, 1.0/17.0,"e6");
		Edge e7 = new Edge(v4, v5, 0.5,"e7");
		//add Edges to graph 
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		graph.addEdge(e5);
		graph.addEdge(e6);
		graph.addEdge(e7);
		
		//Create possible coverage
		Set<Edge> coverE1 = new HashSet<>();
		coverE1.add(e1);
		Set<Edge> coverE3 = new HashSet<>();
		coverE3.add(e3);
		Set<Edge> coverE5= new HashSet<>();
		coverE5.add(e5);		
		//add possible coverage
		Set<Set<Edge>> pC = new HashSet<>();
		pC.add(coverE1);
		pC.add(coverE3);
		pC.add(coverE5);
		
		
		/*first Graph
		//create Vertexes
		Vertex z = new Vertex("z");
		Vertex q = new Vertex("q");
		Vertex r = new Vertex("r");
		Vertex t = new Vertex("t");
		//add Vertex to graph 
		graph.addVertex(z);
		graph.addVertex(q);
		graph.addVertex(r);
		graph.addVertex(t);
		
		//create Edges
		Edge A = new Edge(q, r, 1,"A");
		Edge Ar = new Edge(r, q, 1,"Ar");
		Edge B = new Edge(r, t, 0.5,"B");
		Edge C = new Edge(z, q, 0.5,"C");
		Edge D = new Edge(z, t, 0.5,"D");
		//add Edges to graph 
		graph.addEdge(A);
		graph.addEdge(Ar);
		graph.addEdge(B);
		graph.addEdge(C);
		graph.addEdge(D);
	
		//Create possible coverage
		Set<Edge> coverD = new HashSet<>();
		coverD.add(D);
		Set<Edge> coverA = new HashSet<>();
		coverA.add(A);
		Set<Edge> coverArAndA = new HashSet<>();
		coverArAndA.add(Ar);
		coverArAndA.add(A);		
		//add possible coverage
		Set<Set<Edge>> pC = new HashSet<>();
		pC.add(coverD);
		pC.add(coverA);
		pC.add(coverArAndA);
		*/
		
		
		final double effChosen = 0.36;
		final double effNeighbor = 0.05;
		iF f = new MyF(effChosen , effNeighbor);
		
		//Print sum risk for each possible coverage (in pc - for debug)
		for (Set<Edge> set : pC) {	
			System.out.println(set.toString()+" " +f.func(0, graph, set));			
		}
		
		//Runs the algorithm and print the result
		iAlgFollowPerLead alg =new MyAlgFollowPerLead();
		try {
			List<Set<Edge>> choosenList = alg.MySubmodularFollowThePerturbedLeader(10.0, graph, pC, f, 10);
			int count=0;
			for (Set<Edge> set : choosenList) {
				System.out.print("choose "+ (++count) +": ");
				for (Edge edge : set) {
					System.out.print(edge.getName() + " , ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
