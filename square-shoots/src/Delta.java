public class Delta {
	public int x_component;
	public double y_component;

	public Delta(int x_component, double y_component) {
		this.x_component = x_component;
		this.y_component = y_component;
	}

	public Delta getHorizontalDelta() {
		return new Delta(x_component, 0);
	}

	public Delta getVerticalDelta() {
		return new Delta(0, y_component);
	}

	public Delta add(Delta other) {
		return new Delta(this.x_component + other.x_component, this.y_component + other.y_component);
	}
	
	public String toString(){
		return "x_component: " + x_component + ", y_component: " + y_component; 
	}
}
