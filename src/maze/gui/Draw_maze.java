package maze.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import maze.logic.Maze;


public class Draw_maze extends JPanel {
	private BufferedImage dragon;
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage blank;
	private BufferedImage wall;
	private Maze maze;

	public Draw_maze(Maze maze) {
		this.maze=maze;
		setKeyBindings();
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

	private void setKeyBindings() {
		ActionMap actionMap = getActionMap();
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = getInputMap(condition );

		String vkLeft = "a";
		String vkRight = "d";
		String vkUp = "w";
		String vkDown = "s";
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);

		actionMap.put(vkLeft, new KeyAction(vkLeft));
		actionMap.put(vkRight, new KeyAction(vkRight));
		actionMap.put(vkUp, new KeyAction(vkUp));
		actionMap.put(vkDown, new KeyAction(vkDown));


	}


	public	void  setMaze(Maze maze){
		this.maze=maze;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the backgorund ...
		int frameDimension = 200;
		char [][] board = maze.getBoard();
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
				case 'D':
					g.drawImage(dragon, k*width, i*height, width, height , null);
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

class KeyAction extends AbstractAction {
	private static Interface i;
	public KeyAction(String actionCommand) {
		putValue(ACTION_COMMAND_KEY, actionCommand);
	}

	public static void setInterface(Interface inter) {
		i = inter;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvt) {
		System.out.println(actionEvt.getActionCommand() + " pressed");
		i.updateMaze(actionEvt.getActionCommand().charAt(0));
	}
}














