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
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

public class DotsGeneticAlgorithem extends JFrame {

	private JPanel contentPane;
	private Population pop;
	int framerate;
	private JTextField textField_1;
	
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
		setTitle("Amazy Mazy");
		setResizable(false);
		setBounds(100, 100 , 700 , 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		framerate=60;
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Auto next generation");
		chckbxNewCheckBox.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 12));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(485, 339, 199, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.light"));
		panel_1.setBounds(485, 117, 199, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel gen = new JLabel("Generation :");
		gen.setBounds(0, 11, 199, 58);
		gen.setHorizontalAlignment(SwingConstants.CENTER);
		gen.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 28));
		gen.setBackground(Color.BLACK);
		panel_1.add(gen);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(13, 70, 176, 36);
		panel_1.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("Reset Maze");
		btnNewButton_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(485, 608, 199, 35);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBackground(new Color(139, 69, 19));
		panel.setBounds(25, 25, 450, 620);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Next generation");
		btnNewButton.setBackground(SystemColor.info);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(485, 277, 199, 35);
		contentPane.add(btnNewButton);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(DotsGeneticAlgorithem.class.getResource("/imgs/mazebackground.jpg")));
		background.setBounds(0, 0, 700, 700);
		contentPane.add(background);
	}
}
