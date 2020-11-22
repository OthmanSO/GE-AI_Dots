
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;

class GamePane extends JPanel implements ActionListener {

	MazeGenerator mg;

	public GamePane() {
		mg = MazeGenerator.singletonMazeGenerator();
		setBackground(new Color(139, 69, 19));
		setToolTipText("samples count");
		setBackground(new Color(139, 69, 19));
		setBounds(30, 10, 640, 640);
		setLayout(null);
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.GREEN);
		for (int row = 0; row < 31; row++)
			for (int col = 0; col < 31; col++)
				if (mg.maze[row][col])
					g2d.drawRect(row*20, col*20, 20, 20);
		//drawBee(g2d, Population.bestBeeEver.position[0] * 20, Population.bestBeeEver.position[1] * 20);

	}

	private void drawBee(Graphics2D g2d, int x, int y) {
		g2d.setPaint(Color.yellow);
		g2d.drawLine(x + 4, y, x + 5, y + 1);
		g2d.drawLine(x + 1, y + 3, x + 8, y + 3);
		g2d.drawLine(x + 1, y + 6, x + 8, y + 6);
		g2d.drawLine(x + 4, y + 9, x + 5, y + 9);
		g2d.setPaint(Color.lightGray);
		g2d.drawLine(x + 1, y + 4, x + 3, y + 5);
		g2d.drawLine(x + 6, y + 4, x + 8, y + 5);
		g2d.setPaint(Color.black);
		g2d.drawLine(x + 2, y + 2, x + 7, y + 2);
		g2d.drawLine(x + 4, y + 4, x + 5, y + 5);
		g2d.drawLine(x + 1, y + 7, x + 8, y + 7);
		g2d.drawLine(x + 2, y + 8, x + 7, y + 8);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}