package labb1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class display extends JLabel{
	static display label = new display();
	static Color color12 = Color.red;
	
	static String title = "Circle Coloring Ultra Machine";
	static JFrame frame = new JFrame(title);
	
	public static ActionListener changeColorButton(String color) 
	{ 
		switch(color) {
		case "Blue":
			return event -> {
				color12 = Color.blue;
				label.repaint();
				};
			
		case "Red":
			return event -> {
				color12 = Color.red;
				label.repaint();
				};
			
		case "Green":
			return event -> {
				color12 = Color.green;
				label.repaint();
				};
			
		default:
			return null;
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color12);
		g.fillOval(100, 50, 200, 200);
	}
	public static void main(String[] args) 
	{
		frame.setSize(400, 400);
		label.setSize(300,300);
		frame.add(label);
		label.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		JButton bButton = new JButton("Blue");
		JButton rButton = new JButton("Red");
		JButton gButton = new JButton("Green");
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(bButton);
		panel.add(gButton);
		panel.add(rButton);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.SOUTH);
		
		color12 = Color.red;
		label.repaint();
		
		bButton.addActionListener(
			changeColorButton("Blue"));
		rButton.addActionListener(
			changeColorButton("Red"));
		gButton.addActionListener(
			changeColorButton("Green"));
		
		
	}
}
