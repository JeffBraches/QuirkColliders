import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;
import java.lang.Character;


public class Main extends JFrame implements ActionListener, KeyListener {
	private enum STATE{
	MENU,
	GAME,
	SANDBOX, 
	LEVELSELECT, 
	LEVELPICK
	};
	static STATE state = STATE.MENU;
	static player player1 = new player();
	static enemy enemy1 = new enemy();
	static gameDetails game1 = new gameDetails();
	static LevelSelect level1 = new LevelSelect();
	
	static paintPanel panel= new paintPanel(1000,1000);
	static JPanel btnpanel = new JPanel();
	static JPanel levelpanel = new JPanel();
	static int levelVar;
	
	//Movement key initialization
	public static boolean keyUpPressed = false;
	public static boolean keyDownPressed = false;
	public static boolean keyLeftPressed = false;
	public static boolean keyRightPressed = false;
	
	//default game parameters
	
	public static void setParameters(int level){
		switch(level){
		
		
		case 1:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(25);
			game1.setPauseGame(true);
			break;
		
		case 2:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			game1.setPauseGame(true);
			break;
			
		case 3:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(50);
			game1.setPauseGame(true);
			break;
			
		case 4:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			game1.setPauseGame(true);
			break;
			
		case 5:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(50);
			player1.setHeight(50);
			player1.setHealth(1);
			player1.setHealthMax(1);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(50);
			enemy1.setHeight(50);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			game1.setPauseGame(true);
			break;
		
		//Hyperspeed
		case 6:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(3);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(10);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			game1.setPauseGame(true);
			break;
			
		//Juggernauts
		case 7:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(150);
			player1.setHeight(150);
			player1.setHealth(200);
			player1.setHealthMax(200);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(200);
			enemy1.setHeight(200);
			enemy1.setAverageSpeed(10);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(75);
			game1.setPauseGame(true);
			break;
		
		//Regenerate
		case 8:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(55);
			player1.setHealthMax(55);
			player1.setHealthRegen(10);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(5);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(10);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(50);
			game1.setPauseGame(true);
			break;
		
		//David and Goliath
		case 9:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(30);
			player1.setHeight(30);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(300);
			enemy1.setHeight(300);
			enemy1.setAverageSpeed(4);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(50);
			game1.setPauseGame(true);
			break;
			
		//Mosquito
		case 10:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(300);
			player1.setHeight(300);
			player1.setHealth(200);
			player1.setHealthMax(200);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(50);
			enemy1.setHeight(50);
			enemy1.setAverageSpeed(15);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			enemy1.setDamage(25);
			game1.setPauseGame(true);
			break;
		//Sandbox (or error)
		default:
			player1.setX(0);
			player1.setY(0);
			player1.setXSpeed(0);
			player1.setYSpeed(0);
			player1.setWidth(100);
			player1.setHeight(100);
			player1.setHealth(100);
			player1.setHealthMax(100);
			player1.setHealthRegen(1);
			player1.setEnergy(100);
			player1.setEnergyMax(100);
			player1.setEnergyRegen(2);
			player1.setAcceleration(1);
			enemy1.setX(600);
			enemy1.setY(600);
			enemy1.setWidth(100);
			enemy1.setHeight(100);
			enemy1.setAverageSpeed(8);
			enemy1.setSwitchCount(0);
			enemy1.setMoveCount(0);
			break;
		}
		return;
	}
	
