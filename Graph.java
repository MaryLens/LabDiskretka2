package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Graph {

	Scanner sc = new Scanner(System.in);

	private int nodes, arcs;
	private ArrayList<ArrayList<Integer>> incMatrix = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	private boolean visitedDFS[];
	private boolean visitedBFS[];
	private boolean seen[];
	private Deque<Integer> queue = new ArrayDeque<>();

	public Graph(int nodes, int arcs) {
		this.nodes = nodes;
		this.arcs = arcs;
		visitedDFS = new boolean[nodes + 1];
		visitedBFS = new boolean[nodes + 1];
		seen = new boolean[nodes + 1];
	}

	public Graph() {
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
		visitedDFS = new boolean[nodes + 1];
		visitedBFS = new boolean[nodes + 1];
		seen = new boolean[nodes + 1];
	}

	public void setArcs(int arcs) {
		this.arcs = arcs;
	}


	public void getAdjList() {
		System.out.println("Ñïèñîê ñìåæíîñòè äàííîãî ãðàôà:");
		for (int i = 0; i < nodes; i++) {
			System.out.print((i + 1) + ": ");
			for (int j = 0; j < adjList.get(i).size(); j++) {
				if (j != (adjList.get(i).size() - 1)) {
					System.out.print(adjList.get(i).get(j) + ", ");
				} else {
					System.out.print(adjList.get(i).get(j) + "\n");
				}
			}
		}
	}

	public void setAdjList() {
		System.out.print("Ââîäèòå ñïèñîê ñìåæíîñòè:\n");
		for (int i = 0; i < nodes; i++) {
			adjList.add(new ArrayList<>());
			System.out.print(" " + (i + 1) + ": ");
			int value = sc.nextInt();
			while (value != 0) {
				adjList.get(i).add(value);
				value = sc.nextInt();
			}
		}
	}


	// îáõîä ãðàôà â ãëóáèíó
	public void depthFirstSearch(int start) {
		visitedDFS[start] = true;
		System.out.print(start + " ");

		Iterator<Integer> iterator = adjList.get(start - 1).listIterator();
		while (iterator.hasNext()) {
			int node = iterator.next();
			if (!visitedDFS[node]) {
				depthFirstSearch(node);
			}
		}
		int node = 1;
		while (node < visitedDFS.length) {
			if (!visitedDFS[node]) {
				depthFirstSearch(node);
			}
			node++;
		}

	}

	// îáõîä ãðàôà â øèðèíó
	public void breadthFirstSearch(int start) {
		seen[start]=true;
		queue.add(start);
		while(true) {
			for (int i = 0; i < adjList.get(start-1).size(); i++) {
				if(seen[adjList.get(start-1).get(i)]!=true) {
					queue.add(adjList.get(start-1).get(i));
					seen[adjList.get(start-1).get(i)]=true;
				}
				visitedBFS[start] = true;
			}
			System.out.print(queue.pollFirst()+" ");
			if(!queue.isEmpty()) {
			start = queue.peekFirst();
			} else {
				break;
			}
		}
		System.out.println();
	}

}
