package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Maze;


public class TestMazeWithStaticDragon { 
	
	
	
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', 'E', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
	
	@Test
	public void testNewMaze(){
		Maze maze = new Maze(11,1);
		
		assertEquals(maze.getDragons().size(), 1);
		
	}
	
	@Test
	public void testMoveHeroToFreeCell() {
	Maze maze = new Maze(m1);
	assertEquals(1, maze.getHero().getX());
	assertEquals(3, maze.getHero().getY());
	
	maze.updateEasy('s');
	assertEquals(2, maze.getHero().getX());
	assertEquals(3, maze.getHero().getY());
	}
	
	@Test
	public void testMoveHeroToWall() {
		Maze maze = new Maze(m1);
		assertEquals(1, maze.getHero().getX());
		assertEquals(3, maze.getHero().getY());
		
		maze.updateEasy('w');
		assertEquals(1, maze.getHero().getX());
		assertEquals(3, maze.getHero().getY());
		}

	@Test
	public void testMoveHeroToSword() {
		Maze maze = new Maze(m1);
		assertEquals(maze.getHero().getArmed(), false);
		maze.updateEasy('a');
		assertEquals(maze.getHero().getArmed(), true);
		}
	
	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1);
		assertEquals(maze.getHero().getArmed(), false);
		assertEquals(maze.getHero().getAlive(), true);
		maze.updateEasy('s');
		assertEquals(maze.getHero().getAlive(), false);
		}
	@Test
	public void testDragonDies(){
		Maze maze=new Maze(m1);
		maze.getHero().setArmed(true);
		assertEquals(maze.getHero().getArmed(),true);
		maze.updateEasy('s');
		assertEquals(maze.getDragons().isEmpty(),true);
		assertEquals(maze.getHero().getAlive(),true);
	}
	
	@Test 
	public void leaveLab(){
		Maze maze=new Maze(m1);
		maze.getDragons().remove(0);
		maze.updateEasy('d');
		assertEquals(maze.getGameOver(),true);
	}
	@Test
	public void leaveLabWithoutSword(){
		Maze maze=new Maze(m1);
		maze.getHero().setArmed(false);
		maze.updateEasy('d');
		assertEquals(maze.getGameOver(),false);
	}
	@Test
	public void leaveLabwithSword(){
		Maze maze=new Maze(m1);
		maze.getHero().setArmed(true);
		maze.updateEasy('d');
		assertEquals(maze.getGameOver(),false);
	}
}
