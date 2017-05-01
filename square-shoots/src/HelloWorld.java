import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class HelloWorld extends Applet implements KeyListener, Runnable {
	// Variables
	Thread runner;	
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
             try {Thread.sleep(10);} catch (Exception ex) {}
             }
	 }
	public void paint(Graphics g) {
		//Characters
		paintPlayer2(g,player2);		
		paintPlayer2(g, player);		
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

	private void paintPlayer2(Graphics g, Player player) {		
		g.setColor(Color.decode(player.getBodyColor()));
		g.fillRect(150+ (int)player.getXposition(), 470+ (int)player.getYposition(), 30, 30);
		g.setColor(Color.decode(player.GetEyeColor()));
		g.fillRect(175+ (int)player.getXposition(), 472+ (int)player.getYposition(), 5, 12);
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
			player.moveLeft();
			
		} else if (e.getKeyCode() == 39) {
			player.moveRight();;
		}
		else if (e.getKeyCode() == 38){
			player.jump();
			
		} else if (e.getKeyCode() == 68) {
			player2.moveRight();
			
		} else if (e.getKeyCode() == 65){
			player2.moveLeft();;
			
		} else if (e.getKeyCode() == 87) {
			player2.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37){
			player.horizontalStop();
			
		} else if (e.getKeyCode() == 39) {
			player.horizontalStop();
		}		
	}
}
//box around stage and other player stuffs