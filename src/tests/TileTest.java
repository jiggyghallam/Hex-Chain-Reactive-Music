package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import reactogon.Node;
import reactogon.Tile;

public class TileTest {

    private Tile t;
	private Node n= new Node(1,2);
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGet() {
		t= new Tile("type",0,n,3);
		assertEquals("type",t.getType());//testGetType
		
		assertEquals(3,t.getDirection());//testGetDirection
		
	}


	@Test
	public void testIncrementDirection() {
		t= new Tile("type",0,n,4);
		t.incrementDirection();
		assertEquals(5,t.getDirection());


	}
	@Test
	public void testSet() {
		t = new Tile("type");
		t.setType("tp");
		assertEquals("tp",t.getType());//testSetType
		
		t.setDirection(2);
		assertEquals(2,t.getDirection());//testSetDirection
		t.setID(5);
		assertEquals(5,t.getID());
		t.setNode(n);
		assertEquals(n,t.getNode());		
		
	}

}
