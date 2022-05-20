import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class PolyPanel extends JPanel {
	// Member variables
	private Mass[] polys;
	
	public Vector p;
	public int r;
	
	// Constructors
	public PolyPanel() {
		polys = new Mass[0];
		
		p = new Vector(0, 0);
		r = 0;
	}
	
	// Methods
	public void drawPolys(Mass[] masses) {
		polys = masses;
		updateUI();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < polys.length; i++) {
			g.setColor(polys[i].getColor());
			g.fillPolygon(polys[i].getPoly());
		}
		
		g.setColor(r > 0 ? Color.BLACK : Color.WHITE);
		r = Math.abs(r);
		g.drawOval((int)p.getX() - Math.abs(r), (int)p.getY() - r, r*2, r*2);
		g.fillOval((int)p.getX() - 5, (int)p.getY() - 5, 10, 10);
	}
}
