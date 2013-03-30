package ru.usu.data.structure.rope.impl;

public class RopeHelper
{
    public static void normalize(RopeNode node)
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

    public static int getInfluence(RopeNode node)
    {
        return null == node ? 0 : node.influence;
    }
}
