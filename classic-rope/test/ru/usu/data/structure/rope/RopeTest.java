package ru.usu.data.structure.rope;

import junit.framework.Assert;

import org.junit.Test;


/**
 * @author astarovoyt
 *
 */
public class RopeTest 
{
	@Test
	public void ropeSimpleToStringTest()
	{
		String expected = "abc";
		Rope rope = new RopeBuilder().build(expected);
		
		Assert.assertEquals(expected, rope.toString());
	}
}
