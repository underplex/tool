package com.underplex.tool.geom;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a line of infinite length in Cartesian plane specified with double precision.
 * 
 * Instances of this class are meant to be immutable.
 * 
 * http://math.stackexchange.com/questions/228841/how-do-i-calculate-the-intersections-of-a-straight-line-and-a-circle
 * This was used to work out the how to find the intersects with a circle.
 * 
 * Lines are either vertical or not. If the slope and intercept of the line are defined as numbers, then the line is not vertical. Otherwise, it is.
 * 
 * @author Brandon Irvine
 *
 */
public class Line {

	protected final double slope;
	protected final double intercept;
	protected final double verticalX;
	
	protected Line(double xIntercept){
		this.slope = Double.NaN;
		this.intercept = Double.NaN;
		this.verticalX = xIntercept;
	}
	
	protected Line(Point p1, Point p2){
		
		// sort p1 and p2 from left to right
		Point left = p1;
		Point right = p2;
		if (p1.getX() > p2.getX()){
			left = p2;
			right = p1;
		}
		
		if (p1.getX() == p2.getX()){
			this.slope = Double.NaN;
			this.intercept = Double.NaN;
			this.verticalX = p1.getX();
		} else {
			this.slope = (right.getY() - left.getY())/(right.getX() - left.getX());
			this.verticalX = Double.NaN;
			this.intercept = p1.getY() - (p1.getX() * this.slope);
		}
				
	}
	
	public Line(double slope, double intercept) {
		if (Double.isFinite(slope) && Double.isFinite(intercept)){
			this.slope = slope;
			this.intercept = intercept;
			this.verticalX = Double.NaN;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns slope. If a vertical line, no guarantees about what is returned.
	 * @return
	 */
	public double getSlope() {
		return slope;
	}

	/**
	 * Returns intercept with y axis, that is, the value of y when x = 0. If a vertical line, no guarantees about what is returned. 
	 * @return
	 */
	public double getIntercept() {
		return intercept;
	}
	
	/**
	 * Returns true iff line is perfectly vertical.
	 */
	public boolean isVertical(){
		return (Double.isNaN(this.slope));
	}
	
	/**
	 * Returns the x-coordinate defining this iff this is a vertical line, or NaN if not.
	 * @return
	 */
	public double getVerticalX(){
		return this.verticalX;
	}
	
	/**
	 * Factory method for producing a vertical line in Cartesian space where no slope or y-intercept is logical.
	 * @param xIntercept X-coordinate where the line intercepts the x-axis.
	 * @return
	 */
	public static Line makeVertical(double xIntercept){
		return new Line(xIntercept);
	}

	/**
	 * Returns true iff this intersects the point.
	 * @param point
	 * @return
	 */
	public boolean intersects(Point point){
		if (this.isVertical()){
			return Math.abs(point.getX() - this.verticalX) < Point.PRECISION;
		}
		return Math.abs(point.getY() - (point.getX() * slope + intercept)) < Point.PRECISION;
	}
	
	/**
	 * Calculates y (given the precision of Point) given a value of x.
	 * @param x
	 * @return
	 */
	public double yGivenX(double x){
		double rVal = Double.NaN;
		if (!this.isVertical()){
			rVal = this.slope * x + this.intercept;
		}
		return rVal;
	}
	
	/**
	 * Returns a Set representing any points believed to be intersecting the given Circle, or empty Set if none exist.
	 * 
	 * Tries to solve a quadratic equation to solve for the intersects.
	 * 
	 * Since this method uses the double precision of the classes, it cannot guarantee that it will find single tangential intersection points because of rounding errors inherent to 
	 * the double precision standard.
	 * 
	 * @param circle Circle that is being intercepted
	 * @return
	 */
	public Set<Point> findIntersectsUsingQuad(Circle circle){
		Set<Point> rSet = new HashSet<Point>();
		
		double p = circle.getCenter().getX();
		double q = circle.getCenter().getY();
		double r = circle.getRadius();
		
		if (this.isVertical()){
			double yBase = Math.sqrt(Math.pow(r, 2) - Math.pow(this.verticalX - p, 2));
			double y1 = -yBase + q;
			double y2 = yBase + q;
			rSet.add(new Point(this.verticalX, y1));
			rSet.add(new Point(this.verticalX, y2));
		} else { 
			double c = this.intercept;
			double m = this.slope;
			
			double qA = Math.pow(m, 2) + 1;
			double qB = 2 * (m*c - m*q - p);
			double qC = Math.pow(q, 2) - Math.pow(r,2) + Math.pow(p, 2) - 2*c*q + Math.pow(c, 2);
		
			double discriminant = Math.pow(qB,2) - 4*qA*qC;
			
			if (discriminant == 0){
				
				double xIntercept = -qB / (2 * qA);
				double yIntercept = slope * xIntercept + qC;
				rSet.add(new Point(xIntercept,yIntercept));
				
			} else if (discriminant > 0){
				
				double xIntercept = (-qB + Math.sqrt(discriminant)) / (2 * qA);
				double yIntercept = slope * xIntercept + c;
				rSet.add(new Point(xIntercept,yIntercept));
				
				xIntercept = (-qB - Math.sqrt(discriminant)) / (2 * qA);
				yIntercept = slope * xIntercept + c;
				rSet.add(new Point(xIntercept,yIntercept));
			}
				
		}
		return rSet;
	}

	@Override
	public String toString() {
		return "Line [slope=" + slope + ", intercept=" + intercept + ", verticalX=" + verticalX + "]";
	}
	
	
	
}
