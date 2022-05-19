import java.lang.Math;

import java.awt.Color;

public class Mass {
	// Member variables
	private Vector pos;
	private Vector vel;
	private double rot;
	private double rotVel;
	
	private double density;
	private double area;
	
	private Vector[] vertices;
	
	private Color color;
	
	// Constructors
	public Mass(Vector pos, double density, int color, Vector... vertices) {
		this.pos = new Vector(pos);
		this.vel = new Vector(0, 0);
		this.density = density;
		
		this.rot = 0;
		this.rotVel = 0;
		
		this.color = new Color(color);
		
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
		
		this.vertices = new Vector[vertices.length + 1];
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = new Vector(vertices[i].getX() - centerX, vertices[i].getY() - centerY);
		}
		
		this.vertices[vertices.length] = this.vertices[0];
	}
	
	// Methods
	// Move the mass by an amount dependent on time and constant acceleration (gravity)
	public void move(double t, Vector acc) {
		vel.multiply(t);
		pos.add(vel);
		vel.divide(t);
		
		acc.multiply(t * t * 0.5);
		pos.add(acc);
		acc.divide(t * 0.5);
		
		vel.add(acc);
		acc.divide(t);
		
		rot = (rot + rotVel) % (Math.PI * 2);
		
		//System.out.println(vel.len() + " " + pos.len()+ " " + acc.len());
	}
	
	public double getDistance(Vector v) {
		v.rotate(-rot);
		
		double d = Double.MAX_VALUE;
		for (int i = 0; i < vertices.length - 1; i++) {
			v.subtract(vertices[i]);
			vertices[i+1].subtract(vertices[i]);
			
			double r = Math.min(Math.max(v.dot(vertices[i+1]) / vertices[i+1].lenSquared(), 0), 1);
			vertices[i+1].multiply(r);
			
			d = Math.min(d, v.dist(vertices[i+1]));
			
			vertices[i+1].divide(r);
			vertices[i+1].add(vertices[i]);
			v.add(vertices[i]);
		}
		
		v.rotate(rot);
		
		return d;
	}
	
	// Getters
	public Vector getPos() {return pos;}
	public double getAngle() {return rot;}
	public double getDensity() {return density;}
	public double getArea() {return area;}
	public double getMass() {return density * area;}
	public Vector[] getVertices() {return vertices;}
	public Color getColor() {return color;}
	
	// Setters
	public void setAngle(double rot) {this.rot = rot;}
	public void setDensity(double density) {this.density = density;}
	public void setColor(int color) {this.color = new Color(color);}
}
