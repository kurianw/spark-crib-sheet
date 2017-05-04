import java.awt.Color;
import java.awt.Graphics;

public class Shot extends Collidable {
	
	private Player.Direction direction;
	private int speed;
	private String color;
	
	public Shot(int xposition, double yposition, String color, Player.Direction start_direction) {	
		this.xposition = xposition;
		this.yposition = yposition;
		this.color = color;
		this.direction = start_direction;			
		this.width = 2;
		this.height = 2;
		this.speed = 1;		
	};
	
	public void update() {		
		boolean is_right_moving_shot = direction == Player.Direction.RIGHT;		
		int direction_multiplier = (is_right_moving_shot ? 1: -1);
		xposition += direction_multiplier * speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.decode(color));
		int graphical_xposition = (int) (SquareShoots.LEFT_WORLD_ORIGIN + xposition);
		int graphical_yposition = (int) (SquareShoots.TOP_WORLD_ORIGIN + yposition);
		g.fillRect(graphical_xposition, graphical_yposition, width, height);
	}
}
