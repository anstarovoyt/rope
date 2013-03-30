package ru.usu.data.structure.rope.impl;

/**
 * RopeNode is a node of the Rope's tree
 * 
 * @author astarovoyt
 *
 */
public class RopeNode implements Cloneable
{
    RopeNode left;
    RopeNode right;
    String value;
    int influence;
    int deep;

    /**
     * @return is node a leaf?
     */
    public boolean isLeaf()
    {
        return null == left && null == right;
    }

    /**
     * @return has node one child only?
     */
    public boolean isHalf()
    {
        return (null == left) ^ (null == right);
    }

    /**
     * @return not empty child
     * @throw IllegalStateException if not is leaf
     */
    public RopeNode getHalf()
    {
        if (isLeaf())
        {
            throw new IllegalStateException();
        }
        
        return left == null ? right : left;
    }

    /**
     * Recursive cloning of the node 
     * 
     */
    @Override
    public RopeNode clone() throws CloneNotSupportedException
    {
        RopeNode newRope = new RopeNode();
        newRope.influence = influence;
        newRope.deep = deep;
        newRope.value = value;
        
        if (null != left)
        {
            newRope.left = left.clone();
        }
        
        if (null != right)
        {
            newRope.right = right.clone();
        }
        
        return newRope;
    }
    
    public boolean isEmpty()
    {
        return isLeaf() && null == value;
    }

    @Override
    public String toString()
    {
        return value + " " + influence + " " + deep;
    }
}