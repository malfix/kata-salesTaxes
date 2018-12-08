package sales.taxes.item;

import org.junit.Test;
import sales.taxes.item.math.IRounder;
import sales.taxes.item.math.Rounder;

import static org.junit.Assert.assertEquals;

public class StandardItemTest {

    private double tollerance = 0;
    private IRounder rounder = new Rounder();

    @Test
    public void checkNotimported() {
        StandardItem standardItem = new StandardItem(false, "", 10.0, rounder);

        assertEquals(0.10, standardItem.getPercentageTaxes(), tollerance);
        assertEquals(1.0, standardItem.getTaxes(), tollerance);
    }

    @Test
    public void checkImported() {
        StandardItem standardItem = new StandardItem(true, "", 10.0, rounder);

        assertEquals(0.15, standardItem.getPercentageTaxes(), tollerance);
        assertEquals(1.5, standardItem.getTaxes(), tollerance);
    }
}