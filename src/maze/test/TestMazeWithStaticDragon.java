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
	public void testMoveHeroToFreeCell() {
	Maze maze = new Maze(m1);
	assertEquals(1, maze.getHero().getX());
	assertEquals(3, maze.getHero().getY());
	
	maze.updateEasy('a');
	assertEquals(1, maze.getHero().getX());
	assertEquals(2, maze.getHero().getY());
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

}
