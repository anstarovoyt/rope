package ru.usu.data.structure.rope;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.usu.data.structure.rope.RopeImpl.RopeNode;

/**
 * Tests for building rope
 * 
 * @author astarovoyt
 *
 */
public class RopeTest
{
    public static final String COMPLEX_ROPE_STRING_EXPECTED = "my new str temp";

    public static final String SIMPLE_ROPE_STRING_EXPECTED = "abc";

    @Test
    public void ropeSimpleToStringTest()
    {
        Rope rope = new RopeBuilder().build(SIMPLE_ROPE_STRING_EXPECTED);

        Assert.assertEquals(SIMPLE_ROPE_STRING_EXPECTED, rope.toString());
    }

    @Test
    public void ropeComplexToStringTest()
    {
        Rope rope = generateComplexRope();

        Assert.assertEquals(COMPLEX_ROPE_STRING_EXPECTED, rope.toString());
    }

    @Test
    public void ropeComplexToLengthTest()
    {
        Rope rope = generateComplexRope();

        Assert.assertEquals(COMPLEX_ROPE_STRING_EXPECTED.length(), rope.length());
    }

    @Test
    public void ropeSimpleLengthTest()
    {
        Rope rope = new RopeBuilder().build(SIMPLE_ROPE_STRING_EXPECTED);

        Assert.assertEquals(SIMPLE_ROPE_STRING_EXPECTED.length(), rope.length());
    }

    @Test
    public void ropeSimpleCharAtTest()
    {
        Rope rope = new RopeBuilder().build(SIMPLE_ROPE_STRING_EXPECTED);

        Assert.assertEquals(SIMPLE_ROPE_STRING_EXPECTED.charAt(1), rope.charAt(1));
    }

    @Test
    public void ropeComplexCharAtTest()
    {
        Rope rope = generateComplexRope();

        for (int charIndex = 0; charIndex < COMPLEX_ROPE_STRING_EXPECTED.length(); charIndex++)
        {
            Assert.assertEquals(COMPLEX_ROPE_STRING_EXPECTED.charAt(charIndex), rope.charAt(charIndex));
        }

    }

    @Test
    public void ropeSimpleSplitTest()
    {
        RopeBuilder builder = new RopeBuilder();
        Rope rope = builder.build(SIMPLE_ROPE_STRING_EXPECTED);
        int index = 1;
        List<Rope> ropes = builder.split(rope, index);

        String expectedLeft = SIMPLE_ROPE_STRING_EXPECTED.substring(0, index);
        String expectedRight = SIMPLE_ROPE_STRING_EXPECTED.substring(index);

        Assert.assertEquals(expectedLeft, ropes.get(0).toString());
        Assert.assertEquals(expectedRight, ropes.get(1).toString());
    }

    @Test
    public void ropeComplexSplitTest()
    {
        RopeBuilder builder = new RopeBuilder();
        Rope rope = generateComplexRope();

        for (int index = 0; index < COMPLEX_ROPE_STRING_EXPECTED.length(); index++)
        {
            List<Rope> ropes = builder.split(rope, index);
            String expectedLeft = COMPLEX_ROPE_STRING_EXPECTED.substring(0, index);
            String expectedRight = COMPLEX_ROPE_STRING_EXPECTED.substring(index);

            Assert.assertEquals(expectedLeft, ropes.get(0).toString());
            Assert.assertEquals(expectedRight, ropes.get(1).toString());
        }
    }
    
    @Test
    public void ropeNodeNormalizeTest()
    {
    	RopeNode root = generateNonNormalizeRopeNode();
        RopeBuilder builder = new RopeBuilder();
        builder.normalize(root);
        
        Assert.assertEquals(true, root.left.isLeaf());
        Assert.assertEquals(true, root.right.isLeaf());
        Assert.assertEquals(SIMPLE_ROPE_STRING_EXPECTED, root.left.value);
        

    }

    private Rope generateComplexRope()
    {
        RopeBuilder builder = new RopeBuilder();
        RopeNode parentLeft = builder.createParentNode(builder.createLeafNode("my "), builder.createLeafNode("new "));
        RopeNode parentRight = builder.createParentNode(builder.createLeafNode("str "), builder.createLeafNode("temp"));

        RopeNode root = builder.createParentNode(parentLeft, parentRight);

        return new RopeImpl(root);
    }
    
    private RopeNode generateNonNormalizeRopeNode()
    {
        RopeBuilder builder = new RopeBuilder();
    	RopeNode leafLeft = builder.createLeafNode(SIMPLE_ROPE_STRING_EXPECTED);
    	RopeNode leafRight = builder.createLeafNode(SIMPLE_ROPE_STRING_EXPECTED);
    	
    	RopeNode parent = builder.createParentNode(leafLeft, null);
    	RopeNode parentOfparent = builder.createParentNode(null, parent);
    	RopeNode parentOfparentOfparent = builder.createParentNode(null, parentOfparent);
    	
    	RopeNode root = builder.createParentNode(parentOfparentOfparent, leafRight);
    	
    	return root;
    }
}
