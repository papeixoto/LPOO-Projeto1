package maze.cli;
 //second change
 //first change
 //third change
//5

import java.util.Scanner;

import maze.logic.Maze;
import maze.logic.MazeBuilder;
import maze.logic.IMazeBuilder;

public class Game {

	static public Scanner s = new Scanner(System.in);
	static Maze maze;
	boolean b=false;
		public static void main(String[] args) {
		MazeBuilder mazeBuilder = new MazeBuilder();
		String str=getDifficulty();
		maze = new Maze(mazeBuilder.buildMaze(getDim()));
		printBoard(maze.getBoard());
			while(!maze.getGameOver()){
				switch(str){
					case "Easy":maze.updateEasy(getChar());
					break;
					case "Medium":maze.updateMedium(getChar());
					break;
					case "Hard":maze.updateHard(getChar());
					break;
					default:System.out.println("Invalid Difficulty");
					str=getDifficulty();
					break;}
				
					
		printBoard(maze.getBoard());
		}

		printEndGameMessage();
		printBoard(maze.getBoard());
			
		s.close();
	}

	private static char getChar(){
		System.out.println("movimento:");
		char x = s.next().charAt(0);
		return x;
	}
	
	private static int getDim(){
		System.out.println("dimensao?");
		int x=s.nextInt();
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

