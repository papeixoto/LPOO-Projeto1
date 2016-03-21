package maze.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class MazeBuilder implements IMazeBuilder {

	char[][] lab;


	public MazeBuilder(int size, int numOfDragons) throws IllegalArgumentException {
		if ((size & 1)==0) throw new IllegalArgumentException(); 
		Terrain terrain = new Terrain(size);
		lab = terrain.getBoard();
		generatePieces(numOfDragons);
	}

//	public MazeBuilder() {}
	
/*	public char[][] buildMaze(int size){
		Terrain terrain = new Terrain(size);
		return terrain.getBoard();
	}
*/
	private void generatePieces(int numOfDragons){
		int n = lab.length-1;
		Random rand = new Random();
		ArrayList<Dragon> dragons=new ArrayList<Dragon>();

//		numOfDragoes=rand.nextInt((int)Math.sqrt(n)-1)+1;

		int heroX = rand.nextInt(n)+1;
		int heroY = rand.nextInt(n)+1;
		int swordX = rand.nextInt(n)+1;
		int swordY = rand.nextInt(n)+1;
		//gerar espada
		while (lab[swordX][swordY] != ' ') {
			swordX = rand.nextInt(n)+1;
			swordY = rand.nextInt(n)+1;
		}
		Sword sword = new Sword(swordX, swordY);
		lab[swordX][swordY] = sword.getSymbol();

		//adicionar numero random de dragoes ao arraylist
		for(int i=0;i<numOfDragons;i++)
		{
			int dragonX =rand.nextInt(n)+1;
			int dragonY = rand.nextInt(n)+1;
			while (lab[dragonX][dragonY] != ' ') {
				dragonX = rand.nextInt(n)+1;
				dragonY = rand.nextInt(n)+1;
			}
			Dragon dragon= new Dragon(dragonX, dragonY);
			lab[dragonX][dragonY] = dragon.getSymbol();
			dragons.add(new Dragon(dragonX,dragonY));
		}

		// verificar posicoes dos dragoes adicionados
		// gera heroi e verifica proximidade com dragoes
		while (lab[heroX][heroY] != ' ' || lab[heroX + 1][heroY] == 'D' || lab[heroX][heroY + 1] == 'D'
				|| lab[heroX - 1][heroY] == 'D' || lab[heroX][heroY - 1] == 'D'
				|| lab[heroX + 1][heroY + 1] == 'D' || lab[heroX + 1][heroY - 1] == 'D'
				|| lab[heroX - 1][heroY - 1] == 'D' || lab[heroX - 1][heroY + 1] == 'D') {// System.out.println("tava
			// colado");
			heroX = rand.nextInt(n)+1;
			heroY = rand.nextInt(n)+1;
		}
		Hero hero = new Hero(heroX, heroY);
		lab[heroX][heroY] = hero.getSymbol();
	}
	
	public char[][] getBoard(){
		return lab;
	}



}
