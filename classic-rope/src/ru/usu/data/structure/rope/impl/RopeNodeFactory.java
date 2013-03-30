package ru.usu.data.structure.rope.impl;

class RopeNodeFactory
{
    public RopeNode createNode()
    {
        return new RopeNode();
    }

    /**
     * @return leaf node with string as value
     */
    public RopeNode createLeafNode(String str)
    {
        RopeNode node = createNode();

        node.influence = str.length();
        node.value = str;

        return node;
    }

    /**
     * @return rope node childs left and right
     */
    public RopeNode createParentNode(RopeNode left, RopeNode right)
    {
        RopeNode node = createNode();

        node.influence = RopeHelper.getInfluence(left) + RopeHelper.getInfluence(right);
        node.left = left;
        node.right = right;
        node.deep = RopeHelper.getIncDeep(node);
        return node;
    }
}
