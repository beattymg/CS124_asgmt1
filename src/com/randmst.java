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
	
	
	public static void main(String args[]) {
		
		int numpoints = Integer.parseInt(args[1]);
		int numtrails = Integer.parseInt(args[2]);
		int dimension = Integer.parseInt(args[3]);
		
		for (int i = 0; i < numtrails; i++) {
			createGraph(numpoints, dimension);
			kruskal();
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
	
    public static int find(subset subsets[], int nodeID)
    {
        if (subsets[nodeID].parent != nodeID) {
        	subsets[nodeID].parent = find(subsets, subsets[nodeID].parent);
        }
 
        return subsets[nodeID].parent;
    }

    public static void union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        if (subsets[xroot].rank > subsets[yroot].rank) {
        	subsets[yroot].parent = xroot;
        }
        else if (subsets[xroot].rank < subsets[yroot].rank) {
        	subsets[xroot].parent = yroot;
        }
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
 
    public static void kruskal()
    {
    	
    	int mstEdges = 0;
        edge mst[] = new edge[nodeList.size()];
        
        for (int i=0; i < nodeList.size(); ++i) {
        	mst[i] = edgeList.get(i);
        }

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
        
        subset mstSubsets[] = new subset[nodeList.size()];
        
        for(int j = 0; j < nodeList.size(); j++) {
        	mstSubsets[j] = new subset();
        }
            
        for (int k = 0; k < nodeList.size(); k++)
        {
        	mstSubsets[k].parent = k;
        	mstSubsets[k].rank = 0;
        }
 
        int counter = 0;
        while (mstEdges < nodeList.size() - 1)
        {
        	
            edge tempEdge = edgeList.get(counter);
            counter++;
 
            int a = find(mstSubsets, tempEdge.firstNode.id);
            int b = find(mstSubsets, tempEdge.secondNode.id);
            

            if (a != b)
            {
                mst[mstEdges] = tempEdge;
                mstEdges += 1;
                
                union(mstSubsets, a, b);
            }

        }
        
        double sum = 0;
		for (int x = 0; x < mstEdges; x++)
			sum += mst[x].weight;
        masterout.add(sum);
        
    }
	
}
