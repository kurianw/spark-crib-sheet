import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquareShoots extends Applet implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constants
	public static final int LEFT_WORLD_ORIGIN = 135;
	public static final int TOP_WORLD_ORIGIN = 195;
	// Variables
	Thread runner;
	WorldManager world_manager;

	// Methods
	public void start() {
		if (runner == null) {
			world_manager = new WorldManager();
			runner = new Thread(this);
			runner.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		if (runner != null) {
			runner.stop();
			runner = null;
		}
	}

	public void run() {
		this.addKeyListener(this);
		setFocusable(true);

		while (true) {
			repaint();
			world_manager.update();
			try {
				Thread.sleep(10);
			} catch (Exception ex) {
			}
		}
	}

	public void paint(Graphics g) {
		world_manager.render(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Player player1 = world_manager.getPlayer(0);
		if (player1 != null) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player1.moveLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player1.moveRight();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				player1.jump();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				world_manager.addShot(player1.shoot());
			}

			Player player2 = world_manager.getPlayer(1);
			if (player2 != null) {
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				player2.moveRight();
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				player2.moveLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				player2.jump();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				world_manager.addShot(player2.shoot());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Player player1 = world_manager.getPlayer(0);
		if (player1 != null) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player1.horizontalStop();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player1.horizontalStop();
			}
		}

		Player player2 = world_manager.getPlayer(1);
		if (player2 != null) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				player2.horizontalStop();
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				player2.horizontalStop();
			}
		}
	}
}