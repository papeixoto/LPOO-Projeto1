package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;


public class Draw_maze extends JPanel {
	private BufferedImage dragon;
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage blank;
	private BufferedImage wall;
	private Maze maze;
	private char[][] board;
	
	public Draw_maze() {
		try {
			dragon =  ImageIO.read(new File("dragon.jpg"));
			hero =  ImageIO.read(new File("hero.jpg"));
			sword =  ImageIO.read(new File("sword.jpg"));
			blank =  ImageIO.read(new File("blank.jpg"));
			wall =  ImageIO.read(new File("wall.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the backgorund ...
		int frameDimension = 800;
		board=maze.getBoard();
	//	int x=0,y=0,width=100, height=100;
		int width = frameDimension/board.length ;
		int height = frameDimension/board.length ;
		//g.drawImage(hero, x, y, x + width - 1, y + height - 1, 0, 0, hero.getWidth(), hero.getHeight(), null);

			for (int i = 0; i < board.length; i++) {
				for (int  k= 0; k < board[i].length; k++) {
					switch (board[i][k]) {
					case 'H': 
						g.drawImage(hero, k*width, i*height, width, height , null);
						break;
					case 'E':
						g.drawImage(sword, k*width, i*height, width, height , null);
						break;
					case 'A':
						g.drawImage(hero, k*width, i*height, width, height , null);
						break;
					case 'X':
						g.drawImage(wall, k*width, i*height, width, height , null);
						break;
					case ' ':
						g.drawImage(blank, k*width, i*height, width, height , null);
						break;
					default:
						break;
					}
				}
			}
	}
}
			
		













