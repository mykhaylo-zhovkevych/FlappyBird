package game;

import javax.swing.*;


public class App {

	public static void main (String[] args) {
		
		int boardWidth = 360;
		int boardHeight = 640;
		
		
		
		JFrame frame = new JFrame("Flappy Bird");

		frame.setSize(boardWidth, boardHeight );
		// This will place at the center of the window 
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FlappyBird flappyBird = new FlappyBird();
		frame.add(flappyBird);
		// pack is for correct dimension is added 
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
	
}
