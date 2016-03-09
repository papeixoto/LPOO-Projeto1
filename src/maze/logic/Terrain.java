package maze.logic;

public class Terrain {
	
	private char[][] board;
	
	public Terrain(){
		board = new char[][]{{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S'},
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};
	}
	
	public char[][] getBoard() {return board;}
}
