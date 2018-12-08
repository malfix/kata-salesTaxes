package sales.taxes;

import sales.taxes.formatter.IReceiptFormatter;
import sales.taxes.item.IItem;
import sales.taxes.parser.IItemReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Basket {
    private Map<IItem, BasketItem> itemMap;
    private IItemReader parser;
    private IReceiptFormatter receiptOutput;

    Basket(IItemReader itemReader, IReceiptFormatter receiptFormatter) {
        this.parser = itemReader;
        this.receiptOutput = receiptFormatter;
        this.itemMap = new LinkedHashMap<>();
    }

    void addItems(String... items) {
        for (String item : items) {
            BasketItem basketItem = parser.getBasketItem(item);
            if (basketItem == null)
                continue;
            if (itemMap.get(basketItem.item) == null)
                itemMap.put(basketItem.item, basketItem);
            else {
                BasketItem basketItemExisting = itemMap.get(basketItem.item);
                basketItemExisting.addQuantity(basketItem.quantity);
                itemMap.put(basketItem.item, basketItemExisting);
            }
        }
    }

    List<String> getReceipt() {
        return receiptOutput.getList(itemMap);
    }
}
