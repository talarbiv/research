package subModFullInf.FuncTS;

import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface iF {
	///Return score = sigma risk*(1-eff) -> eff = num get in C'tor of F
	public  Double func(int T,Graph graph,Set<Edge> cover);
}
