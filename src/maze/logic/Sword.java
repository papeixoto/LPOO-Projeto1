package maze.logic;

public class Sword extends Piece{

	private boolean available;
	
	/**Sword constructor*/
	Sword(int x, int y) {
		super(x, y);
		symbol = 'E';
		available = true;
	}
	
	/** set sword available*/
	public void setAvailable(boolean b){available=b;}
	/** check if sword is available*/
	public boolean getAvailable() {return available;}
	
	
}