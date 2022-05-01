import java.lang.Math;

public class Mass {
	// Member variables
	private Vector pos;
	private double angle;
	
	private double density;
	private double area;
	
	private Vector[] vertices;
	
	public Mass(Vector pos, Vector[] vertices, double density) {
		this.pos = new Vector(pos);
		this.density = density;
		
		area = 0;
		double centerX = 0;
		double centerY = 0;
		
		for (int i = 0, j = 1; i < vertices.length; i++, j = (j + 1) % vertices.length) {
			double partialArea = vertices[i].getX() * vertices[j].getY() - vertices[i].getY() * vertices[j].getX();
			
			centerX += partialArea * (vertices[i].getX() + vertices[j].getX());
			centerY += partialArea * (vertices[i].getY() + vertices[j].getY());
			area += partialArea;
		}
		
		centerX /= area * 3;
		centerY /= area * 3;
		area = Math.abs(area / 2);
		
		this.vertices = new Vector[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = new Vector(vertices[i].getX() - centerX, vertices[i].getY() - centerY);
		}
	}
	
	// Getters
	public Vector getPos() {return pos;}
	public double getAngle() {return angle;}
	public double getDensity() {return density;}
	public double getArea() {return area;}
	public Vector[] getVertices() {return vertices;}
	
	// Setters
	public void setAngle(double angle) {this.angle = angle;}
	public void setDensity(double density) {this.density = density;}
}
