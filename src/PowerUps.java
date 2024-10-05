
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
public class PowerUps extends GameObject{
	
	public BufferedImage image;
	public static boolean gotImage = false;	
	
	/*
	Random r1 = new Random();
	Random r2 = new Random();
	*/
	Random r3 = new Random();
	/*
	int randX = r1.nextInt(TankWars.HEIGHT);
	int randY = r2.nextInt(TankWars.WIDTH);
	*/
	int randPowUp;
	
	
	int counter;

	public PowerUps(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 0;
		
		randPowUp = r3.nextInt(3)+1;
			
		
		
		switch (randPowUp) {
			
			case 1:
				
					image = loadImage ("threeBullets.png");	
				
				break;
					
			case 2:
				
					image = loadImage ("shield.png");
				
				break;
					
			case 3:
				
					image = loadImage ("beam.png");
				
				break;
			
		}
		
	}
	
	//Pixlr directions: Size: 480x480, Outline: 20, transparent background, also make purple beam 
	
	void update() {
		counter ++;
		counter %= 300;
		
		//super.update();
		
	}
	
	void draw(Graphics g) {
		
		g.setColor(Color.YELLOW);
        //g.fillRect(x, y, width, height);
        
        if (gotImage) {
        	g.drawImage(image, (int)x, (int)y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect((int)x, (int)y, width, height);
        }
        
	}
	
	/*
	void loadImage(String imageFile) {
	    
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	            gotImage = true;
	        } 
	        
	        catch (Exception e) {
	            
	        }
	        
	    
	}
	*/
	
	BufferedImage loadImage(String imageFile) {
		BufferedImage tmpImage = null;
		
		
	        try {
	        	tmpImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	       
	    
		return tmpImage;
	    
	}
	
}
