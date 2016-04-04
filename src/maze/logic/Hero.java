package maze.logic;

public class Hero extends Piece{
	private boolean armed = false;
	private boolean alive = true;
	
	/**hero constructor*/
	public Hero(int x, int y){
		super(x,y);
		symbol='H';
	}
	
	/** returns hero char*/
	public char getSymbol() {
		if (armed) {setSymbol ('A'); return 'A';}
		setSymbol('H'); return 'H';
		}
	
	/** set hero armed or disarmed*/
	public void setArmed (boolean b){ armed = b;}
	
	/** check if hero is armed*/
	public boolean getArmed() {return armed;}
	
	/** check if hero is alive*/
	public boolean getAlive() {
		// TODO Auto-generated method stub
		return alive;
	}

	/** set hero alive or dead*/
	public void setAlive(boolean b) {
		alive=b;
		
	};
	
}
