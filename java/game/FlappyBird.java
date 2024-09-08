package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.random.*;
import javax.swing.*;


// The JPanel as the canvas for the JFrame, where the components will be rendered 
public class FlappyBird extends JPanel implements ActionListener, KeyListener{

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
	
	// Game logic
	Bird bird;
	int velocityY = 0;
	// Every frame the bird will slow down by one pixel 
	int gravity = 1; 
			
	Timer gameLoop;
	
	
	
	FlappyBird() {
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		// setBackground(Color.blue);
		
		setFocusable(true);
		// Will go and check the three functions from KeyListener
		addKeyListener(this);

		// Load images
		backgroundImg = new ImageIcon(getClass().getResource("/background.png")).getImage();
		birdImg = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
        
        // Bird
        bird = new Bird(birdImg);
        
        // Game timer
        // This actionPerformed method is invoked automatically each time the timer fires (approximately 60 times per second)
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		// Background top left corner is 0,0 and the right bottom corner is 360, 640
		g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);
		
		// The bird class allows quicker access to the bird properties
		g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
	}
	
	public void move() {
		velocityY += gravity;
		bird.y += velocityY;
		bird.y = Math.max(bird.y, 0);
	}
	// Here the ActionListener will be triggered when an action occurs, such as a timer event in this case 
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		
	}


	// Any type of the key will be processed 
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			velocityY = -9;
		}
	}

	// Only character type keys will be processed 
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
