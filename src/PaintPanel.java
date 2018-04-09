import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import java.io.*;
import sun.audio.*;

public class PaintPanel extends JPanel implements ActionListener {
	
	Timer timer = new Timer(1000/30, this);//Refresh rate of 30fps

	int pauseTimer = 0;
	boolean collisionEnemy = false;
	boolean collisionPwr = false;
	boolean powerUpOn = false;
	int invincibleCounter = 0;
	boolean pointFrame = false;
	
	boolean gameOver = false;
	
	int powerUpTimer = 0;
	PowerUp pwr = new PowerUp();
	double xColPwr;
	double yColPwr;
	
	int healthCounter = 0;
	int energyCounter = 0;
	int speedIncreaseTimer = 0;
	
	int finalPoints;
	String finalPointString;
	
	String notification;
	int notificationTimer=120;
	
	boolean rightBounce = false;
	boolean leftBounce = false;
	boolean topBounce = false;
	boolean bottomBounce = false;
	double radiusPowerUp = 50/2;
	double xPwrDif;
	double yPwrDif;				
	double distancePwrSquared;
	
	public PaintPanel(int x, int y){
		setSize(x,y);	//Set size of window
		repaint(); 		//First paint
		timer.start(); 	//Begin refreshing
	}
	public void paint(Graphics g){
		//System.out.println("Paint called");

		if (gameOver == false){	
			if (Main.game1.getPauseGame() == true){
				g.setColor(Color.black); //background
		        g.fillRect(0, 0, this.getWidth(), this.getHeight());
		        g.setColor(Color.orange);
		        g.setFont(new Font("default", Font.BOLD, 50));
		        switch (Main.game1.getLevel()){
		        case 1:
		        	g.drawString("Level 1", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Use the arrow keys to move.", 0, this.getHeight()/2);
			        g.drawString("Get 500 points to advance.", 0, (this.getHeight()/2)+80);
			        break;
		        case 2:
		        	g.drawString("Level 2", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Hold Shift to reduce your size.", 0, this.getHeight()/2);
			        break;
		        case 3:
		        	g.drawString("Level 3", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Bounce off the walls.", 0, this.getHeight()/2);
			        break;
		        case 4:
		        	g.drawString("Level 4", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Collect orange power ups.", 0, this.getHeight()/2);
			        break;
		        case 5:
		        	g.drawString("Level 5", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Sudden Death.", 0, this.getHeight()/2);
			        break;
		        case 6:
		        	g.drawString("Level 6", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Hyperspeed.", 0, this.getHeight()/2);
			        break;
		        case 7:
		        	g.drawString("Level 7", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Juggernauts.", 0, this.getHeight()/2);
			        break;
		        case 8:
		        	g.drawString("Level 8", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Regenerate.", 0, this.getHeight()/2);
			        break;
		        case 9:
		        	g.drawString("Level 9", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("David and Goliath.", 0, this.getHeight()/2);
			        break;
		        case 10:
		        	g.drawString("Level 10", 0,50);
			        g.setColor(Color.yellow);
			        g.drawString("Mosquito.", 0, this.getHeight()/2);
			        break;
			    default:
			    	g.drawString("You WIN!", 0, 50);
			    	g.setColor(Color.yellow);
			    	g.drawString("More levels coming soon!", 0, this.getHeight()/2);
			    	pauseTimer=0;
			    	break;
		        }
		        
		        if (pauseTimer>240){
		        	Main.game1.setPauseGame(false);
		        }
		        else{
		        	pauseTimer++;
		        }        
			}
			else{
				if (invincibleCounter==0){
					g.setColor(Color.black); //background
				}
				else{
					g.setColor(Color.white);
				}
		        g.fillRect(0, 0, this.getWidth(), this.getHeight());
		        
		        if (Main.game1.getLevel()>2 || Main.game1.getLevel()==-1){
		        	g.setColor(Color.cyan);
		        	if (leftBounce == false){
			        	g.fillRect(0,0,8,this.getHeight());
			        }
			        if (rightBounce == false){
			        	g.fillRect(this.getWidth()-8,0,8,this.getHeight());
			        }
			        if (topBounce == false){
			        	g.fillRect(0,0,this.getWidth(),8);
			        }
			        if (bottomBounce == false){
			        	g.fillRect(0,this.getHeight()-8 ,this.getWidth(),8);
			        }
		        }
		        
		        Main.player1.draw(g);
				Main.enemy1.draw(g);

				if (powerUpOn == true){
					g.setColor(Color.orange);
					g.fillOval(pwr.getX(), pwr.getY(), 50, 50);
				}
				
				g.setColor(Color.green);
				String healthString = Integer.toString(Main.player1.getHealth());
				String energyString = Integer.toString(Main.player1.getEnergy());
				String pointString = Integer.toString(Main.game1.getPoints());
				g.setFont(new Font("TimesRoman", Font.BOLD, 20));
				g.drawString("Health: "+healthString, 8, 22);
				
				if(Main.game1.getLevel()>1 || Main.game1.getLevel()==-1){
					g.setColor(Color.cyan);
					g.drawString("Energy: "+energyString, 8, 42);	
				}
				
				g.setColor(Color.yellow);
				g.drawString("Points: "+pointString, this.getWidth()-200, 22);
				
				g.setColor(Color.orange);
				if(notificationTimer<120){
					g.drawString(notification, this.getWidth()-200, 40);
					notificationTimer++;
				}
				updateGame(Main.game1.getLevel());	
			}
			
		}
		
		//Game Over Paint
		else{
			g.setColor(Color.black); //background
	        g.fillRect(0, 0, this.getWidth(), this.getHeight());
	        g.setColor(Color.red);
	        g.setFont(new Font("default", Font.BOLD, 50));
	       
	        g.drawString("Game Over", 0,50);
	        g.setColor(Color.yellow);
	        if (Main.game1.getLevel()==-1){
	        	g.drawString("You got "+finalPointString+" points on Sandbox Mode!", 0, this.getHeight()/2);
	        }
	        else{
	        	g.drawString("You got "+finalPointString+" points on level "+Main.game1.getLevel(), 0, this.getHeight()/2);
	        }
	        
		}
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource()==timer){
			Main.panel.repaint(); //repaint when timer action called
		}
		
	}
	
	public void updateGame(int level){
		
		//Movement
		if (Main.keyUpPressed == true){
			Main.player1.setYSpeed(Main.player1.getYSpeed()-Main.player1.getAcceleration());
		}
		if (Main.keyDownPressed == true){
			Main.player1.setYSpeed(Main.player1.getYSpeed()+Main.player1.getAcceleration());
		}
		if (Main.keyRightPressed == true){
			Main.player1.setXSpeed(Main.player1.getXSpeed()+Main.player1.getAcceleration());
		}
		if (Main.keyLeftPressed == true){
			Main.player1.setXSpeed(Main.player1.getXSpeed()-Main.player1.getAcceleration());
		}
		
		//Bouncing off walls
		if (Main.player1.getY()+Main.player1.getYSpeed()<0){														//Player top bounce
			Main.player1.setYSpeed(Main.player1.getYSpeed()*-4/5);
			if (Main.game1.getLevel()>2 || Main.game1.getLevel()==-1){
				topBounce = true;
			}
			playBounceSound();
		}
		if (Main.player1.getY()+Main.player1.getYSpeed()>(this.getHeight()-Main.player1.getHeight())){ 	//Player bottom bounce
			Main.player1.setYSpeed(Main.player1.getYSpeed()*-4/5);
			if (Main.game1.getLevel()>2 || Main.game1.getLevel()==-1){
				bottomBounce = true;
			}
			playBounceSound();
		}
		if (Main.player1.getX()+Main.player1.getXSpeed()<0){														//Player left bounce
			Main.player1.setXSpeed(Main.player1.getXSpeed()*-4/5);
			if (Main.game1.getLevel()>2 || Main.game1.getLevel()==-1){
				leftBounce = true;
			}
			playBounceSound();
		}
		if (Main.player1.getX()+Main.player1.getXSpeed()>(this.getWidth()-Main.player1.getWidth())){	//Player right bounce
			Main.player1.setXSpeed(Main.player1.getXSpeed()*-4/5);
			if (Main.game1.getLevel()>2 || Main.game1.getLevel()==-1){
				rightBounce = true;
			}
			playBounceSound();
		}
		if (Main.enemy1.getY()+Main.enemy1.getYSpeed()<0){														//Enemy top bounce
			Main.enemy1.setYSpeed(Main.enemy1.getYSpeed()*-4/5);
		}
		if (Main.enemy1.getY()+Main.enemy1.getYSpeed()>(this.getHeight()-Main.enemy1.getHeight())){ 	//Enemy bottom bounce
			Main.enemy1.setYSpeed(Main.enemy1.getYSpeed()*-4/5);
		}
		if (Main.enemy1.getX()+Main.enemy1.getXSpeed()<0){														//Enemy left bounce
			Main.enemy1.setXSpeed(Main.enemy1.getXSpeed()*-4/5);
		}
		if (Main.enemy1.getX()+Main.enemy1.getXSpeed()>(this.getWidth()-Main.enemy1.getWidth())){	//Enemy right bounce
			Main.enemy1.setXSpeed(Main.enemy1.getXSpeed()*-4/5);
		}
		//Player movement set based on acceleration
		Main.player1.setX(Main.player1.getX()+Main.player1.getXSpeed());							
		Main.player1.setY(Main.player1.getY()+Main.player1.getYSpeed());			
		
		//Enemy movement behaviour
		if (Main.enemy1.switchCount == 0){ 																					
			Random rand = new Random();
			Main.enemy1.setSwitchCount(rand.nextInt(180)+60);
			Main.enemy1.setYSpeed((Main.enemy1.getYSpeed()/2)+(rand.nextInt(2*Main.enemy1.getAverageSpeed())-Main.enemy1.getAverageSpeed()));
			Main.enemy1.setXSpeed((Main.enemy1.getXSpeed()/2)+(rand.nextInt(2*Main.enemy1.getAverageSpeed())-Main.enemy1.getAverageSpeed()));
		}
		if (Main.enemy1.moveCount > Main.enemy1.switchCount){
				
			Main.enemy1.setSwitchCount(0);
			Main.enemy1.setMoveCount(0);
			
		}
		if (speedIncreaseTimer > 60){
			Main.enemy1.setAverageSpeed(Main.enemy1.getAverageSpeed()+1);
			speedIncreaseTimer=0;
		}
		Main.enemy1.moveCount++;
		
		//Enemy movement set
		Main.enemy1.setX(Main.enemy1.getX()+Main.enemy1.getXSpeed());
		Main.enemy1.setY(Main.enemy1.getY()+Main.enemy1.getYSpeed());
		speedIncreaseTimer++;
				
		//Enemy collision
		
		double radiusPlayer = Main.player1.getHeight()/2;
		double radiusEnemy = Main.enemy1.getHeight()/2;
		double xColPlayer = (Main.player1.getX()+Main.player1.getWidth()/2);
		double xColEnemy = (Main.enemy1.getX()+Main.enemy1.getWidth()/2);
		double yColPlayer = (Main.player1.getY()+Main.player1.getHeight()/2);
		double yColEnemy = (Main.enemy1.getY()+Main.enemy1.getHeight()/2);
		double xDif = xColPlayer - xColEnemy;
		double yDif = yColPlayer - yColEnemy;					
		double distanceSquared = xDif * xDif + yDif * yDif;
		double distance = Math.sqrt(distanceSquared);
		if (Main.player1.getSmall()==true){
			collisionEnemy = distance < (radiusPlayer/2 + radiusEnemy);
		}
		else{
			collisionEnemy = distance < (radiusPlayer + radiusEnemy);
		}
		
		
				
		//On Enemy collision
		if (collisionEnemy == true && invincibleCounter<1){
			Main.player1.setHealth(Main.player1.getHealth()-Main.enemy1.getDamage());
			invincibleCounter = 45;
			playHurtSound();
		}	
			
		switch(level){
			case -1:
				//Power ups
				if (powerUpTimer > 600){
					Random rand = new Random();
					pwr.setX(rand.nextInt((2*this.getWidth()-100)-this.getWidth()));
					pwr.setY(rand.nextInt((2*this.getHeight()-100)-this.getWidth()));
					pwr.setRandomPowerUp();
					powerUpOn = true;
					powerUpTimer = 0;		
				}
				
				//Power up collision
				radiusPowerUp = 50/2;
				xColPwr = (Main.panel.pwr.getX()+radiusPowerUp);
				yColPwr = (Main.panel.pwr.getY()+radiusPowerUp);
				xDif = xColPlayer - xColPwr;
				yDif = yColPlayer - yColPwr;					
				distanceSquared = xDif * xDif + yDif * yDif;
				distance = Math.sqrt(distanceSquared);
				if (Main.player1.getSmall()==true){
					collisionPwr = distance < (radiusPlayer/2 + radiusPowerUp);
				}
				else{
					collisionPwr = distance < (radiusPlayer + radiusPowerUp);
				}
				
				//On power up collision
				if (collisionPwr == true && powerUpOn == true){
					switch (pwr.getPowerUpType()){
						case health: 
							Main.player1.setHealth(Main.player1.getHealthMax());
							notification = "+Health";
							notificationTimer=0;
							break;
						case energy:
							Main.player1.setEnergy(Main.player1.getEnergyMax());
							notification = "+Energy";
							notificationTimer=0;
							break;
						case slow:
							Main.enemy1.setAverageSpeed(Main.enemy1.getAverageSpeed()/3);
							Main.enemy1.setXSpeed(Main.enemy1.getXSpeed()/3);
							Main.enemy1.setYSpeed(Main.enemy1.getYSpeed()/3);
							notification = "Slow";
							notificationTimer=0;
							break;
						case points:
							Main.game1.setPoints(Main.game1.getPoints()+200);
							notification = "+200 Points";
							notificationTimer=0;
					}
					powerUpOn = false;			
				}
					
				//Bounce effects
				if (topBounce == true && bottomBounce == true && leftBounce == true && rightBounce == true){
					Main.player1.setEnergy(Main.player1.getEnergy()+25);
					if (Main.player1.getEnergy()>Main.player1.getEnergyMax()){
						Main.player1.setEnergy(Main.player1.getEnergyMax());
					}
					Main.player1.setHealth(Main.player1.getHealth()+25);
					if (Main.player1.getHealth()>Main.player1.getHealthMax()){
						Main.player1.setHealth(Main.player1.getHealthMax());
					}
					topBounce = false;
					bottomBounce = false;
					leftBounce = false;
					rightBounce = false;
					Main.game1.setPoints(Main.game1.getPoints()+100);
					playFourBounce();
				}
				break;
			case 1:
				break;
			case 2:
				
				//Player small (shift pressed) effect on energy
				if (Main.player1.getSmall()==true && Main.player1.getEnergy()>0){
				Main.player1.setEnergy(Main.player1.getEnergy()-1);
				}
				if (Main.player1.getEnergy()==0){
					Main.player1.setSmall(false);
				}
				break;
			case 3:
				
				//Player small (shift pressed) effect on energy
				if (Main.player1.getSmall()==true && Main.player1.getEnergy()>0){
				Main.player1.setEnergy(Main.player1.getEnergy()-1);
				}
				if (Main.player1.getEnergy()==0){
					Main.player1.setSmall(false);
				}
				//Bounce effects
				if (topBounce == true && bottomBounce == true && leftBounce == true && rightBounce == true){
					Main.player1.setEnergy(Main.player1.getEnergy()+25);
					if (Main.player1.getEnergy()>Main.player1.getEnergyMax()){
						Main.player1.setEnergy(Main.player1.getEnergyMax());
					}
					Main.player1.setHealth(Main.player1.getHealth()+25);
					if (Main.player1.getHealth()>Main.player1.getHealthMax()){
						Main.player1.setHealth(Main.player1.getHealthMax());
					}
					topBounce = false;
					bottomBounce = false;
					leftBounce = false;
					rightBounce = false;
					Main.game1.setPoints(Main.game1.getPoints()+100);
					playFourBounce();
				}
				break;
			case 4:
				//Player small (shift pressed) effect on energy
				if (Main.player1.getSmall()==true && Main.player1.getEnergy()>0){
				Main.player1.setEnergy(Main.player1.getEnergy()-1);
				}
				if (Main.player1.getEnergy()==0){
					Main.player1.setSmall(false);
				}
				//Power ups
				if (powerUpTimer > 600){
					Random rand = new Random();
					pwr.setX(rand.nextInt((2*this.getWidth()-100)-this.getWidth()));
					pwr.setY(rand.nextInt((2*this.getHeight()-100)-this.getWidth()));
					pwr.setRandomPowerUp();
					powerUpOn = true;
					powerUpTimer = 0;		
				}
				
				//Power up collision
				radiusPowerUp = 50/2;
				xColPwr = (Main.panel.pwr.getX()+radiusPowerUp);
				yColPwr = (Main.panel.pwr.getY()+radiusPowerUp);
				xDif = xColPlayer - xColPwr;
				yDif = yColPlayer - yColPwr;					
				distanceSquared = xDif * xDif + yDif * yDif;
				distance = Math.sqrt(distanceSquared);
				if (Main.player1.getSmall()==true){
					collisionPwr = distance < (radiusPlayer/2 + radiusPowerUp);
				}
				else{
					collisionPwr = distance < (radiusPlayer + radiusPowerUp);
				}
				
				//On power up collision
				if (collisionPwr == true && powerUpOn == true){
					switch (pwr.getPowerUpType()){
						case health: 
							Main.player1.setHealth(Main.player1.getHealthMax());
							notification = "+Health";
							notificationTimer=0;
							break;
						case energy:
							Main.player1.setEnergy(Main.player1.getEnergyMax());
							notification = "+Energy";
							notificationTimer=0;
							break;
						case slow:
							Main.enemy1.setAverageSpeed(Main.enemy1.getAverageSpeed()/3);
							Main.enemy1.setXSpeed(Main.enemy1.getXSpeed()/3);
							Main.enemy1.setYSpeed(Main.enemy1.getYSpeed()/3);
							notification = "Slow";
							notificationTimer=0;
							break;
						case points:
							Main.game1.setPoints(Main.game1.getPoints()+200);
							notification = "200 Points";
							notificationTimer=0;
					}
					powerUpOn = false;			
				}
					
				//Bounce effects
				if (topBounce == true && bottomBounce == true && leftBounce == true && rightBounce == true){
					Main.player1.setEnergy(Main.player1.getEnergy()+25);
					if (Main.player1.getEnergy()>Main.player1.getEnergyMax()){
						Main.player1.setEnergy(Main.player1.getEnergyMax());
					}
					Main.player1.setHealth(Main.player1.getHealth()+25);
					if (Main.player1.getHealth()>Main.player1.getHealthMax()){
						Main.player1.setHealth(Main.player1.getHealthMax());
					}
					topBounce = false;
					bottomBounce = false;
					leftBounce = false;
					rightBounce = false;
					Main.game1.setPoints(Main.game1.getPoints()+100);
					playFourBounce();
				}
				break;
				
			default:
				//Player small (shift pressed) effect on energy
				if (Main.player1.getSmall()==true && Main.player1.getEnergy()>0){
				Main.player1.setEnergy(Main.player1.getEnergy()-1);
				}
				if (Main.player1.getEnergy()==0){
					Main.player1.setSmall(false);
				}
				//Power ups
				if (powerUpTimer > 600){
					Random rand = new Random();
					pwr.setX(rand.nextInt((2*this.getWidth()-100)-this.getWidth()));
					pwr.setY(rand.nextInt((2*this.getHeight()-100)-this.getWidth()));
					pwr.setRandomPowerUp();
					powerUpOn = true;
					powerUpTimer = 0;		
				}
				
				//Power up collision
				radiusPowerUp = 50/2;
				double xColPwr = (Main.panel.pwr.getX()+radiusPowerUp);
				double yColPwr = (Main.panel.pwr.getY()+radiusPowerUp);
				xDif = xColPlayer - xColPwr;
				yDif = yColPlayer - yColPwr;					
				distanceSquared = xDif * xDif + yDif * yDif;
				distance = Math.sqrt(distanceSquared);
				if (Main.player1.getSmall()==true){
					collisionPwr = distance < (radiusPlayer/2 + radiusPowerUp);
				}
				else{
					collisionPwr = distance < (radiusPlayer + radiusPowerUp);
				}
				//On power up collision
				if (collisionPwr == true && powerUpOn == true){
					switch (pwr.getPowerUpType()){
						case health: 
							Main.player1.setHealth(Main.player1.getHealthMax());
							notification = "+Health";
							notificationTimer=0;
							break;
						case energy:
							Main.player1.setEnergy(Main.player1.getEnergyMax());
							notification = "+Energy";
							notificationTimer=0;
							break;
						case slow:
							Main.enemy1.setAverageSpeed(Main.enemy1.getAverageSpeed()/3);
							Main.enemy1.setXSpeed(Main.enemy1.getXSpeed()/3);
							Main.enemy1.setYSpeed(Main.enemy1.getYSpeed()/3);
							notification = "Slow";
							notificationTimer=0;
							break;
						case points:
							Main.game1.setPoints(Main.game1.getPoints()+200);
							notification = "+200 Points";
							notificationTimer=0;
					}
					powerUpOn = false;	
					playPowerUpCollect();
				}
					
				//Bounce effects
				if (topBounce == true && bottomBounce == true && leftBounce == true && rightBounce == true){
					Main.player1.setEnergy(Main.player1.getEnergy()+25);
					if (Main.player1.getEnergy()>Main.player1.getEnergyMax()){
						Main.player1.setEnergy(Main.player1.getEnergyMax());
					}
					Main.player1.setHealth(Main.player1.getHealth()+25);
					if (Main.player1.getHealth()>Main.player1.getHealthMax()){
						Main.player1.setHealth(Main.player1.getHealthMax());
					}
					topBounce = false;
					bottomBounce = false;
					leftBounce = false;
					rightBounce = false;
					Main.game1.setPoints(Main.game1.getPoints()+100);
					playFourBounce();
				}
				break;
		}			
		
		
		//What happens on Player death
		if (Main.player1.getHealth()<1){							
			gameOver=true;
			int finalPoints = Main.game1.getPoints();
			finalPointString = Integer.toString(finalPoints);
		}
		
		//Iterators		
		if (invincibleCounter>0){
			invincibleCounter--;
		}
		if (healthCounter > (120/Main.player1.getHealthRegen()) && Main.player1.getHealth() < Main.player1.getHealthMax()){
			Main.player1.setHealth(Main.player1.getHealth()+1);
			healthCounter = 0;
		}
		
		if (energyCounter > (60/Main.player1.getEnergyRegen()) && Main.player1.getEnergy() < Main.player1.getEnergyMax()){
		 
			Main.player1.setEnergy(Main.player1.getEnergy()+1);
			energyCounter = 0;
		}
		healthCounter++;
		energyCounter++;
		powerUpTimer++;
		
		pointFrame=(!pointFrame);
		if (pointFrame){
			Main.game1.setPoints(Main.game1.getPoints()+1);
		}
		
		//Win Condition
		
		if (Main.game1.getPoints()>500 && Main.game1.getLevel()!=-1){
			Main.game1.setLevel(Main.game1.getLevel()+1);
			Main.setParameters(Main.game1.getLevel());
			Main.game1.setPauseGame(true);
			Main.game1.setPoints(0);
			pauseTimer=0;
			playLevelWin();
		}
	}
	
	//Sound Effects
	private void playBounceSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("Blop.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private void playHurtSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("Ouch.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private void playFourBounce(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("FourBounce.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private void playLevelWin(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("LevelWin.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private void playPowerUpCollect(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("PowerUpCollect.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}

