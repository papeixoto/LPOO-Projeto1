package maze.logic;

public class Dragon extends Piece{
	boolean alive;
	boolean awake;
	
	/**dragon constructor*/
	public Dragon(int x, int y){
		super(x,y);
		symbol = 'D';
		alive = true;
		awake = true;
	}
	
	/**check if dragon is alive*/
	public boolean getAlive() {return alive;}
	
	/**set dragon alive or dead*/
	public void setAlive(boolean b){alive = b;}
	
	/**check if dragon is awake*/
	public boolean getAwake(){return awake;}
	
	/**set dragon awake or asleep*/
	public void setAwake(boolean b){
		awake=b; 
		if(b) symbol='D';
		else symbol='d';}
}
