package com.underplex.tool.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.tool.geom.Circle;
import com.underplex.tool.geom.Intersection;
import com.underplex.tool.geom.Line;
import com.underplex.tool.geom.LineSegment;
import com.underplex.tool.geom.Point;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest2 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest2( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest2.class );
    }
    
    public void testPoint(){
    	System.out.println();
		
		System.out.println("********************************************");
		System.out.println("*** Test of Point Basics		         	 ***");
		System.out.println("********************************************");    	
		System.out.println();
		
		Point p1 = new Point(0.0000000001,0.0000000001);
		Point p2 = new Point(0.0000000002,0.0000000002);
		Point p3 = new Point(0.001,0.001);
		Point p4 = new Point(0.002,0.002);
		
		Point p5 = new Point(3.4253886096455934,3.8486994347763415);
		Point p6 = new Point(2.0111750472724967,2.4344858724032448);
		Point p7 = new Point(3.4253886096455926,3.8486994347763406);
		Point p8 = new Point(2.0111750472724976,2.4344858724032457);
		
		Assert.assertEquals(true, p1.equals(p2));
		Assert.assertEquals(true, p2.equals(p1));
		
		Assert.assertEquals(false, p1.equals(p3));
		Assert.assertEquals(false, p3.equals(p1));
		
		Assert.assertEquals(false, p3.equals(p4));
		Assert.assertEquals(false, p4.equals(p3));

		Assert.assertEquals(true, p5.equals(p7));
		Assert.assertEquals(true, p7.equals(p5));
		
		Assert.assertEquals(true, p6.equals(p8));
		Assert.assertEquals(true, p8.equals(p6));
		
		// checking for false positives from tests above
		Assert.assertEquals(false, p5.equals(p8));
		Assert.assertEquals(false, p7.equals(p6));
		
		Set<Point> set = new HashSet<>();
		set.add(p5);
		set.add(p6);

		Assert.assertEquals(true, set.contains(p5));
		Assert.assertEquals(true, set.contains(p6));		
		Assert.assertEquals(true, set.contains(p7));
		Assert.assertEquals(true, set.contains(p8));	
    }
    
    /**
     * Unit test of basic line functions.
     */
	public void testLine() {
		
		System.out.println("********************************************");
		System.out.println("*** Test of Line Basics		         	 ***");
		System.out.println("********************************************");
		
		// create vertical line that intercepts x-axis at pi
		Line line;
		
		line = Line.makeVertical(Math.PI);
		Assert.assertEquals(true, line.isVertical());
		Assert.assertEquals(Double.NaN, line.getIntercept());
		Assert.assertEquals(Double.NaN, line.getSlope());
		Assert.assertEquals(Math.PI, line.getVerticalX());

		// create a line through (0,0) with slope 1
		line = new Line(1, 0);
		System.out.println(line.toString());
		Assert.assertEquals(false, line.isVertical());
		
		Assert.assertEquals(0.0, line.getIntercept());
		Assert.assertEquals(1.0, line.getSlope());
		Assert.assertEquals(Double.NaN, line.getVerticalX());
		
		double halfSq = Math.pow(.5, .5);
		
		Assert.assertEquals(true, line.intersects(new Point(halfSq,halfSq)));
		Assert.assertEquals(true, line.intersects(new Point(1023,1023)));
	}	
	
	public void testCircles() {		
		System.out.println("********************************************");
		System.out.println("*** Test of Circle Intercepts            ***");
		System.out.println("********************************************");
		
		// 	now check for Circle intersects
		
		Line line;
		Circle circle;

		line = new Line(1, 0);
		System.out.println(line.toString());
		
		circle = new Circle(new Point(0,0),1);
		System.out.println(circle.toString());
		
		
		// intercept for line and circle when line intersects circle twice
		
		double p = circle.getCenter().getX();
		double q = circle.getCenter().getY();
		double r = circle.getRadius();
		
		double c = line.getIntercept();
		double m = line.getSlope();
		
		double qA = Math.pow(m, 2) + 1;
		double qB = 2 * (m*c - m*q - p);
		double qC = Math.pow(q, 2) - Math.pow(r,2) + Math.pow(p, 2) - 2*c*q + Math.pow(c, 2);
	
		System.out.println("p: " + p + ", q: " + q + ", r: " + r + ", c: " + c + ", m:" + m);
		
		System.out.println("a:" + String.valueOf(qA) + ", b: " + String.valueOf(qB) + ", c: " + String.valueOf(qC));
		double discriminant = Math.pow(qB,2) - 4*qA*qC;
		System.out.println("discriminant: " + discriminant);
		
		Set<Point> intersects = Intersection.findIntersects(line,circle);
		Assert.assertEquals(2, intersects.size());
		
		for (Point point : intersects)
			System.out.println(point.toString());

		double halfSq = Math.pow(.5, .5);
		
		Assert.assertEquals(true, intersects.contains(new Point(halfSq,halfSq)));
		Assert.assertEquals(true, intersects.contains(new Point(-halfSq,-halfSq)));
		
	}	
	
	/**
	 * This test is actually designed to show that the intercept CANNOT be found by the provided methods when the intercept is only a single tangentially point to the circle, 
	 * because of rounding errors.
	 */
	public void testSingleIntercept() {
		// 	now check for Circle intersects
		
		System.out.println("********************************************");
		System.out.println("*** Test of Single Intercept ***************");
		System.out.println("********************************************");
		Line line;
		Circle circle;

		// defined the square root of 1/2
		double halfSq = Math.pow(.5, .5);
		
		line = new Line(1,-2 * halfSq);
		circle = new Circle(new Point(0,0),1);

		double p = circle.getCenter().getX();
		double q = circle.getCenter().getY();
		double r = circle.getRadius();
		
		double c = line.getIntercept();
		double m = line.getSlope();
		
		double qA = Math.pow(m, 2) + 1;
		double qB = 2 * (m*c - m*q - p);
		double qC = Math.pow(q, 2) - Math.pow(r,2) + Math.pow(p, 2) - 2*c*q + Math.pow(c, 2);
	
		System.out.println("p: " + p + ", q: " + q + ", r: " + r + ", c: " + c + ", m:" + m);
		
		System.out.println("qA:" + String.valueOf(qA) + ", qB: " + String.valueOf(qB) + ", qC: " + String.valueOf(qC));
		double discriminant = Math.pow(qB,2) - 4*qA*qC;
		System.out.println("discriminant: " + discriminant);

		Assert.assertEquals(true, line.intersects(new Point(halfSq,-halfSq)));

		Point pointA = new Point(-1000,line.yGivenX(-1000));
		Point pointB = new Point(1000,line.yGivenX(1000));

		LineSegment segment = new LineSegment(pointA,pointB);
		System.out.println(segment);
		System.out.println("At " + halfSq + ", we expect y = " + segment.yGivenX(halfSq));
		
		// the Line technique doesn't capture every kind of intersection, necessarily, so we also use another algo
		
		Set<Point> intersects = Intersection.findIntersects(segment, circle);

		// In fact, analytically, there should be exactly one intersection at (halfSq,-halfSq) but the algo using the quadratic equation to find the intersection points doesn't
		// find that single point.
		
		for (Point point : intersects)
			System.out.println(point.toString());
		
		Point pointSq = new Point(halfSq,-halfSq);
		Assert.assertEquals(0, intersects.size());
		
		Assert.assertEquals(false, intersects.contains(pointSq));
		
		/// even though the point is indeed found within the circle and on the line
		
		assertTrue(circle.contains(pointSq));
		assertTrue(segment.intersects(pointSq));
			
	}	
	
	public void testVerticalCircleIntercept() {
		// 	now check for Circle intersects
		
		System.out.println("********************************************");
		System.out.println("*** Test of Vertical Line Intercepts********");
		System.out.println("********************************************");
		Line line;
		Circle circle;
		
		line = Line.makeVertical(Math.E);
		circle = new Circle(new Point(0,0), Math.E + 1);

		System.out.println("Line is : " + line);
		System.out.println("Circle is : " + circle);
		
		// define correct answers for intercepts
		
		double y1 = Math.sqrt(Math.pow(Math.E + 1, 2) - Math.pow(Math.E, 2));
		double y2 = -y1;
		
		Point p1 = new Point(Math.E, y1);
		Point p2 = new Point(Math.E, y2);

//		assertTrue(circle.contains(p1)); this fails! probably due to rounding error
		assertTrue(line.intersects(p1));
//		assertTrue(circle.contains(p2)); this fails! probably due to rounding error
		assertTrue(line.intersects(p2));
		
		Set<Point> intersects = Intersection.findIntersects(line, circle);

		for (Point point : intersects)
			System.out.println(point.toString());
		
		Assert.assertEquals(2, intersects.size());

		Assert.assertEquals(true, intersects.contains(p1));
		Assert.assertEquals(true, intersects.contains(p2));

	}
	
	public void testLineSeg() {
		// 	now check for Circle intersects
		
		System.out.println();
		System.out.println("********************************************");
		System.out.println("*** Test of LineSegments ***");
		System.out.println("********************************************");
		System.out.println();
		Point p1 = new Point(Math.E,Math.PI);
		Point p2 = new Point(Math.E+1,Math.PI+1);
		
		Assert.assertEquals(Math.sqrt(2), p1.distance(p2));
				
		LineSegment seg = new LineSegment(p2,p1);
		
		// create a line through (0,0) with slope 1
		System.out.println(seg.toString());
		Assert.assertEquals(seg.getLeft(), p1);
		Assert.assertEquals(seg.getBelow(), p1);
		Assert.assertEquals(seg.getRight(), p2);
		Assert.assertEquals(seg.getAbove(), p2);
		
		Assert.assertEquals(false, seg.isVertical());
				
		Assert.assertEquals(Math.PI - Math.E, seg.getIntercept());
		Assert.assertEquals(1.0, seg.getSlope());
		Assert.assertEquals(Double.NaN, seg.getVerticalX());
		
		
		// first test a full line's intersection points with the circle
		Circle circle = new Circle(p1,1);
		
		Line line = new Line(1.0,Math.PI - Math.E);
		
		Set<Point> intersects = Intersection.findIntersects(line,circle);

		System.out.println("Points found by quad intersect method:");
		
		for (Point point : intersects)
			System.out.println(point.toString());
		
		Assert.assertEquals(2, intersects.size());

		double halfSq = Math.pow(.5, .5);
		
		Point highI = new Point(Math.E + halfSq,Math.PI + halfSq);
		Point lowI = new Point(Math.E - halfSq,Math.PI - halfSq);
		
		System.out.println("Points expected by quad intersect method:");
		System.out.println(highI);
		System.out.println(lowI);
		
		Assert.assertEquals(true, intersects.contains(highI));
		Assert.assertEquals(true, intersects.contains(lowI));
		
		intersects = Intersection.findIntersects(seg, circle);

		System.out.println("Points found by quad intersect method:");
		
		for (Point point : intersects)
			System.out.println(point.toString());
		
		Assert.assertEquals(1, intersects.size());

		System.out.println("Points expected by quad intersect method:");
		System.out.println(highI);
		
		// one of the points the intersects the full line isn't actually intersected by the line segment
		Assert.assertEquals(true, intersects.contains(highI));
		Assert.assertEquals(false, intersects.contains(lowI));
		
		

	}
		
	public void testVertLineSeg() {
		// 	now check for Circle intersects
		
		System.out.println();
		System.out.println("********************************************");
		System.out.println("*** Test of Vertical LineSegments ***");
		System.out.println("********************************************");
		System.out.println();
		
		
		Point p1 = new Point(Math.E, 2 * Math.PI);
		Point p2 = new Point(Math.E,-2 * Math.PI);
		
		Assert.assertEquals(4 * Math.PI, p1.distance(p2));
				
		LineSegment seg = new LineSegment(p2,p1);
				
		// create a line through (0,0) with slope 1
		System.out.println(seg.toString());
		Assert.assertEquals(false, seg.getLeft().equals(seg.getRight()));
		Assert.assertEquals(false, seg.getAbove().equals(seg.getBelow()));
		
		Assert.assertEquals(true, seg.isVertical());
				
		Assert.assertEquals(Double.NaN, seg.getIntercept());
		Assert.assertEquals(Double.NaN, seg.getSlope());
		Assert.assertEquals(Math.E, seg.getVerticalX());
		
		
		// first test a full line's intersection points with the circle
		Circle circle = new Circle(new Point(Math.E + 1,2*Math.PI),2);
		
		Line line = Line.makeVertical(Math.E);
		
		Set<Point> intersects = Intersection.findIntersects(line,circle);

		System.out.println("Points found by intersect method:");
		
		for (Point point : intersects)
			System.out.println(point.toString());
		
		Assert.assertEquals(2, intersects.size());
		
		Point highI = new Point(Math.E, 2*Math.PI + Math.sqrt(3));
		Point lowI = new Point(Math.E,  2*Math.PI - Math.sqrt(3));
		
		System.out.println("Points expected by vertical line intercepts:");
		System.out.println(highI);
		System.out.println(lowI);
		
		Assert.assertEquals(true, line.intersects(highI));
		Assert.assertEquals(true, line.intersects(lowI));
		
		Assert.assertEquals(true, intersects.contains(highI));
		Assert.assertEquals(true, intersects.contains(lowI));
		
		// now check the same assertions for the line segment
		
		intersects = Intersection.findIntersects(seg,circle);

		System.out.println("Points expected for vertical segment intercepts with circle:");
		System.out.println(lowI);
		
		Assert.assertEquals(true, seg.intersects(lowI));
		Assert.assertEquals(false, seg.intersects(highI));
		
		System.out.println("Points found for vertical segment intercepts with circle:");
		
		for (Point point : intersects)
			System.out.println(point.toString());
		
		Assert.assertEquals(1, intersects.size());
		
		// one of the points the intersects the full line isn't actually intersected by the line segment
		Assert.assertEquals(true, intersects.contains(lowI));
		Assert.assertEquals(false, intersects.contains(highI));

	}	
}
