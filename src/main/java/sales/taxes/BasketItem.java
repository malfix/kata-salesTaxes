package sales.taxes;

import sales.taxes.item.IItem;

public class BasketItem {
    double quantity;
    IItem item;

    public double getQuantity() {
        return quantity;
    }

    public IItem getItem() {
        return item;
    }

    public BasketItem(double quantity, IItem item) {
        this.quantity = quantity;
        this.item = item;
    }

    void addQuantity(double quantityToAdd) {
        quantity += quantityToAdd;
    }
}
