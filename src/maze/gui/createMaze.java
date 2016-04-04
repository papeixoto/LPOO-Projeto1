package maze.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class createMaze extends JLayeredPane implements MouseListener, MouseMotionListener {
	//JLayeredPane layeredPane;
	JPanel board;
	JLabel piece;
	int xAdjustment;
	int yAdjustment;
	int dim=11;
	private BufferedImage dragon;
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage blank;
	private BufferedImage wall;
	
	public createMaze(int dim){
		try {
			dragon =  ImageIO.read(new File("dragon.jpg"));
			hero =  ImageIO.read(new File("hero.jpg"));
			sword =  ImageIO.read(new File("sword.jpg"));
			blank =  ImageIO.read(new File("blank.jpg"));
			wall =  ImageIO.read(new File("wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Dimension boardSize = new Dimension(600, 600);

		//  Use a Layered Pane for this this application
		//layeredPane = new JLayeredPane();
		//getContentPane().add(layeredPane);
		//this.setVisible(false);
		this.setPreferredSize(boardSize);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		//Add a chess board to the Layered Pane 

		board = new JPanel();
		this.add(board, JLayeredPane.DEFAULT_LAYER);
		board.setLayout( new GridLayout(dim, dim) );
		board.setPreferredSize( boardSize );
		board.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < dim*dim; i++) {
			JPanel square = new JPanel( new BorderLayout() );
			board.add( square );

			if((i & 1) == 0) 
				square.setBackground(Color.white );
			else
				square.setBackground(Color.blue );
		}

		//Add a few pieces to the board
		Image auxImage = dragon.getScaledInstance(boardSize.width/dim, boardSize.width/dim, Image.SCALE_SMOOTH);
		JLabel piece = new JLabel( new ImageIcon(auxImage) );
		JPanel panel = (JPanel)board.getComponent(0);
		panel.add(piece);
		auxImage = hero.getScaledInstance(boardSize.width/dim, boardSize.width/dim, Image.SCALE_SMOOTH);
		piece = new JLabel(new ImageIcon(auxImage));
		panel = (JPanel)board.getComponent(15);
		panel.add(piece);
		auxImage = sword.getScaledInstance(boardSize.width/dim, boardSize.width/dim, Image.SCALE_SMOOTH);
		piece = new JLabel(new ImageIcon(auxImage));
		panel = (JPanel)board.getComponent(16);
		panel.add(piece);
		auxImage = wall.getScaledInstance(boardSize.width/dim, boardSize.width/dim, Image.SCALE_SMOOTH);
		piece = new JLabel(new ImageIcon(auxImage));
		panel = (JPanel)board.getComponent(20);
		panel.add(piece);

	}

	public void setDim(int dim){
		this.dim=dim;
	}
	
	public void mousePressed(MouseEvent e){
		piece = null;
		Component c =  board.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel) 
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		piece = (JLabel)c;
		piece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		piece.setSize(piece.getWidth(), piece.getHeight());
		this.add(piece, JLayeredPane.DRAG_LAYER);
	}

	//Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (piece == null) return;
		piece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}

	//Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		if(piece == null) return;

		piece.setVisible(false);
		Component c =  board.findComponentAt(e.getX(), e.getY());

		if (c instanceof JLabel){
			Container parent = c.getParent();
			parent.remove(0);
			parent.add( piece );
		}
		else {
			Container parent = (Container)c;
			parent.add( piece );
		}

		piece.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {

	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e) {

	}
/*
	public static void main(String[] args) {
		JFrame frame = new ChessGameDemo();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}*/
}