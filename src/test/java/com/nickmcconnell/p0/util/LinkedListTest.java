package com.nickmcconnell.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    private LinkedList<String> sut;
    //This example is showing what we will do with the screen
    //the wildcard will allow
    private LinkedList<? extends Object> ex1; // generics with subtyping
    private LinkedList<?> ex2;

    //does this before and after eacch test to ensure data is new each time
    //unit test tests smallest => method.  If its bigger than that its an integration test.
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
        // Arrange (prepare the test)
        int expectedSize = 1;

        // Act (do the test)
        sut.add("data");

        // Assert (ensure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
        // Arrange
        // sometimes blank if there's nothing in particular to do

        // Act
        sut.add(null);

        // Assert
        // sometimes blank, especially if you expect an exception to be thrown
    }

    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = sut.poll();

        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_pollWithPopulatedList() {
        // Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = sut.poll();

        // Assert
        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_peekWithEmptyList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_peekWithPopulatedList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_containsWithEmptyList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_containsWithPopulatedList() {

    }
}
