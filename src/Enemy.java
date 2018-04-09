import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {

	int switchCount;
	int moveCount;
	int averageSpeed;
	int damage;
	
	public Enemy() {
		setX(600);
		setY(600);
		setWidth(100);
		setHeight(100);
		setAverageSpeed(8);
		setSwitchCount(0);
		setMoveCount(0);
		setDamage(50);
		setColor(Color.red);
	}

	public Enemy(int x, int y, int width, int height, int avgSpeed,
                 int switchCount, int moveCount, int damage) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setAverageSpeed(avgSpeed);
		setSwitchCount(switchCount);
		setMoveCount(moveCount);
		setDamage(damage);
		setColor(Color.red);
	}

	@Override
	protected void draw(Graphics g) {
		g.setColor(getColor()); //Enemy colour
		g.fillOval(getX(),getY(),getWidth(), getHeight());	//paint Enemy

	}

	@Override
	protected void update() {

	}

	// Setters

	public void setSwitchCount(int switchCount) {
		this.switchCount = switchCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	public void setAverageSpeed(int averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public void setDamage(int damage){
		this.damage=damage;
	}

	// Getters

	public int getSwitchCount() {
		return this.switchCount;
	}

	public int getMoveCount() {
		return this.moveCount;
	}

	public int getAverageSpeed() {
		return this.averageSpeed;
	}
	
	public int getDamage(){
		return this.damage;
	}
}
