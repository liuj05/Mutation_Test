package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OSBNMutatorTest {

    @Test
    public void test() {
        OSBNMutator m = new OSBNMutator();
        assertEquals(1, m.myMethod(4, 2));
    }

}
