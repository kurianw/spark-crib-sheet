import java.awt.Graphics;

public class Shot extends Collidable {
	
	private Player.Direction shot_direction;
	private int speed;
	private String color;
	
	public Shot(int xposition, double yposition, String color, Player.Direction start_direction) {	
		this.xposition = xposition;
		this.yposition = yposition;
		this.color = color;
		
		this.width = 1;
		this.height = 1;
		this.speed = 1;		
	};
	
	public void update() {
		int direction_multiplier = shot_direction == Player.Direction.RIGHT ? 1: -1;
		xposition += direction_multiplier;
	}
	
	public void render(Graphics g) {
		int graphical_xposition = (int) (HelloWorld.LEFT_WORLD_ORIGIN + xposition);
		int graphical_yposition = (int) (HelloWorld.TOP_WORLD_ORIGIN + yposition);
		g.fillRect(graphical_xposition, graphical_yposition, width, height);
	}
}
