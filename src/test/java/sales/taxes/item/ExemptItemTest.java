package sales.taxes.item;

import org.junit.Test;
import sales.taxes.item.math.IRounder;
import sales.taxes.item.math.Rounder;

import static org.junit.Assert.assertEquals;

public class ExemptItemTest {
    double tollerance = 0;
    IRounder rounder = new Rounder();

    @Test
    public void checkNotimported() {
        ExemptItem exemptItem = new ExemptItem(false, "", 0.0, rounder);

        assertEquals(0, exemptItem.getPercentageTaxes(), tollerance);
        assertEquals(0.0, exemptItem.getTaxes(), tollerance);
    }

    @Test
    public void checkImported() {
        ExemptItem exemptItem = new ExemptItem(true, "", 10.0, rounder);

        assertEquals(0.05, exemptItem.getPercentageTaxes(), tollerance);
        assertEquals(0.5, exemptItem.getTaxes(), tollerance);
    }
}