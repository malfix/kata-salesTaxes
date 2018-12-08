package sales.taxes.item;

public interface IItem {
    boolean isImported();

    String getName();

    double getPrice();

    double getTaxes();

    double getTotalPrice();
}
