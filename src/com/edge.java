package com;

import java.lang.*;

public class edge {
	
	public node firstNode = null;
	public node secondNode = null;
	public double weight;
	
	public static void main(String[] args) {        
		
	}
	
	// 0d edge constructor
	public edge(node firstNode, node secondNode, double weight) {
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.weight = weight;
	}
		
	// 2d 3d 4d edge constructor
	public edge(node firstNode, node secondNode) {
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.weight = findWeight();
	}
	
	

	public double findWeight() {
		
		double weight;
		
		if (firstNode.z == -1) {
			weight = Math.abs(firstNode.x - secondNode.x) + 
					 Math.abs(firstNode.y - secondNode.y);
		}
		else if (firstNode.z1 == -1) {
			weight = Math.abs(firstNode.x - secondNode.x) + 
					 Math.abs(firstNode.y - secondNode.y) + 
					 Math.abs(firstNode.z - secondNode.z);
		}
		else {
			weight = Math.abs(firstNode.x - secondNode.x) + 
					 Math.abs(firstNode.y - secondNode.y) + 
					 Math.abs(firstNode.z - secondNode.z) +
					 Math.abs(firstNode.z1 - secondNode.z1);
			
		}
		
		return weight;
	}
}
