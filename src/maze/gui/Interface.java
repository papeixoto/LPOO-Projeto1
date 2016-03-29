package maze.gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import maze.logic.Maze;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Interface {

	private JFrame frame;
	private JTextField tfSize;
	private JTextField tfDragon;
	private JTextArea textArea;
	private JLabel lblPodeGerarNovo;
	private Maze maze;
	private JButton btnCima;
	private JButton btnBaixo;
	private JButton btnEsquerda;
	private JButton btnDireita;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Courier New", Font.PLAIN, 11));
		frame.setResizable(false);
		frame.setBounds(100, 100, 498, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("133px"),
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
				RowSpec.decode("33px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("112px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel lblNewLabel = new JLabel("Dimensão do labirinto");
		frame.getContentPane().add(lblNewLabel, "2, 2, center, center");
		
		JLabel lblNewLabel_1 = new JLabel("Número de dragões");
		frame.getContentPane().add(lblNewLabel_1, "2, 4, center, center");
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de dragões");
		frame.getContentPane().add(lblNewLabel_2, "2, 6, fill, center");
		
		JComboBox comboBox = new JComboBox();
		frame.getContentPane().add(comboBox, "4, 6, fill, top");
		comboBox.addItem("Estaticos");
		comboBox.addItem("Moveis");
		comboBox.addItem("Adormecem");
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		frame.getContentPane().add(textArea, "2, 8, 5, 5, fill, fill");
		
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
		frame.getContentPane().add(lblPodeGerarNovo, "2, 14, 3, 1, fill, top");
		
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
				textArea.setText(maze.toString());
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnEsquerda.setEnabled(true);
				btnDireita.setEnabled(true);
				lblPodeGerarNovo.setText("Em jogo - Movimente o seu herói");
			}
		});
		frame.getContentPane().add(btnGerarLabirinto, "6, 2, 5, 1, left, top");
		
		JButton btnNewButton = new JButton("Terminar programa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewButton, "6, 4, 5, 1, left, top");
		
		tfSize = new JTextField();
		tfSize.setText("11");
		frame.getContentPane().add(tfSize, "4, 2, left, center");
		tfSize.setColumns(10);
		
		tfDragon = new JTextField();
		tfDragon.setText("1");
		frame.getContentPane().add(tfDragon, "4, 4, left, center");
		tfDragon.setColumns(10);
		
		
	}
	private void updateMaze(char c) {
		maze.update(c);
		textArea.setText(maze.toString());
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
