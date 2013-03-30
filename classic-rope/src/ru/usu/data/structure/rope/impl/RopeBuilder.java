package ru.usu.data.structure.rope.impl;


/**
 * @author astarovoyt
 *
 */
public class RopeBuilder
{
    public RopeImpl build(String value)
    {
        return new RopeImpl(value);
    }
}
