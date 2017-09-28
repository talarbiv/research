package graph;

public class Edge {
	
	private Vertex from;
	private Vertex to;
	private double weiget;//in our case its risk
	private String name;
	
	public Edge(Vertex from,Vertex to,double weiget) {
		this.from=from;
		this.to=to;
		this.weiget=weiget;
		this.setName("");
	}
	public Edge(Vertex from,Vertex to,double weiget,String name) {
		this.from=from;
		this.to=to;
		this.weiget=weiget;
		this.setName(name);
	}
	
	@Override
	public String toString() {
		return name + ": from " + from + " to " + to + " weiget " + weiget;
	}
	@Override
	public boolean equals(Object obj) {
		Edge e = (Edge) obj;
		return e.getName().equals(this.name);
	}
	
	//get's and set's
	public Vertex getFrom() {
		return from;
	}
	public void setFrom(Vertex from) {
		this.from = from;
	}
	public Vertex getTo() {
		return to;
	}
	public void setTo(Vertex to) {
		this.to = to;
	}
	public double getWeiget() {
		return weiget;
	}
	public void setWeiget(double weiget) {
		this.weiget = weiget;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
