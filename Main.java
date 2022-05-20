import java.lang.Math;

import javax.swing.JFrame;
import java.lang.Thread;

public class Main {
	public static final Vector g = new Vector(0, -9.8);
	//public static final double fps = 60;
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	
	public static void main(String[] args) throws InterruptedException {
		Vector p = new Vector(0, 0);
		
		JFrame frame = new JFrame("2D Physics Simulator");
		PolyPanel panel = new PolyPanel(p);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
		Mass[] masses = {Mass.random(v(Math.random() * panel.getWidth(), Math.random() * panel.getHeight()), 1, (int)(Math.random() * 0xffffff), 5, 100),
						 Mass.random(v(panel.getWidth() / 2, panel.getHeight() / 2), 1, (int)(Math.random() * 0xffffff), 5, 100)};
		
		panel.setPolys(masses);
		
		while (true) {
			Thread.sleep(50);
			
			//masses[0].move(0.1, g);
			panel.setr((int)masses[0].getDistance(masses[1], p));
			masses[0].getPos().rotate(0.05, masses[1].getPos());
			//masses[0].getPos().add(1, 0);
			
			panel.setPolys(masses);
			
			//p.add(1, 0);
			//p.set(Math.random() * panel.getWidth(), Math.random() * panel.getHeight());
			//p.rotate(0.1, WIDTH / 2, HEIGHT / 2);
		}
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
