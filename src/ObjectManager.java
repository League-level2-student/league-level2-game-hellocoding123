
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{

	Tank tank;
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<PowerUps> powerUp = new ArrayList<PowerUps>();
	
	Random random = new Random();
	
	int  blueScore = 0;
	int redScore = 0;
	
	 /*
	public String returnValue() {
		
		String score = redScore + " - " + blueScore;
		return score;
	}
	*/
	
	public ObjectManager(Tank tank) {
		this.tank = tank;
	}
	
	void addProjectile(Projectile projectile) {
		
		projectiles.add(projectile);
		

	}
	
	void addPowerUp() {
		
		powerUp.add(new PowerUps(random.nextInt(TankWars.WIDTH),0,50,50));
		
	}
	
	void update() {
		
		for(int i = 0; i < powerUp.size(); i++) {
			powerUp.get(i).update();
			
			if(powerUp.get(i).y > TankWars.HEIGHT) {
				powerUp.get(i).isActive = false;
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++ ) {
			projectiles.get(i).update();
		}
		
		if(tank.isActive == true) {
			//checkCollision();
			purgeObjects();
		} 
		

	}
	
	void draw(Graphics g) {
		
		tank.draw(g);
		
		for(int i = 0; i < powerUp.size(); i++ ) {
			powerUp.get(i).draw(g);
		}
		
		for(int i = 0; i < projectiles.size(); i++ ) {
			projectiles.get(i).draw(g);
		}
	}
	
	void purgeObjects() {
		
		for(int i = 0; i < powerUp.size(); i++ ) {
			if(powerUp.get(i).isActive == false) {
				powerUp.remove(i);
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++ ) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}
	/*
	void checkCollision() {
		
		for(int i = 0; i < powerUp.size(); i++ ) {
			if(tank.collisionBox.intersects(powerUp.get(i).collisionBox)) {
				powerUp.get(i).isActive = false;
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++ ) {
			if(tank.collisionBox.intersects(projectiles.get(i).collisionBox)) {
				projectiles.get(i).isActive = false;
				//redTank.isActive = false;
				blueScore++;
				System.out.println("blue died");
			}
		}
	}
	*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addPowerUp();
	}
}