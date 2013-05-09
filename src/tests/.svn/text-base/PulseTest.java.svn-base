package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import reactogon.Node;
import reactogon.Pulse;
import reactogon.SoundSequencer;

public class PulseTest {
	private Node n=new Node(1,2);
	private Node m=new Node(3,4);

	private int direction=0; 			
	private SoundSequencer soundSequencer;
	
	private Pulse p;
	private Pulse p2;

	@Before
	public void setUp() throws Exception {
		p= new Pulse(n,soundSequencer,direction);
		p2=new Pulse(m,n,soundSequencer,direction);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDirection() {
		assertEquals(0,p.getDirection());
	}

	@Test
	public void testSetDirection() {
		p.setDirection(direction);
		assertEquals(0,p.getDirection());
	}


	@Test
	public void testGetCurrent() {
		assertEquals(n,p.getCurrent());
		assertEquals(m,p2.getCurrent());
	}

	@Test
	public void testSetCurrent() {
		p.setNext(m);
		p.setCurrent();
		assertEquals(m,p.getCurrent());
	}

	@Test
	public void testGetPrevious() {
		assertEquals(n,p2.getPrevious());
		assertEquals(null,p.getPrevious());
		
	}

	@Test
	public void testSetPrevious() {
		p2.setPrevious();
		assertEquals(m,p2.getPrevious());
	}
}
