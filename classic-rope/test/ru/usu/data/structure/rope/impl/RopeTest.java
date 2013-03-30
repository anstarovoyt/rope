package ru.usu.data.structure.rope.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.usu.data.structure.rope.Rope;

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

    private static final RopeNodeFactory factory = new RopeNodeFactory();

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
        List<Rope> ropes = rope.split(index);

        String expectedLeft = SIMPLE_ROPE_STRING_EXPECTED.substring(0, index);
        String expectedRight = SIMPLE_ROPE_STRING_EXPECTED.substring(index);

        Rope leftRope = ropes.get(0);
        Rope rightRope = ropes.get(1);

        Assert.assertEquals(expectedLeft, leftRope.toString());
        Assert.assertEquals(expectedRight, rightRope.toString());
        Assert.assertEquals(0, leftRope.getDeep());
        Assert.assertEquals(0, rightRope.getDeep());
    }

    @Test
    public void ropeComplexSplitTest()
    {
        Rope rope = generateComplexRope();

        for (int index = 0; index < COMPLEX_ROPE_STRING_EXPECTED.length(); index++)
        {
            List<Rope> ropes = rope.split(index);
            String expectedLeft = COMPLEX_ROPE_STRING_EXPECTED.substring(0, index);
            String expectedRight = COMPLEX_ROPE_STRING_EXPECTED.substring(index);

            Assert.assertEquals(expectedLeft, ropes.get(0).toString());
            Assert.assertEquals(expectedRight, ropes.get(1).toString());
        }
    }

    @Test
    public void ropeComplexSplitDeepTest()
    {
        Rope rope = generateComplexRope();
        int index = 3;

        List<Rope> ropes = rope.split(index);

        Rope leftRope = ropes.get(0);
        Rope rightRope = ropes.get(1);
        RopeHelper.printRope((RopeImpl)leftRope);
        Assert.assertEquals(1, leftRope.getDeep());
        Assert.assertEquals(2, rightRope.getDeep());
    }

    @Test
    public void ropeSimpleDeepTest()
    {
        RopeBuilder builder = new RopeBuilder();
        Rope rope = builder.build(SIMPLE_ROPE_STRING_EXPECTED);

        Assert.assertEquals(0, rope.getDeep());
    }

    @Test
    public void ropeComplexDeepTest()
    {
        Rope rope = generateComplexRope();

        Assert.assertEquals(2, rope.getDeep());
    }

    @Test
    public void ropeComplexPrintTest()
    {
        RopeImpl rope = generateComplexRope();
        RopeHelper.printRope(rope);
    }

    @Test
    public void ropeSimpleAppendTest()
    {
        RopeBuilder builder = new RopeBuilder();
        RopeImpl rope = builder.build(SIMPLE_ROPE_STRING_EXPECTED);
        RopeImpl ropeAppendix = builder.build(SIMPLE_ROPE_STRING_EXPECTED);

        RopeImpl result = rope.append(ropeAppendix);

        RopeHelper.printRope(result);
        Assert.assertEquals(SIMPLE_ROPE_STRING_EXPECTED + SIMPLE_ROPE_STRING_EXPECTED, result.toString());
    }

    @Test
    public void ropeComplexAppendTest()
    {
        RopeImpl rope = generateComplexRope();
        RopeImpl ropeAppendix = generateComplexRope();

        RopeImpl result = rope.append(ropeAppendix);

        RopeHelper.printRope(result);
        Assert.assertEquals(COMPLEX_ROPE_STRING_EXPECTED + COMPLEX_ROPE_STRING_EXPECTED, result.toString());
    }

    @Test
    public void ropeComplexSubSequenceTest()
    {
        RopeImpl rope = generateComplexRope();
        RopeImpl ropeAppendix = generateComplexRope();

        RopeImpl result = rope.append(ropeAppendix);

        String string = COMPLEX_ROPE_STRING_EXPECTED + COMPLEX_ROPE_STRING_EXPECTED;

        for (int i = 0; i < string.length() - 1; i++)
        {
            for (int j = i + 1; j < string.length(); j++)
            {
                Assert.assertEquals("substr " + i + " " + j, string.subSequence(i, j), result.subSequence(i, j).toString());
            }
        }
    }

    @Test
    public void ropeComplexSubSequenceTest2()
    {
        RopeImpl rope = generateComplexRope();
        RopeImpl ropeAppendix = generateComplexRope();

        RopeImpl result = rope.append(ropeAppendix);

        String string = COMPLEX_ROPE_STRING_EXPECTED + COMPLEX_ROPE_STRING_EXPECTED;
        int i = 1;
        int j = 5;

        Assert.assertEquals(string.subSequence(i, j), result.subSequence(i, j).toString());
    }

    private RopeImpl generateComplexRope()
    {
        RopeNode parentLeft = factory.createParentNode(factory.createLeafNode("my "), factory.createLeafNode("new "));
        RopeNode parentRight = factory.createParentNode(factory.createLeafNode("str "), factory.createLeafNode("temp"));

        RopeNode root = factory.createParentNode(parentLeft, parentRight);

        return new RopeImpl(root);
    }
}
