package homework.test;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;
import homework.service.MathService;

import static homework.utils.AssertionUtils.assertEquals;

public class MathServiceTest {
    MathService mathService;

    @Before
    public void setUp() {
        mathService = new MathService();
    }

    @Test
    public void sumShouldReturnTwo() {
        assertEquals(2, mathService.sum(1, 1));
    }

    @Test
    public void minusShouldReturnTwo() {
        assertEquals(2, mathService.minus(4, 2));
    }

    @Test
    public void multiplyShouldReturnNine() {
        assertEquals(9, mathService.multiply(3, 3));
    }

    @Test
    public void divideShouldReturnThree() {
        assertEquals(3, mathService.divide(9, 3));
    }

    @Test
    public void exceptionTest() {
        assertEquals(999, mathService.divide(3, 0));
    }

    @Test
    public void failTest() {
        assertEquals(999, mathService.divide(3, 3));
    }

    @After
    public void callDestroy() {
        mathService = null;
    }
}
