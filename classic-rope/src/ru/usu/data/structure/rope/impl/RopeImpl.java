package ru.usu.data.structure.rope.impl;

import java.util.Arrays;
import java.util.List;

import ru.usu.data.structure.rope.Rope;

/**
 * Rope implementation
 * 
 * @author astarovoyt
 * 
 */
public class RopeImpl implements Rope
{
    private final RopeNodeFactory factory = new RopeNodeFactory();

    final RopeNode root;

    public RopeImpl(String wrapped)
    {
        root = factory.createLeafNode(wrapped);
    }

    public RopeImpl(RopeNode node)
    {
        root = node;
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
        return split(this, index);
    }

    @Override
    public void append(String appendix)
    {

    }

    List<Rope> split(RopeImpl rope, int index)
    {
        if (index >= rope.length())
        {
            throw new IndexOutOfBoundsException("max index can be " + (rope.length() - 1));
        }

        RopeNode leftSplitted = factory.createNode();
        RopeNode rightSplitted = factory.createNode();
        try
        {
            split(leftSplitted, rightSplitted, rope.root, index);
            RopeHelper.normalize(leftSplitted);
            RopeHelper.normalize(rightSplitted);
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }

        return Arrays.asList(new Rope[] { new RopeImpl(leftSplitted), new RopeImpl(rightSplitted) });
    }

    void split(RopeNode leftParent, RopeNode rightParent, RopeNode parent, int index) throws CloneNotSupportedException
    {
        if (parent.isLeaf())
        {
            String parentValue = parent.value;

            leftParent.value = parentValue.substring(0, index);
            leftParent.influence = leftParent.value.length();

            rightParent.value = parentValue.substring(index);
            rightParent.influence = rightParent.value.length();
            return;
        }

        leftParent.influence = index;
        rightParent.influence = parent.influence - index;

        if (index < parent.left.influence)
        {
            rightParent.right = parent.right.clone();

            RopeNode leftChildOfRightParent = factory.createNode();
            rightParent.left = leftChildOfRightParent;

            split(leftParent, leftChildOfRightParent, parent.left, index);
        }
        else
        {
            leftParent.left = parent.left.clone();
            RopeNode rightChildOfleftParent = factory.createNode();
            leftParent.right = rightChildOfleftParent;

            split(rightChildOfleftParent, rightParent, parent.right, index - parent.left.influence);
        }
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
