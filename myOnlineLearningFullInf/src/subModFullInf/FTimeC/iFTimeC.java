package subModFullInf.FTimeC;

import java.util.List;
import java.util.Set;

import graph.Edge;
import graph.Graph;

public interface iFTimeC {
	/**This function is responsible for changing the risk by changing the time and choosing the enforcement*/
	public void timeChange(Graph graph,List<Set<Edge>> choosenList);
}
