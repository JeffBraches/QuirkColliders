import java.awt.Color;
import java.awt.Graphics;

public class player extends GameObject {

	private int health;
	private int healthMax;
	private int healthRegen;
	private int energy;
	private int energyMax;
	private int energyRegen;
	private int acceleration;
	private boolean small;

	public player() {
		setX(0);
		setY(0);
		setWidth(100);
		setHeight(100);
		setHealth(100);
		setHealthMax(100);
		setHealthRegen(1);
		setEnergy(100);
		setEnergyMax(100);
		setEnergyRegen(2);
		setAcceleration(1);
		setColor(Color.cyan);
	}
	public player(int x,int y, int width, int height, int maxHealth,int hRegen, int maxEnergy,int eRegen, int accel, Color color){
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setHealth(maxHealth);
		setHealthMax(maxHealth);
		setHealthRegen(hRegen);
		setEnergy(maxEnergy);
		setEnergyMax(maxEnergy);
		setEnergyRegen(eRegen);
		setAcceleration(accel);
		setColor(color);
	}

	@Override
	protected void draw(Graphics g) {
		g.setColor(getColor()); // player colour
		if (getSmall()) { // If small, draw half size player
			g.fillOval(getX() + getWidth() / 4, getY() + getHeight() / 4,
					getWidth() / 2, getHeight() / 2);
		} else { // Else, draw full size player
			g.fillOval(getX(), getY(), getWidth(), getHeight());// paint player
		}
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

	// Setters

	public void setHealth(int health) {
		this.health = health;
	}

	public void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	public void setHealthRegen(int healthRegen) {
		this.healthRegen = healthRegen;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void setEnergyMax(int energyMax) {
		this.energyMax = energyMax;
	}

	public void setEnergyRegen(int energyRegen) {
		this.energyRegen = energyRegen;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	// Getters

	public int getHealth() {
		return this.health;
	}

	public int getHealthMax() {
		return this.healthMax;
	}

	public int getHealthRegen() {
		return this.healthRegen;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getEnergyMax() {
		return this.energyMax;
	}

	public int getEnergyRegen() {
		return this.energyRegen;
	}

	public int getAcceleration() {
		return this.acceleration;
	}

	public boolean getSmall() {
		return this.small;
	}

}
