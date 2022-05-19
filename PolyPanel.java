import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Graphics;

public class PolyPanel extends JPanel {
	// Member variables
	private ArrayList<int[]> polyX;
	private ArrayList<int[]> polyY;
	
	private int numPolys;
	private Mass[] polys;
	
	// Constructors
	public PolyPanel() {
		polyX = new ArrayList<>();
		polyY = new ArrayList<>();
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
				masses[i].getVertices()[j].rotate(masses[i].getAngle());
				
				vertX[j] = (int)(masses[i].getVertices()[j].getX() + masses[i].getPos().getX());
				vertY[j] = (int)(getHeight() - masses[i].getVertices()[j].getY() - masses[i].getPos().getY());
				
				masses[i].getVertices()[j].rotate(-masses[i].getAngle());
			}
			
			polyX.add(vertX);
			polyY.add(vertY);
		}
		
		updateUI();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < numPolys; i++) {
			g.setColor(polys[i].getColor());
			g.fillPolygon(polyX.get(i), polyY.get(i), polyX.get(i).length);
			
			g.setColor(polys[i].getColor().brighter());
			g.fillOval((int)polys[i].getPos().getX() - 5, getHeight() - (int)polys[i].getPos().getY() - 5, 10, 10);
		}
	}
}
