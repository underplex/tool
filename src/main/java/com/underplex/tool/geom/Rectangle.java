package com.underplex.tool.geom;

/**
 * Represents a rectangle in Cartesian plane with double precision.
 * 
 * The precision of the double numbers are not well defined here, so this might have strange behavior depending on how points are defined and their precision. *
 */
public class Rectangle {
	
	private final double left;
	private final double right;
	private final double below;
	private final double above;
	private final double height;
	private final double width;
	
	public Rectangle(Point p1, Point p2){
		if (p1.getX() < p2.getX()){
			this.left = p1.getX();
			this.right = p2.getX();
		} else {
			this.left = p2.getX();
			this.right = p1.getX();
		}
		if (p1.getY() < p2.getY()){
			this.below = p1.getY();
			this.above = p2.getY();
		} else {
			this.below = p2.getY();
			this.above = p1.getY();
		}
		
		this.height = this.above - this.below;
		this.width = this.right - this.left;
	}

	/**
	 * Returns x coord of left boundary for this rectangle.
	 * @return
	 */
	public double getLeft() {
		return left;
	}
	
	/**
	 * Returns x coord of right boundary for this rectangle. 
	 */	
	public double getRight() {
		return right;
	}
	
	/**
	 * Returns y coord of lower boundary for this rectangle. 
	 */	
	public double getBelow() {
		return below;
	}

	/**
	 * Returns y coord of upper boundary for this rectangle. 
	 */	
	public double getAbove() {
		return above;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
	
	/**
	 * Returns true if this contains point p1, false otherwise.
	 * 
	 * This might have strange behavior at the boundaries, given that the precision of Point isn't well defined.
	 * @param p1
	 * @return
	 */
	public boolean contains(Point p1){
		if (p1.getX() >= this.left && p1.getY() <= this.right){
			if (p1.getY() >= this.below && p1.getY() <= this.above){
				return true;
			}
		}
		return false;
	}
}