	public Main(){
		JFrame f = new JFrame("QuirkColliders");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.addKeyListener(this);
		
		if (state == STATE.GAME){
			setParameters(1);
			f.remove(btnpanel);
			f.getContentPane().add(panel);
		}
		else if (state == STATE.LEVELPICK){
			setParameters(levelVar);
			game1.setLevel(levelVar);
			f.remove(levelpanel);
			f.getContentPane().add(panel);
		}
		else if (state == STATE.SANDBOX){
			setParameters(-1);
			game1.setLevel(-1);
			f.remove(btnpanel);
			f.getContentPane().add(panel);
		}
		else if (state == STATE.LEVELSELECT){
			f.remove(btnpanel);
			levelpanel.setLayout(null);
			levelpanel.setBounds(0, 0, 1000, 1000);
			f.add(levelpanel);
			JButton levelOne = new JButton("Level One");
			JButton levelTwo = new JButton("Level Two");
			JButton levelThree = new JButton("Level Three");
			JButton levelFour = new JButton("Level Four");
			JButton levelFive = new JButton("Level Five");
			JButton levelSix = new JButton("Level Six");
			JButton levelSeven = new JButton("Level Seven");
			JButton levelEight = new JButton("Level Eight");
			JButton levelNine = new JButton("Level Nine");
			JButton levelTen = new JButton("Level Ten");
			levelpanel.add(levelOne);
			levelpanel.add(levelTwo);
			levelpanel.add(levelThree);
			levelpanel.add(levelFour);
			levelpanel.add(levelFive);
			levelpanel.add(levelSix);
			levelpanel.add(levelSeven);
			levelpanel.add(levelEight);
			levelpanel.add(levelNine);
			levelpanel.add(levelTen);
			levelOne.setBounds(0, 100, 300, 100);
			levelTwo.setBounds(0, 200, 300, 100);
			levelThree.setBounds(0, 300, 300, 100);
			levelFour.setBounds(0, 400, 300, 100);
			levelFive.setBounds(0, 500, 300, 100);
			levelSix.setBounds(0, 600, 300, 100);
			levelSeven.setBounds(0, 700, 300, 100);
			levelEight.setBounds(0, 800, 300, 100);
			levelNine.setBounds(350, 100, 300, 100);
			levelTen.setBounds(350, 200, 300, 100);
			levelOne.addActionListener(this);
			levelTwo.addActionListener(this);
			levelThree.addActionListener(this);
			levelFour.addActionListener(this);
			levelFive.addActionListener(this);
			levelSix.addActionListener(this);
			levelSeven.addActionListener(this);
			levelEight.addActionListener(this);
			levelNine.addActionListener(this);
			levelTen.addActionListener(this);
		}
		else{
			
			btnpanel.setLayout(null);
			btnpanel.setBounds(0, 0, 1000,1000);
			f.add(btnpanel);
			JButton startButton = new JButton("Start Game");
			startButton.addActionListener(this);
			startButton.setBounds((btnpanel.getWidth()/2)-150,(btnpanel.getHeight()/2)-150,300,100);
			btnpanel.add(startButton);
			JButton sandboxButton = new JButton("Sandbox Mode");
			sandboxButton.setBounds((btnpanel.getWidth()/2)-150,(btnpanel.getHeight()/2)-50,300,100);
			btnpanel.add(sandboxButton);
			sandboxButton.addActionListener(this);
			JButton levelSelect = new JButton("Level Select");
			levelSelect.setBounds((btnpanel.getWidth()/2)-150, (btnpanel.getHeight()/2)+50, 300, 100);
			btnpanel.add(levelSelect);
			levelSelect.addActionListener(this);
		}
		f.setSize(1000, 1000);
		f.setLocation(0, 0);
		f.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Main();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: 
				keyUpPressed = true;
				//player1.setYSpeed(Main.player1.getYSpeed()-Main.player1.getAcceleration());
	            break;
			case KeyEvent.VK_DOWN:
	        	keyDownPressed = true;
				//player1.setYSpeed(Main.player1.getYSpeed()+Main.player1.getAcceleration());
	        	break;
			case KeyEvent.VK_RIGHT:
				keyRightPressed = true;
	        	//player1.setXSpeed(Main.player1.getXSpeed()+Main.player1.getAcceleration());
	            break;
			case KeyEvent.VK_LEFT:
	        	keyLeftPressed = true;
				//player1.setXSpeed(Main.player1.getXSpeed()-Main.player1.getAcceleration());
	            break;
			case KeyEvent.VK_SHIFT:
				if (game1.getLevel()>1 || game1.getLevel()==-1){
					player1.setSmall(true);	
				}
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: 
			keyUpPressed = false;
			break;
		case KeyEvent.VK_DOWN:
        	keyDownPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyRightPressed = false;
			break;
		case KeyEvent.VK_LEFT:
        	keyLeftPressed = false;
			break;
		case KeyEvent.VK_SHIFT:
			player1.setSmall(false);
			break;
	}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Start Game")){
			state = STATE.GAME;
		}
		if (e.getActionCommand().equals("Sandbox Mode")){
			state = STATE.SANDBOX;
		}
		if (e.getActionCommand().equals("Level Select")){
			state = STATE.LEVELSELECT;
		}
		if (e.getActionCommand().equals("Level One")){
			state = STATE.LEVELPICK;
			levelVar = 1;
		}
		if (e.getActionCommand().equals("Level Two")){
			state = STATE.LEVELPICK;
			levelVar = 2;
		}
		if (e.getActionCommand().equals("Level Three")){
			state = STATE.LEVELPICK;
			levelVar = 3;
		}
		if (e.getActionCommand().equals("Level Four")){
			state = STATE.LEVELPICK;
			levelVar = 4;
		}
		if (e.getActionCommand().equals("Level Five")){
			state = STATE.LEVELPICK;
			levelVar = 5;
		}
		if (e.getActionCommand().equals("Level Six")){
			state = STATE.LEVELPICK;
			levelVar = 6;
		}
		if (e.getActionCommand().equals("Level Seven")){
			state = STATE.LEVELPICK;
			levelVar = 7;
		}
		if (e.getActionCommand().equals("Level Eight")){
			state = STATE.LEVELPICK;
			levelVar = 8;
		}
		if (e.getActionCommand().equals("Level Nine")){
			state = STATE.LEVELPICK;
			levelVar = 9;
		}
		if (e.getActionCommand().equals("Level Ten")){
			state = STATE.LEVELPICK;
			levelVar = 10;
		}
		new Main();
	}
	
}
