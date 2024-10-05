
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    
    public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	int currentState = MENU;
    
    Font titleFont;
    Font defaultFont;
    
    Timer frameDraw;
    Timer powerUpSpawn;
    
    Tank blueTank = new Tank(400,100,50,40, "blue");
    Tank redTank = new Tank(400,400,50,40, "red");
    
    ObjectManager objectManager = new ObjectManager(blueTank, redTank);
    

    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	defaultFont = new Font("Arial", Font.PLAIN, 20);
    	
	   	frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		 
		if (needImage) {
			 loadImage("tankwarslogo.png");
		}

	}
    
    void startGame() {
    	
        powerUpSpawn = new Timer(9000, objectManager);
        powerUpSpawn.start();
    }
    
    void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
    
	@Override
	public void paintComponent(Graphics g){
		
		if(currentState == MENU){
		    drawMenuState(g);
		}
		
		else if(currentState == GAME){
		    drawGameState(g);
		}
		
		else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	void updateMenuState() { 
		 
	}
	void updateGameState() { 

		objectManager.update();
		
		if((objectManager.redScore >= 15 || objectManager.blueScore >= 15) && Math.abs(objectManager.redScore - objectManager.blueScore) >= 2) {
			currentState = END;
		}
	}
	void updateEndState() { 
		 
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
		
		g.drawImage(image, 190, 170, 502, 192, null);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Tank Wars 2P",235, 100);
		
		
		g.setFont(defaultFont);
		g.drawString("Press ENTER To Start",292, 445);
		
		g.drawString("Press SPACE For Instructions",260, 500);
		objectManager.redScore = 0;
		objectManager.blueScore = 0;
	}
	
	void drawGameState(Graphics g) { 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT); 
		g.setFont(titleFont);
		
		objectManager.draw(g);
		
		g.setFont(defaultFont);
		
		if((objectManager.redScore >= 15 || objectManager.blueScore >= 15) && !(Math.abs(objectManager.redScore - objectManager.blueScore) >= 2)) {
			
			g.drawString("Score: Red " + objectManager.redScore + " - " + objectManager.blueScore + " Blue (2 point advantage needed to win)",10, 20);
		}
		
		else {
			
			g.drawString("Score: Red " + objectManager.redScore + " - " + objectManager.blueScore + " Blue",10, 20);
		}
		
	}
	
	void drawEndState(Graphics g)  { 
		
		if(objectManager.redScore > objectManager.blueScore) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
			
			g.setFont(titleFont);
			g.setColor(Color.WHITE);
			g.drawString("RED WINS",94, 100);
			
			g.setFont(defaultFont);
			g.drawString("Red killed blue " + objectManager.redScore + " times", 156, 400);
		}
		
		else if(objectManager.redScore == objectManager.blueScore) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
			
			g.setFont(titleFont);
			g.setColor(Color.WHITE);
			g.drawString("TIE",94, 100);
			
			g.setFont(defaultFont);
			g.drawString("Both red and blue got a score of " + objectManager.redScore, 156, 400);
		}
		
		else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, TankWars.WIDTH, TankWars.HEIGHT);
			
			g.setFont(titleFont);
			g.setColor(Color.WHITE);
			g.drawString("BLUE WINS",94, 100);
			
			g.setFont(defaultFont);
			g.drawString("Blue killed red " + objectManager.blueScore + " times", 156, 400);
		}
		
		g.drawString("Press ENTER To Play Again",135, 500);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 
		 if(currentState == MENU){
			 updateMenuState();
		 }
		 
		 else if(currentState == GAME){
			 updateGameState();
		 }
		 
		 else if(currentState == END){
			 updateEndState();
		 }
		 
		 repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			
		    if (currentState == END) {
		        currentState = MENU;
		    } 
		    
		    else {
		    	
		    	if(currentState == MENU) {
		    		startGame();
		    	}
		    	
		    	if(currentState == GAME) {
		    		powerUpSpawn.stop();
		    	}
		    	
		        currentState++;
		    }
		}   
		
		if (currentState == GAME) {
			
			//blue tank movement
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    if (blueTank.y > 0) {
			    	blueTank.up = true;
				}
			    else {
			    	blueTank.up = false;
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    blueTank.down = true;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    blueTank.rotatingLeft = true;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				blueTank.rotatingRight = true;
			}
			
			//red tank movement
			if (e.getKeyCode()==KeyEvent.VK_W) {
			    if (redTank.y > 0) {
			    	redTank.up = true;
				}
			    else {
			    	redTank.up = false;
			    }
			}
			
			if (e.getKeyCode()==KeyEvent.VK_S) {
			    redTank.down = true;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_A) {
				redTank.rotatingLeft = true;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_D) {
				redTank.rotatingRight = true;
			}
			
			//shoot
			if (e.getKeyCode()==KeyEvent.VK_SLASH && blueTank.canShoot) {
				objectManager.addProjectile(blueTank.getProjectile());
			}
			
			if (e.getKeyCode()==KeyEvent.VK_G && redTank.canShoot) {
				objectManager.addProjectile(redTank.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//blue tank key release check
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			blueTank.up = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			blueTank.down = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			blueTank.rotatingLeft = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			blueTank.rotatingRight = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_SLASH) {
			blueTank.canShoot = true;
		}
		
		//red tank key release check
		if (e.getKeyCode()==KeyEvent.VK_W) {
			redTank.up = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_S) {
			redTank.down = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_A) {
			redTank.rotatingLeft = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_D) {
			redTank.rotatingRight = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_G) {
			redTank.canShoot = true;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
