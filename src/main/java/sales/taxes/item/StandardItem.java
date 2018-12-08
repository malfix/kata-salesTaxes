package sales.taxes.item;

import sales.taxes.item.math.IRounder;

public class StandardItem extends AbstractItem {
    public StandardItem(boolean isImported, String name, double basePrice, IRounder rounder) {
        super(isImported, name, basePrice, rounder);
    }

    protected double getBasePercentage() {
        return 0.10;
    }
}
