import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Collidable {

	// Constants
	public static final double g = 0.005;
	private static final int BODY_WIDTH = 30;
	private static final int BODY_HEIGHT = 30;
	private static final int EYE_HORIZONTAL_OFFSET = 25;
	private static final int EYE_VERTICAL_OFFSET = 2;
	private static final int EYE_WIDTH = 5;
	private static final int EYE_HEIGHT = 12;

	// Direction Enum
	public enum Direction {
		RIGHT, LEFT
	}
	
	// Direction Enum
	private enum Stage {
		ALIVE, DEAD, WON
	}

	// Variables
	public int xspeed;
	public double yspeed;
	public String color;
	public String eyes;

	// private variables
	private Direction direction;
	private Stage stage;

	public Player(int xposition, double yposition, String color, String eyes, Direction start_direction) {
		this.xposition = xposition;
		this.yposition = yposition;
		this.color = color;
		this.eyes = eyes;
		this.direction = start_direction;

		this.stage = Stage.ALIVE;
		this.width = BODY_WIDTH;
		this.height = BODY_HEIGHT;
	};

	public void jump() {
		yspeed = 1;
	};

	public void moveLeft() {
		direction = Direction.LEFT;
		xspeed = -1;
	};

	public void moveRight() {
		direction = Direction.RIGHT;
		xspeed = 1;
	}

	public void horizontalStop() {
		xspeed = 0;
	}

	public void verticalStop() {
		yspeed = 0;
	}

	public void accelerate() {
		yspeed = yspeed - g;
	}

	public void setColor(String color, String eyes) {
		this.color = color;
		this.eyes = eyes;
	};

	public String getBodyColor() {
		return color;
	}

	public String getEyeColor() {
		return eyes;
	}

	public double getYposition() {
		return yposition;
	};

	public int getXposition() {
		return xposition;
	}

	public Delta getDelta() {
		return new Delta(xspeed, -1 * yspeed);
	}
	
	public boolean isAlive() {
		return stage != Stage.DEAD;
	}

	public Player applyDelta(Delta d) {
		Player moved = new Player(xposition + d.x_component, yposition + d.y_component, color, eyes, direction);
		moved.xspeed = xspeed;
		moved.yspeed = yspeed;		
		moved.stage = stage;
		return moved;
	}

	public Delta getNewHorizontalDelta(Collidable other) {
		if (xspeed < 0) { // left
			return new Delta(other.xposition + other.width + 1 - xposition, 0);
		} else { // right
			return new Delta(other.xposition - xposition - width - 1, 0);
		}
	};

	public Delta getNewVerticalDelta(Collidable other) {
		if (yspeed > 0) { // up
			return new Delta(0, other.yposition + other.height + 1 - yposition);
		} else { // down
			return new Delta(0, other.yposition - yposition - height - 1);
		}
	}

	public Shot shoot() {
		int shot_offset = (direction == Direction.LEFT ? -3 : EYE_HORIZONTAL_OFFSET + EYE_WIDTH + 3);
		return new Shot(shot_offset + xposition, yposition, eyes, direction);
	}

	void render(Graphics g) {
		if (isAlive()) {
			// Fill Body
			g.setColor(Color.decode(getBodyColor()));
			int graphical_xposition = (int) (SquareShoots.LEFT_WORLD_ORIGIN + getXposition());
			int graphical_yposition = (int) (SquareShoots.TOP_WORLD_ORIGIN + getYposition());
			g.fillRect(graphical_xposition, graphical_yposition, width, height);

			// Fill Eyes
			int eye_offset = (direction == Direction.LEFT ? 0 : EYE_HORIZONTAL_OFFSET);
			g.setColor(Color.decode(getEyeColor()));
			g.fillRect(eye_offset + graphical_xposition, EYE_VERTICAL_OFFSET + graphical_yposition, EYE_WIDTH,
					EYE_HEIGHT);
		}
	}

	public void kill() {
		this.stage = Stage.DEAD;
	}

	public boolean hasWon() {		
		return this.stage == Stage.WON;
	}

	public void wins() {
		stage = Stage.WON;		
	}

	ArrayList<Shot> handleShots(ArrayList<Shot> shots, ArrayList<StageComponent> stage) {
		ArrayList<Shot> absorbed_shots = new ArrayList<Shot>();			
		for (Shot shot : shots) {
			for (StageComponent component : stage) {
				if (shot.collides(component)) {
					absorbed_shots.add(shot);
				}
			}
			if (collides(shot) && isAlive()) {
				if (!hasWon()) {						
					kill();										
				}
				absorbed_shots.add(shot);
			}
		}						
		return absorbed_shots;
	}

	Player handleNonFatalCollisions(Player other, ArrayList<StageComponent> stage) {
		Delta delta = getDelta();
		for (StageComponent stage_component : stage) {
			delta = updateDelta(delta, stage_component);
		}		
		if (other.isAlive() && other != this) {
			delta = updateDelta(delta, other);
		}		
		accelerate();
		return applyDelta(delta);		
	}

	Delta updateDelta(Delta original_delta, Collidable other) {
		Delta new_delta = original_delta;
		if (applyDelta(new_delta.getHorizontalDelta()).collides(other)) {
			new_delta = getNewHorizontalDelta(other).add(new_delta.getVerticalDelta());
			horizontalStop();
		}
		if (applyDelta(new_delta.getVerticalDelta()).collides(other)) {
			new_delta = new_delta.getVerticalDelta().add(getNewVerticalDelta(other));
			verticalStop();
		}
		return new_delta;
	}
}
