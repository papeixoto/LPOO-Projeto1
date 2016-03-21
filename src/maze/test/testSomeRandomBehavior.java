package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Dragon;
import maze.logic.Hero;
import maze.logic.Maze;
import maze.logic.Sword;

public class testSomeRandomBehavior {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'D', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

	char [][] m2 =
		{{'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', 'X', ' ', ' ', ' ', 'S'},
				{'X', ' ', 'X', ' ', 'D', ' ', 'X'},
				{'X', ' ', 'X', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', ' ', ' ', ' ', 'X'},
				{'X', 'E', 'X', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X'}};		


	@Test
	public void testModesMedium() {
		boolean noMove = false, moveUp=false, moveDown = false, moveLeft=false, moveRight=false;
		Maze maze=new Maze(m2);



		while(!noMove || !moveUp || !moveDown || !moveLeft || !moveRight){
			int x=maze.getDragons().get(0).getX();
			int y =maze.getDragons().get(0).getY();
			maze.updateMedium('s');
			char[][] board = maze.getBoard();
			if(board[x][y]==maze.getDragons().get(0).getSymbol())
			{
				noMove=true;

			}
			if(board[x-1][y]==maze.getDragons().get(0).getSymbol())
			{
				moveUp=true;

			}
			if(board[x+1][y]==maze.getDragons().get(0).getSymbol())
			{
				moveDown=true;

			}
			if(board[x][y-1]==maze.getDragons().get(0).getSymbol())
			{
				moveLeft=true;

			}
			if(board[x][y+1]==maze.getDragons().get(0).getSymbol())
			{
				moveRight=true;

			}
		}
		assertEquals(true,noMove && moveUp && moveDown && moveLeft && moveRight);
	}
	
	@Test
	public void testModesHard() {
		boolean noMove = false, moveUp=false, moveDown = false, moveLeft=false, moveRight=false, changeState = false;
		Maze maze=new Maze(m2);



		while(!noMove || !moveUp || !moveDown || !moveLeft || !moveRight || !changeState){
			int x=maze.getDragons().get(0).getX();
			int y =maze.getDragons().get(0).getY();
			char c=maze.getDragons().get(0).getSymbol();
			maze.updateHard('s');
			char[][] board = maze.getBoard();
			if(board[x][y]==maze.getDragons().get(0).getSymbol())
			{
				noMove=true;
				if(board[x][y]!=c) changeState=true;

			}
			if(board[x-1][y]==maze.getDragons().get(0).getSymbol())
			{
				moveUp=true;

			}
			if(board[x+1][y]==maze.getDragons().get(0).getSymbol())
			{
				moveDown=true;

			}
			if(board[x][y-1]==maze.getDragons().get(0).getSymbol())
			{
				moveLeft=true;

			}
			if(board[x][y+1]==maze.getDragons().get(0).getSymbol())
			{
				moveRight=true;

			}
		}
		assertEquals(true,noMove && moveUp && moveDown && moveLeft && moveRight && changeState);
	}

	@Test
	public void testHardSleepingDragon(){
		Maze maze=new Maze(m1);
		boolean move=false;
		maze.getDragons().get(0).setAwake(false);
		//acorda tem que estar no mesmo sitio
		maze.updateHard('s');
		if(m1[1][3]=='D' || m1[1][3]=='d')
			move=true;
		assertEquals(true,move);
	}
	/*
	@Test
	public void testHardAwakeDragon(){
		Maze maze=new Maze(m1);
		boolean move=false;
		maze.getDragons().get(0).setAwake(true);
		//adormece ou mexe-se


		maze.updateHard('s');

		if(maze.getBoard()[1][3]==maze.getDragons().get(0).getSymbol() && !maze.getDragons().get(0).getAwake())
		{
			move=true;
		}
		if(maze.getBoard()[2][3]==maze.getDragons().get(0).getSymbol())
		{
			move=true;
		}
		if(maze.getBoard()[1][2]==maze.getDragons().get(0).getSymbol())
		{
			move=true;
		}

		assertEquals(true,move);
	}
	 */
}

