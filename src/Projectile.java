
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	double tdx;
	double tdy;
	
	String tank;

	public Projectile(int x, int y, int width, int height, double dx, double dy, String tank) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		tdx=dx;
		tdy=dy;
		
		this.tank = tank;
		
		if (needImage) {
		    loadImage ("bullet.png");
		}
	}
	
	void update() {
		x += tdx*5;
		y += tdy*5;
		super.update();
	}
	
	void draw(Graphics g) {
		
		g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
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
	*/
}
