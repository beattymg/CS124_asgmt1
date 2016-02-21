package com;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

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
	public static ArrayList<subset> subsetList = new ArrayList<subset>();
	public static ArrayList<Double> masterout = new ArrayList<Double>();
	
	public static int V, E, T;    // V-> no. of vertices & E->no.of edges
	
	
	public static void main(String args[]) {
		int numpoints = Integer.parseInt(args[1]);
		int numtrails = Integer.parseInt(args[2]);
		int dimension = Integer.parseInt(args[3]);
		
		T = numtrails;
		V = numpoints;
		E = V -1;
		
		for (int i = 0; i < T; i++) {
			createGraph(numpoints, dimension);
			KruskalMST();
			edgeList.clear();
	        nodeList.clear();
	        subsetList.clear();
		}
		
		double total = 0;
		double avg;
		int n = masterout.size();
		for (int i = 0; i < n; i++)
			total += masterout.get(i);
		avg = ((double) total) / n;
		System.out.println(avg);
		
     }
		
	
	public static void createGraph(int n, int dimension) {
		
		Random r = new Random();
		// double randomEdgeCutoff = 1.6877*Math.pow(n, -0.732);
		double randomEdgeCutoff = 3.5*Math.pow(n, -0.732);
		double squareEdgeCutoff = 2.3983*Math.pow(n,  -0.502);
		double cubeEdgeCutoff = 1.3454*Math.pow(n, -0.282);
		double hypercubeEdgeCutoff = 1.7136*Math.pow(n, -0.257);
		// System.out.println(randomEdgeCutoff);
		
		switch (dimension) {
        case 0: for (int i = 0; i < n; i++) {
					nodeList.add(new node(i));
				}
        	
        		for (int i = 0; i < nodeList.size(); i++) {
        			for (int j = i+1; j < nodeList.size(); j++) {
        			double edgeWeight = r.nextDouble();
					if (edgeWeight < randomEdgeCutoff)
						edgeList.add(new edge(nodeList.get(i), nodeList.get(j), edgeWeight));
        			}
        		}
                break;
                
        case 2: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble(), i));
				}
			
        		for (int i = 0; i < nodeList.size(); i++) {
        			for (int j = i+1; j < nodeList.size(); j++) {
						if (Math.sqrt(Math.pow(Math.abs(nodeList.get(i).x - nodeList.get(j).x), 2) + 
								Math.pow(Math.abs(nodeList.get(i).y - nodeList.get(j).y), 2)) < squareEdgeCutoff)
						edgeList.add(new edge(nodeList.get(i), nodeList.get(j)));	
					}
				}
		        break;
		        
        case 3: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble(), r.nextDouble(), i));
				}
		
		        for (int i = 0; i < nodeList.size(); i++) {
					for (int j = i+1; j < nodeList.size(); j++) {
						if (Math.sqrt(Math.pow(Math.abs(nodeList.get(i).x - nodeList.get(j).x), 2) + 
								Math.pow(Math.abs(nodeList.get(i).y - nodeList.get(j).y), 2) + 
								Math.pow(Math.abs(nodeList.get(i).z - nodeList.get(j).z), 2)) < cubeEdgeCutoff)
						edgeList.add(new edge(nodeList.get(i), nodeList.get(j)));	
					}
				}
		        break;
		        
        case 4: for (int i = 0; i < n; i++) {
					nodeList.add(new node(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble(), i));
				}
		
		        for (int i = 0; i < nodeList.size(); i++) {
					for (int j = i+1; j < nodeList.size(); j++) {
						if (Math.sqrt(Math.pow(Math.abs(nodeList.get(i).x - nodeList.get(j).x), 2) + 
								Math.pow(Math.abs(nodeList.get(i).y - nodeList.get(j).y), 2) + 
								Math.pow(Math.abs(nodeList.get(i).z - nodeList.get(j).z), 2) + 
								Math.pow(Math.abs(nodeList.get(i).z1 - nodeList.get(j).z1), 2)) < hypercubeEdgeCutoff)
						edgeList.add(new edge(nodeList.get(i), nodeList.get(j)));	
					}
				}
		        break;
		        
        default: System.out.println("invalid dimension");
                 break;
        }
	}
	
    // A utility function to find set of an element i
    // (uses path compression technique)
    public static int find(subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
 
    // A function that does union of two sets of x and y
    // (uses union by rank)
    public static void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
 
        // If ranks are same, then make one as root and increment
        // its rank by one
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
 
    // The main function to construct MST using Kruskal's algorithm
    public static void KruskalMST()
    {
        edge result[] = new edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        for (i=0; i<V; ++i)
            result[i] = edgeList.get(i);
 
        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        
        Collections.sort(edgeList, new Comparator<edge>() {
            @Override
            public int compare(edge e1, edge e2) {
            	if (e1 == null || e2 == null) {
            		return 0;
            	}
            	if (e1.weight < e2.weight)
            		return -1;
            	if (e1.weight > e2.weight)
            		return 1;
            	return 0;
            }
        });
 
        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();
 
        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
 
        i = 0;  // Index used to pick next edge
 
        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment the index
            // for next iteration
            edge next_edge = edgeList.get(i);
            i++;
 
            int x = find(subsets, next_edge.firstNode.id);
            int y = find(subsets, next_edge.secondNode.id);
            
            
 
            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }
        
        double sum = 0;
		for (int x = 0; x < e; x++)
			sum += result[x].weight;
        masterout.add(sum);
        // System.out.println(sum);
        // print the contents of result[] to display the built MST
        // System.out.println("Following are the edges in the constructed MST");
        //double largest_edge = result[0].weight;
        // for (i = 0; i < e; ++i)
            //System.out.println(result[i].firstNode.id+" -- "+result[i].secondNode.id+" == "+
            //                   result[i].weight);
        	// System.out.println(result[i].weight);
        
        
        
    }
	
}
