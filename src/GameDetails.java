
public class GameDetails {
	private int points;
	private int level;
	private boolean pauseGame;
	
	public GameDetails(){
		points = 0;
		level = 1;
		pauseGame=false;
	}
	
	public GameDetails(int points, int level, boolean pauseGame){
		setPoints(points);
		setLevel(level);
		setPauseGame(pauseGame);
	}
	//Setters
	public void setPoints(int points){
		this.points=points;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public void setPauseGame(boolean pauseGame){
		this.pauseGame=pauseGame;
	}

	//Getters
	public int getPoints(){
		return this.points;
	}
	public int getLevel(){
		return this.level;
	}
	public boolean getPauseGame(){
		return this.pauseGame;
	}
}
