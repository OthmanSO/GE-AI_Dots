import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.EventObject;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import java.awt.Dialog.ModalExclusionType;

public class BeesGeneticAlgorithem extends JFrame {

	private JPanel contentPane;
	private JTextField gencount;
	private JTextField samplesnum;
	static Integer lastint;
	private MazeGenerator mg;
	private Population pop;
	private int framerate;
	JCheckBox chckbxNewCheckBox;
	JPanel gamePanel;
	JButton NewGenBtn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeesGeneticAlgorithem frame = new BeesGeneticAlgorithem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BeesGeneticAlgorithem() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		mg = MazeGenerator.singletonMazeGenerator();
		framerate = 60;
		pop = new Population(50);
		lastint = 50;
		setType(Type.UTILITY);
		setTitle("Amazy Mazy");
		setResizable(false);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		samplesnum = new JTextField();
		samplesnum.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 15));
		samplesnum.setHorizontalAlignment(SwingConstants.CENTER);
		samplesnum.setText("50");
		samplesnum.setBounds(815, 610, 66, 35);
		contentPane.add(samplesnum);
		samplesnum.setColumns(3);

		chckbxNewCheckBox = new JCheckBox("Auto next generation");
		chckbxNewCheckBox.setBackground(SystemColor.info);
		chckbxNewCheckBox.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 12));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(740, 339, 199, 23);
		contentPane.add(chckbxNewCheckBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setBounds(740, 108, 199, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel gen = new JLabel("Generation :");
		gen.setBounds(0, 11, 199, 58);
		gen.setHorizontalAlignment(SwingConstants.CENTER);
		gen.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 28));
		gen.setBackground(Color.BLACK);
		panel_1.add(gen);

		gencount = new JTextField();
		gencount.setHorizontalAlignment(SwingConstants.CENTER);
		gencount.setText("0");
		gencount.setBackground(SystemColor.info);
		gencount.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		gencount.setEditable(false);
		gencount.setColumns(4);
		gencount.setBounds(13, 70, 176, 36);
		panel_1.add(gencount);

		JButton btnNewButton_1 = new JButton("Reset Maze");
		btnNewButton_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1.setForeground(SystemColor.infoText);
		btnNewButton_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetALL();
			}
		});
		btnNewButton_1.setBounds(740, 564, 199, 35);
		contentPane.add(btnNewButton_1);

		gamePanel = new JPanel();
		gamePanel.setToolTipText("samples count");
		gamePanel.setBackground(new Color(139, 69, 19));
		gamePanel.setBounds(30, 10, 640, 640);
		contentPane.add(gamePanel);
		gamePanel.setLayout(null);

		NewGenBtn = new JButton("Next generation");
		NewGenBtn.setBackground(SystemColor.info);
		NewGenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGeneration();
			}
		});
		NewGenBtn.setBounds(740, 272, 199, 35);
		contentPane.add(NewGenBtn);

		JLabel background = new JLabel("New label");
		background.setBackground(new Color(240, 240, 240));
		background.setIcon(new ImageIcon(BeesGeneticAlgorithem.class.getResource("/imgs/mazebackground.jpg")));
		background.setBounds(0, 0, 1042, 700);
		contentPane.add(background);
	}

	// regenerate new maze + new population with the number of samples that given in
	// samplesnum JTextField
	private void ResetALL() {
		int x;
		try {
			x = Integer.parseInt(samplesnum.getText());
		} catch (Exception e) {
			return;
		}
		pop = new Population(x);
		mg.generate();
	}

	// this function should generate the new generation
	private void newGeneration() {
		Integer tmp;
		do {
			tmp = Integer.parseInt(gencount.getText()) + 1;
			gencount.setText(tmp.toString());
			pop.crosssverAndMutation();
			thisGeneration();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
			}
		} while (chckbxNewCheckBox.isSelected());
		NewGenBtn.enable();
	}

	private void thisGeneration() {
		NewGenBtn.disable();
		ImageIcon image = new ImageIcon(BeesGeneticAlgorithem.class.getResource("/imgs/groundBlock.png"));
		while (!pop.allDead()) {
			for (int r = 0; r < 32; r++)
				for (int c = 0; c < 32; c++)
					if (mg.maze[r][c]) {
						JLabel l = new JLabel();

						l.setLocation(r * 20, c * 20);
						gamePanel.add(l);
					}
			image = new ImageIcon(BeesGeneticAlgorithem.class.getResource("/imgs/bee.png"));
			int[][] beesPos = pop.GetAllPos();
			for (int[] pos : beesPos) {
				JLabel l = new JLabel();
				l.setIcon(image);
				l.setLocation(pos[0] * 20, pos[1] * 20);
				gamePanel.add(l);
			}
			gamePanel.repaint();
			gamePanel.validate();

		}
	}
}