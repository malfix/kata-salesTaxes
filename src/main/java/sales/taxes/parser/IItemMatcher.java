package sales.taxes.parser;

import sales.taxes.item.IItem;

public interface IItemMatcher {
    IItem match(double basePrice, boolean isImported, String name);
}
