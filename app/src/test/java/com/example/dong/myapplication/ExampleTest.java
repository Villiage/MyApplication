package com.example.dong.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by dong on 2016/2/25.
 */
public class ExampleTest {



    @Test
     public void testHello()throws Exception{
         final int expected = 1;
         final int reality = 2;
         assertEquals(expected, reality);
     }

}
