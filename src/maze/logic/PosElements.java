package maze.logic;

public class PosElements {
	protected int posX;
	protected int posY;
	protected char symbol;
	
	PosElements (int x, int y){
		posX=x;
		posY=y;
	}
	
	public int getX(){return posX;}
	public int getY(){return posY;}
	public void setX(int x){posX=x;}
	public void setY(int y){posY=y;}
	public char getSymbol() {return symbol;}
	public void setSymbol(char s) {symbol=s;}
}
