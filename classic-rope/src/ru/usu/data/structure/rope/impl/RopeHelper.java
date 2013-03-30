package ru.usu.data.structure.rope.impl;

import java.io.PrintStream;

import ru.usu.data.structure.rope.Rope;

public class RopeHelper
{
    public static final long[] FIBONACCI = { 0l, 1l, 1l, 2l, 3l, 5l, 8l, 13l, 21l, 34l, 55l, 89l, 144l, 233l, 377l,
            610l, 987l, 1597l, 2584l, 4181l, 6765l, 10946l, 17711l, 28657l, 46368l, 75025l, 121393l, 196418l, 317811l,
            514229l, 832040l, 1346269l, 2178309l, 3524578l, 5702887l, 9227465l, 14930352l, 24157817l, 39088169l,
            63245986l, 102334155l, 165580141l, 267914296l, 433494437l, 701408733l, 1134903170l, 1836311903l,
            2971215073l, 4807526976l, 7778742049l, 12586269025l, 20365011074l, 32951280099l, 53316291173l,
            86267571272l, 139583862445l, 225851433717l, 365435296162l, 591286729879l, 956722026041l, 1548008755920l,
            2504730781961l, 4052739537881l, 6557470319842l, 10610209857723l, 17167680177565l, 27777890035288l,
            44945570212853l, 72723460248141l, 117669030460994l, 190392490709135l, 308061521170129l, 498454011879264l,
            806515533049393l, 1304969544928657l, 2111485077978050l, 3416454622906707l, 5527939700884757l,
            8944394323791464l, 14472334024676221l, 23416728348467685l, 37889062373143906l, 61305790721611591l,
            99194853094755497l, 160500643816367088l, 259695496911122585l, 420196140727489673l, 679891637638612258l,
            1100087778366101931l, 1779979416004714189l, 2880067194370816120l, 4660046610375530309l,
            7540113804746346429l };

    private static final String SPACES = "                        "
            + "                                                " + "                                                "
            + "                                                " + "                                ";

    public static final int MIN_LENGTH = 17;

    private static final RopeNodeFactory factory = new RopeNodeFactory();

    public static RopeImpl concatenate(final RopeImpl left, final RopeImpl right)
    {
        if (left == null || right == null)
        {
            return left == null ? right : left;
        }

        checkArgs(left, right);

        if (left.length() + right.length() < MIN_LENGTH)
        {
            return balanceIfNessesary(new RopeImpl(left.toString() + right.toString()));
        }

        if (left.isFlat() && !right.isFlat())
        {
            RopeNode rootNode = right.root;
            if (left.length() + rootNode.left.influence < MIN_LENGTH)
            {
                RopeNode node = factory.createParentNode(factory.createLeafNode(left.toString() + rootNode.left.value),
                        rootNode.right);
                return balanceIfNessesary(new RopeImpl(node));
            }
        }

        if (right.isFlat() && !left.isFlat())
        {
            RopeNode rootNode = left.root;
            if (rootNode.right.influence + right.length() < MIN_LENGTH)
            {
                RopeNode node = factory.createParentNode(rootNode.left,
                        factory.createLeafNode(rootNode.right.value + right.toString()));
                return balanceIfNessesary(new RopeImpl(node));
            }
        }

        return balanceIfNessesary(new RopeImpl(factory.createParentNode(left.root, right.root)));
    }

    public static RopeImpl balanceIfNessesary(RopeImpl result)
    {
        return new RopeBalancer(result).balanceIfNessesary();
    }

    private static void checkArgs(final RopeImpl left, final RopeImpl right)
    {
        if ((long)left.length() + right.length() > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException("Max rope length can be" + Integer.MAX_VALUE);
        }
    }

    public static boolean isBalanced(final Rope r)
    {
        final int depth = r.getDeep();
        if (depth >= FIBONACCI.length - 2)
            return false;
        return (FIBONACCI[depth + 2] <= r.length());
    }

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
            copyNodePropeties(node, child);
        }
        else
        {
            normalize(node.left);
            normalize(node.right);
            node.deep = getIncDeep(node);
        }
        
        if (node.right != null && node.right.isEmpty())
        {
            node.right = null;
        }
        
        if (node.left != null && node.left.isEmpty())
        {
            node.left = null;
        }
    }

    private static void copyNodePropeties(RopeNode dest, RopeNode source)
    {
        dest.left = source.left;
        dest.right = source.right;
        dest.value = source.value;
        dest.deep = source.deep;
    }

    public static int getInfluence(RopeNode node)
    {
        return null == node ? 0 : node.influence;
    }

    public static int getDeep(RopeNode node)
    {
        return null == node ? 0 : node.deep;
    }

    public static int getIncDeep(RopeNode node)
    {
        return Math.max(RopeHelper.getDeep(node.left), RopeHelper.getDeep(node.right)) + 1;
    }

    public static void printRope(RopeImpl rope)
    {
        printRopeNode(rope.root);
    }

    public static void printRopeNode(RopeNode rope)
    {
        visualize(rope, System.out, 0);
    }

    static void visualize(final RopeNode node, final PrintStream out, final int depth)
    {
        if (null == node)
        {
            return;
        }

        if (node.deep == 0)
        {
            out.print(SPACES.substring(0, depth * 2));
            out.println("\"" + node + "\"");

            return;
        }

        out.print(SPACES.substring(0, depth * 2));
        out.println("concat[left]");
        visualize(node.left, out, depth + 1);
        out.print(SPACES.substring(0, depth * 2));
        out.println("concat[right]");
        visualize(node.right, out, depth + 1);
    }
}
