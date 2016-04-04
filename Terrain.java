package maze.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Terrain {
	
	private char[][] board;
	public char[][] visitedCells;
	Piece guideCell;	
	Stack<Piece> pathHistory;
	
	
	public Terrain(int size){
		

		board = new char[size][size];
		visitedCells=new char[size/2][size/2];
		guideCell = new Piece(0,0);
		pathHistory = new Stack();

		fill();
		exit();

		while(!pathHistory.isEmpty()){
			generate();
		//	print();
		}
	}
	
	
	private void fill(){
		for(int x=0; x<board.length; x++)
			for(int y=0; y<board.length; y++){
				if(x == 0 || y == 0 || x == board.length-1 || y == board.length-1  || ((x & 1) == 0 || (y & 1) == 0))
					board[x][y] = 'X';
				else board[x][y] = ' ';
			}

		for(char[] line:visitedCells)
			for(char cell:line)
				cell = '.';
	}

	private void exit(){
		int random1 = ThreadLocalRandom.current().nextInt(0, 4);
		int random2 = ThreadLocalRandom.current().nextInt(0, (board.length-1)/2);

		switch(random1){
		case 0: 
			guideCell.setY(board.length-1);
			guideCell.setX(random2*2+1);
			break;
		case 1: 
			guideCell.setX(board.length-1);
			guideCell.setY(random2*2+1);
			break;
		case 2: 
			guideCell.setY(0);
			guideCell.setX(random2*2+1);
			break;
		case 3: 
			guideCell.setX(0); 
			guideCell.setY(random2*2+1);
			break;
		}


		board[guideCell.posX][guideCell.posY] = 'S';
		guideCell.setX((guideCell.posX-1)/2);
		guideCell.setY((guideCell.posY-1)/2);
		visitedCells[guideCell.posX][guideCell.posY] = '+';

		pathHistory.push(guideCell);
	}

	void generate(){
		List<Integer> newCells = new ArrayList<Integer>();

		int x = guideCell.getX();
		int y = guideCell.getY();

		if(x-1>=0 && visitedCells[x-1][y]!='+') newCells.add(0);
		if(y+1<visitedCells.length && visitedCells[x][y+1]!='+') newCells.add(1);
		if(x+1<visitedCells.length && visitedCells[x+1][y]!='+') newCells.add(2);
		if(y-1>=0 && visitedCells[x][y-1]!='+') newCells.add(3);

		if(newCells.size()==0) {
			Piece temp = pathHistory.pop();
			guideCell.setX(temp.getX()); 
			guideCell.setY(temp.getY()); 
			
			return;
		}

		switch (newCells.get(ThreadLocalRandom.current().nextInt(0, newCells.size()))){
		case 0: guideCell.setX(x-1); break;
		case 1: guideCell.setY(y+1); break;
		case 2: guideCell.setX(x+1); break;
		case 3: guideCell.setY(y-1); break;
		}

		board[(guideCell.getX()+x)+1][(guideCell.getY()+y)+1]=' ';
		visitedCells[guideCell.getX()][guideCell.getY()]='+';
		Piece temp = new Piece(guideCell.getX(),guideCell.getY());
		pathHistory.push(temp);
	}

	public char[][] getBoard() {return board;}
	
}
