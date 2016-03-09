package maze.logic;

public class Dragon extends PosElements{
	boolean alive;
	boolean awake;
	
	public Dragon(int x, int y){
		super(x,y);
		symbol = 'D';
		alive = true;
		awake = true;
	}
	
	public boolean getAlive() {return alive;}
	public void setAlive(boolean b){alive = b;}
	public boolean getAwake(){return awake;}
	public void setAwake(boolean b){
		awake=b; 
		if(b) symbol='D';
		else symbol='d';}
}
