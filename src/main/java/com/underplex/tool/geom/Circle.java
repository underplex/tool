package com.underplex.tool.geom;

/**
 * A circle in Cartesian space with double-precision specifications.
 * 
 * Instances are meant to be immutable once constructed.
 * 
 * Circles with radius zero can be constructed.
 * @author Brandon Irvine
 *
 */
public class Circle {
	
	private final Point center;
	private final double radius;
	private final double radiusSquared;
	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
		this.radiusSquared = Math.pow(radius, 2);
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 * Returns true iff it is believed the circle contains the Point.
	 * 
	 * Rounding errors may mean that some points aren't properly detected as being in the circle.
	 * @return
	 */
	public boolean contains(Point point){
		return point.distance(this.center) <= radius;		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + "]";
	}
	
}
