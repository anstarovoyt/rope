package ru.usu.data.structure.rope.impl;

import ru.usu.data.structure.rope.Rope;

/**
 * @author astarovoyt
 *
 */
public class RopeBuilder
{
    private final RopeNodeFactory factory = new RopeNodeFactory();
    
    public Rope build(String value)
    {
        return new RopeImpl(value);
    }

    RopeNode createNode()
    {
        return factory.createNode();
    }

    RopeNode createLeafNode(String str)
    {
        return factory.createLeafNode(str);
    }

    RopeNode createParentNode(RopeNode left, RopeNode right)
    {
        return factory.createParentNode(left, right);
    }
}
