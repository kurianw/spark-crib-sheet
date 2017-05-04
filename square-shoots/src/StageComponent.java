import java.awt.Color;
import java.awt.Graphics;

public class StageComponent extends Collidable {	
	
	public StageComponent(int xposition, double yposition, int width, int height) {
		this.xposition = xposition;
		this.yposition = yposition;
		this.width = width;
		this.height = height;
	}
	
	void render(Graphics g) {		
		g.setColor(Color.BLACK);
		int graphical_xposition = SquareShoots.LEFT_WORLD_ORIGIN + xposition;
		int graphical_yposition = SquareShoots.TOP_WORLD_ORIGIN + (int) yposition;
		g.fillRect(graphical_xposition, graphical_yposition, width, height);		
	}
}
