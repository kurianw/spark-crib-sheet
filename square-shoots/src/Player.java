import java.awt.Color;
import java.awt.Graphics;

public class Player {
public int xposition;
public double yposition;
public int xspeed;
public double yspeed;
public String color;
public String eyes;
public static final double g = 0.005;
public Player(){};
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

}

