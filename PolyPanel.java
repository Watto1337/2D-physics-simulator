import javax.swing.JPanel;
import java.awt.Graphics;

import java.util.ArrayList;

public class PolyPanel extends JPanel {
	private ArrayList<int[]> polyX;
	private ArrayList<int[]> polyY;
	private int numPolys;
	
	public PolyPanel(Mass[] masses) {
		polyX = new ArrayList<>();
		polyY = new ArrayList<>();
		numPolys = masses.length;
		
		updatePolys(masses);
	}
	
	public void updatePolys(Mass[] masses) {
		for (int i = 0; i < masses.length; i++) {
			int numVerts = masses[i].getVertices().length;
			
			int[] vertX = new int[numVerts];
			int[] vertY = new int[numVerts];
			
			for (int j = 0; j < numVerts; j++) {
				vertX[j] = (int)(masses[i].getVertices()[j].getX() + masses[i].getPos().getX());
				vertY[j] = (int)(masses[i].getVertices()[j].getY() + masses[i].getPos().getY());
			}
			
			polyX.add(vertX);
			polyY.add(vertY);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < numPolys; i++)
			g.fillPolygon(polyX.get(i), polyY.get(i), polyX.get(i).length);
	}
}