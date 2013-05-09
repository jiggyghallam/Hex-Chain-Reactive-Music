package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import reactogon.Node;

public class NodeTest {
	private int i=1;
	private int j=2;
	private Node node;

	@Before
	public void setUp() throws Exception {
		node=new Node(i,j);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetI() {
        assertEquals(1, node.getI());      
	}

	@Test
	public void testGetJ() {
        assertEquals(2, node.getJ());      
	}

}
