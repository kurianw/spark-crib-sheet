import java.util.ArrayList;

public abstract class Collidable {
	public int xposition;
	public double yposition;
	public int width;
	public int height;

	public boolean collides(Collidable other, boolean up) {
		boolean collision_detected = this.xposition < other.xposition + other.width
				&& this.xposition + this.width > other.xposition && this.yposition < other.yposition + other.height
				&& this.height + this.yposition > other.yposition;
		if (up && collision_detected) {
			System.out.println("There is an unexpected overlap on the y axis");
			System.out.println("this y position ceiling:" + Double.toString(this.yposition));
			System.out.println("other y position floor:" + Double.toString(other.yposition + other.height));
		}
		return collision_detected;
	}

	public void resolve(ArrayList<Collidable> others) {
	};
}
