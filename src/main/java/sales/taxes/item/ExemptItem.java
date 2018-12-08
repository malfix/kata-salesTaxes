package sales.taxes.item;

import sales.taxes.item.math.IRounder;

public class ExemptItem extends AbstractItem {
    public ExemptItem(boolean isImported, String name, double basePrice, IRounder rounder) {
        super(isImported, name, basePrice, rounder);
    }

    protected double getBasePercentage() {
        return 0.00;
    }
}
