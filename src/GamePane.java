
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePane extends JPanel implements ActionListener {

	private final int DELAY = 150;
	private Timer timer;

	public GamePane() {
		setBackground(new Color(139, 69, 19));
		initTimer();
	}

	private void initTimer() {

		timer = new Timer(DELAY, this);
		timer.start();
	}

	public Timer getTimer() {
		return timer;
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setPaint(Color.blue);

		int w = getWidth();
		int h = getHeight();

		Random r = new Random();

		 {

			int x = 20;
			int y = 30;
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
