import java.util.Random;


public class powerUp {
	
	public enum powerUpType {
		slow, health, energy, points
	}
	private powerUpType powerUp;
	private int x;
	private int y;
	
	//Setters
	public void setRandomPowerUp(){
		int pick = new Random().nextInt(powerUp.values().length);
		this.powerUp = powerUp.values()[pick];
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	//Getters
	
	public powerUpType getPowerUpType(){
		return this.powerUp;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
