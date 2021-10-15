import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;



/**
 * @author fredrik
 *
 */
public class HashTest{
	
	Hash testhash = new Hash();
	PlayerItem one = new PlayerItem(new File("/home/fredrik/FRAKMusic/Music/Disfigure-Blank.wav"));
	PlayerItem two = new PlayerItem(new File("/home/fredrik/FRAKMusic/Music/Deafkev-Invincible.wav"));
	@Test
	public void SetHash() throws Exception {
		
	}

	@Test
	public void testSize() throws Exception {
		testhash.add(one);
		testhash.add(one);
		testhash.add(two);
		 assertEquals("printqueue removes abcd", 2 , testhash.size());
	}

	@Test
	public void testFindMatch() throws Exception {
		testhash.add(one);
		testhash.add(one);
		testhash.add(two);
		assertEquals("find the name",one.getName(),testhash.findMatch(one.getName()).getName());
		
	}

	@Test
	public void testAdd() throws Exception {
		testhash.add(null);
		assertEquals("adding null",0,testhash.size());
		
		testhash.add(one);
		testhash.add(one);
		assertEquals("adding 2 of the same",1,testhash.size());
		testhash.add(one);
		testhash.add(two);
		assertEquals("adding 2 ",2,testhash.size());
	}

	@Test
	public void testRemove() throws Exception {
		testhash.add(one);
		testhash.add(two);
		testhash.remove(one.getName());
		assertEquals("remove one value",null ,testhash.findMatch(one.getName()));
		assertEquals("removed one value less one", 1, testhash.size());
		
		testhash.add(one);
		testhash.add(two);
		testhash.remove(one.getName());
		testhash.remove(two.getName());
		assertEquals("remove one value",null ,testhash.findMatch(one.getName()));
		assertEquals("remove one value",null ,testhash.findMatch(two.getName()));
		assertEquals("removed one value less one", 0, testhash.size());
	}

}

