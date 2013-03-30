package ru.usu.data.structure.rope.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author astarovoyt
 *
 */
public class RopeBalancer
{
    private static final RopeNodeFactory factory = new RopeNodeFactory();
    private static final short MAX_ROPE_DEPTH = 96;
    RopeImpl rope;

    public RopeBalancer(RopeImpl rope)
    {
        this.rope = rope;
    }
    
    public RopeImpl balanceIfNessesary()
    {
        if (rope.getDeep() > MAX_ROPE_DEPTH)
        {
            balance();
        }
        
        return rope;
    }

    public void balance()
    {
        rope.root = merge(getLeafNodes());
    }
    

    public List<RopeNode> getLeafNodes()
    {
        List<RopeNode> leafs = new ArrayList<>();
        putLeafs(rope.root, leafs);
        return leafs;
    }

    public void putLeafs(RopeNode node, List<RopeNode> leafs)
    {
        if (null == node)
        {
            return;
        }

        if (node.isLeaf())
        {
            leafs.add(node);
            return;
        }

        putLeafs(node.left, leafs);
        putLeafs(node.right, leafs);
    }

    public RopeNode merge(List<RopeNode> leafNodes)
    {
        return merge(leafNodes, 0, leafNodes.size());
    }

    public RopeNode merge(List<RopeNode> leafNodes, int start, int end)
    {
        int range = end - start;
        switch (range)
        {
        case 1:
            return leafNodes.get(start);
        case 2:
            return factory.createParentNode(leafNodes.get(start), leafNodes.get(start + 1));
        default:
            int middle = start + (range / 2);
            return factory.createParentNode(merge(leafNodes, start, middle), merge(leafNodes, middle, end));
        }
    }

}
