import java.awt.Color;
import java.awt.Graphics;


public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int xSpeed;
	protected int ySpeed;
	protected Color color;

	
	
	
	protected abstract void draw(Graphics g);
	protected abstract void update();
	
	////////// Getters && Setters///////////////////////////////
	public void setX(int x){
		this.x=x;
		return;
	}
	public void setY(int y){
		this.y=y;
		return;
	}
	public void setWidth(int width){
		this.width=width;
		return;
	}
	public void setHeight(int height){
		this.height=height;
		return;
	}
	public void setXSpeed(int xSpeed){
		this.xSpeed=xSpeed;
	}
	public void setYSpeed(int ySpeed){
		this.ySpeed=ySpeed;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	

	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public int getXSpeed(){
		return this.xSpeed;
	}
	public int getYSpeed(){
		return this.ySpeed;
	}
	public Color getColor(){
		return this.color;
	}
	
}
