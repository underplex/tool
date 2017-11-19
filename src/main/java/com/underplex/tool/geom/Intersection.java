package com.underplex.tool.geom;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Intersection {

	private Intersection(){
		// don't instantiate
	}
	
	/**
	 * Returns a Set representing any points believed to be intersecting the given Circle, or empty Set if none exist.
	 * 
	 * Since this method uses the double precision of the classes, it cannot guarantee that it will find single tangential intersection points because of rounding errors inherent to 
	 * the double precision standard.
	 * 
	 * @param circle Circle that is being intercepted
	 * @return
	 */
	public static Set<Point> findIntersects(Line line, Circle circle){
		Set<Point> rSet = new HashSet<Point>();
		
		double p = circle.getCenter().getX();
		double q = circle.getCenter().getY();
		double r = circle.getRadius();
		
		if (line.isVertical()){
			double yBase = Math.sqrt(Math.pow(r, 2) - Math.pow(line.getVerticalX() - p, 2));
			double y1 = -yBase + q;
			double y2 = yBase + q;
			rSet.add(new Point(line.getVerticalX(), y1));
			rSet.add(new Point(line.getVerticalX(), y2));
		} else { 
			double c = line.getIntercept();
			double m = line.getSlope();
			
			double qA = Math.pow(m, 2) + 1;
			double qB = 2 * (m*c - m*q - p);
			double qC = Math.pow(q, 2) - Math.pow(r,2) + Math.pow(p, 2) - 2*c*q + Math.pow(c, 2);
		
			double discriminant = Math.pow(qB,2) - 4*qA*qC;
			
			if (discriminant == 0){
				
				double xIntercept = -qB / (2 * qA);
				double yIntercept = line.getSlope() * xIntercept + qC;
				rSet.add(new Point(xIntercept,yIntercept));
				
			} else if (discriminant > 0){
				
				double xIntercept = (-qB + Math.sqrt(discriminant)) / (2 * qA);
				double yIntercept = line.getSlope() * xIntercept + c;
				rSet.add(new Point(xIntercept,yIntercept));
				
				xIntercept = (-qB - Math.sqrt(discriminant)) / (2 * qA);
				yIntercept = line.getSlope() * xIntercept + c;
				rSet.add(new Point(xIntercept,yIntercept));
			}
				
		}
		return rSet;
	}

	public static Set<Point> findIntersects(LineSegment segment, Circle circle) {
		Set<Point> all = Intersection.findIntersects(segment.makeLine(),circle);
		Set<Point> rSet = new HashSet<>();
		for (Point p : all)
			if (segment.intersects(p))
				rSet.add(p);
		
		return rSet;
	}
}
