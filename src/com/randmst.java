package com;

import java.util.Random;
import java.util.ArrayList;
/*
 * 
 * Matthew Beatty & Henry Dornier
 * CS 124
 * Programming Assignment 1
 * 2/16/16
 */

public class randmst {
	
	public static ArrayList<node> nodeList = new ArrayList<node>();
	public static ArrayList<edge> edgeList = new ArrayList<edge>();
	
	
	public static void main(String args[]) {
		int numpoints = Integer.parseInt(args[1]);
		int numtrails = Integer.parseInt(args[2]);
		int dimension = Integer.parseInt(args[3]);
		
		createGraph(numpoints, dimension);
		
		for (edge edge : edgeList) {
			System.out.println(edge.weight);
		}
		
     }
		
	
	public static void createGraph(int n, int dimension) {
		
		Random r = new Random();
		
		switch (dimension) {
        case 0: for (int i = 0; i < n; i++) {
					nodeList.add(new node());
				}
        
				for (node node : nodeList) {
					for (node other_node : nodeList) {
						edgeList.add(new edge(node, other_node, r.nextDouble()));
					}
				}
                break;
                
        case 2: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble()));
				}
		
				for (node node : nodeList) {
					for (node other_node : nodeList) {
						edgeList.add(new edge(node, other_node));
					}
				}
		        break;
		        
        case 3: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble(), r.nextDouble()));
				}
		
				for (node node : nodeList) {
					for (node other_node : nodeList) {
						edgeList.add(new edge(node, other_node));
					}
				}
		        break;
		        
        case 4: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble()));
				}
		
				for (node node : nodeList) {
					for (node other_node : nodeList) {
						edgeList.add(new edge(node, other_node));
					}
				}
		        break;
		        
        default: System.out.println("invalid dimension");
                 break;
        }
	}
}
