package main;

import java.util.Scanner;

public class App {
	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int nodes, arcs;
		System.out.print("������� ����������� ������ �����:\n ");
		nodes = sc.nextInt();
		System.out.print("������� ����������� ��� �����:\n ");
		arcs = sc.nextInt();
		
		Graph graph = new Graph(nodes, arcs);

		graph.setAdjList();
		System.out.println("������� ��������� �������");
		int start = sc.nextInt();
		graph.depthFirstSearch(start);
		System.out.println();
		graph.breadthFirstSearch(start);
		
		sc.close();
	}
	
	

	// �� -1 1 0 0 0 0 1 -1 0 0 0 -1 0 1 0 1 0 0 -1 0 -1 0 0 0 1 0 0 0 1 -1 0 0 2 0 0
	// �� 0 1 0 0 1 0 0 0 1 0 0 1 1 0 0 1 0 0 0 0 0 0 0 1 0
	//5 7 2 5 0 4 0 2 3 0 1 0 4 0 1

}
