package maze.logic;

public class Piece {
	protected int posX;
	protected int posY;
	protected char symbol;
	
	/**Piece constructor*/
	Piece (int x, int y){
		posX=x;
		posY=y;
	}
	/** get posX*/
	public int getX(){return posX;}
	/**get posY*/
	public int getY(){return posY;}
	/**set posX*/
	public void setX(int x){posX=x;}
	/**set posY*/
	public void setY(int y){posY=y;}
	/**get char symbol*/
	public char getSymbol() {return symbol;}
	/**set char symbol*/
	public void setSymbol(char s) {symbol=s;}
}
