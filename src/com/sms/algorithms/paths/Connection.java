package com.sms.algorithms.paths;

public class Connection {

	public final int x;
	public final int y;
	
	private Connection(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Connection make(int x, int y) {
		return new Connection(x, y);
	}
}
