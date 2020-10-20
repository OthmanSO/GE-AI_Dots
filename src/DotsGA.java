import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DotsGA extends JPanel {
	private JFrame frame;
	private Population pop;
	private JLabel gen;
	
	public DotsGA() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(500, 700);
		frame.setVisible(true);
		frame.setTitle("Dots");
		gen = new JLabel("Gen = 0");
		frame.add(gen,BorderLayout.NORTH);
		frame.add(this,BorderLayout.CENTER);
		this.setBackground(Color.WHITE);	
	}
	
	private void createPopulation() {
		pop = new Population(50);
	}
	
	private void play() {
		//////////////////////////////
		this.breedNewBabies();
	}

	private void breedNewBabies() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String args[]) {
		DotsGA g = new DotsGA();
		System.out.println("fsdohgioodsaF");
		g.createPopulation();
		g.play();
	}

	
	
}
