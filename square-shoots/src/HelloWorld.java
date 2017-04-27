import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class HelloWorld extends Applet implements KeyListener, Runnable {
	// Variables
	Thread runner;
	 int xposition = 0;
	int xspeed = 0;
	int xposition2 = 0;
	double yspeed = 0;
	double g = .005;
	double yposition = 0;
	double yposition2 = 0;
	public Player player;
	public Player player2;
	// Methods
	public void start () {
		if (runner == null) {
			runner = new Thread(this);
					runner.start();
				
		}
	}
	
	public void stop() {
		if (runner != null) {
			runner.stop();
			runner = null;
		}
	}

	 public void run() {
		 this.addKeyListener(this);
		 setFocusable(true);
		 player = new Player();
		 player.setColor("#ff0000","#1b8754");
		 player2 = new Player();
		 player2.setColor("#0000ff","#dda22c");
         while (true) {
             repaint(); 
             player.update();
             player2.update();
             yposition = yposition - yspeed;
             xposition = xposition + xspeed;
             yposition2 = yposition2 - yspeed;
             xposition2 = xposition2 + xspeed;
             if (yposition < 0) {
            	 if (yposition2 < 0) 
            	 // calculate gravity's affect
            	 yspeed = yspeed - g;
             }
             else {
            	 yposition = 0;
            	 yposition2 = 0;
             }
             try {Thread.sleep(10);} catch (Exception ex) {}
             }
	 }
	public void paint(Graphics g) {
		//Characters
		paintcharacter(g, 470+ (int)player2.getYposition(), 720+ (int)player2.getXposition(),"#ff0000");  //Red
		g.setColor(Color.decode(player2.GetEyeColor()));
		g.fillRect(720+ (int)player2.getXposition(), 472+ (int)player2.getYposition(), 5, 12);
		
		paintcharacter(g, 470+ (int)player.getXposition(), 150+ (int)player.getXposition(),"#0000ff"); //Blue
		g.setColor(Color.decode(player.GetEyeColor()));
		g.fillRect(175+ (int)player.getXposition(), 472+ (int)player.getXposition(), 5, 12);		
		//Stage
		g.setColor(Color.BLACK);
		g.fillRect(150, 500, 600, 25);
		g.fillRect(250, 400, 400, 25);
		g.fillRect(150, 300, 250, 25);
		g.fillRect(500, 300, 250, 25);
		g.fillRect(135, 195, 15, 330);
		g.fillRect(750, 195, 15, 330);
		g.fillRect(135, 195, 630, 15);	
		//g.fillRect(x, y, width, height);
		
	
	}
	public void paintcharacter(Graphics g, int y, int x, String c) {
		g.setColor(Color.decode(c));
		g.fillRect(x, y, 30, 30);
	}

	public Polygon getHexagon(int x, int y, int size) {
		Polygon hex = new Polygon();
		for (int i = 0; i < 6; i++) {
			hex.addPoint((int) (x + (size * Math.cos(i*2*Math.PI / 6))),
					(int) (y + (size * Math.sin(i*2*Math.PI / 6))));
		}
		return hex;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37){
			xspeed = -1;
			
		} else if (e.getKeyCode() == 39) {
			xspeed = 1;
		}
		else if (e.getKeyCode() == 38){
			yspeed =1;
			
		} else if (e.getKeyCode() == 68) {
			xspeed =1;
			
		} else if (e.getKeyCode() == 65){
			xspeed = -1;
			
		} else if (e.getKeyCode() == 87) {
			yspeed = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		xspeed=0;
	}
}
//box around stage and other player stuffs