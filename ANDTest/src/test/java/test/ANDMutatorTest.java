package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ANDMutatorTest {

    @Test
    public void test() {
        ANDMutator m = new ANDMutator();
        assertEquals(4, m.myMethod(4, 2, 1));
    }

}
