package sales.taxes.formatter;

import sales.taxes.BasketItem;
import sales.taxes.item.IItem;

import java.util.List;
import java.util.Map;

public interface IReceiptFormatter {
    List<String> getList(Map<IItem, BasketItem> items);
}
