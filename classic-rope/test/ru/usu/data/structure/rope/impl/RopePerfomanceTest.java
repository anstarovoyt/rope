package ru.usu.data.structure.rope.impl;

import junit.framework.Assert;

import org.junit.Test;

import ru.usu.data.structure.rope.Rope;
import ru.usu.data.structure.rope.RopeBuilder;

public class RopePerfomanceTest
{
    public static final int COUNT = 100000;

    @Test
    public void appendTestJavaString()
    {
        String result = RopeTest.SIMPLE_ROPE_STRING_EXPECTED;
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++)
        {
            result = result + RopeTest.SIMPLE_ROPE_STRING_EXPECTED;
        }
        long end = System.currentTimeMillis();
        System.out.print(end - start);
        Assert.assertNotNull(result);
    }
    
    @Test
    public void appendTestJavaBuilder()
    {
        StringBuilder result = new StringBuilder(RopeTest.SIMPLE_ROPE_STRING_EXPECTED);
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++)
        {
            result = result.insert(0, RopeTest.SIMPLE_ROPE_STRING_EXPECTED);
        }
        long end = System.currentTimeMillis();
        System.out.print(end - start);
        Assert.assertNotNull(result);
    }
    
    @Test
    public void appendTest()
    {
        Rope first = new RopeBuilder().build(RopeTest.SIMPLE_ROPE_STRING_EXPECTED);
        Rope rope = new RopeBuilder().build(RopeTest.SIMPLE_ROPE_STRING_EXPECTED);
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++)
        {
            rope = first.append(rope);
        }
        long end = System.currentTimeMillis();
        
        System.out.print(end - start);
        Assert.assertNotNull(rope);
    }
}
