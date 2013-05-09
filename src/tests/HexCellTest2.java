package tests;

import static org.junit.Assert.*;

import hex.HexCell;
import hex.ImageLoader;
import hex.Tile;

import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HexCellTest2 {
	
	private ImageLoader il = new ImageLoader();
	private BufferedImage image; 
	private HexCell hc;
	private Tile tile; 
	private boolean state = false;
	private boolean hasTile=false; 
	private boolean playNote=false; 
	private int note=0;
	private int[] array = {17, 52, 70, 52, 17, 0};
	private int[] array2 = {0, 0, 30, 60, 60, 30};
    
    
	@Before
	public void setUp() throws Exception {
    hc = new HexCell(il,0,0, 0);
	}

	@After
	public void tearDown() throws Exception {
	
	}
	@Test
	public void testObject() {
		assertNotNull(new ImageLoader());
	}
	
	@Test
    public void testGetMethod() { 
            assertEquals(il.getImage(tile, state), hc.getImage()); 
	        assertEquals(false, hc.getState());      
	        assertEquals(tile, hc.getTile());
	        assertEquals(false, hc.getPlayNote());  
	        assertEquals(0, hc.getNote());  
	        assertEquals(false, hc.getHasTile());
			assertEquals(0,hc.getCornerX());
			assertEquals(0,hc.getCornerY());
			assertArrayEquals(array,hc.getCornersX());			
			assertArrayEquals(array2,hc.getCornersY());

    }      

	@Test
    public void testSetMethod() {         
		hc.setImage(tile,state); 
        hc.setState(state);
        hc.setTile(tile);
        hc.setPlayNote(playNote);
        hc.setNote(note);
        hc.setHasTile(hasTile);
         
        assertEquals(il.getImage(tile, state), hc.getImage());      
        assertEquals(false, hc.getState());      
        assertEquals(tile, hc.getTile());
        assertEquals(false, hc.getPlayNote());  
        assertEquals(0, hc.getNote());  
        assertEquals(false, hc.getHasTile());  
    }  

	@Test
	public void testRefreshImage() {
		hc.refreshImage();
		assertNotNull(il.getImage(tile, state));
	}	
	
	@Test
	public void testFlipState() {
         hc.flipState();
         assertEquals(true,hc.getState());
	}






}
