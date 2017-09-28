package subModFullInf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
		
		List<Set<Edge>> _linkList = new LinkedList<>();
		for (int t = 1; t < T; t++) {
			/***get a Graph after calculate the past(time effect) in this Formula risk = risk * (1-(EffC/2^k)) k=1 or 2. 
			if we want to change the graph we need to do other fanc with real graph*/
			Graph graphAfterEffC= calcChange(graph,_linkList,f);
			//choose argmin of min sum of(ft(S)+R(S))) t value run between 1 to (t-1) CHANGE IT TO BINRY SEARCH
			Set<Edge> lastChoose = argmin(possibleC,graphAfterEffC,f,t,R);
			//add the last choose to start lincList
			_linkList.add(0,lastChoose);
			//here we take just the First two 
			if(_linkList.size()>2)
				_linkList = _linkList.subList(0,2);
			//add the last choose to choosenList
			choosenList.add(lastChoose); 
		}

		return choosenList;
	}
	/***get a Graph after calculate the past(time effect) in this Formula risk = risk * (1-(EffC/2^k)) k = {1,...,linkList size}. 
	if we want to change the graph we need to do other fanc with real graph*/
	private Graph calcChange(Graph graph, List<Set<Edge>> _linkList,iF f) {
		Graph graphAfterEffC = new Graph(graph);
		for (int i = 0; i < _linkList.size(); i++) {
			//get the set then choose i time before this
			Set<Edge> chIBefore = _linkList.get(i);
			//Percent reduction (1-(EffC/2^i)) 
			double percentR = 1 - (f.getEffC()/(Math.pow(2,i+1)));
			for (Edge edge : graphAfterEffC.getEdges()) {
				for (Edge e : chIBefore) {
					if(e.equals(edge))
						edge.setWeiget(edge.getWeiget()*percentR);
				}
			}
		}
		return graphAfterEffC;
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
