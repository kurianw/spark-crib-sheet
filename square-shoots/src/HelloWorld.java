import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class HelloWorld extends Applet implements KeyListener, Runnable {
	// Constants
	public static final int LEFT_WORLD_ORIGIN = 135;
	public static final int TOP_WORLD_ORIGIN = 195;
	// Variables
	Thread runner;
	public Player player;
	public Player player2;
	public ArrayList<StageComponent> stage;
	ArrayList<Collidable> player_potential_barriers;
	ArrayList<Collidable> player2_potential_barriers;

	// Methods
	public void start() {
		if (runner == null) {
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
		player = new Player(585, 275, "#ff0000", "#1b8754", Player.Direction.LEFT);
		player2 = new Player(15, 275, "#0000ff", "#dda22c", Player.Direction.RIGHT);
		stage = new ArrayList<StageComponent>();
		stage.add(new StageComponent(15, 305, 600, 25));
		stage.add(new StageComponent(115, 305, 400, 25));
		stage.add(new StageComponent(15, 105, 250, 25));
		stage.add(new StageComponent(365, 105, 250, 25));
		stage.add(new StageComponent(0, 0, 15, 330));
		stage.add(new StageComponent(615, 0, 15, 330));
		stage.add(new StageComponent(0, 0, 630, 15));

		player_potential_barriers = new ArrayList<Collidable>();
		player_potential_barriers.addAll(stage);
		player_potential_barriers.add(player2);

		player2_potential_barriers = new ArrayList<Collidable>();
		player2_potential_barriers.addAll(stage);
		player2_potential_barriers.add(player);

		while (true) {
			repaint();
			player.update(player_potential_barriers, true);
			player2.update(player2_potential_barriers, false);
			try {
				Thread.sleep(10);
			} catch (Exception ex) {
			}
		}
	}

	public void paint(Graphics g) {
		// Characters
		player.render(g);
		player2.render(g);
		// Stage
		for (StageComponent component : stage) {
			component.render(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.jump();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.shoot();

		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			player2.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player2.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			player2.jump();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player2.shoot();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.horizontalStop();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.horizontalStop();

		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			player2.horizontalStop();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player2.horizontalStop();
		}
	}
}