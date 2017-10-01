package subModFullInf.FTimeC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Edge;
import graph.Graph;

public class MyTimeC implements iFTimeC {
	
	private double PercentR;

	/**C'tor Percentage of reduction from the last selected group according to the description of the function
	 * {@link #timeChange(Graph, List)}  example if you want Percentage of reduction 1% you send 0.01
	 * <p>if PercentR< 0 or PercentR>1 put default value ('0')</p>
	 * */
	public MyTimeC(double PercentR) {
		if(PercentR< 0||PercentR>1)
			this.PercentR=0;
		else
			this.PercentR = PercentR;
	}
	
	
	/**This function reduces the risk by risk*(1-PercentR) (PercentR you send in C'tor) of each enforced road,
 	 * And increases the number of downloads equally to the sum of the total amount of the other roads for each of the other roads;
	 *  Except for a case of risk 1 in which we shall not make any change.
	 *  <br><p>A hidden assumption is that the list is ordered from the last one,
	 *   which is enforced until the oldest one that has been decided to present</p>
	 *  */
	@Override//TO DO not care yet to 1 risk
	public void timeChange(Graph graph, List<Set<Edge>> choosenList) {
		if(choosenList.size()>0){
			Set<Edge> lastChoose = choosenList.get(0);
			if(lastChoose.size() > 0){
				Set<Edge> notLastChoose= graph.getEdges();
				for (Edge edge : lastChoose) {
					notLastChoose.removeIf((e)->(edge.equals(e)));
				}
				//count one risk its counting all edge there not in last choose and wiegt=1
				int countOneRisk=0;
				for (Edge edge : notLastChoose) {
					if(edge.getWeiget()==1)
						countOneRisk++;
				}
				
				if(notLastChoose.size()-countOneRisk>0 ){
					lastChoose.forEach((e)->e.setWeiget(e.getWeiget()*(1-this.PercentR)));
					double IncPercentRisk = (lastChoose.size()*this.PercentR)/notLastChoose.size();
					notLastChoose.forEach((e)->e.setWeiget(e.getWeiget()*(1+IncPercentRisk)));
				}
				//add back the lst choose than i removed 
				graph.getEdges().addAll(lastChoose);
			}
			
		}
	}

}
