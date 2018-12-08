package sales.taxes.parser;

import sales.taxes.item.ExemptItem;
import sales.taxes.item.IItem;
import sales.taxes.item.StandardItem;
import sales.taxes.item.math.Rounder;

public class ItemMatcher implements IItemMatcher {

    public IItem match(double basePrice, boolean isImported, String name) {
        if (isAnExemptItem(name))
            return new ExemptItem(isImported, name, basePrice, new Rounder());
        return new StandardItem(isImported, name, basePrice, new Rounder());
    }

    boolean isAnExemptItem(String name) {
        return name.contains("book") || name.contains("chocolate") || name.contains("pills");
    }
}
