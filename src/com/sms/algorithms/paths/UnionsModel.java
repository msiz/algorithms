package com.sms.algorithms.paths;

import java.util.LinkedList;
import java.util.List;

public class UnionsModel {

	private final int side;
	private final List<Connection> connections = new LinkedList<Connection>();
	private final QuickFindAlgorithm algorithm; 
	
	public UnionsModel(int side) {
		this.side = side;
		algorithm = new QuickFindAlgorithm(side * side);
	}
	
	public void connect(int x, int y) {
		Connection connection = Connection.make(x, y);
		if (!connections.contains(connection)) {
			connections.add(connection);
		}
		if (!algorithm.isConnected(x, y)) {
			algorithm.connect(x, y);
		}
	}
	
	public boolean isConnected(int x, int y) {
		return algorithm.isConnected(x, y);
	}
	
	public List<Connection> getConnections() {
		return connections;
	}
	
	public int getSide() {
		return side;
	}
}
