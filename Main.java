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
			
			panel.setr((int)masses[0].getDistance(masses[1], p));
			masses[0].getPos().rotate(0.01, masses[1].getPos());
			masses[0].setAngle(masses[0].getAngle() + 0.017);
			masses[1].setAngle(masses[1].getAngle() + 0.023);
			
			panel.setPolys(masses);
		}
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
