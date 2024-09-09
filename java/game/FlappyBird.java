package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
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
	
	// Pipes
	int pipeX = boardWidth;
	int pipeY = 0;
	int pipeWidth = 64;
	int pipeHeight = 512;
	
	class Pipe {
		int x = pipeX;
		int y = pipeY;
		int width = pipeWidth;
		int height = pipeHeight;
		Image img;
		boolean passed = false;
		
		Pipe(Image img) {
			this.img = img;
		}
	}
	
	
	// Game logic
	Bird bird;
	// Move pipes to the left speed (simulates bird moving to the right)
	int velocityX = -4; 
	// Move the bird up and down speed
	int velocityY = 0;
	// Every frame the bird will slow down by one pixel 
	int gravity = 1; 
			
	ArrayList<Pipe> pipes;
	Random random = new Random();
	
	Timer gameLoop;
	Timer placePipesTimer;
	boolean gameOver = false;
	double score = 0;
	
	
	
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
        
        // Bird Object that initialised with bird img
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();
        
        
        
        // Place Pipes Timer for adding new pipe every 1500s
        placePipesTimer = new Timer(1500, new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		placePipes();
        	}
        });
        placePipesTimer.start();
        
        
        // Game timer
        // This actionPerformed method is invoked automatically each time the timer fires (approximately 60 times per second)
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
		
	}
	
	
	public void placePipes() {
		int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
		int openingSpace = boardHeight/4;
		
		
		Pipe topPipe = new Pipe(topPipeImg);
		topPipe.y = randomPipeY;
		pipes.add(topPipe);
		
		Pipe bottomPipe = new Pipe(bottomPipeImg);
		bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
		pipes.add(bottomPipe);
		
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
		
		
		// Pipe
        for (int i = 0; i < pipes.size(); i++ ) {
        	Pipe pipe = pipes.get(i);
        	g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        
        // Score representation
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		if(gameOver) {
			g.drawString("Game Over " + String.valueOf((int) score), 10, 35);
		}
		else {
			g.drawString(String.valueOf((int) score), 10, 35);
		}
	}
	
	public void move() {
		// Bird
		velocityY += gravity;
		bird.y += velocityY;
		bird.y = Math.max(bird.y, 0);
		
		// Pipes
		for(int i = 0; i < pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			pipe.x += velocityX;
			
			if(!pipe.passed && bird.x > pipe.x + pipe.width ) {
				pipe.passed = true;
				score += 0.5;
			}
			
			if(collision(bird, pipe)) {
				gameOver = true;
			}
		}
		
		if (bird.y > boardHeight) {
			gameOver = true;
		}
	}
	
	public boolean collision(Bird b, Pipe p) {
		
		// Specific formula for the collision is a bit complicated
		return b.x < p.x + p.width && 
				b.x + b.width > p.x &&
				b.y < p.y + p.height &&
				b.y + b.height > p.y;
	}
	
	
	// Here the ActionListener will be triggered when an action occurs, such as a timer event in this case 
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		
		if (gameOver) {
			placePipesTimer.stop();
			gameLoop.stop();
		}
	}


	// Any type of the key will be processed 
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			velocityY = -9;
			
			if(gameOver) {
				// Restart the game by resetting the conditions
				bird.y = birdY;
				velocityY = 0;
				pipes.clear();
				score = 0;
				gameOver = false;
				gameLoop.start();
				placePipesTimer.start();
			}
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


// 36:12 / 54:01

