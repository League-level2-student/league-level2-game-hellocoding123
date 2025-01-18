
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Projectile extends GameObject{
	
	public BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	double tdx;
	double tdy;
	
	Tank blueTank;
	Tank redTank;
	
	String tankColor;
	
	//PowerUp powUp = new PowerUp();
	/*
	enum PowerUp{
		BEAM,BIG,TRIPLE,NONE;
	}
	*/

	public Projectile(int x, int y, int width, int height, double dx, double dy, String tankColor) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		tdx=dx;
		tdy=dy;
		
		this.tankColor = tankColor;
		
		if (needImage) {
		    image = loadImage ("bullet.png");
		}
		
			
	}
	
	public Projectile(int x, int y, int width, int height, double dx, double dy, String tankColor, String powUp) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		tdx=dx;
		tdy=dy;
		System.out.println("bob");
		this.tankColor = tankColor;
		if (needImage) {
		    image = loadImage ("bullet.png");
		}
		
		switch (powUp) {
		
			case "beam": 
				if(tankColor == "blue") {
					while(blueTank.beamLoop < 12) {
						blueTank.getProjectile();
					}
					blueTank.shootPowUp = "";
				}
				
				if(tankColor == "red") {
					while(redTank.beamLoop < 12) {
						redTank.getProjectile();
					}
					redTank.shootPowUp = "";
				}
				
				break;
				
			case "big":
				break;
				
		}
		
	}
	
	void update() {
		x += tdx*5;
		y += tdy*5;
		super.update();
		
		if(x > TankWars.WIDTH || x < 0 || y > TankWars.HEIGHT || y < 0) {
			this.isActive = false;
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(image, (int)x, (int)y, width, height, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillOval((int)x, (int)y, width, height);
		}
	}
	
	BufferedImage loadImage(String imageFile) {
		BufferedImage tmpImage = null;
		
		
	        try {
	        	tmpImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	       
	    
		return tmpImage;
	    
	}
	/*
	public Projectile getProjectile() {
		
        return new Projectile(x+width/2, y, 10, 10);
	} 
	*/
}
