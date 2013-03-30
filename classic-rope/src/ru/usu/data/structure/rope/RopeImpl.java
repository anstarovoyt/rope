package ru.usu.data.structure.rope;

import java.util.List;

/**
 * Rope implementation
 * 
 * @author astarovoyt
 * 
 */
public class RopeImpl implements Rope
{
    public static class RopeNode implements Cloneable
    {
        RopeNode left;
        RopeNode right;
        String value;
        int influence;

        public boolean isLeaf()
        {
            return null == left && null == right;
        }

        public boolean isHalf()
        {
            return (null == left) ^ (null == right);
        }

        public RopeNode getHalf()
        {
            return left == null ? right : left;
        }

        @Override
        public RopeNode clone() throws CloneNotSupportedException
        {
            RopeNode newRope = new RopeNode();
            newRope.influence = influence;
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
    }

    final RopeNode root;

    public RopeImpl(String wrapped)
    {
        root = createNode(wrapped);
    }

    public RopeImpl(RopeNode node)
    {
        root = node;
    }

    private RopeNode createNode(String wrapped)
    {
        RopeNode node = new RopeNode();
        node.influence = wrapped.length();
        node.value = wrapped;
        return node;
    }

    @Override
    public int length()
    {
        return root.influence;
    }

    @Override
    public char charAt(int index)
    {
        if (index >= root.influence)
        {
            throw new IndexOutOfBoundsException("max index can be " + (root.influence - 1));
        }

        return charAt(index, root);
    }

    char charAt(int index, RopeNode currentNode)
    {
        if (currentNode.isLeaf())
        {
            return currentNode.value.charAt(index);
        }

        if (index < currentNode.left.influence)
        {
            return charAt(index, currentNode.left);
        }
        else
        {
            return charAt(index - currentNode.left.influence, currentNode.right);
        }
    }

    @Override
    public CharSequence subSequence(int start, int end)
    {
        return null;
    }

    @Override
    public List<Rope> split(int index)
    {
        return null;
    }

    @Override
    public void append(String appendix)
    {

    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        toString(root, builder);
        return builder.toString();
    }

    private void toString(RopeNode node, StringBuilder builder)
    {
        if (null == node)
        {
            return;
        }

        if (node.isLeaf())
        {
            builder.append(node.value);
            return;
        }

        toString(node.left, builder);
        toString(node.right, builder);
    }
}
