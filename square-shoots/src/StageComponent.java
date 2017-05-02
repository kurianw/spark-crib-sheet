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
		g.fillRect(xposition, (int) yposition, width, height);		
	}
}
