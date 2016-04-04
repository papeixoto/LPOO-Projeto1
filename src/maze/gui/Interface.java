package maze.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import maze.logic.Maze;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.istack.internal.NotNull;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;



public class Interface {

	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_UP = "move up";
	private static final String MOVE_DOWN = "move down";
	 private static final String MOVE_RIGHT = "move right";
	    private static final String MOVE_LEFT = "move left";
	
	private JFrame images;
	private JFrame frame;
	private JTextField tfSize;
	private JTextField tfDragon;
	private JLabel lblPodeGerarNovo;
	private Maze maze;
	private JButton btnCima;
	private JButton btnBaixo;
	private JButton btnEsquerda;
	private JButton btnDireita;
	private Draw_maze panel;
	private createMaze panelCreate;
	private JButton btnNewButton_1;
	
	private String iSize = "11";
	private String iDragon = "1";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
		KeyAction.setInterface(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//mostrar imagem dragao

		//------------------------
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Courier New", Font.PLAIN, 11));
		frame.setBounds(100, 100, 498, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = new Dimension(800,800);
        frame.getContentPane().setPreferredSize(d);
        frame.pack();
        frame.setResizable(false);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("133px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("141px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("4px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("82px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("79px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				RowSpec.decode("23px"),
				RowSpec.decode("33px:grow"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("112px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));

		JLabel lblNewLabel = new JLabel("Dimensão do labirinto");
		frame.getContentPane().add(lblNewLabel, "2, 2, center, center");
		
		
		tfSize = new JTextField();
		tfSize.setText(iSize);
		frame.getContentPane().add(tfSize, "4, 2, left, center");
		tfSize.setColumns(10);

		tfDragon = new JTextField();
		tfDragon.setText(iDragon);
		frame.getContentPane().add(tfDragon, "4, 4, left, center");
		tfDragon.setColumns(10);
		
		maze = new Maze(Integer.parseInt(iSize), Integer.parseInt(iDragon));
		panelCreate = new createMaze(Integer.parseInt(tfSize.getText()));
		//frame.getContentPane().add(panelCreate, "2, 8, 3, 5, fill, fill");
		panel=new Draw_maze(maze);
		panel.setVisible(false);
		frame.getContentPane().add(panel, "2, 8, 3, 5, fill, fill");
		
		
		btnNewButton_1 = new JButton("Criar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( ( Integer.parseInt(tfSize.getText()) & 1) == 0 || Integer.parseInt(tfDragon.getText()) > Integer.parseInt(tfSize.getText())){
					lblPodeGerarNovo.setText("tamanho do labirinto ou número de dragões inválidos");
					return;
				}
				panel.setVisible(false);
		//		frame.remove(panelCreate);
				panelCreate = new createMaze(Integer.parseInt(tfSize.getText()));
				panelCreate.setVisible(true);
				panelCreate.revalidate();
				
				btnCima.setEnabled(false);
				btnBaixo.setEnabled(false);
				btnEsquerda.setEnabled(false);
				btnDireita.setEnabled(false);
			}
		});
		frame.getContentPane().add(btnNewButton_1, "10, 2");

		JLabel lblNewLabel_1 = new JLabel("Número de dragões");
		frame.getContentPane().add(lblNewLabel_1, "2, 4, center, center");

		JLabel lblNewLabel_2 = new JLabel("Tipo de dragões");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_2, "2, 6, fill, center");

		JComboBox comboBox = new JComboBox();
		frame.getContentPane().add(comboBox, "4, 6, fill, top");
		comboBox.addItem("Estaticos");
		comboBox.addItem("Moveis");
		comboBox.addItem("Adormecem");

		btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaze('w');
			}
		});


		btnCima.setEnabled(false);
		frame.getContentPane().add(btnCima, "8, 8, 3, 1, center, bottom");

		btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaze('s');
			}
		});
		btnBaixo.setEnabled(false);
		frame.getContentPane().add(btnBaixo, "8, 12, 3, 1, center, top");

		btnEsquerda = new JButton("Esquerda");
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaze('a');
			}


		});
		btnEsquerda.setEnabled(false);
		frame.getContentPane().add(btnEsquerda, "8, 10, fill, top");

		btnDireita = new JButton("Direita");
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaze('d');
			}
		});
		btnDireita.setEnabled(false);
		frame.getContentPane().add(btnDireita, "10, 10, fill, top");

		lblPodeGerarNovo = new JLabel("Pode gerar novo labirinto");
		frame.getContentPane().add(lblPodeGerarNovo, "2, 16, 3, 1, fill, top");

		JButton btnGerarLabirinto = new JButton("Gerar novo labirinto");
		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( ( Integer.parseInt(tfSize.getText()) & 1) == 0 || Integer.parseInt(tfDragon.getText()) > Integer.parseInt(tfSize.getText())){
					lblPodeGerarNovo.setText("tamanho do labirinto ou número de dragões inválidos");
					return;
				}
				maze = new Maze(Integer.parseInt(tfSize.getText()), Integer.parseInt(tfDragon.getText()));
				String x = String.valueOf(comboBox.getSelectedItem());
				if(x.equals("Estaticos"))maze.setDifficulty(0);
				if(x.equals("Moveis"))maze.setDifficulty(1);
				if(x.equals("Adormecem"))maze.setDifficulty(2);
				//				textArea.setText(maze.toString());
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnEsquerda.setEnabled(true);
				btnDireita.setEnabled(true);
				lblPodeGerarNovo.setText("Em jogo - Movimente o seu herói");
				panelCreate.setVisible(false);
				panel.setVisible(true);panel.setMaze(maze); panel.repaint();
				
			}
		});
		frame.getContentPane().add(btnGerarLabirinto, "6, 2, 3, 1, default, top");

		JButton btnNewButton = new JButton("Terminar programa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewButton, "6, 4, 5, 1, default, top");

		


	}
	public void updateMaze(char c) {
		maze.update(c);
		panel.repaint();
		if(maze.getGameOver()) printEndGameMessage();
	}

	private void printEndGameMessage(){
		if(!maze.getHero().getAlive()) lblPodeGerarNovo.setText("GAME OVER - YOUR HERO IS DEAD");
		if(maze.getHero().getAlive()) lblPodeGerarNovo.setText("CONGRATULATIONS YOU WON");
		btnCima.setEnabled(false);
		btnBaixo.setEnabled(false);
		btnEsquerda.setEnabled(false);
		btnDireita.setEnabled(false);

	}
}
