package com.underplex.tool.test;

import java.util.List;

import com.underplex.tool.ArrayRing;
import com.underplex.tool.Ring;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RingTest 
    extends TestCase
{
	public RingTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RingTest.class );
    }
    
    public void test(){
    	
    	Gamer gamer1a = new Gamer(1,"a");
    	Gamer gamer1b = new Gamer(1,"b");
    	Gamer gamer2a = new Gamer(2,"a");
    	Gamer gamer2b = new Gamer(2,"b");
    	Gamer gamer3a = new Gamer(3,"a");
    	Gamer gamer3b = new Gamer(3,"a");
    	
    	assertTrue(gamer1a.equals(gamer1b));
    	assertFalse(gamer1b.equals(gamer2b));
    	
    	Ring<Gamer> players = new ArrayRing<Gamer>();
    	assertTrue(players.isEmpty());
    	
    	players.addFirst(gamer1a);
    	players.addFirst(gamer2a);
    	
    	assertTrue(players.size() == 2);
    	assertTrue(players.contains(gamer1a));
    	assertTrue(players.getFirst().equals(gamer2a));
    	assertTrue(players.getLast().equals(gamer1a));
    	assertFalse(players.getLast().equals(gamer2a));
    	assertFalse(players.getFirst().equals(gamer1a));
    	
    	players.clear();
    	assertTrue(players.isEmpty());
    	assertTrue(players.size() == 0);
    	
    	players.addLast(gamer1b);
    	players.addLast(gamer2b);
       	assertTrue(players.getFirst().equals(gamer1b));
    	assertTrue(players.getLast().equals(gamer2b));
    	
    	players.addLast(gamer3b);
    	assertTrue(players.contains(gamer2b));
    	assertTrue(players.indexOf(gamer1b) == 0);    	
    	assertTrue(players.indexOf(gamer2b) == 1);    	
       	assertTrue(players.indexOf(gamer3b) == 2);    	
        
    	assertTrue(players.getLast().equals(gamer3b));
    	assertTrue(players.getFirst().equals(gamer1b));
    	
    	// now try to add a player that equals an existing player
    	
    	assertFalse(players.addLast(gamer1a));
    	assertTrue(players.size() == 3);
    	
    	// change first element
    	assertTrue(players.makeFirst(gamer2b));
    	assertFalse(players.makeFirst(gamer2b));
    	assertTrue(players.getFirst().equals(gamer2b));
    	
    	// relative positions of players didn't change, only the "first" designation
    	assertTrue(players.indexOf(gamer2b) == 0);    	
    	assertTrue(players.indexOf(gamer3b) == 1);    	
       	assertTrue(players.indexOf(gamer1b) == 2);     	
    	
       	assertTrue(players.next(gamer2b).equals(gamer3b));
       	assertFalse(players.next(gamer2b).equals(gamer1b));
       	
       	assertTrue(players.previous(gamer2b).equals(gamer1b));
       	assertFalse(players.previous(gamer2b).equals(gamer3b));
 
       	List<Gamer> myList = players.toList();
       	
       	assertTrue(myList.get(0).equals(players.getFirst()));
       	assertTrue(myList.get(0).equals(players.get(0)));
       	assertTrue(myList.get(1).equals(players.get(1)));
       	assertTrue(myList.get(2).equals(players.get(2)));
       	assertTrue(myList.get(2).equals(players.getLast()));
	}	
    
    
    public static class Gamer{
    	
    	private int id;
    	private String idExt;

		public Gamer(int id, String idExt) {
			super();
			this.id = id;
			this.idExt = idExt;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getIdExt() {
			return idExt;
		}

		public void setIdExt(String idExt) {
			this.idExt = idExt;
		}

		@Override
		public String toString() {
			return "Gamer [id=" + id + ", idExt=" + idExt + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			Gamer other = (Gamer) obj;
			if (id != other.id)
				return false;
			return true;
		}   	
    	
    }
}
