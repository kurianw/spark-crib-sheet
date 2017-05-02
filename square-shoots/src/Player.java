import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Collidable {
public int xspeed;
public double yspeed;
public String color;
public String eyes;
public static final double g = 0.005;

public Player(String color, String eyes){
	this.color = color;
	this.eyes = eyes;
};

public void jump(){
	yspeed = 1;
};
public void moveLeft(){
	xspeed = -1;
};
public void moveRight(){
	xspeed = 1;
}
public void horizontalStop() {
	xspeed = 0;
}

public void update(){
	   yposition = yposition - yspeed;
       xposition = xposition + xspeed;
       if (yposition < 0) {
      	 // calculate gravity's affect
      	 yspeed = yspeed - g;
       }
       else {
      	 yposition = 0;
       }
};
public void setColor(String color, String eyes){
	this.color = color;
	this.eyes = eyes;
};

public String getBodyColor() {
	return color;
}

public String GetEyeColor() {
	return eyes;
}
public double getYposition(){
	return yposition;
};
public double getXposition(){
	return xposition;
}

void render(Graphics g) {		
	g.setColor(Color.decode(getBodyColor()));
	g.fillRect(150+ (int)getXposition(), 470+ (int)getYposition(), 30, 30);
	g.setColor(Color.decode(GetEyeColor()));
	g.fillRect(175+ (int)getXposition(), 472+ (int)getYposition(), 5, 12);
}
public void setPosition(int xPosition, int yPosition) {
	this.xposition = xPosition;
	this.yposition = yPosition;
	
}

public void resolve(ArrayList<Collidable> others) {
	int new_xposition = xposition;
	double new_yposition = yposition;
	for (Collidable other: others) {
		new_xposition = resolveHorizontalAxis(new_xposition, other);
		new_yposition = resolveVerticalAxis(new_yposition, other);
	}
}

private int resolveHorizontalAxis(int new_xposition, Collidable other) {
	if (xspeed < 0) { //left
		if (new_xposition < other.xposition + other.width) {
			new_xposition = other.xposition + other.width + 1;
		}
	} else { //right
		if (new_xposition + width > other.xposition) {
			new_xposition = other.xposition - width - 1;
		}
	}
	return new_xposition;
};	


private double resolveVerticalAxis(double new_yposition, Collidable other) {
	if (yspeed < 0) { //up
		if (new_yposition > other.yposition - other.height) {
			new_yposition = other.yposition - other.height - 1;
		}
	} else { //down
		if (new_yposition - height < other.yposition) {
			new_yposition = other.yposition + height + 1;
		}
	}
	return new_yposition;
};

}

