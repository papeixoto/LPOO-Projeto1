package maze.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class MazeBuilder implements IMazeBuilder {

	char[][] lab;
	public char[][] visitedCells;
	Piece guideCell;	
	Stack<Piece> pathHistory;

	public char[][] buildMaze(int size) throws IllegalArgumentException {
		if ((size & 1)==0) throw new IllegalArgumentException(); 

		lab = new char[size][size];
		visitedCells=new char[size/2][size/2];
		guideCell = new Piece(0,0);
		pathHistory = new Stack();

		fill();
		exit();

		while(!pathHistory.isEmpty()){
			generate();
		//	print();
		}

	return lab;
}

private void print() {
	for(char[] row:lab){
		for(char cell:row){
			System.out.print(cell);
			System.out.print(' ');
		}
		System.out.println();
	}
	
	for(char[] row:visitedCells){
		for(char cell:row){
			System.out.print(cell);
			System.out.print(' ');
		}
		System.out.println();
	}
	
	System.out.print(guideCell.getX()); 
	System.out.println(guideCell.getY()); 
		
	}

private void fill(){
	for(int x=0; x<lab.length; x++)
		for(int y=0; y<lab.length; y++){
			if(x == 0 || y == 0 || x == lab.length-1 || y == lab.length-1  || ((x & 1) == 0 || (y & 1) == 0))
				lab[x][y] = 'X';
			else lab[x][y] = ' ';
		}

	for(char[] line:visitedCells)
		for(char cell:line)
			cell = '.';
}

private void exit(){
	int random1 = ThreadLocalRandom.current().nextInt(0, 4);
	int random2 = ThreadLocalRandom.current().nextInt(0, (lab.length-1)/2);

	switch(random1){
	case 0: 
		guideCell.setY(lab.length-1);
		guideCell.setX(random2*2+1);
		break;
	case 1: 
		guideCell.setX(lab.length-1);
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


	lab[guideCell.posX][guideCell.posY] = 'S';
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

	lab[(guideCell.getX()+x)+1][(guideCell.getY()+y)+1]=' ';
	visitedCells[guideCell.getX()][guideCell.getY()]='+';
	Piece temp = new Piece(guideCell.getX(),guideCell.getY());
	pathHistory.push(temp);
}



}
