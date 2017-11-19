package com.underplex.tool.geom;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents segment of a line in Cartesian space with double precision.
 * 
 * Generally, an instance of this, if defined with two points with different Cartesian coordinates, will return different points for its left/rigth operations and below/above operations.
 * The only case where it would not would be if the points used to construct it are in fact the same Cartesian coordinates.
 *  
 * @author Brandon Irvine
 *
 */
public class LineSegment extends Line{

	private final Point left;
	private final Point right;
	private final Point above;
	private final Point below;
	private final double length;
	
	public LineSegment(Point p1, Point p2) {
		super(p1,p2);
		this.length = p1.distance(p2);
		if (p1.getX() < p2.getX()){
			this.left = p1;
			this.right = p2;
		} else {
			this.left = p2;
			this.right = p1;
		}
		if (p1.getY() < p2.getY()){
			this.below = p1;
			this.above = p2;
		} else {
			this.below = p2;
			this.above = p1;
		}
		
	}

	/**
	 * Returns length of this line segment.
	 * @return
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Returns leftmost end point of this line segment, or either point if both have same X value.
	 * 
	 * The left and right never return the same point unless the same point was used to define the line segment.
	 * @return
	 */
	public Point getLeft() {
		return left;
	}

	/**
	 * Returns rightmost end point of this line segment, or either point if both have same X value.
	 * 
	 * The left and right never return the same point unless the same point was used to define the line segment.
	 * @return
	 */
	public Point getRight() {
		return right;
	}

	/**
	 * Returns higher end point of this line segment, or either point if both have same Y value.
	 * 
	 * The above and below never return the same point unless the same point was used to define the line segment.
	 * @return
	 */
	public Point getAbove() {
		return above;
	}

	/**
	 * Returns lower end point of this line segment, or either point if both have same Y value.
	 * The above and below never return the same point unless the same point was used to define the line segment.
     *
	 * @return
	 */
	public Point getBelow() {
		return below;
	}

	@Override
	public boolean intersects(Point point) {
		if (super.intersects(point)){
			if (point.getX() >= left.getX() && point.getX() <= right.getX())
				if (point.getY() >= below.getY() && point.getY() <= above.getY())
					return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "LineSegment [inter=" + this.intercept + ", slope=" + slope + ", left=" + left + ", right=" + right + "]";
	}

	/**
	 * Returns the infinite line that this segment is a part of, as a new immutable instance of Line.
	 * @return
	 */
	public Line makeLine(){
		Line line = null;
		if (this.isVertical()){
			line = Line.makeVertical(this.getVerticalX());
		} else {
			line = new Line(this.getSlope(),this.getIntercept());
		}
		return line;
	}
}
