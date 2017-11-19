package com.underplex.tool.test;

import java.util.HashMap;
import java.util.Map;

import com.underplex.tool.stat.Normalizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest3 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest3( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest3.class );
    }
    
    public void test(){
    	// test of normalizer 
    	
    	Map<String,Double> scores = new HashMap<>();
    	
    	Map<String,Double> failed;
    	
    	failed = Normalizer.normalize(null);
    	assertTrue(failed.isEmpty());
    	
    	failed = Normalizer.normalize(scores);
    	assertTrue(failed.isEmpty());
    	
    	scores.put("Brandon",96.0);
    	failed = Normalizer.normalize(scores);
    	assertTrue(failed.isEmpty());
    	
    	scores.put("Rachel",97.0);
    	Map<String,Double> normed;

    	normed = Normalizer.normalize(scores);
    	assertTrue(normed.size()==2);
    	assertTrue(normed.get("Brandon")==0.0);
    	assertTrue(normed.get("Rachel")==1.0);

    	scores.put("Gerard",98.0);
    	normed = Normalizer.normalize(scores);
    	assertTrue(normed.size()==3);
    	assertTrue(normed.get("Brandon")==0.0);
    	assertTrue(normed.get("Rachel")==0.5);
    	assertTrue(normed.get("Gerard")==1.0);
    	assertTrue(normed.get("Trump")==null);
    	
	}	
}
