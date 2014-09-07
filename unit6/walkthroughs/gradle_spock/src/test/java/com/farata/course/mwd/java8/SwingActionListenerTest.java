package com.farata.course.mwd.java8;

import org.junit.*;

import static org.junit.Assert.*;

public class SwingActionListenerTest {

    @BeforeClass
    public static void setupTest() {
        System.out.println("// run before the first test method ");
    }

    @Before
    public void setup() throws Exception {
        System.out.println("// run before every test method");
    }

    @After
    public void teardown() throws Exception {
        System.out.println("// run after the last test method");
    }

    @Test
    public void colorShouldBeGreen() throws Exception {
        System.out.println("// Test 1");
        String color = "green";
        assertEquals("color should be green", "green", color);
    }

    @Test
    public void testName() throws Exception {
        System.out.println("// Test 2");
        assertTrue(1==1);
    }

    @AfterClass
    public static void cleanupTest() {
        System.out.println("// run after the last test method");
    }
}