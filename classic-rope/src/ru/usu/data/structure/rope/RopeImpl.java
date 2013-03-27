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
	public static class RopeNode
	{
		RopeNode left;
		RopeNode right;
		String value;
		int influence;
		
		public boolean isLeaf()
		{
			return null == left && null == right;
		}
	}
	
	@SuppressWarnings("unused")
	private RopeNode root;
	
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
	public void append(String string) {
		
	}
}
