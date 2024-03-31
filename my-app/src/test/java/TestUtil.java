import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestUtil {
    Util util;

    @Before
    public void setUp() {
        util = new Util();
    }

    @Test
    public void testUtilCompute() {
        assertFalse(util.compute());
    }

    @Test
    public void testWithOneArg() { // length = 1 ise false
	assertFalse(util.compute(3));
    }

    @Test
    public void testWithEvenArgs() { // length % 2 == 0 ise false 
        assertFalse(util.compute(1, 3, 5, 7));
    }

    @Test(expected = RuntimeException.class) // args icerisinde 0 varsa RuntimeException
    public void testWithZeroArg() {
        util.compute(0);
    }
	
    @Test(expected = RuntimeException.class) // args icerisinde 0 varsa RuntimeException
    public void testWithMultipleZeroArg() {
        util.compute(1, 3, 0, 0, 5);
    }

    @Test
    public void testWithSumDivisibleArgs() { // sum args icerisindeki herhangi bir elemana bolunebiliyorsa return true
        assertTrue(util.compute(2, 3, 4));
    }

    @Test
    public void testWithSumNotDivisibleArgs() { // sum args icerisindeki herhangi bir elemana bolunemiyorsa return false
        assertFalse(util.compute(7, 2, 4));
    }
}