package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.LinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    private LinkedList<String> sut;

    private LinkedList<? extends Object> ex1;
    private LinkedList<?> ex2;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() {
        int expectedSize = 1;

        sut.add("data");

        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
       sut.add(null);

    }

    @Test
    public void test_pollWithEmptyList() {

        String actualResult = sut.poll();

        Assert.assertNull(actualResult);
    }

    @Test
    public void test_pollWithPopulatedList() {
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        String actualResult = sut.poll();

        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }
}
