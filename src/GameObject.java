
import java.awt.Rectangle;
import java.awt.Graphics;

public class GameObject {

	double x;
	double y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	Rectangle collisionBox;
	
	public GameObject(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
		
		/*
		while(isActive == true) {
			this.x = x;
			this.y = y;
		}
		*/
	}
	
	void update() {
		
		collisionBox.setBounds((int)x, (int)y, width, height);
		
	}
}
