
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
public void setColor( String color, String eyes){
	this.color = color;
	this.eyes = eyes;
};
public double getYposition(){
	return yposition;
};
public double getXposition(){
	return xposition;
}	
}

