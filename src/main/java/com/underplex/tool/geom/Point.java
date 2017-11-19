package com.underplex.tool.geom;

/**
 * Represents point in Cartesian plane with double precision x and y values.
 * 
 * This class largely repeats functionality of similar <code>Point2D</code> class in <code>java.awt.geom</code>, with the difference that this class is meant to be immutable.
 * @author Brandon Irvine
 */
public class Point {

	public final static double PRECISION = 0.000001;
	public final static double PRECISION_INV = 1/PRECISION;
	private final double x;
	private final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}	

	/*
	 * Returns distance to point from this point.
	 */
	public double distance(Point point){
		double deltaXsq = Math.pow(this.x - point.getX(),2);
		double deltaYsq = Math.pow(this.y - point.getY(),2);
		
		return Math.sqrt(deltaXsq + deltaYsq);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = (long)(x * PRECISION_INV);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = (long)(y * PRECISION_INV);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Math.abs(x - other.getX()) > PRECISION)
			return false;
		if (Math.abs(y - other.getY()) > PRECISION)	
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
