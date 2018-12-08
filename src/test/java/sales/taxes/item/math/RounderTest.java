package sales.taxes.item.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RounderTest {
    private Rounder rounder;

    @Before
    public void initialize(){
        rounder = new Rounder();
    }

    @Test
    public void roundTo01(){
        assertEquals(0.01, rounder.roundToCent(0.009),0);
        assertEquals(0.01, rounder.roundToCent(0.011),0);
    }

    @Test
    public void roundTo05(){
        assertEquals(0.10, rounder.roundUpToFiveCents(0.076),0);
        assertEquals(0.15, rounder.roundUpToFiveCents(0.124),0);
        assertEquals(0.05, rounder.roundUpToFiveCents(0.029),0);
        assertEquals(0.10, rounder.roundUpToFiveCents(0.074),0);
    }
}