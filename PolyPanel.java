import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Graphics;

public class PolyPanel extends JPanel {
	// Member variables
	private ArrayList<int[]> polyX;
	private ArrayList<int[]> polyY;
	
	private int numPolys;
	private Mass[] polys;
	
	private Vector p;
	private int r;
	
	// Constructors
	public PolyPanel(Vector point) {
		polyX = new ArrayList<>();
		polyY = new ArrayList<>();
		
		p = point;
		r = 0;
	}
	
	// Methods
	public void setPolys(Mass[] masses) {
		polyX.clear();
		polyY.clear();
		numPolys = masses.length;
		
		polys = masses;
		
		for (int i = 0; i < masses.length; i++) {
			int numVerts = masses[i].getVertices().length;
			
			int[] vertX = new int[numVerts];
			int[] vertY = new int[numVerts];
			
			for (int j = 0; j < numVerts; j++) {
				Vector v = masses[i].getVertices()[j];
				
				v.rotate(masses[i].getAngle());
				
				vertX[j] = (int)(v.getX() + masses[i].getPos().getX());
				vertY[j] = (int)(getHeight() - v.getY() - masses[i].getPos().getY());
				
				v.rotate(-masses[i].getAngle());
			}
			
			polyX.add(vertX);
			polyY.add(vertY);
		}
		
		updateUI();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawOval((int)p.getX() - r, getHeight() - (int)p.getY() - r, r*2, r*2);
		g.fillOval((int)p.getX() - 5, getHeight() - (int)p.getY() - 5, 10, 10);
		
		for (int i = 0; i < numPolys; i++) {
			g.setColor(polys[i].getColor());
			g.fillPolygon(polyX.get(i), polyY.get(i), polyX.get(i).length);
		}
	}
	
	public void setr(int d) {
		r = d;
	}
}
