package maze.cli;
 //second change
 //first change
 //third change
//5

import java.util.Scanner;

import maze.logic.IMazeBuilder;
import maze.logic.Maze;
import maze.logic.MazeBuilder;

public class Game {

	static public Scanner s = new Scanner(System.in);
	static Maze maze;
	boolean b=false;
	
	public static void main(String[] args) {
		MazeBuilder mazeBuilder = new MazeBuilder();
		maze = new Maze(mazeBuilder.buildMaze(31));
		/*		String str=getDifficulty();
		printBoard(maze.getBoard());
			while(!maze.getGameOver()){
				if(str.equals("Easy"))
					maze.updateEasy(getChar());
				if(str.equals("Medium"))
					maze.updateMedium(getChar());
				if(str.equals("Hard"))
					maze.updateHard(getChar());
				else if(str!="Easy" || str!="Medium" || str!="Hard")
				{System.out.println("Invalid Difficulty");
				}
				
					
		printBoard(maze.getBoard());

		}
*/		
		printBoard(maze.getBoard());
	//	printEndGameMessage();
			
		s.close();
	}


	private static char getChar(){
		System.out.println("movimento:");
		char x = s.next().charAt(0);
		return x;
	}
	public static String getDifficulty()
	{
		System.out.println("Introduza a dificuldade");
		String x = s.nextLine();
		return x;
	}
	

	private static void printEndGameMessage(){
		if(!maze.getHero().getAlive()) System.out.println("GAME OVER - YOUR HERO IS DEAD");
		if(maze.getHero().getAlive()) System.out.println("CONGRATULATIONS YOU WON");
	}
	
	private static void printBoard(char[][] board){
		for(char[] row:board){
			for(char cell:row){
				System.out.print(cell);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}

