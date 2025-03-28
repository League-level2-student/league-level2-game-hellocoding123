
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tank extends GameObject{
	
	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;
	public boolean rotatingLeft = false;
	public boolean rotatingRight = false;
	
	public BufferedImage image;
	public BufferedImage imageWhiteTank;
	public boolean needImage = true;
	public boolean gotImage = false;	
	
	boolean canShoot = true;
	
	boolean isBouncingBack = false;
	int bounceDistance = 20;
	
	boolean isHit = false;
	
	int angle = 0;
	
	int shieldLoop = 0;
	
	int blinks = 0;
	public boolean isBlinking = false;
	
	String tankColor;
	int counter = 0;
	
	int beamLoop = 0;
	
	String shootPowUp = "";
	
	Random r = new Random();
	int rand = 0;

	public Tank(int x, int y, int width, int height, String tankColor) {
		
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;
		
		this.tankColor = tankColor;
		
		if(tankColor.equals("blue")) {
			if (needImage) {
			    image = loadImage ("bluetank.png");
			}
		}
		else if(tankColor.equals("red")) {
			if (needImage) {
			    image = loadImage ("redtank.png");
			}
		}
		imageWhiteTank = loadImage("whitetank.png");		
				
	}
	
	void draw(Graphics g) {
		
        Graphics2D g2 = (Graphics2D)g;
        double radAngle = Math.toRadians(angle);
        
        g2.rotate(radAngle, x+width/2, y+height/2);
        
        if(isHit && blinks < 6 || shootPowUp == "shield") {
        	
        	isBlinking = true;
        	
        	if(counter >= 10) {
		        if (gotImage) {
		        	g.drawImage(image, (int)x, (int)y, width, height, null);
		        } else {
		        	g.fillRect((int)x, (int)y, width, height);
		        }
        	}else {
        		g.drawImage(imageWhiteTank, (int)x, (int)y, width, height, null);
        	}
        	if(counter == 0) {
        		blinks++;
        		shieldLoop++;
        		if(shieldLoop >= 16) {
        			shootPowUp = "";
        		}
        	}
        	
        }
        else {
        	if (gotImage) {
	        	g.drawImage(image, (int)x, (int)y, width, height, null);
	        } else {
	        	g.fillRect((int)x, (int)y, width, height);
	        }
        	
        	if(blinks >= 6) {
        		isBlinking = false;
        	}
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
		counter ++;
		counter %= 20;

		
		if(isBouncingBack && bounceDistance > 0) {
			for(int i = 0; i < bounceDistance; i++) {
				down();
			}
			
			bounceDistance /= 2;
		}
		
		else if(bounceDistance <= 0) {
			bounceDistance = 7;
			isBouncingBack = false;
		}
		
		else {
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
	
	
	BufferedImage loadImage(String imageFile) {
		BufferedImage tmpImage = null;
		
		if (needImage) {
	        try {
	        	tmpImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
		return tmpImage;
	    
	}
	
//	enum PowerUp{
//		BEAM,BIG,TRIPLE,NONE;
//	}
	
	public Projectile getProjectile() {
		
		if(shootPowUp == "") {
			canShoot = false;
        	return new Projectile((int)x+width/2-6, (int)y+height/2-6, 10, 10, calcDx(), calcDy(), tankColor);
		}
		
		else {
			if(shootPowUp == "big") {
				shootPowUp = "";
				canShoot = false;
        		return new Projectile((int)x+width/2-50, (int)y+height/2-60, 100, 100, calcDx(), calcDy(), tankColor, shootPowUp);
			}
			
			if(shootPowUp == "beam") {
//				for(int i = 0; i < 6; i++) {
//					shootPowUp = "";
//					return new Projectile((int)x+width/2 - i, (int)y+height/2-6, 10, 10, calcDx(), calcDy(), tankColor);
//				}
				
				if(beamLoop >= 20) {
					canShoot = false;
					shootPowUp = "";
				}
				rand = r.nextInt(30) - 9;
				beamLoop++;
				return new Projectile((int)x+width/2 - rand, (int)y+height/2 - rand, 5, 5, calcDx(), calcDy(), tankColor);
				
			}
			if(!(shootPowUp == "shield")) {
				shootPowUp = "";
			}
			canShoot = false;
			return new Projectile((int)x+width/2-6, (int)y+height/2-6, 10, 10, calcDx(), calcDy(), tankColor);
		}
		
	}

	public void isHit() {
		// TODO Auto-generated method stub
		counter = 0;
		blinks = 0;
		isHit = true;
		
	} 
}
