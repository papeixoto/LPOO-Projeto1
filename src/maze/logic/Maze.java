package maze.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {

	private char[][] board;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean gameOver = false;
	private int difficulty; 
	private ArrayList<Dragon> dragons;


	/**Maze constructor for Board generated in MazeBuilder*/
	public Maze(int size, int numOfDragons){
		MazeBuilder mazeBuilder =  new MazeBuilder(size, numOfDragons);
		board = mazeBuilder.getBoard();
		dragons = new ArrayList<Dragon>();

		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[0].length; y++) {
				switch (board[x][y]) {
				case 'H':
					hero = new Hero(x, y);
					break;
				case 'D':
					dragons.add(new Dragon(x, y));
					break;
				case 'E':
					sword = new Sword(x, y);
					break;
				}
			}
	}

	/**Maze constructor for default Board*/
	public Maze(char[][] board) {
		this.board = board;
		dragons = new ArrayList<Dragon>();
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[0].length; y++) {
				switch (board[x][y]) {
				case 'H':
					hero = new Hero(x, y);
					break;
				case 'D':
					dragons.add(new Dragon(x, y));
					break;
				case 'E':
					sword = new Sword(x, y);
					break;
				}
			}
	}
	
	/**set game difficulty*/
	public void setDifficulty(int d){
		difficulty=d;
	}
	
	/**Board as a string*/
	public String toString(){
		String string = "";
		
		for(char[] row:board){
			for(char cell:row){
				string += cell + " ";
			}
			string+="\n";
		}
		
		return string;
	}
	
	/** get Board*/
	public char[][] getBoard(){
		return board;
	}

	/** get ArrayList of dragons*/
	public ArrayList<Dragon> getDragons()
	{
		return dragons;
	}

	/** set a specific board position with cell*/ 
	private void setCell(int x, int y, char cell){
		board[x][y]=cell;
	}

	/** check if game has finished*/
	public boolean getGameOver() {return gameOver;}
	
	/** get Hero*/
	public Hero getHero() {return hero;}

	/** update the game according to the difficulty*/
	public void update(char c){
		if(!gameOver)
		switch (difficulty) {
		case 0:
			updateEasy(c);
			break;
		case 1:
			updateMedium(c);
			break;
		case 2:
			updateHard(c);
			break;
		default:
			break;
		}
	}
	
	/** update game in easy difficulty*/
	public void updateEasy(char c){
		switch (c) {
		case 'a': processHeroMove(hero.getX(), hero.getY()-1);break;
		case 's': processHeroMove(hero.getX()+1, hero.getY());break;
		case 'd': processHeroMove(hero.getX(), hero.getY()+1);break;
		case 'w': processHeroMove(hero.getX()-1, hero.getY());break;
		default : System.out.println("Invalid command");
		}
	}
	
	/** update game in medium difficulty*/
	public void updateMedium(char c){
		switch (c) {
		case 'a': processHeroMove(hero.getX(), hero.getY()-1); if(!gameOver) updateMediumDragons(); break;
		case 's': processHeroMove(hero.getX()+1, hero.getY()); if(!gameOver) updateMediumDragons(); break;
		case 'd': processHeroMove(hero.getX(), hero.getY()+1); if(!gameOver) updateMediumDragons();break;
		case 'w': processHeroMove(hero.getX()-1, hero.getY()); if(!gameOver) updateMediumDragons();break;
		default : System.out.println("Invalid command");
		}
	}

	/** update game in hard difficulty*/
	public void updateHard(char c){
		switch (c) {
		case 'a': processHeroMove(hero.getX(), hero.getY()-1); if(!gameOver) updateHardDragons(); break;
		case 's': processHeroMove(hero.getX()+1, hero.getY()); if(!gameOver) updateHardDragons(); break;
		case 'd': processHeroMove(hero.getX(), hero.getY()+1); if(!gameOver) updateHardDragons();break;
		case 'w': processHeroMove(hero.getX()-1, hero.getY()); if(!gameOver) updateHardDragons();break;
		default : System.out.println("Invalid command");
		}
	}

	/** process hero movement,if he can move to position(x,y) if he can leave the maze and if he kills or dies when near a dragon */
	private void processHeroMove(int x, int y){
		if (board[x][y] == 'S' && dragons.isEmpty()) {moveHero(x,y); gameOver=true; return;}
		if (board[x][y] == ' ') moveHero(x, y);
		if (board[x][y] == 'E') {hero.setArmed(true); moveHero(x, y);}
		if (heroNextToDragon() && !hero.getArmed()) {gameOver=true; hero.setAlive(false);return;}
		if (hero.getArmed()) killDragons();
	}
	
	/**update in hard difficulty, dragon movement is random and he can fall asleep*/
	public void updateHardDragons() {
		for(int i=0; i<dragons.size();i++){
			dragon=dragons.get(i);
			List<Integer> newCells = new ArrayList<Integer>();

			newCells.add(0);
			if(dragon.getAwake()){
				if(board[dragon.getX()-1][dragon.getY()]==' ' || board[dragon.getX()-1][dragon.getY()]==sword.getSymbol()) newCells.add(1);
				if(board[dragon.getX()][dragon.getY()+1]==' ' ||board[dragon.getX()][dragon.getY()+1]==sword.getSymbol()) newCells.add(2);
				if(board[dragon.getX()+1][dragon.getY()]==' ' ||board[dragon.getX()+1][dragon.getY()]==sword.getSymbol()) newCells.add(3);
				if(board[dragon.getX()][dragon.getY()-1]==' ' ||board[dragon.getX()][dragon.getY()-1]==sword.getSymbol()) newCells.add(4);
			}
			if(gameOver==false){newCells.add(5);}

			switch (newCells.get(ThreadLocalRandom.current().nextInt(0, newCells.size()))){
			case 0: moveDragon(dragon.getX(),dragon.getY()); 
			System.out.println("dragon");break;
			case 1: moveDragon(dragon.getX()-1,dragon.getY());
			System.out.println("up");break;
			case 2: moveDragon(dragon.getX(),dragon.getY()+1);
			System.out.println("right");break;
			case 3: moveDragon(dragon.getX()+1,dragon.getY());;
			System.out.println("down");break;
			case 4: moveDragon(dragon.getX(),dragon.getY()-1);
			System.out.println("left");break;
			case 5: 
				if(dragon.getAwake()) {
					dragon.setAwake(false);  System.out.println("Dragon Sleeping"); }
				else  {dragon.setAwake(true);  System.out.println("Dragon Awake"); }
				moveDragon(dragon.getX(),dragon.getY()); 
				break;
			default: System.out.println("Error can't move dragon");
			}

			dragons.set(i, dragon);
		}
		if (heroNextToDragon() && !hero.getArmed()) {gameOver=true; hero.setAlive(false); return;}  
		killDragons();

	}
	/**update in medium difficulty, dragon starts moving randomly*/
	public void updateMediumDragons() {
		for(int i=0; i<dragons.size();i++){
			dragon=dragons.get(i);

			if(!dragon.getAlive()) return;
			List<Integer> newCells = new ArrayList<Integer>();

			newCells.add(0);
			if(board[dragon.getX()-1][dragon.getY()]==' ' || board[dragon.getX()-1][dragon.getY()]==sword.getSymbol()) newCells.add(1);
			if(board[dragon.getX()][dragon.getY()+1]==' ' ||board[dragon.getX()][dragon.getY()+1]==sword.getSymbol()) newCells.add(2);
			if(board[dragon.getX()+1][dragon.getY()]==' ' ||board[dragon.getX()+1][dragon.getY()]==sword.getSymbol()) newCells.add(3);
			if(board[dragon.getX()][dragon.getY()-1]==' ' ||board[dragon.getX()][dragon.getY()-1]==sword.getSymbol()) newCells.add(4);

			switch (newCells.get(ThreadLocalRandom.current().nextInt(0, newCells.size()))){
			case 0: moveDragon(dragon.getX(),dragon.getY()); dragon.setAwake(true);
			dragon.setSymbol('D');System.out.println("dragon");break;
			case 1: moveDragon(dragon.getX()-1,dragon.getY());dragon.setAwake(true);
			dragon.setSymbol('D'); System.out.println("up");break;
			case 2: moveDragon(dragon.getX(),dragon.getY()+1);dragon.setAwake(true);
			dragon.setSymbol('D'); System.out.println("right");break;
			case 3: moveDragon(dragon.getX()+1,dragon.getY());dragon.setAwake(true);
			dragon.setSymbol('D'); System.out.println("down");break;
			case 4: moveDragon(dragon.getX(),dragon.getY()-1);dragon.setAwake(true);
			dragon.setSymbol('D'); System.out.println("left");break;
			}
		}
		if(!hero.getArmed() && heroNextToDragon()) 
		{gameOver=true; hero.setAlive(false); return;} 
		killDragons();
	}

	/** if (x,y) is empty or a sword, moves dragon to that position*/
	private void moveDragon(int x, int y) {
		if(x==sword.getX() && y==sword.getY() && sword.getAvailable()){
			board[dragon.getX()][dragon.getY()]=' ';
			board[x][y]='F';
			dragon.setX(x);
			dragon.setY(y);
			return;
		}
		if(board[x][y] == ' '  || board[x][y] == 'D' || board[x][y] == 'd') {
			if(sword.getX()==dragon.getX() && sword.getY()==dragon.getY() && sword.getAvailable())
				board[dragon.getX()][dragon.getY()]=sword.getSymbol();
			else board[dragon.getX()][dragon.getY()]=' ';
			board[x][y] = dragon.getSymbol();
			dragon.setX(x);
			dragon.setY(y);
		}
	}

	/** check if hero is next to dragon*/
	public boolean heroNextToDragon(){
		if(board[hero.getX()+1][hero.getY()]=='D') return true;
		if(board[hero.getX()-1][hero.getY()]=='D') return true;
		if(board[hero.getX()][hero.getY()+1]=='D') return true;
		if(board[hero.getX()][hero.getY()-1]=='D') return true;
		if(board[hero.getX()+1][hero.getY()+1]=='D') return true;
		if(board[hero.getX()+1][hero.getY()-1]=='D') return true;
		if(board[hero.getX()-1][hero.getY()+1]=='D') return true;
		if(board[hero.getX()-1][hero.getY()-1]=='D') return true;
		return false;
	}

	/** hero kills some dragon when he is armed and next to it*/
	public void killDragons(){

		if(hero.getArmed()){
			Iterator<Dragon> it = dragons.iterator();
			while(it.hasNext()){
				dragon=it.next();
				if(hero.getX()+1==dragon.getX() && hero.getY()==dragon.getY()) {board[hero.getX()+1][hero.getY()]=' '; it.remove();}
				if(hero.getX()-1==dragon.getX()&&hero.getY()==dragon.getY()) {board[hero.getX()-1][hero.getY()]=' '; it.remove(); }
				if(hero.getX()==dragon.getX()&&hero.getY()+1==dragon.getY()) {board[hero.getX()][hero.getY()+1]=' '; it.remove(); }
				if(hero.getX()==dragon.getX()&&hero.getY()-1==dragon.getY()) {board[hero.getX()][hero.getY()-1]=' '; it.remove(); }
				if(hero.getX()+1==dragon.getX()&&hero.getY()+1==dragon.getY()) {board[hero.getX()+1][hero.getY()+1]=' '; it.remove(); }
				if(hero.getX()+1==dragon.getX()&&hero.getY()-1==dragon.getY()) {board[hero.getX()+1][hero.getY()-1]=' '; it.remove(); }
				if(hero.getX()-1==dragon.getX()&&hero.getY()+1==dragon.getY()) {board[hero.getX()-1][hero.getY()+1]=' '; it.remove(); }
				if(hero.getX()-1==dragon.getX()&&hero.getY()-1==dragon.getY()) {board[hero.getX()-1][hero.getY()-1]=' '; it.remove(); }
			}
		}

	}

	/** moves hero to (x,y)*/
	private void moveHero(int x, int y){
		setCell(hero.getX(), hero.getY(), ' ');
		hero.setX(x);
		hero.setY(y);
		setCell(x, y, hero.getSymbol());
	}
}