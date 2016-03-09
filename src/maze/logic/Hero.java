package maze.logic;

public class Hero extends PosElements{
	private boolean armed = false;
	private boolean alive = true;
	
	public Hero(int x, int y){
		super(x,y);
		symbol='H';
	}

	public char getSymbol() {
		if (armed) {setSymbol ('A'); return 'A';}
		setSymbol('H'); return 'H';
		}
	public void setArmed (boolean b){ armed = b;}
	public boolean getArmed() {return armed;}

	public boolean getAlive() {
		// TODO Auto-generated method stub
		return alive;
	}

	public void setAlive(boolean b) {
		alive=b;
		
	};
	
}
