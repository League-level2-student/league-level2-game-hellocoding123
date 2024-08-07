
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {

	Tank blueTank;
	Tank redTank;

	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<PowerUps> powerUp = new ArrayList<PowerUps>();

	Random random = new Random();

	int blueScore = 0;
	int redScore = 0;

	/*
	 * public String returnValue() {
	 * 
	 * String score = redScore + " - " + blueScore; return score; }
	 */

	public ObjectManager(Tank blueTank, Tank redTank) {
		this.blueTank = blueTank;
		this.redTank = redTank;
	}

	void addProjectile(Projectile projectile) {

		projectiles.add(projectile);

	}

	void addPowerUp() {

		powerUp.add(new PowerUps(random.nextInt(TankWars.WIDTH), 0, 50, 50));

	}

	void update() {

		redTank.update();
		blueTank.update();

		for (int i = 0; i < powerUp.size(); i++) {
			powerUp.get(i).update();

			if (powerUp.get(i).y > TankWars.HEIGHT) {
				powerUp.get(i).isActive = false;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}

		checkCollision();
		purgeObjects();

	}

	void draw(Graphics g) {

		blueTank.draw(g);
		redTank.draw(g);

		for (int i = 0; i < powerUp.size(); i++) {
			powerUp.get(i).draw(g);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}

	void purgeObjects() {

		for (int i = 0; i < powerUp.size(); i++) {
			if (powerUp.get(i).isActive == false) {
				powerUp.remove(i);
				i--;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
				i--;
			}
		}
	}

	void checkCollision() {

		for (Projectile p : projectiles) {
			if (p.tankColor.equals("blue")) {
				// if touching red tank
				if (redTank.collisionBox.intersects(p.collisionBox)) {
					p.isActive = false;
					redTank.isActive = false;
					blueScore++;
					System.out.println("blue died");
				}
			}
			
			else if(p.tankColor.equals("red")) {
				if (blueTank.collisionBox.intersects(p.collisionBox)) {
					p.isActive = false;
					blueTank.isActive = false;
					redScore++;
					System.out.println("red died");
				}
			}
		}
		
		if(blueTank.collisionBox.intersects(redTank.collisionBox)) {
			blueTank.isBouncingBack = true;
			redTank.isBouncingBack = true;
		}

//		for(int i = 0; i < powerUp.size(); i++ ) {
//			if(tank.collisionBox.intersects(powerUp.get(i).collisionBox)) {
//				powerUp.get(i).isActive = false;
//			}
//		}

//		for(int i = 0; i < projectiles.size(); i++ ) {
//			

//			if(tank.collisionBox.intersects(projectiles.get(i).collisionBox)) {
//				projectiles.get(i).isActive = false;
//				redTank.isActive = false;
//				blueScore++;
//				System.out.println("blue died");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addPowerUp();
	}
}