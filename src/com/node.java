package com;

public class node {
	
	public double x = -1;
	public double y = -1;
	public double z = -1;
	public double z1 = -1;
	
	
	public static void main(String[] args) {        
		
	}
	
	// 0d constructor
	public node() {
		
	}
	
	// 2d constructor
	public node(double x, double y) {
		this.x = x;
		this.y = y;
		
	}
	
	// 3d constructor
	public node(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// 4d constructor
	public node(double x, double y, double z, double z1) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.z1 = z1;
	}

}