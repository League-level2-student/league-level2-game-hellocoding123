/*

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class RedTank extends GameObject{
	
	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;
	public boolean rotatingLeft = false;
	public boolean rotatingRight = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	double angle = 0.0;

	public RedTank(int x, int y, int width, int height) {
		
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;
		
		if (needImage) {
		    loadImage ("redtank.png");
		}
	}
	
	void draw(Graphics g) {
		
		g.setColor(Color.BLUE);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.rotate(angle, x+width/2, y+height/2);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
        
        g2.rotate(-angle, x+width/2, y+height/2);
        
        if(rotatingLeft == true) {
        	angle -= 0.05;
        }
        
        if(rotatingRight == true) {
        	angle += 0.05;
        }
	}
	
	public void update() {
		if (right && x < TankWars.WIDTH - width) {
			right();
		}
		
		if (left && x > 0) {
			left();
		}
		
		if (up && y > 0) {
			up();
		}
		
		if (down && y < TankWars.HEIGHT - height) {
			down();
		}
		
		super.update();
	}
	
	public void right() {
        x+=speed;
    }
	
	public void left() {
        x-=speed;
    }
	
	public void up() {
        y-=speed;
    }
	
	public void down() {
        y+=speed;
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
	/*
	public Projectile getProjectile() {
		
        return new Projectile(x+width/2, y, 10, 10);
	} 
	
}
*/