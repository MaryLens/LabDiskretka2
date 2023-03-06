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
	private Deque<Integer> queue = new ArrayDeque<>();

	public Graph(int nodes, int arcs) {
		this.nodes = nodes;
		this.arcs = arcs;
		visitedDFS = new boolean[nodes + 1];
		visitedBFS = new boolean[nodes + 1];
	}

	public Graph() {
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
		visitedDFS = new boolean[nodes + 1];
		visitedBFS = new boolean[nodes + 1];
	}

	public void setArcs(int arcs) {
		this.arcs = arcs;
	}

	public void getIncMatrix() {
		System.out.print("Матрица инцедентности данного графа:\n    ");
		for (int i = 0; i < nodes; i++) {
			System.out.print(" x" + (i + 1));
		}
		System.out.println("\n");
		for (int i = 0; i < arcs; i++) {
			System.out.print("l" + (i + 1) + " ");
			for (int j = 0; j < nodes; j++) {
				System.out.printf("%3d", incMatrix.get(i).get(j));
			}
			System.out.println();
		}
	}

	public void setIncMatrix() {
		System.out.print("Вводите матрицу инцидентности:\n ");
		for (int i = 0; i < arcs; i++) {
			incMatrix.add(new ArrayList<>());
			for (int j = 0; j < nodes; j++) {
				incMatrix.get(i).add(sc.nextInt());
			}
		}
		this.fromIncMatrixToAdjList();
		this.fromAdjListToAdjMatrix();
	}

	public void getAdjMatrix() {
		System.out.print("Матрица смежности данного графа:\n    ");
		for (int i = 0; i < nodes; i++) {
			System.out.print(" x" + (i + 1));
		}
		System.out.println("\n");
		for (int i = 0; i < nodes; i++) {
			System.out.print("x" + (i + 1) + " ");
			for (int j = 0; j < nodes; j++) {
				System.out.printf("%3d", adjMatrix.get(i).get(j));
			}
			System.out.println();
		}
	}

	public void setAdjMatrix() {
		System.out.print("Вводите матрицу смежности:\n ");
		for (int i = 0; i < nodes; i++) {
			adjMatrix.add(new ArrayList<>());
			for (int j = 0; j < nodes; j++) {
				adjMatrix.get(i).add(sc.nextInt());
			}
		}
		this.fromAdjMatrixToAdjList();
		this.fromAdjListToIncMatrix();
	}

	public void getAdjList() {
		System.out.println("Список смежности данного графа:");
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
		System.out.print("Вводите список смежности:\n");
		for (int i = 0; i < nodes; i++) {
			adjList.add(new ArrayList<>());
			System.out.print(" " + (i + 1) + ": ");
			int value = sc.nextInt();
			while (value != 0) {
				adjList.get(i).add(value);
				value = sc.nextInt();
			}
		}
		this.fromAdjListToAdjMatrix();
		this.fromAdjListToIncMatrix();
	}

	public void fromAdjMatrixToAdjList() {

		for (int i = 0; i < nodes; i++) {
			adjList.add(new ArrayList<>());
			for (int j = 0; j < nodes; j++) {
				if (adjMatrix.get(i).get(j) == 1) {
					adjList.get(i).add(j + 1);
				}
			}
		}

	}

	public void fromAdjListToAdjMatrix() {
		for (int i = 0; i < nodes; i++) {
			adjMatrix.add(new ArrayList<>());
			for (int j = 0; j < nodes; j++) {
				adjMatrix.get(i).add(0);
			}
			for (int j = 0; j < adjList.get(i).size(); j++) {
				if (adjList.get(i).get(j) == (i + 1)) {
					adjMatrix.get(i).set(adjList.get(i).get(j) - 1, 1);
				} else {
					adjMatrix.get(i).set(adjList.get(i).get(j) - 1, 1);
				}
			}
		}

	}

	public void fromIncMatrixToAdjList() {

		for (int i = 0; i < nodes; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < arcs; i++) {
			int k = -1;
			for (int j = 0; j < nodes; j++) {
				if (incMatrix.get(i).get(j) == -1) {
					k = j;
				}
				if (incMatrix.get(i).get(j) == 2) {
					adjList.get(j).add(j + 1);
				}
			}
			if (k >= 0) {
				for (int j = 0; j < nodes; j++) {
					if (incMatrix.get(i).get(j) == 1) {
						adjList.get(k).add(j + 1);
					}
				}
				k = -1;
			}

		}

	}

	public void fromAdjListToIncMatrix() {
		int k = 0;
		for (int i = 0; i < arcs; i++) {
			incMatrix.add(new ArrayList<>());
			for (int j = 0; j < nodes; j++) {
				incMatrix.get(i).add(0);
			}
		}
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < adjList.get(i).size(); j++) {
				if ((i + 1) == adjList.get(i).get(j)) {
					incMatrix.get(k).set(i, 2);
				} else {
					incMatrix.get(k).set(i, -1);
					incMatrix.get(k).set(adjList.get(i).get(j) - 1, 1);
				}
				k++;
			}
		}
	}

	// обход графа в глубину
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

	// обход графа в ширину
	public void breadthFirstSearch(int start) {
		queue.add(start);
		while(true) {
			for (int i = 0; i < adjList.get(start-1).size(); i++) {
				if(visitedBFS[adjList.get(start-1).get(i)]!=true) {
					queue.add(adjList.get(start-1).get(i));
				}
				visitedBFS[adjList.get(start-1).get(i)] = true;
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
