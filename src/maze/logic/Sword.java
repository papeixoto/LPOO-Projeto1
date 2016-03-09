package maze.logic;

public class Sword extends PosElements{

	private boolean available;
	
	Sword(int x, int y) {
		super(x, y);
		symbol = 'E';
		available = true;
	}
	
	public void setAvailable(boolean b){available=b;}
	public boolean getAvailable() {return available;}
	
	
}