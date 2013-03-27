package ru.usu.data.structure.rope;

import java.util.List;

/**
 * Rope implementation
 * 
 * @author astarovoyt
 * 
 */
public class RopeImpl implements Rope {
	public static class RopeNode {
		RopeNode left;
		RopeNode right;
		String value;
		int influence;

		public boolean isLeaf() {
			return null == left && null == right;
		}
	}

	private RopeNode root;

	public RopeImpl(String wrapped) {
		root = createNode(wrapped);
	}

	private RopeNode createNode(String wrapped) {
		RopeNode node = new RopeNode();
		node.influence = wrapped.length();
		node.value = wrapped;
		return node;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public char charAt(int index) {
		return 0;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return null;
	}

	@Override
	public List<Rope> split(int index) {
		return null;
	}

	@Override
	public Rope merge(Rope left, Rope right) {
		return null;
	}

	@Override
	public void append(String string) 
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
