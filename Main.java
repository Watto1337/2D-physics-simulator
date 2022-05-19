import java.lang.Math;

import javax.swing.JFrame;
import java.lang.Thread;

public class Main {
	public static final Vector g = new Vector(0, -9.8);
	public static final double fps = 60;
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("2D Physics Simulator");
		PolyPanel panel = new PolyPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
		Mass[] masses = {new Mass(v(Math.random() * WIDTH, Math.random() * HEIGHT), 1, (int)(Math.random() * 0xffffff),
								  v(Math.random() * 200 - 100, Math.random() * 200 - 100),
								  v(Math.random() * 200 - 100, Math.random() * 200 - 100),
								  v(Math.random() * 200 - 100, Math.random() * 200 - 100))};
		
		panel.setPolys(masses);
		
		while (true) {
			Thread.sleep(10);
			
			masses[0].move(0.1, g);
			
			panel.setPolys(masses);
		}
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
