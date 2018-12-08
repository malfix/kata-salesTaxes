package sales.taxes.formatter;

import org.junit.Test;
import sales.taxes.BasketItem;
import sales.taxes.item.ExemptItem;
import sales.taxes.item.IItem;
import sales.taxes.item.StandardItem;
import sales.taxes.item.math.IRounder;
import sales.taxes.item.math.Rounder;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class ReceiptOutputTest {

    private IRounder rounder = new Rounder();

    @Test
    public void firstUseCase() {
        IReceiptFormatter receiptOutput = new ReceiptFormatter();
        List<String> expectedOutput = Arrays.asList(
                "1 book: 12.49",
                "1 music CD: 16.49",
                "1 chocolate bar: 0.85",
                "Sales Taxes: 1.50",
                "Total: 29.83");

        assertEquals(expectedOutput, receiptOutput.getList(getItemsFirstUseCase()));
    }

    @Test
    public void secondUseCase() {
        IReceiptFormatter receiptOutput = new ReceiptFormatter();
        List<String> expectedOutput = Arrays.asList(
                "1 imported box of chocolates: 10.50",
                "1 imported bottle of perfume: 54.65",
                "Sales Taxes: 7.65",
                "Total: 65.15");

        assertEquals(expectedOutput, receiptOutput.getList(getItemsSecondUseCase()));
    }

    @Test
    public void thirdUseCase() {
        IReceiptFormatter receiptOutput = new ReceiptFormatter();
        List<String> expectedOutput = Arrays.asList(
                "1 imported bottle of perfume: 32.19",
                "1 bottle of perfume: 20.89",
                "1 packet of headache pills: 9.75",
                "1 imported box of chocolates: 11.85",
                "Sales Taxes: 6.70",
                "Total: 74.68");

        assertEquals(expectedOutput, receiptOutput.getList(getItemsThirdUseCase()));
    }

    private Map<IItem, BasketItem> getItemsFirstUseCase() {
        Map<IItem, BasketItem> basket = new LinkedHashMap<IItem, BasketItem>();
        addItems(basket, new ExemptItem(false, "book", 12.49, rounder), 1d);
        addItems(basket, new StandardItem(false, "music CD", 14.99, rounder), 1d);
        addItems(basket, new ExemptItem(false, "chocolate bar", 0.85, rounder), 1d);
        return basket;
    }

    private Map<IItem, BasketItem> getItemsSecondUseCase() {
        Map<IItem, BasketItem> basket = new LinkedHashMap<IItem, BasketItem>();
        addItems(basket, new ExemptItem(true, "box of chocolates", 10.00, rounder), 1d);
        addItems(basket, new StandardItem(true, "bottle of perfume", 47.50, rounder), 1d);
        return basket;
    }


    private Map<IItem, BasketItem> getItemsThirdUseCase() {
        Map<IItem, BasketItem> basket = new LinkedHashMap<IItem, BasketItem>();
        addItems(basket, new StandardItem(true, "bottle of perfume", 27.99, rounder), 1d);
        addItems(basket, new StandardItem(false, "bottle of perfume", 18.99, rounder), 1d);
        addItems(basket, new ExemptItem(false, "packet of headache pills", 9.75, rounder), 1d);
        addItems(basket, new ExemptItem(true, "box of chocolates", 11.25, rounder), 1d);
        return basket;
    }

    private void addItems(Map<IItem, BasketItem> basket, IItem item, double quantity) {
        basket.put(item, new BasketItem(quantity, item));
    }

}