package ru.usu.data.structure.rope;

import java.util.List;

/**
 * Rope interface
 * 
 * @author astarovoyt
 *
 */
public interface Rope extends CharSequence 
{
	public List<Rope> split(int index);
	
	public Rope merge(Rope left, Rope right);
	
	public void append(String string);
}