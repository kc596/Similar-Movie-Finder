package buzzotel.similarmovies;

/**
 * The {@code Edge} class represents a weighted edge in an weighted Graph.
 * Each edge consists of two integers v and w (naming the two vertices) and a
 * real-value weight. <b> More weight ~ More similarity </b>
 *
 * @author Kunal Chaudhary
 */

public class Edge implements Comparable<Edge>{
	private final int v, w;
	private final double weight;

	/**
	 * Initializes an edge between vertices v and w with given weight.
	 *
	 * @param v one vertex
	 * @param w the other vertex
	 * @param weight the weight of this edge
	 * @throws IllegalArgumentException if either v or w is a negative integer
	 */
	public Edge(int v, int w, double weight){
		if(v < 0 || w < 0) throw new IllegalArgumentException("Vertex index must be a nonnegative integer");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * @return either endpoint of this edge
	 */
	public int either(){
		return v;
	}

	/**
	 * @param one endpoint of this edge
	 * @return the other endpoint of this edge
	 * @throws IllegalArgumentException if input is not one of the endpoints
	 */
	public int other(int one){
		if(one==this.v) return w;
		else if(one==this.w) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}

	/**
	 * @return weight of the edge
	 */
	public double weight(){
		return weight;
	}

	/**
	 * Compares two edges by weight.
	 *
	 * @param that is the edge to compare to this edge
	 * @return	a negative integer if weight of this edge is less than weight of other edge;
	 *			a positive integer if weight of this edge is greater than weight of other edge;
	 *			and zero if weight of both edges are equal
	 */
	@Override
	public int compareTo(Edge that){
		return Double.compare(this.weight, that.weight);
	}
}