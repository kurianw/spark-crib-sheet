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

	// Variables
	public int xspeed;
	public double yspeed;
	public String color;
	public String eyes;	

	// private variables
	private int old_xposition;
	private double old_yposition;
	private Direction shot_directions;
	
	public Player(int xposition, double yposition, String color, String eyes, Direction start_direction) {
		this.color = color;
		this.eyes = eyes;
		this.xposition = xposition;
		this.yposition = yposition;
		this.width = BODY_WIDTH;
		this.height = BODY_HEIGHT;		
	};

	public void jump() {		
		yspeed = 1;
	};

	public void moveLeft() {
		
		xspeed = -1;
	};

	public void moveRight() {		
		xspeed = 1;
	}

	public void horizontalStop() {
		xspeed = 0;
	}

	public void update(ArrayList<Collidable> potential_collisions, boolean debug) {
		old_xposition = xposition;
		old_yposition = yposition;
		yposition = yposition - yspeed;
		xposition = xposition + xspeed;		
		ArrayList<Collidable> collisions = new ArrayList<Collidable>();
		for (Collidable potential_barrier : potential_collisions) {			
			if (collides(potential_barrier, false)) {
				collisions.add(potential_barrier);
			}
		}
		resolve(collisions);
		yspeed = yspeed - g;
	};

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

	public double getXposition() {
		return xposition;
	}

	void render(Graphics g) {
		// Fill Body
		g.setColor(Color.decode(getBodyColor()));
		int graphical_xposition = (int) (HelloWorld.LEFT_WORLD_ORIGIN + getXposition());
		int graphical_yposition = (int) (HelloWorld.TOP_WORLD_ORIGIN + getYposition());
		g.fillRect(graphical_xposition, graphical_yposition, width, height);

		// Fill Eyes
		g.setColor(Color.decode(getEyeColor()));
		g.fillRect(EYE_HORIZONTAL_OFFSET + graphical_xposition, EYE_VERTICAL_OFFSET + graphical_yposition, EYE_WIDTH,
				EYE_HEIGHT);
	}

	public void resolve(ArrayList<Collidable> others) {		
		for (Collidable other : others) {
			Player xOnlyPlayer = new Player(xposition, old_yposition, "", "", Direction.RIGHT);
			Player yOnlyPlayer = new Player(old_xposition, yposition, "", "", Direction.RIGHT);
			if (xOnlyPlayer.collides(other, false)) {
				xposition = resolveHorizontalAxis(xposition, other);
			}
			if (yOnlyPlayer.collides(other, false))
			yposition = resolveVerticalAxis(yposition, other);
		}
	}

	private int resolveHorizontalAxis(int new_xposition, Collidable other) {

		if (xspeed < 0) { // left
			if (new_xposition < other.xposition + other.width) {
				new_xposition = other.xposition + other.width + 1;
				xspeed = 0;
			}
		} else if (xspeed > 0) { // right
			if (new_xposition + width > other.xposition) {			
				new_xposition = other.xposition - width - 1;
				xspeed = 0;
			}
		}
		return new_xposition;
	};

	private double resolveVerticalAxis(double new_yposition, Collidable other) {		
		if (yspeed > 0) { // up
			if (new_yposition < other.yposition + other.height) {
				new_yposition = (int) (other.yposition + other.height) + 1;				
				yspeed = 0;				
			}
		} else if (yspeed < 0) { // down
			if (new_yposition + height > other.yposition) {
				new_yposition = (int) (other.yposition - height) - 1;
				yspeed = 0;
			}
		}
		return new_yposition;
	}

	public void shoot() {
		// TODO create a shot.		
	}
}
