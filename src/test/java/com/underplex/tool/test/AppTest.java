package com.underplex.tool.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.underplex.tool.Combiner;
import com.underplex.tool.Finder;
import com.underplex.tool.Measure;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
	public void test() {
		// this is a new test comment
		Set<String> departs = new HashSet<>();
		departs.add( "new york" );
		departs.add( "boston");
		departs.add( "los angeles");
		departs.add( "phoenix");
		departs.add( "MINNEAPOLIS");
		departs.add( "detROIT" );

		Set<String> arrives = new HashSet<>();
		arrives.add( "atlanta" );
		arrives.add( "san francisco");
		arrives.add( "kanSAS city");
		arrives.add( "housTON");
		arrives.add( "baltimore");
		arrives.add( "DENver" );
		
		Set<String> routes = new HashSet<>();
		for ( String city : departs )
			for (String city2: arrives )
				routes.add( city + " to " + city2);

		Assert.assertEquals(36, routes.size() );
		
		String mnToAtlanta = Finder.find(routes, "atlanta", "minneapolis");
		String nyToSF = Finder.find(routes, "new", "cISco");
		String nyToDenver = Finder.find(routes, "denver", "YORK");
		String bosToKC = Finder.find(routes, "b","o","s","t","o","n","c","i","t","y");
		String notRoute1 = Finder.find(routes, "atlanta", "san fran");
		String notRoute2 = Finder.find(routes, "denver", "YORK", "balti");
		routes.clear();
		String notRoute3 = Finder.find(routes, "new york", "baltimore");

		Assert.assertEquals("MINNEAPOLIS to atlanta", mnToAtlanta );
		Assert.assertEquals("new york to san francisco", nyToSF );
		Assert.assertEquals("new york to DENver", nyToDenver );
		Assert.assertEquals("boston to kanSAS city", bosToKC );
		Assert.assertEquals(null, notRoute1 );
		Assert.assertEquals(null, notRoute2 );
		Assert.assertEquals(null, notRoute3 );
		
	}


	public void test1(){
		
		System.out.println("*************************************************************");
		System.out.println("Test 1: Regular n choose k tests");
		System.out.println("*************************************************************");
		
				
		Set<Character> chars = new HashSet<Character>();
		chars.add('A');
		chars.add('B');
		chars.add('X');
		chars.add('Y');
		chars.add('Z');

		Set<Set<Character>> outSets;
				
		outSets = Combiner.chooseK(chars, 0);
		Assert.assertEquals(1, outSets.size());

		outSets = Combiner.chooseK(chars, 1);
		Assert.assertEquals(5, outSets.size());

		outSets = Combiner.chooseK(chars, 2);
		Assert.assertEquals(10, outSets.size());

		outSets = Combiner.chooseK(chars, 3);
		Assert.assertEquals(10, outSets.size());

		outSets = Combiner.chooseK(chars, 4);
		Assert.assertEquals(5, outSets.size());

		outSets = Combiner.chooseK(chars, 5);
		Assert.assertEquals(1, outSets.size());

		outSets = Combiner.chooseK(chars, 6);
		Assert.assertEquals(1, outSets.size()); // there's an empty set returned

//		For debugging...		
	
		Combiner.printSets(Combiner.chooseK(chars, 0));
		Combiner.printSets(Combiner.chooseK(chars, 1));
		Combiner.printSets(Combiner.chooseK(chars, 2));
		Combiner.printSets(Combiner.chooseK(chars, 3));
		Combiner.printSets(Combiner.chooseK(chars, 4));
		Combiner.printSets(Combiner.chooseK(chars, 5));
		Combiner.printSets(Combiner.chooseK(chars, 6));
	}
	
	
	public void test2(){

		System.out.println("*************************************************************");
		System.out.println("Test 2: Conditions for n choose k where a single empty set is returned");
		System.out.println("*************************************************************");
		Set<Set<Character>> outSets;
		
		Set<Character> chars = null;
		
		outSets = Combiner.chooseK(chars, 5);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);

		chars = new HashSet<Character>();
		outSets = Combiner.chooseK(chars, 3);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);
		
		chars = new HashSet<Character>();
		chars.add('A');
		chars.add('B');
		chars.add('X');
		chars.add('Y');
		chars.add('Z');
		
		outSets = Combiner.chooseK(chars, 99);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);		

		outSets = Combiner.chooseK(chars, 0);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);
		
		outSets = Combiner.chooseK(chars, -9);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);
	}
	
	
	public void test3(){

		System.out.println("*************************************************************");
		System.out.println("Test 3: Conditions for choosing each where a single empty set is returned");
		System.out.println("*************************************************************");
		
		Set<Set<Character>> outSets;
		
		Set<Set<Character>> among = null;
		
		outSets = Combiner.chooseOneFromEach(among);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);

		among = new HashSet<Set<Character>>();;
		
		outSets = Combiner.chooseOneFromEach(among);
		Assert.assertEquals(1, outSets.size());
		Combiner.printSets(outSets);

		
	}
	
	
	public void test4(){

		System.out.println("*************************************************************");
		System.out.println("Test 4: Conditions for choosing each for regular cases");
		System.out.println("*************************************************************");
		
		
		Set<Set<Character>> among = new HashSet<Set<Character>>();;;
		
		Set<Character> chars1 = new HashSet<Character>();
		chars1.add('A');
		chars1.add('B');
		chars1.add('C');

		Set<Character> chars2 = new HashSet<Character>();
		chars2.add('M');
		chars2.add('N');
		chars2.add('O');

		Set<Character> chars3 = new HashSet<Character>();
		chars3.add('X');
		chars3.add('Y');
		chars3.add('Z');

		among.add(chars1);
		among.add(chars2);
		among.add(chars3);
		
		Set<Set<Character>> outSets;

		outSets = Combiner.chooseOneFromEach(among);
		Combiner.printSets(outSets);
		Assert.assertEquals(27, outSets.size());

		chars2.clear(); // this should mean we effectively ignore the second set
		outSets = Combiner.chooseOneFromEach(among);
		Combiner.printSets(outSets);
		Assert.assertEquals(9, outSets.size());		

		chars2.clear(); // this should mean we effectively ignore the second set
		chars3.clear(); // this should mean we effectively ignore the 3rd set
		outSets = Combiner.chooseOneFromEach(among);
		Combiner.printSets(outSets);
		Assert.assertEquals(3, outSets.size());
		
		chars1.clear(); // this should mean we effectively ignore the first set
		chars2.clear(); // this should mean we effectively ignore the second set
		chars3.clear(); // this should mean we effectively ignore the 3rd set
		outSets = Combiner.chooseOneFromEach(among);
		Combiner.printSets(outSets);
		Assert.assertEquals(1, outSets.size()); // we still get an empty set
	}
	
	
	public void test5(){
		
		System.out.println("*************************************************************");
		System.out.println("Test 5: Using within method");
		System.out.println("*************************************************************");
		
		Measure.Point p1; 
		Measure.Point p2;
		Measure.Point p3;
		int distance;
		
		p1 = new Measure.Point(0,0);
		p2 = new Measure.Point(1000,1000);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));

		System.out.println();

		p1 = new Measure.Point(0,1000);
		p2 = new Measure.Point(1000,0);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		System.out.println();
		
		p1 = new Measure.Point(1000,1000);
		p2 = new Measure.Point(0,0);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		System.out.println();
		
		p1 = new Measure.Point(1000,0);
		p2 = new Measure.Point(0,1000);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		p1 = new Measure.Point(0,1000);
		p2 = new Measure.Point(0,1000);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		System.out.println();

		p1 = new Measure.Point(1000,0);
		p2 = new Measure.Point(1000,0);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		System.out.println();
		


		p1 = new Measure.Point(1000,1000);
		p2 = new Measure.Point(1000,950);
		distance = 100;
		
		System.out.println();
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		p1 = new Measure.Point(1000,1000);
		p2 = new Measure.Point(1000,4000);
		distance = 100;
		
		System.out.println();
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		p1 = new Measure.Point(1000,1000);
		p2 = new Measure.Point(4000,1000);
		distance = 100;
		
		System.out.println();
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		p1 = new Measure.Point(1000,4000);
		p2 = new Measure.Point(1000,1000);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
		
		p1 = new Measure.Point(4000,1000);
		p2 = new Measure.Point(1000,1000);
		distance = 100;
		
		p3 = Measure.within(p1, p2, distance);
		System.out.println("Closest point to " + p1.toString() + " within " + distance + " of " + p2.toString() + ": " + p3.toString());
		System.out.println("Calculating distance from p2 to p3: " + p2.distance(p3));
	}
}
