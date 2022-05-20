import java.lang.Math;

import javax.swing.JFrame;
import java.lang.Thread;

public class Main {
	public static final Vector g = new Vector(0, -3.8);
	//public static final double fps = 60;
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("2D Physics Simulator");
		PolyPanel panel = new PolyPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
		Mass[] masses = {Mass.random(v(panel.getWidth() / 2, panel.getHeight() / 3), 0, 1, (int)(Math.random() * 0xffffff), (int)(Math.random() * 7 + 3), 100),
						 Mass.random(v(panel.getWidth() / 2, panel.getHeight() / 2), 0, 1, (int)(Math.random() * 0xffffff), (int)(Math.random() * 7 + 3), 100)};
		
		masses[0].setRotVel(0.1);
		
		while (true) {
			Thread.sleep(50);
			
			//masses[0].move(0.3, g);
			
			panel.r = (int)masses[0].getDistance(masses[1], panel.p);
			masses[0].getPos().rotate(0.017, masses[1].getPos());
			masses[0].setRot(masses[0].getRot() - 0.01);
			masses[1].setRot(masses[1].getRot() + 0.023);
			
			panel.drawPolys(masses);
		}
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
