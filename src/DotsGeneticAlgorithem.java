import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class DotsGeneticAlgorithem extends JFrame {

	private JPanel contentPane;
	private Population pop;
	private JLabel gen;
	int framerate;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DotsGeneticAlgorithem frame = new DotsGeneticAlgorithem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DotsGeneticAlgorithem() {
		setType(Type.UTILITY);
		setTitle("DotsGA");
		setResizable(false);
		setBounds(100, 100 , 700 , 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		framerate=60;
		contentPane.setLayout(null);
		gen = new JLabel("Generation :");
		gen.setHorizontalAlignment(SwingConstants.CENTER);
		gen.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 28));
		gen.setBounds(498, 59, 186, 38);
		contentPane.add(gen);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBackground(Color.GREEN);
		panel.setBounds(25, 25, 450, 620);
		contentPane.add(panel);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(DotsGeneticAlgorithem.class.getResource("/imgs/mazebackground.jpg")));
		background.setBounds(0, 0, 694, 671);
		contentPane.add(background);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(569, 333, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(566, 372, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
