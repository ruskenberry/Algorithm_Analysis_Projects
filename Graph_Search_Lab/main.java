import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {
	private static final int WHITE = 1;
	private static final int GRAY = 2;
	private static final int BLACK = 3;
	
	public static class VertexNode{
		private char key;
		int color = WHITE;
		private int distance;
		private VertexNode parent;
		
		VertexNode(char k) { key = k;}
		public char getKey() { return key; }
		public void setColor(int c){ color = c; }
		public int getColor(){ return color; }
		public void setDistance(int d){ distance = d; }
		public int getDistance(){ return distance; }
		public void setParent(VertexNode p){ parent = p; }
		public VertexNode getParent(){ return parent; }
	}
	public static class Edge{
		private char source;
		private char destination;
		
		Edge(char s, char d) {  source = s; destination = d; }
		public char getSource() { return source; }
		public char getDestination() { return destination; }
	}
	

	public static void main(String args[]) throws IOException{

		Scanner file = new Scanner(new File("THISONE.txt"));
		int numV = file.nextInt();
		VertexNode v[] = new VertexNode[numV];
		for(int i = 0; i < v.length; i++){
			v[i] = new VertexNode(file.next().charAt(0));
			}
		
		int numEdge = file.nextInt();
		Edge edges[] = new Edge[numEdge];
		for(int i = 0; i < edges.length; i++)
			edges[i] = new Edge(file.next().charAt(0), file.next().charAt(0));
		
		boolean[][] matrix = new boolean[numV][numV];
		
		for(int i = 0; i < edges.length; i++)
		{
			char source = edges[i].getSource();
			char dest = edges[i].getDestination();
			int sPos = -1, dPos = -1;
			
			for(int j = 0; j < v.length; j++){
				if(v[j].key == source) sPos = j;
				if(v[j].key == dest) dPos = j;
			}
			matrix[sPos][dPos] = true;
			matrix[dPos][sPos] = true;
		}
		
		for(int k = 0; k < numV; k ++) {
			System.out.print("[");
			System.out.print(v[k].getKey() + " ");
			for(int q = 0; q < numV; q ++) {
				if(matrix[k][q]) System.out.print("1 ");
				if(!matrix[k][q])System.out.print("0 ");
			}
			System.out.print("]");
			System.out.println();
		}
		
		bfs(v, matrix, 0);
	}
	
	public static int findKey(VertexNode[] t, VertexNode v){
		int pos = -1;
		for (int n = 0; n < t.length; n++){
			if(t[n].getKey() == v.getKey()){
				pos = n;
				break;
			}
		}
		return pos;
	}
	
	public static void bfs(VertexNode[] v, boolean[][]mat, int s){
		for(int i = 0; i < v.length;i++){
			v[i].setColor(WHITE);
			v[i].setDistance(Integer.MAX_VALUE);
			v[i].setParent(null);
		}
		v[s].setColor(GRAY);
		v[s].setDistance(0);
		v[s].setParent(null);
		
		Queue<VertexNode> Q = new LinkedList<VertexNode>();
		Q.add (v[s]);
		
		while(!Q.isEmpty()) {
			VertexNode u = Q.peek();
			
			int uPos = findKey(v ,u);
			
			for(int i = 0; i < mat.length; i++) {
				if(mat[i][uPos] == true){		
					
					if(v[i].getColor() == WHITE){
						v[i].setColor(GRAY);
						v[i].setDistance(u.getDistance() + 1) ;
						v[i].setParent(u);
						Q.add(v[i]);
					}
				}
			}
				VertexNode ab = Q.remove();
				System.out.println("-" + ab.getKey() + " Distance " + ab.getDistance());
				u.setColor(BLACK);
			
			}
		System.out.println("DONE");
	}
}