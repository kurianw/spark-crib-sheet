import java.util.ArrayList;

public abstract class Collidable {
	public int xposition;
	public double yposition;
	public int width;
	public int height;
	
	public boolean collides(Collidable other) {
		return this.xposition < other.xposition + other.width &&
				   this.xposition + this.width > other.xposition &&
				   this.yposition < other.yposition + other.height &&
				   this.height + this.yposition > other.yposition;				
	}
	
	public void resolve(ArrayList<Collidable> others) {		
	};	
}
