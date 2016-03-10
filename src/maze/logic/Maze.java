package maze.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {

	private char[][] board;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean gameOver=false;
	private String difficulty;

	public Maze() {
		Terrain terrain = new Terrain();
		board = terrain.getBoard();
		hero = new Hero(1,1);
		board[1][1]=hero.getSymbol();
		dragon = new Dragon(3,1);
		board[3][1]=dragon.getSymbol();
		sword = new Sword(8,1);
		board[8][1] = sword.getSymbol();
	}
	
	public Maze(char [][] board){
		this.board=board;
		
		for(int x=0; x<board.length; x++)
			for(int y=0; y<board[0].length;y++){
				switch (board[x][y]){
				case 'H': hero=new Hero(x,y); break;
				case 'D': dragon=new Dragon(x,y); break;
				case 'E': sword=new Sword(x,y);break;
				}
			}
	}
	
	public char[][] getBoard(){
		return board;
	}

	private char getCell(int x, int y){
		return board[x][y];
	}

	private void setCell(int x, int y, char cell){
		board[x][y]=cell;
	}

	public boolean getGameOver() {return gameOver;}
	public Hero getHero() {return hero;}

	//update no easy, tirando o update dragons
	
	public void updateEasy(char c){
		switch (c) {
		case 'a': processHeroMoveEasy(hero.getX(), hero.getY()-1);break;
		case 's': processHeroMoveEasy(hero.getX()+1, hero.getY());break;
		case 'd': processHeroMoveEasy(hero.getX(), hero.getY()+1);break;
		case 'w': processHeroMoveEasy(hero.getX()-1, hero.getY());break;
		default : System.out.println("Invalid command");
		}
	}
	public void updateMedium(char c){
		switch (c) {
		case 'a': processHeroMove(hero.getX(), hero.getY()-1); if(!gameOver) updateMediumDragons(); break;
		case 's': processHeroMove(hero.getX()+1, hero.getY()); if(!gameOver) updateMediumDragons(); break;
		case 'd': processHeroMove(hero.getX(), hero.getY()+1); if(!gameOver) updateMediumDragons();break;
		case 'w': processHeroMove(hero.getX()-1, hero.getY()); if(!gameOver) updateMediumDragons();break;
		default : System.out.println("Invalid command");
		}
	}
	
	public void updateHard(char c){
		switch (c) {
		case 'a': processHeroMove(hero.getX(), hero.getY()-1); if(!gameOver) updateHardDragons(); break;
		case 's': processHeroMove(hero.getX()+1, hero.getY()); if(!gameOver) updateHardDragons(); break;
		case 'd': processHeroMove(hero.getX(), hero.getY()+1); if(!gameOver) updateHardDragons();break;
		case 'w': processHeroMove(hero.getX()-1, hero.getY()); if(!gameOver) updateHardDragons();break;
		default : System.out.println("Invalid command");
		}
	}
	
	private void processHeroMoveEasy(int x, int y){
		if (board[x][y] == 'S' && !dragon.getAlive()) {moveHero(x,y); gameOver=true; return;}
		if (board[x][y] == ' ') moveHero(x, y);
		if (board[x][y] == 'E') {hero.setArmed(true); moveHero(x, y);}
		if (heroNextToDragon() && !hero.getArmed()) {gameOver=true; hero.setAlive(false);return;}
		if (hero.getArmed()) killDragons();
	}
	
	private void processHeroMove(int x, int y){
		if (board[x][y] == 'S' && !dragon.getAlive()) {moveHero(x,y); gameOver=true; return;}
		if (board[x][y] == ' ') moveHero(x, y);
		if (board[x][y] == 'E') {hero.setArmed(true); moveHero(x, y);}
		if (heroNextToDragon() && !hero.getArmed()) {gameOver=true; hero.setAlive(false);return;}
		if (hero.getArmed()) killDragons();
	}
//update no modo em q o dragao se move aleatoriamente e tambem pode adormecer
	public void updateHardDragons() {
		if(!dragon.getAlive()) return;
		List<Integer> newCells = new ArrayList<Integer>();

		newCells.add(0);
		if(dragon.getAwake()){
		if(board[dragon.getX()-1][dragon.getY()]==' ' || board[dragon.getX()-1][dragon.getY()]==sword.getSymbol()) newCells.add(1);
		if(board[dragon.getX()][dragon.getY()+1]==' ' ||board[dragon.getX()][dragon.getY()+1]==sword.getSymbol()) newCells.add(2);
		if(board[dragon.getX()+1][dragon.getY()]==' ' ||board[dragon.getX()+1][dragon.getY()]==sword.getSymbol()) newCells.add(3);
		if(board[dragon.getX()][dragon.getY()-1]==' ' ||board[dragon.getX()][dragon.getY()]==sword.getSymbol()) newCells.add(4);
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

		if (heroNextToDragon()) {gameOver=true; hero.setAlive(false); return;}  
		killDragons();
	}
//update no modo em que o dragao se move
	public void updateMediumDragons() {
		if(!dragon.getAlive()) return;
		List<Integer> newCells = new ArrayList<Integer>();

		newCells.add(0);
		if(board[dragon.getX()-1][dragon.getY()]==' ' || board[dragon.getX()-1][dragon.getY()]==sword.getSymbol()) newCells.add(1);
		if(board[dragon.getX()][dragon.getY()+1]==' ' ||board[dragon.getX()][dragon.getY()+1]==sword.getSymbol()) newCells.add(2);
		if(board[dragon.getX()+1][dragon.getY()]==' ' ||board[dragon.getX()+1][dragon.getY()]==sword.getSymbol()) newCells.add(3);
		if(board[dragon.getX()][dragon.getY()-1]==' ' ||board[dragon.getX()][dragon.getY()]==sword.getSymbol()) newCells.add(4);

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

		if(!hero.getArmed() && heroNextToDragon()) 
		{gameOver=true; hero.setAlive(false); return;} 
		killDragons();
	}

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

	private boolean heroNextToDragon(){
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

	private void killDragons(){
		if(hero.getArmed()){
			if(board[hero.getX()+1][hero.getY()]=='D') {board[hero.getX()+1][hero.getY()]=' '; dragon.setAlive(false);}
			if(board[hero.getX()-1][hero.getY()]=='D') {board[hero.getX()-1][hero.getY()]=' '; dragon.setAlive(false);}
			if(board[hero.getX()][hero.getY()+1]=='D') {board[hero.getX()][hero.getY()+1]=' '; dragon.setAlive(false);}
			if(board[hero.getX()][hero.getY()-1]=='D') {board[hero.getX()][hero.getY()-1]=' '; dragon.setAlive(false);}
			if(board[hero.getX()+1][hero.getY()+1]=='D') {board[hero.getX()+1][hero.getY()+1]=' '; dragon.setAlive(false);}
			if(board[hero.getX()+1][hero.getY()-1]=='D') {board[hero.getX()+1][hero.getY()-1]=' '; dragon.setAlive(false);}
			if(board[hero.getX()-1][hero.getY()+1]=='D') {board[hero.getX()-1][hero.getY()+1]=' '; dragon.setAlive(false);}
			if(board[hero.getX()-1][hero.getY()-1]=='D') {board[hero.getX()-1][hero.getY()-1]=' '; dragon.setAlive(false);}
		}
		
		}

	private void moveHero(int x, int y){
		setCell(hero.getX(), hero.getY(), ' ');
		hero.setX(x);
		hero.setY(y);
		setCell(x, y, hero.getSymbol());
	}
}

