package ru.usu.data.structure.rope;

import ru.usu.data.structure.rope.impl.RopeImpl;


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
