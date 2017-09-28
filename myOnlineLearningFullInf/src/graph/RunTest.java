package graph;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class RunTest {

	public static void main(String[] args) {
		Graph graph = new Graph();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(c);
		graph.addVertex(new Vertex("c"));

		graph.addEdge(new Edge(a, b, 5));
		graph.addEdge(new Edge(b, c, 7));
		graph.addEdge(new Edge(c, b, 9));

		for (Entry<String, Set<Edge>> entry : graph.getmapVtoAdgs().entrySet()) {
			System.out.print(entry.getKey() + " ");
			entry.getValue().forEach(v->System.out.print(v));
			System.out.println();
			
		}
		
	}

}
