
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
public class PowerUps extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Random r1 = new Random();
	Random r2 = new Random();
	Random r3 = new Random();
	int randX = r1.nextInt(TankWars.HEIGHT);
	int randY = r2.nextInt(TankWars.WIDTH);
	int randPowUp = r3.nextInt(1);

	int counter;

	public PowerUps(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 0;
		
		if (needImage) {
		    loadImage ("alien.png");
		}
	}
	
	//Pixlr directions: Size: 480x480, Outline: 20, transparent background, also make purple beam 
	
	void update() {
		counter ++;
		counter %= 60;
		/*
		y += speed + 5;
		super.update();
		*/
	}
	
	void draw(Graphics g) {
		/*
		g.setColor(Color.YELLOW);
        //g.fillRect(x, y, width, height);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
        */
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
	
}
