import java.lang.Math;

import javax.swing.JFrame;
import java.lang.Thread;

public class Main {
	public static double g = 9.8;
	public static double fps = 60;
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("2D Physics Simulator");
		PolyPanel panel = new PolyPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
		while (true) {
			Thread.sleep(1000);
			
			panel.setPolys(new Mass[] {new Mass(v(Math.random() * WIDTH, Math.random() * HEIGHT), 1, (int)(Math.random() * 0xffffff),
												v(Math.random() * 200 - 100, Math.random() * 200 - 100),
												v(Math.random() * 200 - 100, Math.random() * 200 - 100),
												v(Math.random() * 200 - 100, Math.random() * 200 - 100))});
		}
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
