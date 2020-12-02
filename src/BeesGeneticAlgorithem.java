import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class BeesGeneticAlgorithem extends JFrame {

	private JPanel contentPane;
	private JTextField gencount;
	private JTextField samplesnum;
	static Integer lastint;
	private MazeGenerator mg;
	private Population pop;
	private int framerate;
	private GamePane gamePane;
	private JButton NewGenBtn;
	private Thread t ;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("constructer constructer1");
					BeesGeneticAlgorithem frame = new BeesGeneticAlgorithem();
					System.out.println("constructer constructer22");
					frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private BeesGeneticAlgorithem() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BeesGeneticAlgorithem.class.getResource("/imgs/bee.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mg = new MazeGenerator();

		mg.generate();

		framerate = 10;

		pop = new Population(50);
		lastint = 50;
		setTitle("Amazy Mazy");
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
		gencount.setText("1");
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

		gamePane = new GamePane();
		gamePane.setFrameRate(framerate);
		t = new Thread(gamePane);
		t.run();
		contentPane.add(gamePane);

		NewGenBtn = new JButton("Next generation");
		NewGenBtn.setBackground(SystemColor.info);
		NewGenBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGeneration();
				Integer tmp;
				tmp = Integer.parseInt(gencount.getText()) + 1;
				gencount.setText(tmp.toString());
			}
		});

		NewGenBtn.setBounds(740, 272, 199, 35);
		contentPane.add(NewGenBtn);

		JLabel background = new JLabel("New label");
		background.setBackground(new Color(240, 240, 240));
		background.setIcon(new ImageIcon(BeesGeneticAlgorithem.class.getResource("/imgs/mazebackground.jpg")));
		background.setBounds(0, 0, 1042, 700);
		contentPane.add(background);
		thisGeneration();
	}

	// regenerate new maze + new population with the number of samples that given in
	// samplesnum JTextField
	private void ResetALL() {
		int x;
		try {
			x = Integer.parseInt(samplesnum.getText());
			lastint = x;

		} catch (Exception e) {
			x = lastint;
			samplesnum.setText(lastint.toString());
			System.out.println(
					"not Acceptable!, U should enter a number . Defult samples set to the last value that u entered :) "
							+ lastint);
		}
		gencount.setText("1");
		pop = new Population(x);
		mg.generate();
		gamePane.repaint();
	}

	// this function should generate the new generation
	private void newGeneration() {
		pop.crosssverAndMutation();
		thisGeneration();
	}

	private void thisGeneration() {
		NewGenBtn.disable();
		pop.runAll();// simulate all and apply the fitness function to get best one
		pop.simulateNextStepForBestBee(gamePane, framerate);
		NewGenBtn.enable();
	}
}