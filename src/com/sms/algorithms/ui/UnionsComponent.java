package com.sms.algorithms.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sms.algorithms.paths.Connection;
import com.sms.algorithms.paths.UnionsModel;

@SuppressWarnings("serial")
public class UnionsComponent extends Component {

	private static final Dimension DEFAULT_SIZE = new Dimension(500, 500);
	private static final int PADDING = 20;
	private static final int RADIUS = 20;
	private static final int HALF_RADIUS = RADIUS / 2;
	
	private final UnionsModel model;
	private Point[] coords; 
	private int selectedIndex = -1;
	
	public UnionsComponent(UnionsModel model) {
		this.model = model;
		setPreferredSize(DEFAULT_SIZE);
		int side = model.getSide();
		int count = side * side;
		int step = (DEFAULT_SIZE.height - 2 * PADDING) / side;
		coords = new Point[count];
		int x = PADDING;
		int y = PADDING;
		int counter = 0;
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				coords[counter++] = new Point(x + step * i, y + step * j);
			}
		}
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int button = e.getButton();
				
				int oldSelectedIndex = selectedIndex;
				int newSelectedIndex = -1;
				for (int i = 0; i < coords.length; i++) {
					Point point = coords[i];
					if (Math.sqrt(Math.pow(point.x + HALF_RADIUS - x, 2) + Math.pow(point.y + HALF_RADIUS - y, 2)) <= HALF_RADIUS) {
						newSelectedIndex = i;
						break;
					}
				}
				
				if (newSelectedIndex != -1) {
					switch (button) {
					case MouseEvent.BUTTON1:
						selectedIndex = newSelectedIndex;
						if (oldSelectedIndex != -1) {
							model.connect(selectedIndex, oldSelectedIndex);
						}
						break;
					case MouseEvent.BUTTON3:
						if (oldSelectedIndex != -1) {
							boolean connected = model.isConnected(newSelectedIndex, oldSelectedIndex);
							System.out.println(connected);
						}
						break;
					}
				}
				repaint();
			}
		});
	}
	
	public void paint(Graphics g) {
		for (Point point: coords) {
			g.fillOval(point.x, point.y, RADIUS, RADIUS);
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		for (Connection connection: model.getConnections()) {
			Point first = coords[connection.x];
			Point second = coords[connection.y];
			g.drawLine(first.x + HALF_RADIUS, first.y + HALF_RADIUS, second.x + HALF_RADIUS, second.y + HALF_RADIUS);
		}
		if (selectedIndex != -1) {
			Point point = coords[selectedIndex];
			g.setColor(Color.RED);
			g.fillOval(point.x, point.y, RADIUS, RADIUS);
			g.setColor(Color.BLACK);
		}
	}
	
}
