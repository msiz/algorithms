package com.sms.algorithms.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sms.algorithms.paths.UnionsModel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContent();
		pack();
		setVisible(true);
	}
	
	private void createContent() {
		getContentPane().add(new JLabel("Test"));
		UnionsModel model = new UnionsModel(10);
		model.connect(0, 1);
		getContentPane().add(new UnionsComponent(model));
	}

}
