package com;

public class node {
	
	public double x = -1;
	public double y = -1;
	public double z = -1;
	public double z1 = -1;
	public int id = -1;
	
	
	public static void main(String[] args) {        
		
	}
	
	// 0d constructor
	public node() {
		
	}
	
	public node(int id) {
		this.id = id;
	}
	
	// 2d constructor
	public node(double x, double y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	// 3d constructor
	public node(double x, double y, double z, int id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
	
	// 4d constructor
	public node(double x, double y, double z, double z1, int id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.z1 = z1;
		this.id = id;
	}

}