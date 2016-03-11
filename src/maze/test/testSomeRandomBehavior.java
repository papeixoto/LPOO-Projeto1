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
		
		

	@Test
	public void testModesMedium() {
		Maze maze=new Maze(m1);
		boolean move=false;
		maze.updateMedium('s');
		if(m1[1][3]==maze.getDragon().getSymbol())
		{
			move=true;
		}
		if(m1[2][3]==maze.getDragon().getSymbol())
		{
			move=true;
		}
		if(m1[1][2]==maze.getDragon().getSymbol())
		{
			move=true;
		}
		AssertEquals(true,move);
		
	}
	private void AssertEquals(boolean b, boolean move) {
		// TODO Auto-generated method stub
		
	}
	@Test
	public void testModesHard(){
		Maze maze=new Maze(m1);
		boolean move=false;
		maze.getDragon().setAwake(false);
		//acorda tem que estar no mesmo sitio
		maze.updateHard('s');
		if(m1[1][3]==maze.getDragon().getSymbol())
			move=true;
		assertEquals(true,move);
		
	}

}
