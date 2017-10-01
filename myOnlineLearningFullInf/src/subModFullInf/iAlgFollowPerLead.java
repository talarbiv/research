package subModFullInf;

import java.util.List;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import subModFullInf.FTimeC.iFTimeC;
import subModFullInf.FuncTS.iF;


public interface iAlgFollowPerLead {
	
	public List<Set<Edge>> MySubmodularFollowThePerturbedLeader (Double range ,Graph graph,Set<Set<Edge>> possibleC,iF f,int T,iFTimeC timeC) throws Exception;
}
