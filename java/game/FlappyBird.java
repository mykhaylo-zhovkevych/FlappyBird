package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.random.*;
import javax.swing.*;


// The JPanel as the canvas for the JFrame, where the components will be rendered 
public class FlappyBird extends JPanel{

	int boardWidth = 360;
	int boardHeight = 640;
	
	// Images
	Image backgroundImg;
	Image birdImg;
	Image topPipeImg;
	Image bottomPipeImg;
	
	// Bird
	int birdX = boardWidth/8;
	int birdY = boardHeight/2;
	int birdWidth = 34;
	int birdHeight = 24;
	
	class Bird {
		int x = birdX;
		int y = birdY;
		int width = birdWidth;
		int height = birdHeight;
		Image img;
		
		Bird(Image img) {
			this.img = img;
			
		}
	}
	
	// game logic
	Bird bird;
	
	
	FlappyBird() {
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		// setBackground(Color.blue);
		

		// load images
		backgroundImg = new ImageIcon(getClass().getResource("/background.png")).getImage();
		birdImg = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
        
        // bird
        bird = new Bird(birdImg);
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		// background top left corner is 0,0 and the right bottom corner is 360, 640
		g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);
		
		// the bird class allows quicker access to the bird properties
		g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
	}
	
}
