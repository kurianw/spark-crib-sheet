import java.awt.Graphics;
import java.util.ArrayList;

public class WorldManager {	
	Player player;
	Player player2;
	ArrayList<StageComponent> stage;
	ArrayList<Shot> shots;
	int live_count;

	public WorldManager() {				
		stage = new ArrayList<StageComponent>();
		shots = new ArrayList<Shot>();

		player = new Player(585, 275, "#ff0000", "#1b8754", Player.Direction.LEFT);
		player2  = new Player(15, 275, "#0000ff", "#dda22c", Player.Direction.RIGHT);
		live_count = 2;
		
		stage = new ArrayList<StageComponent>();
		stage.add(new StageComponent(15, 305, 600, 25));
		stage.add(new StageComponent(115, 305, 400, 25));
		stage.add(new StageComponent(15, 105, 250, 25));
		stage.add(new StageComponent(365, 105, 250, 25));
		stage.add(new StageComponent(0, 0, 15, 330));
		stage.add(new StageComponent(615, 0, 15, 330));
		stage.add(new StageComponent(0, 0, 630, 15));
	}

	public void addShot(Shot shot) {
		shots.add(shot);
	}

	public Player getPlayer(int index) {
		if (index == 0) {
			return player;
		} else {
			return player2;
		}
	}

	public void render(Graphics g) {
		// Characters
		player.render(g);
		player2.render(g);
		// Stage
		for (StageComponent component : stage) {
			component.render(g);
		}
		// Shots
		for (Shot shot : shots) {
			shot.render(g);
		}
	}

	public void update() {				
		player = player.handleNonFatalCollisions(player2, stage);
		player2 = player2.handleNonFatalCollisions(player, stage);
		
		ArrayList<Shot> absorbed_shots = new ArrayList<Shot>();
		absorbed_shots.addAll(player.handleShots(shots, stage));
		if (!player.isAlive()) {
			player2.wins();
		}
		
		absorbed_shots.addAll(player2.handleShots(shots, stage));
		if (!player2.isAlive()) {
			player.wins();
		}
		
		shots.removeAll(absorbed_shots);
		for (Shot shot : shots) {
			shot.update();
		}
	}
}
