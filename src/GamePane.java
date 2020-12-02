
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePane extends JPanel implements ActionListener, Runnable {
	static Bee b;

	Timer timer;

	public GamePane() {

		setBackground(new Color(139, 69, 19));
		setToolTipText("samples count");
		setBackground(new Color(139, 69, 19));
		setBounds(30, 10, 640, 640);
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		System.out.println("frame");
		super.paintComponent(g);
		if (!(b.isDead()))
			b.nextMove();
		g2d.setPaint(Color.GREEN);
		for (int row = 0; row <= 31; row++)
			for (int col = 0; col <= 31; col++)
				if (MazeGenerator.isAWall(row, col))
					g2d.fillRect(row * 20, col * 20, 20, 20);
		int x = b.position[0] * 20;
		int y = b.position[1] * 20;
		System.out.println(x + "," + y);
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
		System.out.println("frame updated");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			repaint();// this will call at every 1 second
			revalidate();
		}
	}

	@Override
	public void run() {
		timer.start();
	}

	public void setFrameRate(int framerate) {
		timer = new Timer(1000 / framerate, this);
	}
}