package sales.taxes.parser;

import sales.taxes.BasketItem;

public interface IItemReader {
    BasketItem getBasketItem(String textToParse);
}
