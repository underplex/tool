package com.underplex.tool;


/**
 * Utility class for measurements and angles.
 * @author Brandon Irvine
 *
 */
public class Measure {

	/**
	 * Don't instantiate this class.
	 */
	private Measure(){
	}
	
	/**
	 * Returns point within distance of p2 that is closest to p1.
	 * 
	 * p1 and p2 may be any points on the Cartesian 2D plane. 
	 * While p1 and p2 must have integer x and y coordinates, distance do not, which creates some rounding errors here. You may need to adjust
	 * distance to get the desired results.
	 * @param p1
	 * @param p2
	 * @param distance
	 * @return
	 */
	public static Point within(Point p1, Point p2, double distance){

		Point rPoint = p1;
		
		if (p1.distance(p2) > distance){
		
			// need to check that we won't attempt division by zero...
			if (p1.getX() == p2.getX()){
				int ySign = -1;
				if (p1.getY() > p2.getY()){
					ySign = 1;
				}
				rPoint = new Point((int)(p2.getX()), (int)(p2.getY() + (ySign * distance)));
				
			} else {
				int xSign = 1;
				int ySign = 1;
				
				if (p1.getX() < p2.getX()){
					xSign = -1;
				}	
				if (p1.getY() < p2.getY()){
					ySign = -1;
				}
				
				double diffY = (double)(p1.getY() - p2.getY());
				double diffX = (double)(p1.getX() - p2.getX());
				
				double angle = Math.atan(diffY/diffX);
				
				double findX = xSign * Math.abs(distance * Math.cos(angle));
				double findY = ySign * Math.abs(distance * Math.sin(angle));
				 
				rPoint = new Point((int)(p2.getX() + findX), (int)(p2.getY() + findY));
			}
		}
		
		return rPoint;
		
	}

	public static class Point {

		private final int x;
		private final int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}
		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * Returns distance between this and point.
		 */
		public double distance(Point point) {
			double distX = (double) (this.getX() - point.getX());
			double distY = (double) (this.getY() - point.getY());
			return Math.sqrt((distX * distX) + (distY * distY));
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getX();
			result = prime * result + getY();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Point))
				return false;
			Point other = (Point) obj;
			if (getX() != other.getX())
				return false;
			if (getY() != other.getY())
				return false;
			return true;
		}

		@Override
		public String toString(){
			return Integer.toString(getX()) + " " + Integer.toString(getY());
		}

	}
}