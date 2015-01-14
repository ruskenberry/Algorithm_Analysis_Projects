import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
		@Override
		public String toString() {
			return "[" + key + "] ";
		}
	}
	public static class Edge{
		private char source;
		private char destination;

		Edge(char s, char d) {  source = s; destination = d; }
		public char getSource() { return source; }
		public char getDestination() { return destination; }

		@Override
		public String toString() {
			return "Edge [source=" + source + ", destination=" + destination
					+ "]";
		}	
	}

	static int that = 0;
	static int howLong = 60;
	static String name;
	public static void main(String args[]) throws IOException{
		int answer = 0;
		Scanner keyboard = new Scanner(System.in);
		String output1 = "Hello, I am Leonhard. I will assist you in finding paths in graphs today.";
		String output2 = "The two ways that I search graphs are Breadth First (bfs) and Depth First (dfs)";
		String output3 = "What is your name?: ";
		String output = ", Before we start ... Do you want me to print out the adjacency matrix or "
				+ "adjancey list before I search?\n(0 for yes, 1 for no): ";
		char[] c = output1.toCharArray();
		for(int cool = 0; cool < output1.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		System.out.println();
		c = output2.toCharArray();
		for(int cool = 0; cool < output2.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		System.out.println();
		c = output3.toCharArray();
		for(int cool = 0; cool < output3.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		name = keyboard.nextLine();
		//StringBuilder thisOne = new StringBuilder().append(name).append(output);
		String newString = (name).concat(output);
		//System.out.println();
		c = newString.toCharArray();
		for(int cool = 0; cool < newString.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		//System.out.println();
		that = keyboard.nextInt();
		while(answer >= 0){
			System.out.print(name + ", Do you want to perform a bfs or dfs?(1 for bfs, 2 for dfs, -1 to exit): ");
			answer = keyboard.nextInt();
			switch(answer){
			case 1:
				bfs();
				break;
			case 2:
				dfs();
				break;
			case -1:
				System.out.print("Goodbye, " + name + ". Say hello to Mr. Turing for me!");
				break;
			default:
				System.out.println(name + ", I did not reconize your answer...");
				break;
			}
		}
	}

	public static void dfs() throws IOException{
		Scanner keyboard = new Scanner(System.in);
		//System.out.println(name + ", please input the name of the data file that contains graph info: ");
		//String fd = keyboard.nextLine();
		Scanner file = new Scanner(System.in);
		boolean found = false;
		do{
			System.out.print(name + ", please input the name of the data file that contains graph info: ");
			String fd = keyboard.nextLine();
			try{
				file = new Scanner(new File(fd));
				found = true;
			}catch(FileNotFoundException e){
				System.out.println("FILE D.N.E.");
			}
		}while(found == false);
		int numV = file.nextInt();
		VertexNode v[] = new VertexNode[numV];
		for(int i = 0; i < v.length; i++){
			v[i] = new VertexNode(file.next().charAt(0));
		}

		int numEdge = file.nextInt();
		Edge edges[] = new Edge[numEdge];
		for(int i = 0; i < edges.length; i++){
			edges[i] = new Edge(file.next().charAt(0), file.next().charAt(0));
		}

		LinkedList<VertexNode>[] adj = new LinkedList[numV];
		for(int j = 0; j < v.length; j++){
			for(int r = 0; r < edges.length; r++){
				if(edges[r].getSource() == v[j].getKey()){
					if(adj[j] == null)
						adj[j]= new LinkedList<VertexNode>();
					for(int q = 0; q < v.length;q++)
						if(edges[r].getDestination() == v[q].getKey())
							adj[j].add(v[q]);

				}
			}
			if(that != 1)
				System.out.println(v[j] + " " + adj[j]);
		}

		for(int i = 0; i < v.length; i++){
			v[i].setColor(WHITE);
			v[i].setParent(null);
			v[i].setDistance(Integer.MAX_VALUE);
		}
		System.out.print("Where do you want the search to start?(0 to " + (numV-1) +") : ");

		int	s = keyboard.nextInt();
		String outputThis = "Performing Depth First Search, Please Wait";
		char[] c = outputThis.toCharArray();
		for(int cool = 0; cool < outputThis.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		System.out.println();
		v[s].setColor(GRAY);
		v[s].setDistance(0);
		v[s].setParent(null);

		Stack<VertexNode> Q = new Stack<VertexNode>();

		Q.add(v[s]);
		while (!Q.isEmpty()) {
			VertexNode u = Q.peek();
			VertexNode a = Q.pop();
			int uPos = findKey(v, u);
			System.out.println("Visisted " + a + " Distance: " + a.distance);
			u.color = BLACK;
			if(adj[uPos] != null){

				for (int g = 0; g < adj[uPos].size(); g++) {
					VertexNode b = getThis(adj[uPos], g);

					int bPos = findKey(v, b);

					if (v[bPos].color == WHITE) {
						v[bPos].color = GRAY;
						v[bPos].distance = u.distance + 1;
						v[bPos].parent = u;
						Q.add(v[bPos]);
					}
				}
			}
			else{
				System.out.println("We can go no further");
			}
		}
	}
	public static VertexNode getThis(LinkedList<VertexNode> adj, int x){
		LinkedList<VertexNode> temp = (LinkedList<VertexNode>) adj.clone();
		for(int i = 0; i < x; i++)
			temp.pop();
		VertexNode v = temp.pop();
		return v;
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
	public static void bfs() throws IOException{
		Scanner keyboard = new Scanner(System.in);
		Scanner file = new Scanner(System.in);
		boolean found = false;
		do{
			System.out.print(name + ", please input the name of the data file that contains graph info: ");
			String fd = keyboard.nextLine();
			try{
				file = new Scanner(new File(fd));
				found = true;
			}catch(FileNotFoundException e){
				System.out.println("FILE D.N.E.");
			}
		}while(found == false);

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

		for(int i = 0; i < v.length;i++){
			v[i].setColor(WHITE);
			v[i].setDistance(Integer.MAX_VALUE);
			v[i].setParent(null);
		}
		if(that != 1){
			for(int k = -1; k < numV; k ++) {
				if(k == -1){
					System.out.print("  ");
					for(int x =0; x < numV; x ++){
						System.out.print(v[x].getKey() + " ");
					}
					System.out.println();
				}else{

					System.out.print(v[k].getKey() + " ");
					for(int q = 0; q < numV; q ++) {
						if(matrix[k][q]) System.out.print("1 ");
						if(!matrix[k][q])System.out.print("0 ");
					}
					System.out.println();
				}
			}
		}

		System.out.print("Where do you want the search to start?(0 to " + (numV-1) +") : ");
		int	s = keyboard.nextInt();
		String outputThis = "Performing Breadth First Search, Please Wait";
		char[] c = outputThis.toCharArray();
		for(int cool = 0; cool < outputThis.length(); cool ++){
			try {
				//sending the actual Thread of execution to sleep X milliseconds
				Thread.sleep(howLong);
			} catch(InterruptedException ie) {}
			System.out.print(c[cool]);
		}
		System.out.println();

		v[s].setColor(GRAY);
		v[s].setDistance(0);
		v[s].setParent(null);

		Queue<VertexNode> Q = new LinkedList<VertexNode>();
		Q.add (v[s]);

		while(!Q.isEmpty()) {
			VertexNode u = Q.peek();

			int uPos = findKey(v ,u);

			for(int i = 0; i < matrix.length; i++) {
				if(matrix[i][uPos] == true){		

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