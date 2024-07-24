
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Math;

import javax.imageio.ImageIO;

public class Tank extends GameObject{
	
	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;
	public boolean rotatingLeft = false;
	public boolean rotatingRight = false;
	
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;	
	
	int angle = 0;
	
	String tank;

	public Tank(int x, int y, int width, int height, String tank) {
		
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;
		
		this.tank = tank;
		
		if(tank.equals("blue")) {
			if (needImage) {
			    loadImage ("bluetank.png");
			}
		}
		else if(tank.equals("red")) {
			if (needImage) {
			    loadImage ("redtank.png");
			}
		}
	}
	
	void draw(Graphics g) {
		
        Graphics2D g2 = (Graphics2D)g;
        double radAngle = Math.toRadians(angle);
        
        g2.rotate(radAngle, x+width/2, y+height/2);
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.fillRect(x, y, width, height);
        }
        
        g2.rotate(-radAngle, x+width/2, y+height/2);
        
	}
	
	double calcDx() {
		
		double dx = 0.0;
		
		if(angle >= 0 && angle <= 90 || angle <= 0 && angle >= -90) {
			dx = Math.cos(Math.toRadians(angle));
		}
		
		if(angle > 90 && angle <= 180 || angle < -90 && angle >= -180) {
			dx = -Math.cos(Math.PI - Math.toRadians(angle));
		}
		
		if(angle > 180 && angle <= 270 || angle < -180 && angle >= -270) {
			dx = -Math.sin(3*Math.PI/2 - Math.toRadians(angle));
		}
		
		if(angle > 270 && angle < 360 || angle < -270 && angle > -360) {
			dx = Math.cos((2)*Math.PI - Math.toRadians(angle));
		}
		
		return dx;
	}
	
	double calcDy() {
		
		double dy = 0.0;
		
		if(angle >= 0 && angle <= 90 || angle <= 0 && angle >= -90) {
			dy = Math.sin(Math.toRadians(angle));
		}
		
		if(angle > 90 && angle <= 180 || angle < -90 && angle >= -180) {
			dy = Math.sin(Math.PI - Math.toRadians(angle));
		}
		
		if(angle > 180 && angle <= 270 || angle < -180 && angle >= -270) {
			dy = -Math.cos(3*Math.PI/2 - Math.toRadians(angle));
		}
		
		if(angle > 270 && angle < 360 || angle < -270 && angle > -360) {
			dy = -Math.sin((2)*Math.PI - Math.toRadians(angle));
		}
		
		return dy;
		
	}
	
	public void update() {
		
		if (up && y > 0) {
			up(1);
		}
		
		if (down && y < TankWars.HEIGHT - height) {
			down();
		}
		
		 if(rotatingLeft == true) {
	        angle -= 4;
	        angle%=360;
	     }
	        
	     if(rotatingRight == true) {
	        angle += 4;
	        angle%=360;
	     }
		
		super.update();
	}
	/*
	public void right() {
        x+=Math.sin(angle);
    }
	
	public void left() {
        x-=Math.sin(angle);
    }
	*/
	public void up(int posOrNeg) {
		double tdx = calcDx();
		double tdy = calcDy();
      x += calcDx()*4*posOrNeg;
      y += calcDy()*4*posOrNeg;
    }
	
	public void down() {
      up(-1);
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
	
	public Projectile getProjectile() {
		
        return new Projectile(x+width/2-5, y+height/2-5, 10, 10, calcDx(), calcDy(), tank);
	} 
}
