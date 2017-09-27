package subModFullInf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import subModFullInf.FuncTS.iF;

/**if we want to do somting with time we can change func-argmin*/ 

public class MyAlgFollowPerLead implements iAlgFollowPerLead {

	@Override
	public List<Set<Edge>> MySubmodularFollowThePerturbedLeader(Double range, Graph graph, Set<Set<Edge>> possibleC,iF f, int T) throws Exception {
		if (range<=0)
			throw new Exception("Invalid range ");
		List<Set<Edge>> choosenList = new ArrayList<>();
		//rand to all edge in graph num between [-1/range,1/range]
		Map<Edge, Double> randMap = creatRandMap(graph.getEdges(),range);
		//R it's R function in psodo code
		//Map<Set<Edge>,Double> R = creatR(possibleC,randMap);
		Map<Set<Edge>,Double> R = creatDemoR(possibleC,randMap);
		for (int t = 1; t < T; t++) {
			//choose argmin of min sum of(ft(S)+R(S))) t value run between 1 to (t-1) CHANGE IT TO BINRY SEARCH
			choosenList.add(argmin(possibleC,graph,f,t,R)); 
		}

		return choosenList;
	}
	private Set<Edge> argmin(Set<Set<Edge>> possibleC,Graph graph, iF f, int t, Map<Set<Edge>, Double> R) {
		Set<Edge> result=null;
		Double min = Double.MAX_VALUE;
		Double temp=0.0;
		for (Set<Edge> set : possibleC) {
			temp=0.0;
			//in algo psodo code line 4  i understand its min on sigma(f)+R
			for (int T = 1; T < t; T++) {
				temp+=f.func(T,graph, set);
			}
			temp+=R.get(set);
			if(temp<min){
				min=temp;
				result = set;
			}
		}
		return result;
	}
	private Map<Set<Edge>, Double> creatDemoR(Set<Set<Edge>> possibleC, Map<Edge, Double> randMap) {
		Map<Set<Edge>, Double> R = new HashMap<>();; 
		for (Set<Edge> set : possibleC) {
			Double valueSet= 0.0;
			R.put(set, valueSet);
		}
		return R;
	}
	private  Map<Set<Edge>, Double> creatR(Set<Set<Edge>> possibleC, Map<Edge, Double> randMap) {
		Map<Set<Edge>, Double> R = new HashMap<>();; 
		for (Set<Edge> set : possibleC) {
			Double valueSet= getValueSet(set,randMap);
			R.put(set, valueSet);
		}
		return R;
	}
	private  Double getValueSet(Set<Edge> set, Map<Edge, Double> randMap) {
		Double valueSet=0.0;
		//go on all elment add sum the rand value 
		for (Edge edge : set) 
			valueSet+=randMap.get(edge);

		return valueSet;
	}
	//[-1/range,1/range]
		private  Map<Edge, Double> creatRandMap(Set<Edge> edges,double range) throws Exception{
			if (range<=0)throw new Exception("Invalid range ");
			Map<Edge, Double> randMap = new HashMap<>();
			Random rand = new Random();
			for (Edge edge: edges) {
				 double randRe= (rand.nextDouble()*2-1)/range;//[-1/range,1/range]
				 randMap.put(edge, randRe);
			}
			return randMap;
		}
}
