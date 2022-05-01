import javax.swing.JFrame;

public class Main {
	// Constants
	public static final double GRAVITY = 9.8;
	
	public static void main(String[] args) {
		Mass m = new Mass(v(100, 100), new Vector[] {v(0, 0), v(0, -10), v(-10, 0)}, 1);
		
		JFrame frame = new JFrame("2D Physics Simulator");
		PolyPanel panel = new PolyPanel(new Mass[] {m});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(750, 500);
		frame.setVisible(true);
	}
	
	private static Vector v(double x, double y) {return new Vector(x, y);}
}
