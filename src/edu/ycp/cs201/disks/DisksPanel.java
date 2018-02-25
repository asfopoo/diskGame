package edu.ycp.cs201.disks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DisksPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400; // constant width
	public static final int HEIGHT = 300; // constant height
	
	private Timer timer; // declares timer
	private int x;  //  x coord
	private int y;  // y coord
	private Random rand;  // random value for radius
	private int radius; // radius
	private Disk[] disks; // array that holds the disk objects
	private int diskCount; //disk counter 
	private int check = 0; // variable that checks if game is over
	int count = 350; // timer variable 
	private int z = 10;
	private HighScore [] high;
	String initials;
	int score;
	private int scoreCheck = 0;
	
	
	public DisksPanel() {
		
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.GRAY);
		rand = new Random(); // gets new random number in rand variable
		disks= new Disk[500]; // array of disks whith 500 spaces of memory 
		diskCount = 0; // disk count set to 0
		radius = 30; //initially set radius to 30
		high = new HighScore[9];
		
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				handleMouseMove(e);
			}
		});
		
		// Schedule timer events to fire every 100 milliseconds (0.1 seconds)
		this.timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerEvent(e);
				
				
			}
		});
		timer.start(); // starts timer
	}

	// You can call this method to convert a DiskColor value into
	// a java.awt.Color object.
	public Color colorOf(DiskColor dc) {
		return new Color(dc.red(), dc.green(), dc.blue()); // creates new color
	}

	// This method is called whenever the mouse is moved
	protected void handleMouseMove(MouseEvent e) {
		
		this.x = e.getX(); // gets mouse coordinates
		this.y = e.getY();		
		
		repaint();	//repaint panel	
	}
	
	// This method is called whenever the mouse is clicked
		protected void handleMouseClick(MouseEvent e) {
		if(check < 1) { // if statement to verify game is not over ... if check = 1 or more, game over
		
			timer.restart();// restarts timer everytime mouse is clicked 
			count = 350; // sets count which is the timer location on the screen 
			z += 5; // increases speed of timer each turn !*!*!*!*!*!*!*!*!*!*!*!*!*!* EXTRA CREDIT *!*!*!*!*!*!*!*!**!
			
			this.x = e.getX(); 
			this.y = e.getY();
			
			int randColor = rand.nextInt(15);; //  makes random
			DiskColor newDiskColor = DiskColor.values()[randColor];	// stores color
			Disk bounds = new Disk(x, y, radius , newDiskColor ); // creates new disk object
			
			if(diskCount > 0) { // as long as one disk already exists
				if (bounds.isOutOfBounds(WIDTH, HEIGHT)){ //false = legal move
					check++;
					repaint();
				} 	
				for(int i = 0; i < diskCount; i ++) {//creates for loop to run through array
					if (bounds.overlaps(disks[i]) == true) {// false = legal move
						check++; // increments check variable
					}
				}				
			}
			diskCount ++;	// increments disk counter			
			disks[diskCount - 1] = bounds;	// stored the current disk			
			repaint();			
			radius = rand.nextInt(35) + 10; // sets the new radius between 10 and 44
			if (check > 0) {
				timer.stop();
			}
		}	
	}
	
	// This method is called whenever a timer event fires
	protected void handleTimerEvent(ActionEvent e) {
		count -= z; // decrements counter, this variable sets location on the screen of red bar
		
		if(count == 0) { // if  count = 0 the player ran out if time and the game is over
			check++;
		}
		repaint();		
	}
	
	private static final Font FONT = new Font("Dialog", Font.BOLD, 26);

	// This method is called automatically whenever the contents of
	// the window need to be redrawn.
	@Override
	public void paintComponent(Graphics g) {
		// Paint the window background
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawOval(x - radius, y - radius, radius * 2 , radius * 2); // draws empty oval over the mouse pointer
		
		for (int i = 0; i < diskCount ; i++) { //cycles through array and prints the disk objects
			DiskColor dc = disks[i].getColor();
			Color c= colorOf(dc);
			g.setColor(c);
			g.fillOval((int)disks[i].getX() - (int)disks[i].getRadius(),(int)disks[i].getY() - (int)disks[i].getRadius(), (int)disks[i].getRadius() * 2,(int)disks[i].getRadius() * 2);	
		}	
		
		Font f = new Font(Font.DIALOG, Font.BOLD, 26); // sets color and font for the strings 
		g.setFont(f);
		g.setColor(Color.BLACK);
		if(check > 0) { // check is more than 0 -> game is over
			g.drawString("Game over", 125, 150);// first number is width, second number is height
		}	
		g.drawString("" + diskCount, 350, 250);	// prints the disk counter 
		Color barColor = new Color(255, 23, 23, 63); // creates the reddish timer color
		g.setColor(barColor);
		g.fillRect(0, 260, count, 20); // prints the redish timer
	
	}	
	public void checkScore(){
		
		
		HighScore winner = new HighScore(initials, score);
		for(int i = 0; i < high.length; i++) {
			high[i].setScore(5); 
			high[i].setInitials(initials);
		}
		
		for(int j = 0; j < high.length; j++) {
			if(high[j].getScore() < diskCount) {
				scoreCheck ++;
			}
		}
		if(scoreCheck > 0) {
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
