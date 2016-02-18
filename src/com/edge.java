package com;

import java.lang.Math;

public class edge {
	
	public node firstNode = null;
	public node secondNode = null;
	public double weight;
	
	public edge() {
		
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
            weight = Math.sqrt(Math.pow(Math.abs(firstNode.x - secondNode.x), 2) + 
                     Math.pow(Math.abs(firstNode.y - secondNode.y), 2));
        }
        else if (firstNode.z1 == -1) {
            weight = Math.sqrt(Math.pow(Math.abs(firstNode.x - secondNode.x), 2) + 
                     Math.pow(Math.abs(firstNode.y - secondNode.y), 2) +
                     Math.pow(Math.abs(firstNode.z - secondNode.z), 2));
        }
        else {
            weight = Math.sqrt(Math.pow(Math.abs(firstNode.x - secondNode.x), 2) + 
                     Math.pow(Math.abs(firstNode.y - secondNode.y), 2) +
                     Math.pow(Math.abs(firstNode.z - secondNode.z), 2) +
                     Math.pow(Math.abs(firstNode.z1 - secondNode.z1), 2));
        }
		return weight;
	}
}
