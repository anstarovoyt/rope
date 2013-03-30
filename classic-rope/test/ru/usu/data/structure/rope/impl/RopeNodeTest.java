package ru.usu.data.structure.rope.impl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test of rope's node operations
 * @author astarovoyt
 *
 */
public class RopeNodeTest
{
    private static final RopeNodeFactory factory = new RopeNodeFactory();

    @Test
    public void ropeNodeNormalizeTest()
    {
        RopeNode root = generateNonNormalizeRopeNode();
        RopeHelper.normalize(root);

        Assert.assertEquals(true, root.left.isLeaf());
        Assert.assertEquals(true, root.right.isLeaf());
        Assert.assertEquals(1, root.deep);
        Assert.assertEquals(RopeTest.SIMPLE_ROPE_STRING_EXPECTED, root.left.value);

    }

    private RopeNode generateNonNormalizeRopeNode()
    {
        RopeNode leafLeft = factory.createLeafNode(RopeTest.SIMPLE_ROPE_STRING_EXPECTED);
        RopeNode leafRight = factory.createLeafNode(RopeTest.SIMPLE_ROPE_STRING_EXPECTED);

        RopeNode parent = factory.createParentNode(leafLeft, null);
        RopeNode parentOfparent = factory.createParentNode(null, parent);
        RopeNode parentOfparentOfparent = factory.createParentNode(null, parentOfparent);

        RopeNode root = factory.createParentNode(parentOfparentOfparent, leafRight);

        return root;
    }
}
