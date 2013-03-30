package ru.usu.data.structure.rope;

import java.util.Arrays;
import java.util.List;

import ru.usu.data.structure.rope.RopeImpl.RopeNode;

/**
 * @author astarovoyt
 *
 */
public class RopeBuilder
{
    public Rope build(String value)
    {
        return new RopeImpl(value);
    }

    RopeNode createNode()
    {
        return new RopeNode();
    }

    RopeNode createLeafNode(String str)
    {
        RopeNode node = createNode();

        node.influence = str.length();
        node.value = str;

        return node;
    }

    RopeNode createParentNode(RopeNode left, RopeNode right)
    {
        RopeNode node = createNode();

        node.influence = getInfluence(left) + getInfluence(right);
        node.left = left;
        node.right = right;

        return node;
    }

    List<Rope> split(Rope rope, int index)
    {
        return split((RopeImpl)rope, index);
    }

    List<Rope> split(RopeImpl rope, int index)
    {
        if (index >= rope.length())
        {
            throw new IndexOutOfBoundsException("max index can be " + (rope.length() - 1));
        }

        RopeNode leftSplitted = createNode();
        RopeNode rightSplitted = createNode();
        try
        {
            split(leftSplitted, rightSplitted, rope.root, index);
        }
        catch (CloneNotSupportedException e)
        {
            //Cannot be
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

            RopeNode leftChildOfRightParent = createNode();
            rightParent.left = leftChildOfRightParent;

            split(leftParent, leftChildOfRightParent, parent.left, index);
        }
        else
        {
            leftParent.left = parent.left.clone();
            RopeNode rightChildOfleftParent = createNode();
            leftParent.right = rightChildOfleftParent;

            split(rightChildOfleftParent, rightParent, parent.right, index - parent.left.influence);
        }
    }

    void normalize(RopeNode node)
    {
        if (node == null || node.isLeaf())
        {
            return;
        }

        if (node.isHalf())
        {
            RopeNode child = node.getHalf();
            normalize(child);

            //do normalize
            node.left = child.left;
            node.right = child.right;
            node.value = child.value;
        }
        else
        {
            normalize(node.left);
            normalize(node.right);
        }
    }

    private int getInfluence(RopeNode node)
    {
        return null == node ? 0 : node.influence;
    }
}
