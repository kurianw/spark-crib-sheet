import java.awt.Graphics;
import java.util.ArrayList;

public class WorldManager {
	ArrayList<Player> players;	
	ArrayList<StageComponent> stage;
	ArrayList<Shot> shots;

	public WorldManager() {
		players = new ArrayList<Player>();		
		stage = new ArrayList<StageComponent>();
		shots = new ArrayList<Shot>();

		players.add(new Player(585, 275, "#ff0000", "#1b8754", Player.Direction.LEFT));
		players.add(new Player(15, 275, "#0000ff", "#dda22c", Player.Direction.RIGHT));		
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
		return players.get(index);
	}

	public void render(Graphics g) {
		// Characters
		for (Player player : players) {
			player.render(g);
		}
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
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);

			// Non Fatal Collisions
			Delta delta = player.getDelta();
			for (StageComponent stage_component : stage) {
				delta = updateDelta(player, delta, stage_component);
			}
			for (Player other : players) {
				if (other.isAlive() && other != player) {
					delta = updateDelta(player, delta, other);
				}
			}
			player.accelerate();
			players.set(i, player.applyDelta(delta));

			// Fatal Collisions
			ArrayList<Shot> absorbed_shots = new ArrayList<Shot>();			
			for (Shot shot : shots) {
				for (StageComponent component : stage) {
					if (shot.collides(component)) {
						absorbed_shots.add(shot);
					}
				}
				if (player.collides(shot) && player.isAlive()) {
					if (!player.hasWon()) {						
						player.kill();
						players.set(i, player);						
					}
					absorbed_shots.add(shot);
				}
			}
			if (absorbed_shots.size() > 0) {
				shots.removeAll(absorbed_shots);
			}			
			for (Shot shot : shots) {
				shot.update();
			}
		}		
	}

	private Delta updateDelta(Player player, Delta original_delta, Collidable other) {
		Delta new_delta = original_delta;
		if (player.applyDelta(new_delta.getHorizontalDelta()).collides(other)) {
			new_delta = player.getNewHorizontalDelta(other).add(new_delta.getVerticalDelta());
			player.horizontalStop();
		}
		if (player.applyDelta(new_delta.getVerticalDelta()).collides(other)) {
			new_delta = new_delta.getVerticalDelta().add(player.getNewVerticalDelta(other));
			player.verticalStop();
		}
		return new_delta;
	}
}
