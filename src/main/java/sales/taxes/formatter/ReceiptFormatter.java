package sales.taxes.formatter;

import sales.taxes.BasketItem;
import sales.taxes.item.IItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReceiptFormatter implements IReceiptFormatter {
    String IMPORTED_TEXT = "imported ";
    String ROW_ITEM_FORMAT = "%.0f %s%s: %.2f";
    String SALE_TAXES_FORMAT = "Sales Taxes: %.2f";
    String TOTAL_FORMAT = "Total: %.2f";

    public List<String> getList(Map<IItem, BasketItem> items) {
        double totalTaxes = 0;
        double totalPrice = 0;

        List<String> list = new ArrayList<>();
        for (IItem item : items.keySet()) {
            BasketItem basketItem = items.get(item);
            list.add(formatSingleItem(basketItem));
            totalTaxes += getTotalTaxes(item, basketItem);
            totalPrice += getTotalPrice(item, basketItem);
        }
        list.add(formatTaxes(totalTaxes));
        list.add(formatTotalPrice(totalPrice));
        return list;
    }

    private double getTotalPrice(IItem item, BasketItem basketItem) {
        return basketItem.getQuantity() * item.getTotalPrice();
    }

    private double getTotalTaxes(IItem item, BasketItem basketItem) {
        return basketItem.getQuantity() * item.getTaxes();
    }

    private String formatTotalPrice(double totalPrice) {
        return String.format(Locale.ROOT, TOTAL_FORMAT, totalPrice);
    }

    private String formatTaxes(double totalTaxes) {
        return String.format(Locale.ROOT, SALE_TAXES_FORMAT, totalTaxes);
    }

    protected String formatSingleItem(BasketItem basketItem) {
        return String.format(Locale.ROOT,
                ROW_ITEM_FORMAT,
                basketItem.getQuantity(),
                (basketItem.getItem().isImported() ? IMPORTED_TEXT : ""),
                basketItem.getItem().getName(),
                getTotalPrice(basketItem.getItem(), basketItem));
    }
}
